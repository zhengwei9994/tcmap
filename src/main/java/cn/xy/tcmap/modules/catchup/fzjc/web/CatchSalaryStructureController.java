/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.fzjc.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import cn.xy.tcmap.modules.catchup.csyx.entity.CatchAirQuality;
import cn.xy.tcmap.modules.catchup.fzjc.entity.CatchSalaryStructure;
import cn.xy.tcmap.modules.catchup.fzjc.service.CatchSalaryStructureService;

/**
 * 人才结构分析Controller
 * @author gxq
 * @version 2018-10-18
 */
@Controller
@RequestMapping(value = "${adminPath}/fzjc/catchSalaryStructure")
public class CatchSalaryStructureController extends BaseController {

	@Autowired
	private CatchSalaryStructureService catchSalaryStructureService;
	
	@ModelAttribute
	public CatchSalaryStructure get(@RequestParam(required=false) String id) {
		CatchSalaryStructure entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = catchSalaryStructureService.get(id);
		}
		if (entity == null){
			entity = new CatchSalaryStructure();
		}
		return entity;
	}
	
	@RequiresPermissions("fzjc:catchSalaryStructure:view")
	@RequestMapping(value = {"list", ""})
	public String list(CatchSalaryStructure catchSalaryStructure, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CatchSalaryStructure> page = catchSalaryStructureService.findPage(new Page<CatchSalaryStructure>(request, response), catchSalaryStructure); 
		model.addAttribute("page", page);
		return "catchup/fzjc/catchSalaryStructureList";
	}

	@RequiresPermissions("fzjc:catchSalaryStructure:view")
	@RequestMapping(value = "form")
	public String form(CatchSalaryStructure catchSalaryStructure, Model model) {
		model.addAttribute("catchSalaryStructure", catchSalaryStructure);
		return "catchup/fzjc/catchSalaryStructureForm";
	}

	@RequiresPermissions("fzjc:catchSalaryStructure:edit")
	@RequestMapping(value = "save")
	public String save(CatchSalaryStructure catchSalaryStructure, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, catchSalaryStructure)){
			return form(catchSalaryStructure, model);
		}
		if(catchSalaryStructure != null && "".equals(catchSalaryStructure.getId())){
	        	 List<CatchSalaryStructure> list = catchSalaryStructureService.findList(catchSalaryStructure);
	        	 if(list != null && list.size() > 0){
	        		 addMessage(redirectAttributes, "该日期下已有数据，请重新添加");
	        	 }else{
	        		 catchSalaryStructureService.save(catchSalaryStructure);
	        	     addMessage(redirectAttributes, "保存成功");
	        	 }
	     }else{
	        	catchSalaryStructureService.save(catchSalaryStructure);
	   	        addMessage(redirectAttributes, "保存成功");
	    	}
		return "redirect:"+Global.getAdminPath()+"/fzjc/catchSalaryStructure/?repage";
	}
	
	@RequiresPermissions("fzjc:catchSalaryStructure:edit")
	@RequestMapping(value = "delete")
	public String delete(CatchSalaryStructure catchSalaryStructure, RedirectAttributes redirectAttributes) {
		catchSalaryStructureService.delete(catchSalaryStructure);
		addMessage(redirectAttributes, "删除人才结构分析成功");
		return "redirect:"+Global.getAdminPath()+"/fzjc/catchSalaryStructure/?repage";
	}

}