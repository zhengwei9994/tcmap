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
import cn.xy.tcmap.modules.catchup.csly.entity.TouristEmotionsNegative;
import cn.xy.tcmap.modules.catchup.csly.service.TouristEmotionsNegativeService;

/**
 * 负面情绪Controller
 * @author wufan
 * @version 2019-08-16
 */
@Controller
@RequestMapping(value = "${adminPath}/csly/touristEmotionsNegative")
public class TouristEmotionsNegativeController extends BaseController {

	@Autowired
	private TouristEmotionsNegativeService touristEmotionsNegativeService;
	
	@ModelAttribute
	public TouristEmotionsNegative get(@RequestParam(required=false) String id) {
		TouristEmotionsNegative entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = touristEmotionsNegativeService.get(id);
		}
		if (entity == null){
			entity = new TouristEmotionsNegative();
		}
		return entity;
	}
	
	@RequiresPermissions("csly:touristEmotionsNegative:view")
	@RequestMapping(value = {"list", ""})
	public String list(TouristEmotionsNegative touristEmotionsNegative, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TouristEmotionsNegative> page = touristEmotionsNegativeService.findPage(new Page<TouristEmotionsNegative>(request, response), touristEmotionsNegative); 
		model.addAttribute("page", page);
		return "catchup/csly/touristEmotionsNegativeList";
	}

	@RequiresPermissions("csly:touristEmotionsNegative:view")
	@RequestMapping(value = "form")
	public String form(TouristEmotionsNegative touristEmotionsNegative, Model model) {
		model.addAttribute("touristEmotionsNegative", touristEmotionsNegative);
		return "catchup/csly/touristEmotionsNegativeForm";
	}

	@RequiresPermissions("csly:touristEmotionsNegative:edit")
	@RequestMapping(value = "save")
	public String save(TouristEmotionsNegative touristEmotionsNegative, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, touristEmotionsNegative)){
			return form(touristEmotionsNegative, model);
		}
		touristEmotionsNegativeService.save(touristEmotionsNegative);
		addMessage(redirectAttributes, "保存负面情绪成功");
		return "redirect:"+Global.getAdminPath()+"/csly/touristEmotionsNegative/?repage";
	}
	
	@RequiresPermissions("csly:touristEmotionsNegative:edit")
	@RequestMapping(value = "delete")
	public String delete(TouristEmotionsNegative touristEmotionsNegative, RedirectAttributes redirectAttributes) {
		touristEmotionsNegativeService.delete(touristEmotionsNegative);
		addMessage(redirectAttributes, "删除负面情绪成功");
		return "redirect:"+Global.getAdminPath()+"/csly/touristEmotionsNegative/?repage";
	}

}