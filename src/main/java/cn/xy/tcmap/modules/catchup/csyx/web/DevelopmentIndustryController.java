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
import cn.xy.tcmap.modules.catchup.csyx.entity.DevelopmentIndustry;
import cn.xy.tcmap.modules.catchup.csyx.service.DevelopmentIndustryService;

/**
 * 产业发展Controller
 * @author wufan
 * @version 2019-07-31
 */
@Controller
@RequestMapping(value = "${adminPath}/csyx/developmentIndustry")
public class DevelopmentIndustryController extends BaseController {

	@Autowired
	private DevelopmentIndustryService developmentIndustryService;
	
	@ModelAttribute
	public DevelopmentIndustry get(@RequestParam(required=false) String id) {
		DevelopmentIndustry entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = developmentIndustryService.get(id);
		}
		if (entity == null){
			entity = new DevelopmentIndustry();
		}
		return entity;
	}
	
	@RequiresPermissions("csyx:developmentIndustry:view")
	@RequestMapping(value = {"list", ""})
	public String list(DevelopmentIndustry developmentIndustry, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<DevelopmentIndustry> page = developmentIndustryService.findPage(new Page<DevelopmentIndustry>(request, response), developmentIndustry); 
		model.addAttribute("page", page);
		return "catchup/csyx/developmentIndustryList";
	}

	@RequiresPermissions("csyx:developmentIndustry:view")
	@RequestMapping(value = "form")
	public String form(DevelopmentIndustry developmentIndustry, Model model) {
		model.addAttribute("developmentIndustry", developmentIndustry);
		return "catchup/csyx/developmentIndustryForm";
	}

	@RequiresPermissions("csyx:developmentIndustry:edit")
	@RequestMapping(value = "save")
	public String save(DevelopmentIndustry developmentIndustry, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, developmentIndustry)){
			return form(developmentIndustry, model);
		}
		developmentIndustryService.save(developmentIndustry);
		addMessage(redirectAttributes, "保存产业发展成功");
		return "redirect:"+Global.getAdminPath()+"/csyx/developmentIndustry/?repage";
	}
	
	@RequiresPermissions("csyx:developmentIndustry:edit")
	@RequestMapping(value = "delete")
	public String delete(DevelopmentIndustry developmentIndustry, RedirectAttributes redirectAttributes) {
		developmentIndustryService.delete(developmentIndustry);
		addMessage(redirectAttributes, "删除产业发展成功");
		return "redirect:"+Global.getAdminPath()+"/csyx/developmentIndustry/?repage";
	}

}