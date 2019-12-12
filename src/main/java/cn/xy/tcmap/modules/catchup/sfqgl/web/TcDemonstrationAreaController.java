/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.sfqgl.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import cn.xy.tcmap.modules.sys.entity.Dict;
import cn.xy.tcmap.modules.sys.utils.DictUtils;
import com.google.common.collect.Lists;
import org.springframework.web.multipart.MultipartFile;
import cn.xy.tcmap.common.utils.excel.ImportExcel;
import org.springframework.web.bind.annotation.RequestMethod;
import cn.xy.tcmap.common.utils.DateUtils;
import cn.xy.tcmap.common.utils.excel.ExportExcel;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.xy.tcmap.common.config.Global;
import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.web.BaseController;
import cn.xy.tcmap.common.utils.StringUtils;
import cn.xy.tcmap.modules.catchup.sfqgl.entity.TcDemonstrationArea;
import cn.xy.tcmap.modules.catchup.sfqgl.service.TcDemonstrationAreaService;

/**
 * 示范区管理Controller
 *
 * @author wufan
 * @version 2019-12-04
 */
@Controller
@RequestMapping(value = "${adminPath}/csyx/tcDemonstrationArea")
public class TcDemonstrationAreaController extends BaseController {

    @Autowired
    private TcDemonstrationAreaService tcDemonstrationAreaService;

    @ModelAttribute
    public TcDemonstrationArea get(@RequestParam(required = false) String id) {
        TcDemonstrationArea entity = null;
        if (StringUtils.isNotBlank(id)) {
            entity = tcDemonstrationAreaService.get(id);
        }
        if (entity == null) {
            entity = new TcDemonstrationArea();
        }
        return entity;
    }

    @RequiresPermissions("csyx:tcDemonstrationArea:view")
    @RequestMapping(value = {"list", ""})
    public String list(TcDemonstrationArea tcDemonstrationArea, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<TcDemonstrationArea> page = tcDemonstrationAreaService.findPage(new Page<TcDemonstrationArea>(request, response), tcDemonstrationArea);
        model.addAttribute("page", page);
        return "catchup/sfqgl/tcDemonstrationAreaList";
    }

    @RequiresPermissions("csyx:tcDemonstrationArea:view")
    @RequestMapping(value = "form")
    public String form(TcDemonstrationArea tcDemonstrationArea, Model model) {
        model.addAttribute("tcDemonstrationArea", tcDemonstrationArea);
        return "catchup/sfqgl/tcDemonstrationAreaForm";
    }

    @RequiresPermissions("csyx:tcDemonstrationArea:edit")
    @RequestMapping(value = "save")
    public String save(TcDemonstrationArea tcDemonstrationArea, Model model, RedirectAttributes redirectAttributes) {
        if (!beanValidator(model, tcDemonstrationArea)) {
            return form(tcDemonstrationArea, model);
        }
        try {
            tcDemonstrationAreaService.save(tcDemonstrationArea);
            addMessage(redirectAttributes, "保存示范区管理成功");
        } catch (Exception e) {
            addMessage(redirectAttributes, "保存示范区管理失败！失败信息：" + e.getMessage());
        }
        return "redirect:" + Global.getAdminPath() + "/csyx/tcDemonstrationArea/?repage";
    }

    @RequiresPermissions("csyx:tcDemonstrationArea:edit")
    @RequestMapping(value = "delete")
    public String delete(TcDemonstrationArea tcDemonstrationArea, RedirectAttributes redirectAttributes) {
        tcDemonstrationAreaService.delete(tcDemonstrationArea);
        addMessage(redirectAttributes, "删除示范区管理成功");
        return "redirect:" + Global.getAdminPath() + "/csyx/tcDemonstrationArea/?repage";
    }

    /**
     * 导出数据
     *
     * @param tcDemonstrationArea
     * @param request
     * @param response
     * @param redirectAttributes
     * @return
     */
    @RequiresPermissions("csyx:tcDemonstrationArea:view")
    @RequestMapping(value = "export", method = RequestMethod.POST)
    public String exportFile(TcDemonstrationArea tcDemonstrationArea, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
        String wjm = new String(request.getParameter("name"));
        try {
            String fileName = wjm + "数据" + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx";
            // Page<TcDemonstrationArea> page = tcDemonstrationAreaService.findPage(new Page<TcDemonstrationArea>(request, response, -1),  tcDemonstrationArea);
            //导出全部数据
            List<TcDemonstrationArea> tcDemonstrationAreaList = tcDemonstrationAreaService.findList(new TcDemonstrationArea());
            new ExportExcel(wjm + "数据", TcDemonstrationArea.class).setDataList(tcDemonstrationAreaList).write(response, fileName).dispose();
            return null;
        } catch (Exception e) {
            addMessage(redirectAttributes, "导出" + wjm + "数据失败！失败信息：" + e.getMessage());
        }
        return "redirect:" + Global.getAdminPath() + "/csyx/tcDemonstrationArea/?repage";
    }

