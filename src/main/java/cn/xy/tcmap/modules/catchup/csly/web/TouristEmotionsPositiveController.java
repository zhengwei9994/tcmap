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
import cn.xy.tcmap.modules.catchup.csly.entity.TouristEmotionsPositive;
import cn.xy.tcmap.modules.catchup.csly.service.TouristEmotionsPositiveService;

/**
 * 正面情绪Controller
 * @author wufan
 * @version 2019-08-16
 */
@Controller
@RequestMapping(value = "${adminPath}/csly/touristEmotionsPositive")
public class TouristEmotionsPositiveController extends BaseController {

	@Autowired
	private TouristEmotionsPositiveService touristEmotionsPositiveService;
	
	@ModelAttribute
	public TouristEmotionsPositive get(@RequestParam(required=false) String id) {
		TouristEmotionsPositive entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = touristEmotionsPositiveService.get(id);
		}
		if (entity == null){
			entity = new TouristEmotionsPositive();
		}
		return entity;
	}
	
	@RequiresPermissions("csly:touristEmotionsPositive:view")
	@RequestMapping(value = {"list", ""})
	public String list(TouristEmotionsPositive touristEmotionsPositive, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TouristEmotionsPositive> page = touristEmotionsPositiveService.findPage(new Page<TouristEmotionsPositive>(request, response), touristEmotionsPositive); 
		model.addAttribute("page", page);
		return "catchup/csly/touristEmotionsPositiveList";
	}

	@RequiresPermissions("csly:touristEmotionsPositive:view")
	@RequestMapping(value = "form")
	public String form(TouristEmotionsPositive touristEmotionsPositive, Model model) {
		model.addAttribute("touristEmotionsPositive", touristEmotionsPositive);
		return "catchup/csly/touristEmotionsPositiveForm";
	}

	@RequiresPermissions("csly:touristEmotionsPositive:edit")
	@RequestMapping(value = "save")
	public String save(TouristEmotionsPositive touristEmotionsPositive, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, touristEmotionsPositive)){
			return form(touristEmotionsPositive, model);
		}
		touristEmotionsPositiveService.save(touristEmotionsPositive);
		addMessage(redirectAttributes, "保存正面情绪成功");
		return "redirect:"+Global.getAdminPath()+"/csly/touristEmotionsPositive/?repage";
	}
	
	@RequiresPermissions("csly:touristEmotionsPositive:edit")
	@RequestMapping(value = "delete")
	public String delete(TouristEmotionsPositive touristEmotionsPositive, RedirectAttributes redirectAttributes) {
		touristEmotionsPositiveService.delete(touristEmotionsPositive);
		addMessage(redirectAttributes, "删除正面情绪成功");
		return "redirect:"+Global.getAdminPath()+"/csly/touristEmotionsPositive/?repage";
	}

}