/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.sys.web;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import cn.xy.tcmap.common.beanvalidator.BeanValidators;
import cn.xy.tcmap.common.utils.DateUtils;
import cn.xy.tcmap.common.utils.excel.ExportExcel;
import cn.xy.tcmap.common.utils.excel.ImportExcel;
import cn.xy.tcmap.modules.sys.entity.Office;
import cn.xy.tcmap.modules.sys.service.OfficeService;
import com.google.common.collect.ComputationException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
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
import cn.xy.tcmap.modules.sys.entity.Area;
import cn.xy.tcmap.modules.sys.service.AreaService;
import cn.xy.tcmap.modules.sys.utils.UserUtils;

/**
 * 区域Controller
 * @author ThinkGem
 * @version 2013-5-15
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/area")
public class AreaController extends BaseController {

	@Autowired
	private AreaService areaService;

	@Autowired
	private OfficeService officeService;
	
	@ModelAttribute("area")
	public Area get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return areaService.get(id);
		}else{
			return new Area();
		}
	}

	@RequiresPermissions("sys:area:view")
	@RequestMapping(value = {"list", ""})
	public String list(Area area, Model model) {
		model.addAttribute("list", areaService.findAll());
		return "modules/sys/areaList";
	}

	@RequiresPermissions("sys:area:view")
	@RequestMapping(value = "form")
	public String form(Area area, Model model) {
		if (area.getParent()==null||area.getParent().getId()==null){
			area.setParent(UserUtils.getUser().getOffice().getArea());
		}
		area.setParent(areaService.get(area.getParent().getId()));
//		// 自动获取排序号
//		if (StringUtils.isBlank(area.getId())){
//			int size = 0;
//			List<Area> list = areaService.findAll();
//			for (int i=0; i<list.size(); i++){
//				Area e = list.get(i);
//				if (e.getParent()!=null && e.getParent().getId()!=null
//						&& e.getParent().getId().equals(area.getParent().getId())){
//					size++;
//				}
//			}
//			area.setCode(area.getParent().getCode() + StringUtils.leftPad(String.valueOf(size > 0 ? size : 1), 4, "0"));
//		}
		model.addAttribute("area", area);
		return "modules/sys/areaForm";
	}
	
	@RequiresPermissions("sys:area:edit")
	@RequestMapping(value = "save")
	public String save(Area area, Model model, RedirectAttributes redirectAttributes) {
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/sys/area";
		}
		if (!beanValidator(model, area)){
			return form(area, model);
		}
		areaService.save(area);
		addMessage(redirectAttributes, "保存区域'" + area.getName() + "'成功");
		return "redirect:" + adminPath + "/sys/area/";
	}
	
	@RequiresPermissions("sys:area:edit")
	@RequestMapping(value = "delete")
	public String delete(Area area, RedirectAttributes redirectAttributes) {
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/sys/area";
		}
        Office office = new Office();
		office.setArea(area);
        List<Office> officeList = officeService.findList(true);
        boolean flag = false;//是否有area的数据
        for (Office office1:officeList){
         if(office1.getArea().getId().equals(area.getId())){
             flag=true;
             break;
         }
        }
        if(flag){
            addMessage(redirectAttributes,"删除区域失败, 区域有关联机构");
        }else {
            areaService.delete(area);
            addMessage(redirectAttributes, "删除区域成功");
        }
		return "redirect:" + adminPath + "/sys/area/";
	}

	/**
	 * 导出区域
	 * @return
	 */
	@RequiresPermissions("sys:area:view")
	@RequestMapping(value = "export", method= RequestMethod.POST)
	public String exportArea(Area area, HttpServletRequest request,HttpServletResponse response,RedirectAttributes redirectAttributes){
		String fileName="区域管理"+ DateUtils.getDate("yyyymmddhhmmss")+".xlsx";

		try {
			List<Area> areaList=areaService.findAreaExcel();
			new ExportExcel("区域管理", Area.class,1).setDataList(areaList).write(response,fileName).dispose();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			addMessage(redirectAttributes,"导出区域管理失败，失败信息:"+e.getMessage());
		}
		return "redirect:" + adminPath + "/sys/area/";
	}

	/**
	 * 下载组织机构模板
	 * @return
	 */
	@RequiresPermissions("sys:area:view")
	@RequestMapping(value = "import/template")
	public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes){
		String fileName="区域管理.xlsx";
		List<Area> areaList=Lists.newArrayList();
		try {
			new ExportExcel("区域管理",Area.class,2).setDataList(areaList).write(response,fileName).dispose();
			return  null;
		} catch (IOException e) {
			addMessage(redirectAttributes,"导入模板下载失败，信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/sys/area/";
	}
	/**
	 * 导入区域
	 * @return
	 */
	@RequiresPermissions("sys:area:view")
	@RequestMapping(value = "import", method= RequestMethod.POST)
	public String importArea(MultipartFile file,RedirectAttributes redirectAttributes){

		if(Global.isDemoMode()){
			addMessage(redirectAttributes,"演示模式，禁止操作！");
			return "redirect:" + adminPath + "/sys/area/";
		}
		int successNum=0;
		int failureNum=0;
		StringBuilder failureMsg=new StringBuilder();
		try {
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<Area> areaList=ei.getDataList(Area.class);
			for(Area  area:areaList){
			try {
                   if(StringUtils.isNotEmpty(area.getName())){
                   	  Area area1=new Area();
                   	  area1.setCode(area.getCode());
                   	  List<Area> areaList1=areaService.findList(area1);
                   	  if(areaList1!=null&& areaList1.size()>0){
                   	  	area.setParent(areaList1.get(1));
					  }
					areaService.save(areaService.importArea(area));
					successNum++;
				   }
			} catch (ConstraintViolationException ex) {
				failureMsg.append("</br>区域"+area.getName()+"导入失败");
				List<String>   messageList= BeanValidators.extractPropertyAndMessageAsList(ex,":");
				for(String massage:messageList){
					failureMsg.append(massage+";");
					failureNum++;
				}
			}catch (Exception exception){
				failureMsg.append("</br>区域"+area.getName()+"导入失败");
				exception.printStackTrace();
			}
			}
			if(failureNum>0){
				failureMsg.insert(0,"导入失败"+failureNum+"条数据,失败信息如下：");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条信息"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入机构失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/sys/area/";
	}
	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required=false) String extId, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<Area> list = areaService.findAll();
		for (int i=0; i<list.size(); i++){
			Area e = list.get(i);
			if (StringUtils.isBlank(extId) || (extId!=null && !extId.equals(e.getId()) && e.getParentIds().indexOf(","+extId+",")==-1)){
				Map<String, Object> map = Maps.newHashMap();
				map.put("id", e.getId());
				map.put("pId", e.getParentId());
				map.put("name", e.getName());
				mapList.add(map);
			}
		}
		return mapList;
	}
}
