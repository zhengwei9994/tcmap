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
import cn.xy.tcmap.modules.catchup.csly.entity.TouristTimeNegative;
import cn.xy.tcmap.modules.catchup.csly.service.TouristTimeNegativeService;

/**
 * 实时负面评价Controller
 * @author wufan
 * @version 2019-08-19
 */
@Controller
@RequestMapping(value = "${adminPath}/csly/touristTimeNegative")
public class TouristTimeNegativeController extends BaseController {

	@Autowired
	private TouristTimeNegativeService touristTimeNegativeService;
	
	@ModelAttribute
	public TouristTimeNegative get(@RequestParam(required=false) String id) {
		TouristTimeNegative entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = touristTimeNegativeService.get(id);
		}
		if (entity == null){
			entity = new TouristTimeNegative();
		}
		return entity;
	}
	
	@RequiresPermissions("csly:touristTimeNegative:view")
	@RequestMapping(value = {"list", ""})
	public String list(TouristTimeNegative touristTimeNegative, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TouristTimeNegative> page = touristTimeNegativeService.findPage(new Page<TouristTimeNegative>(request, response), touristTimeNegative); 
		model.addAttribute("page", page);
		return "catchup/csly/touristTimeNegativeList";
	}

	@RequiresPermissions("csly:touristTimeNegative:view")
	@RequestMapping(value = "form")
	public String form(TouristTimeNegative touristTimeNegative, Model model) {
		model.addAttribute("touristTimeNegative", touristTimeNegative);
		return "catchup/csly/touristTimeNegativeForm";
	}

	@RequiresPermissions("csly:touristTimeNegative:edit")
	@RequestMapping(value = "save")
	public String save(TouristTimeNegative touristTimeNegative, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, touristTimeNegative)){
			return form(touristTimeNegative, model);
		}
		touristTimeNegativeService.save(touristTimeNegative);
		addMessage(redirectAttributes, "保存实时负面评价成功");
		return "redirect:"+Global.getAdminPath()+"/csly/touristTimeNegative/?repage";
	}
	
	@RequiresPermissions("csly:touristTimeNegative:edit")
	@RequestMapping(value = "delete")
	public String delete(TouristTimeNegative touristTimeNegative, RedirectAttributes redirectAttributes) {
		touristTimeNegativeService.delete(touristTimeNegative);
		addMessage(redirectAttributes, "删除实时负面评价成功");
		return "redirect:"+Global.getAdminPath()+"/csly/touristTimeNegative/?repage";
	}

}