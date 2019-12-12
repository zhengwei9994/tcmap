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
import cn.xy.tcmap.modules.catchup.csly.entity.TouristWcPositive;
import cn.xy.tcmap.modules.catchup.csly.service.TouristWcPositiveService;

/**
 * 厕所正面词云Controller
 * @author wufan
 * @version 2019-08-16
 */
@Controller
@RequestMapping(value = "${adminPath}/csly/touristWcPositive")
public class TouristWcPositiveController extends BaseController {

	@Autowired
	private TouristWcPositiveService touristWcPositiveService;
	
	@ModelAttribute
	public TouristWcPositive get(@RequestParam(required=false) String id) {
		TouristWcPositive entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = touristWcPositiveService.get(id);
		}
		if (entity == null){
			entity = new TouristWcPositive();
		}
		return entity;
	}
	
	@RequiresPermissions("csly:touristWcPositive:view")
	@RequestMapping(value = {"list", ""})
	public String list(TouristWcPositive touristWcPositive, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TouristWcPositive> page = touristWcPositiveService.findPage(new Page<TouristWcPositive>(request, response), touristWcPositive); 
		model.addAttribute("page", page);
		return "catchup/csly/touristWcPositiveList";
	}

	@RequiresPermissions("csly:touristWcPositive:view")
	@RequestMapping(value = "form")
	public String form(TouristWcPositive touristWcPositive, Model model) {
		model.addAttribute("touristWcPositive", touristWcPositive);
		return "catchup/csly/touristWcPositiveForm";
	}

	@RequiresPermissions("csly:touristWcPositive:edit")
	@RequestMapping(value = "save")
	public String save(TouristWcPositive touristWcPositive, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, touristWcPositive)){
			return form(touristWcPositive, model);
		}
		touristWcPositiveService.save(touristWcPositive);
		addMessage(redirectAttributes, "保存厕所正面词云成功");
		return "redirect:"+Global.getAdminPath()+"/csly/touristWcPositive/?repage";
	}
	
	@RequiresPermissions("csly:touristWcPositive:edit")
	@RequestMapping(value = "delete")
	public String delete(TouristWcPositive touristWcPositive, RedirectAttributes redirectAttributes) {
		touristWcPositiveService.delete(touristWcPositive);
		addMessage(redirectAttributes, "删除厕所正面词云成功");
		return "redirect:"+Global.getAdminPath()+"/csly/touristWcPositive/?repage";
	}

}