/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.sys.web;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import cn.xy.tcmap.common.beanvalidator.BeanValidators;
import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.utils.DateUtils;
import cn.xy.tcmap.common.utils.excel.ExportExcel;
import cn.xy.tcmap.common.utils.excel.ImportExcel;
import cn.xy.tcmap.modules.catchup.csyx.entity.BusinessBalance;
import cn.xy.tcmap.modules.sys.service.SystemService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import cn.xy.tcmap.common.config.Global;
import cn.xy.tcmap.common.utils.StringUtils;
import cn.xy.tcmap.common.web.BaseController;
import cn.xy.tcmap.modules.sys.entity.Office;
import cn.xy.tcmap.modules.sys.entity.User;
import cn.xy.tcmap.modules.sys.service.OfficeService;
import cn.xy.tcmap.modules.sys.utils.DictUtils;
import cn.xy.tcmap.modules.sys.utils.UserUtils;

/**
 * 机构Controller
 *
 * @author ThinkGem
 * @version 2013-5-15
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/office")
public class OfficeController extends BaseController {

    @Autowired
    private OfficeService officeService;

    @Autowired
    private SystemService systemService;

    @ModelAttribute("office")
    public Office get(@RequestParam(required = false) String id) {
        if (StringUtils.isNotBlank(id)) {
            return officeService.get(id);
        } else {
            return new Office();
        }
    }

    @RequiresPermissions("sys:office:view")
    @RequestMapping(value = {""})
    public String index(Office office, Model model) {
//        model.addAttribute("list", officeService.findAll());
        return "modules/sys/officeIndex";
    }

    @RequiresPermissions("sys:office:view")
    @RequestMapping(value = {"list"})
    public String list(Office office, Model model) {
        model.addAttribute("list", officeService.findList(office));
        return "modules/sys/officeList";
    }

    @RequiresPermissions("sys:office:view")
    @RequestMapping(value = "form")
    public String form(Office office, Model model) {
        User user = UserUtils.getUser();
        if (office.getParent() == null || office.getParent().getId() == null) {
            office.setParent(user.getOffice());
        }
        office.setParent(officeService.get(office.getParent().getId()));
        if (office.getArea() == null) {
            office.setArea(user.getOffice().getArea());
        }
        // 自动获取排序号
        if (StringUtils.isBlank(office.getId()) && office.getParent() != null) {
            int size = 0;
            List<Office> list = officeService.findAll();
            for (int i = 0; i < list.size(); i++) {
                Office e = list.get(i);
                if (e.getParent() != null && e.getParent().getId() != null
                        && e.getParent().getId().equals(office.getParent().getId())) {
                    size++;
                }
            }
            office.setCode(office.getParent().getCode() + StringUtils.leftPad(String.valueOf(size > 0 ? size + 1 : 1), 3, "0"));
        }
        model.addAttribute("office", office);
        return "modules/sys/officeForm";
    }

    @RequiresPermissions("sys:office:edit")
    @RequestMapping(value = "save")
    public String save(Office office, Model model, RedirectAttributes redirectAttributes) {
        if (Global.isDemoMode()) {
            addMessage(redirectAttributes, "演示模式，不允许操作！");
            return "redirect:" + adminPath + "/sys/office/";
        }
        if (!beanValidator(model, office)) {
            return form(office, model);
        }
        try{
           officeService.save(office);

           if (office.getChildDeptList() != null) {
               Office childOffice = null;
               for (String id : office.getChildDeptList()) {
                   childOffice = new Office();
                   childOffice.setName(DictUtils.getDictLabel(id, "sys_office_common", "未知"));
                   childOffice.setParent(office);
                   childOffice.setArea(office.getArea());
                   childOffice.setType("2");
                   childOffice.setGrade(String.valueOf(Integer.valueOf(office.getGrade()) + 1));
                   childOffice.setUseable(Global.YES);
                   officeService.save(childOffice);
               }
           }

           addMessage(redirectAttributes, "保存机构'" + office.getName() + "'成功");
        }catch (Exception e){
            addMessage(redirectAttributes,"保存机构'" + office.getName() + "'失败");
        }
        String id = "0".equals(office.getParentId()) ? "" : office.getParentId();
        return "redirect:" + adminPath + "/sys/office/list?id=" + id + "&parentIds=" + office.getParentIds();
    }

    @RequiresPermissions("sys:office:edit")
    @RequestMapping(value = "delete")
    public String delete(Office office, RedirectAttributes redirectAttributes) {
        if (Global.isDemoMode()) {
            addMessage(redirectAttributes, "演示模式，不允许操作！");
            return "redirect:" + adminPath + "/sys/office/list";
        }
//		if (Office.isRoot(id)){
//			addMessage(redirectAttributes, "删除机构失败, 不允许删除顶级机构或编号空");
//		}else{
        try {
            List<User> userByOfficeId = systemService.findUserByOfficeId(office.getId());
            if(userByOfficeId!=null && userByOfficeId.size()>0){
                throw new Exception("机构下有关联用户，不能删除");
            }
            officeService.delete(office);
            addMessage(redirectAttributes, "删除机构成功");
        }catch (Exception e){
            addMessage(redirectAttributes, e.getMessage());
        }
//		}
        return "redirect:" + adminPath + "/sys/office/list?id=" + office.getParentId() + "&parentIds=" + office.getParentIds();
    }

    /**
     * 获取机构JSON数据。
     *
     * @param extId    排除的ID
     * @param type     类型（1：公司；2：部门/小组/其它：3：用户）
     * @param grade    显示级别
     * @param response
     * @return
     */
    @RequiresPermissions("user")
    @ResponseBody
    @RequestMapping(value = "treeData")
    public List<Map<String, Object>> treeData(@RequestParam(required = false) String extId, @RequestParam(required = false) String type,
                                              @RequestParam(required = false) Long grade, @RequestParam(required = false) Boolean isAll, HttpServletResponse response) {
        List<Map<String, Object>> mapList = Lists.newArrayList();
        List<Office> list = officeService.findList(isAll);
        for (int i = 0; i < list.size(); i++) {
            Office e = list.get(i);
            if ((StringUtils.isBlank(extId) || (extId != null && !extId.equals(e.getId()) && e.getParentIds().indexOf("," + extId + ",") == -1))
                    && (type == null || (type != null && (type.equals("1") ? type.equals(e.getType()) : true)))
                    && (grade == null || (grade != null && Integer.parseInt(e.getGrade()) <= grade.intValue()))
                    && Global.YES.equals(e.getUseable())) {
                Map<String, Object> map = Maps.newHashMap();
                map.put("id", e.getId());
                map.put("pId", e.getParentId());
                map.put("pIds", e.getParentIds());
                map.put("name", e.getName());
                if (type != null && "3".equals(type)) {
                    map.put("isParent", true);
                }
                mapList.add(map);
            }
        }
        return mapList;
    }

    /**
     * 导出组织机构数据
     * @return
     */
    @RequiresPermissions("sys:office:view")
    @RequestMapping(value = "export", method= RequestMethod.POST)
    public String exportFile(Office office, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
        try {
            String fileName = "组织机构"+ DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            List<Office> all = officeService.findAllExcel();
            new ExportExcel("组织机构", Office.class).setDataList(all).write(response, fileName).dispose();
            return null;
        } catch (Exception e) {
            addMessage(redirectAttributes, "导出组织机构！失败信息："+e.getMessage());
        }
        return "redirect:" + adminPath + "/sys/office/list?id=" + office.getParentId() + "&parentIds=" + office.getParentIds();
    }

    /**
     * 下载组织机构数据模板
     * @return
     */
    @RequiresPermissions("sys:office:view")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
        try {
            String fileName = "组织机构.xlsx";
            List<Office> list = Lists.newArrayList();
            //示例
            Office office = new Office();
            list.add(office);
            new ExportExcel("组织机构", Office.class, 2).setDataList(list).write(response, fileName).dispose();
            return null;
        } catch (Exception e) {
            addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
        }
        return "modules/sys/officeList";
    }

    /**
     * 导入组织机构数据
     * @param file
     * @param redirectAttributes
     * @return
     */
    @RequiresPermissions("sys:office:view")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
        if(Global.isDemoMode()){
            addMessage(redirectAttributes, "演示模式，不允许操作！");
            return "redirect:" + adminPath + "modules/sys/officeList";
        }
        try {
            int successNum = 0;
            int failureNum = 0;
            StringBuilder failureMsg = new StringBuilder();
            ImportExcel ei = new ImportExcel(file, 1, 0);
            List<Office> list = ei.getDataList(Office.class);
            for (Office office : list){
                try{
                    if(StringUtils.isNotEmpty(office.getParentName())){
                        Office office1 = new Office();
                        office1.setCode(office.getParentCode());
                        List<Office> list1 = officeService.findList(office1,0);
                        if(list1!=null && list1.size()>0){
                            if(list1.size()==1){
                                office.setParent(list1.get(0));
                            }else {
                                office.setParent(list1.get(1));
                            }
                        }
                    }
                    officeService.save(officeService.type2Int(office));
                    successNum++;
                }catch(ConstraintViolationException ex){
                    failureMsg.append("<br/>机构 "+office.getName()+" 导入失败：");
                    List<String> messageList = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");  //告诉你出错原因
                    for (String message : messageList){
                        failureMsg.append(message+"; ");
                        failureNum++;
                    }
                }catch (Exception ex) {
                    failureMsg.append("<br/>机构 "+office.getName()+" 导入失败："+ex.getMessage());
                }
            }
            if (failureNum>0){
                failureMsg.insert(0, "，失败 "+failureNum+" 条信息，导入信息如下：");
            }
            addMessage(redirectAttributes, "已成功导入 "+successNum+" 条信息"+failureMsg);
        } catch (Exception e) {
            addMessage(redirectAttributes, "导入机构失败！失败信息："+e.getMessage());
        }
        return  "modules/sys/officeList";
    }
}
