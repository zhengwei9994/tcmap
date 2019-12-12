/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csly.web;

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
import cn.xy.tcmap.modules.catchup.csly.entity.TouristSatisfaction;
import cn.xy.tcmap.modules.catchup.csly.service.TouristSatisfactionService;

/**
 * 满意度雷达图Controller
 * @author wufan
 * @version 2019-08-16
 */
@Controller
@RequestMapping(value = "${adminPath}/csly/touristSatisfaction")
public class TouristSatisfactionController extends BaseController {

	@Autowired
	private TouristSatisfactionService touristSatisfactionService;
	
	@ModelAttribute
	public TouristSatisfaction get(@RequestParam(required=false) String id) {
		TouristSatisfaction entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = touristSatisfactionService.get(id);
		}
		if (entity == null){
			entity = new TouristSatisfaction();
		}
		return entity;
	}
	
	@RequiresPermissions("csly:touristSatisfaction:view")
	@RequestMapping(value = {"list", ""})
	public String list(TouristSatisfaction touristSatisfaction, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TouristSatisfaction> page = touristSatisfactionService.findPage(new Page<TouristSatisfaction>(request, response), touristSatisfaction); 
		model.addAttribute("page", page);
		return "catchup/csly/touristSatisfactionList";
	}

	@RequiresPermissions("csly:touristSatisfaction:view")
	@RequestMapping(value = "form")
	public String form(TouristSatisfaction touristSatisfaction, Model model) {
		model.addAttribute("touristSatisfaction", touristSatisfaction);
		return "catchup/csly/touristSatisfactionForm";
	}

	@RequiresPermissions("csly:touristSatisfaction:edit")
	@RequestMapping(value = "save")
	public String save(TouristSatisfaction touristSatisfaction, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, touristSatisfaction)){
			return form(touristSatisfaction, model);
		}
		touristSatisfactionService.save(touristSatisfaction);
		addMessage(redirectAttributes, "保存满意度雷达图成功");
		return "redirect:"+Global.getAdminPath()+"/csly/touristSatisfaction/?repage";
	}
	
	@RequiresPermissions("csly:touristSatisfaction:edit")
	@RequestMapping(value = "delete")
	public String delete(TouristSatisfaction touristSatisfaction, RedirectAttributes redirectAttributes) {
		touristSatisfactionService.delete(touristSatisfaction);
		addMessage(redirectAttributes, "删除满意度雷达图成功");
		return "redirect:"+Global.getAdminPath()+"/csly/touristSatisfaction/?repage";
	}

}