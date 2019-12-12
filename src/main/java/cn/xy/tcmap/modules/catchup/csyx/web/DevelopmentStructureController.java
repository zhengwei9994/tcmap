/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csyx.web;

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
import cn.xy.tcmap.modules.catchup.csyx.entity.DevelopmentStructure;
import cn.xy.tcmap.modules.catchup.csyx.service.DevelopmentStructureService;

import java.util.List;

/**
 * 产业结构Controller
 * @author wufan
 * @version 2019-07-31
 */
@Controller
@RequestMapping(value = "${adminPath}/csyx/developmentStructure")
public class DevelopmentStructureController extends BaseController {

	@Autowired
	private DevelopmentStructureService developmentStructureService;
	
	@ModelAttribute
	public DevelopmentStructure get(@RequestParam(required=false) String id) {
		DevelopmentStructure entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = developmentStructureService.get(id);
		}
		if (entity == null){
			entity = new DevelopmentStructure();
		}
		return entity;
	}
	
	@RequiresPermissions("csyx:developmentStructure:view")
	@RequestMapping(value = {"list", ""})
	public String list(DevelopmentStructure developmentStructure, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<DevelopmentStructure> page = developmentStructureService.findPage(new Page<DevelopmentStructure>(request, response), developmentStructure); 
		model.addAttribute("page", page);
		return "catchup/csyx/developmentStructureList";
	}

	@RequiresPermissions("csyx:developmentStructure:view")
	@RequestMapping(value = "form")
	public String form(DevelopmentStructure developmentStructure, Model model) {
		model.addAttribute("developmentStructure", developmentStructure);
		return "catchup/csyx/developmentStructureForm";
	}

	@RequiresPermissions("csyx:developmentStructure:edit")
	@RequestMapping(value = "save")
	public String save(DevelopmentStructure developmentStructure, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, developmentStructure)){
			return form(developmentStructure, model);
		}
		try {
			if(developmentStructure.getIsNewRecord()){
				DevelopmentStructure ds = new DevelopmentStructure();
				ds.setMonth(developmentStructure.getMonth());
				ds.setYear(developmentStructure.getYear());
				ds.setIndustry(developmentStructure.getIndustry());
				List<DevelopmentStructure> list = developmentStructureService.findList(ds);
				if(list != null && list.size()>0){
					throw new Exception("数据重复");
				}
			}
			developmentStructureService.save(developmentStructure);
			addMessage(redirectAttributes, "保存产业结构成功");
		}catch (Exception e){
			addMessage(redirectAttributes, "保存产业结构失败,原因"+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/csyx/developmentStructure/?repage";
	}
	
	@RequiresPermissions("csyx:developmentStructure:edit")
	@RequestMapping(value = "delete")
	public String delete(DevelopmentStructure developmentStructure, RedirectAttributes redirectAttributes) {
		developmentStructureService.delete(developmentStructure);
		addMessage(redirectAttributes, "删除产业结构成功");
		return "redirect:"+Global.getAdminPath()+"/csyx/developmentStructure/?repage";
	}

}