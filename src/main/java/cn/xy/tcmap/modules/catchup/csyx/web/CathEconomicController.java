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
import cn.xy.tcmap.modules.catchup.csyx.entity.CathEconomic;
import cn.xy.tcmap.modules.catchup.csyx.service.CathEconomicService;

/**
 * 经济指标名称经济指标统计Controller
 * @author xuzhou
 * @version 2018-05-24
 */
@Controller
@RequestMapping(value = "${adminPath}/csyx/cathEconomic")
public class CathEconomicController extends BaseController {

	@Autowired
	private CathEconomicService cathEconomicService;
	
	@ModelAttribute
	public CathEconomic get(@RequestParam(required=false) String id) {
		CathEconomic entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = cathEconomicService.get(id);
		}
		if (entity == null){
			entity = new CathEconomic();
		}
		return entity;
	}
	
	@RequiresPermissions("csyx:cathEconomic:view")
	@RequestMapping(value = {"list", ""})
	public String list(CathEconomic cathEconomic, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CathEconomic> page = cathEconomicService.findPage(new Page<CathEconomic>(request, response), cathEconomic); 
		model.addAttribute("page", page);
		return "catchup/csyx/cathEconomicList";
	}

	@RequiresPermissions("csyx:cathEconomic:view")
	@RequestMapping(value = "form")
	public String form(CathEconomic cathEconomic, Model model) {
		model.addAttribute("cathEconomic", cathEconomic);
		return "catchup/csyx/cathEconomicForm";
	}

	@RequiresPermissions("csyx:cathEconomic:edit")
	@RequestMapping(value = "save")
	public String save(CathEconomic cathEconomic, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, cathEconomic)){
			return form(cathEconomic, model);
		}
		cathEconomicService.save(cathEconomic);
		addMessage(redirectAttributes, "保存成功");
		return "redirect:"+Global.getAdminPath()+"/csyx/cathEconomic/?repage";
	}
	
	@RequiresPermissions("csyx:cathEconomic:edit")
	@RequestMapping(value = "delete")
	public String delete(CathEconomic cathEconomic, RedirectAttributes redirectAttributes) {
		cathEconomicService.delete(cathEconomic);
		addMessage(redirectAttributes, "删除成功");
		return "redirect:"+Global.getAdminPath()+"/csyx/cathEconomic/?repage";
	}

}