    /**
     * 导入用户数据
     *
     * @param file
     * @param redirectAttributes
     * @return
     */
    @RequiresPermissions("csyx:tcDemonstrationArea:edit")
    @RequestMapping(value = "import", method = RequestMethod.POST)
    public String importFile(MultipartFile file, TcDemonstrationArea tcDemonstrationArea, RedirectAttributes redirectAttributes) {
        try {
            int successNum = 0;
            int failureNum = 0;
            StringBuilder failureMsg = new StringBuilder();
            ImportExcel ei = new ImportExcel(file, 1, 0);
            List<TcDemonstrationArea> list = ei.getDataList(TcDemonstrationArea.class);
            List<Dict> dictList = DictUtils.getDictList("coordinate_source");
            List<String> collect = dictList.stream().map(Dict::getValue).collect(Collectors.toList());
            for (TcDemonstrationArea t : list) {
                if (!collect.contains(t.getSource())) {
                    failureMsg.append("坐标来源：" + t.getSource() + " 不存在，请先添加坐标来源");
                    continue;
                }
                if (t.getName().length() > 20 || t.getName().length() == 0) {
                    failureMsg.append(t.getName() + ": 名称长度有误");
                    failureNum++;
                    continue;
                }
                if (t.getAddress().length() > 50 || t.getAddress().length() == 0) {
                    failureMsg.append(t.getName() + ": 地址长度有误");
                    failureNum++;
                    continue;
                }
                Pattern detailsPattern = Pattern.compile("^.{1,255}$");
                if (t.getDetails().length() > 255 || t.getDetails().length() == 0) {
                    failureMsg.append(t.getName() + ": 描述长度有误");
                    failureNum++;
                    continue;
                }
                Pattern longitudePattern = Pattern.compile("^[\\-\\+]?(0(\\.\\d{1,10})?|([1-9](\\d)?)(\\.\\d{1,10})?|1[0-7]\\d{1}(\\.\\d{1,10})?|180\\.0{1,10})$");
                if (!longitudePattern.matcher(t.getLongitude()).matches()) {
                    failureMsg.append(t.getName() + ": 经度有误");
                    failureNum++;
                    continue;
                }
                Pattern dimensionPattern = Pattern.compile("^[\\-\\+]?((0|([1-8]\\d?))(\\.\\d{1,10})?|90(\\.0{1,10})?)$");
                if (!dimensionPattern.matcher(t.getDimension()).matches()) {
                    failureMsg.append(t.getName() + ": 维度有误");
                    failureNum++;
                    continue;
                }
				boolean flag = tcDemonstrationAreaService.isRepeat(t);
                if(flag){
					failureMsg.append(t.getName() + ": 数据重复");
					failureNum++;
					continue;
				}
				tcDemonstrationAreaService.save(t);
                successNum++;
            }
            if (failureNum > 0) {
                failureMsg.insert(0, "，失败 " + failureNum + " 条，导入信息如下：");
            }
            addMessage(redirectAttributes, "已成功导入 " + successNum + " 条" + failureMsg);
        } catch (Exception e) {
            addMessage(redirectAttributes, "导入失败！失败信息：" + e.getMessage());
        }
        return "redirect:" + Global.getAdminPath() + "/csyx/tcDemonstrationArea/?repage";
    }

    /**
     * 下载导入数据模板
     *
     * @param response
     * @param redirectAttributes
     * @return
     */
    @RequiresPermissions("csyx:tcDemonstrationArea:view")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(TcDemonstrationArea tcDemonstrationArea, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
        String wjm = request.getParameter("name");
        try {
            String fileName = wjm + "数据导入模板.xlsx";
            List<TcDemonstrationArea> list = Lists.newArrayList();
            list.add(tcDemonstrationArea);
            new ExportExcel(wjm + "数据", TcDemonstrationArea.class, 2).setDataList(list).write(response, fileName).dispose();
            return null;
        } catch (Exception e) {
            addMessage(redirectAttributes, "导入模板下载失败！失败信息：" + e.getMessage());
        }
        return "redirect:" + Global.getAdminPath() + "/csyx/tcDemonstrationArea/?repage";
    }

}