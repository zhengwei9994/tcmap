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
import cn.xy.tcmap.modules.catchup.csly.entity.TouristEmotionMonth;
import cn.xy.tcmap.modules.catchup.csly.service.TouristEmotionMonthService;

/**
 * 一月各来源情绪占比Controller
 * @author wufan
 * @version 2019-08-16
 */
@Controller
@RequestMapping(value = "${adminPath}/csly/touristEmotionMonth")
public class TouristEmotionMonthController extends BaseController {

	@Autowired
	private TouristEmotionMonthService touristEmotionMonthService;
	
	@ModelAttribute
	public TouristEmotionMonth get(@RequestParam(required=false) String id) {
		TouristEmotionMonth entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = touristEmotionMonthService.get(id);
		}
		if (entity == null){
			entity = new TouristEmotionMonth();
		}
		return entity;
	}
	
	@RequiresPermissions("csly:touristEmotionMonth:view")
	@RequestMapping(value = {"list", ""})
	public String list(TouristEmotionMonth touristEmotionMonth, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TouristEmotionMonth> page = touristEmotionMonthService.findPage(new Page<TouristEmotionMonth>(request, response), touristEmotionMonth); 
		model.addAttribute("page", page);
		return "catchup/csly/touristEmotionMonthList";
	}

	@RequiresPermissions("csly:touristEmotionMonth:view")
	@RequestMapping(value = "form")
	public String form(TouristEmotionMonth touristEmotionMonth, Model model) {
		model.addAttribute("touristEmotionMonth", touristEmotionMonth);
		return "catchup/csly/touristEmotionMonthForm";
	}

	@RequiresPermissions("csly:touristEmotionMonth:edit")
	@RequestMapping(value = "save")
	public String save(TouristEmotionMonth touristEmotionMonth, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, touristEmotionMonth)){
			return form(touristEmotionMonth, model);
		}
		touristEmotionMonthService.save(touristEmotionMonth);
		addMessage(redirectAttributes, "保存一月各来源情绪占比成功");
		return "redirect:"+Global.getAdminPath()+"/csly/touristEmotionMonth/?repage";
	}
	
	@RequiresPermissions("csly:touristEmotionMonth:edit")
	@RequestMapping(value = "delete")
	public String delete(TouristEmotionMonth touristEmotionMonth, RedirectAttributes redirectAttributes) {
		touristEmotionMonthService.delete(touristEmotionMonth);
		addMessage(redirectAttributes, "删除一月各来源情绪占比成功");
		return "redirect:"+Global.getAdminPath()+"/csly/touristEmotionMonth/?repage";
	}

}