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
import cn.xy.tcmap.modules.catchup.csly.entity.TouristWcNegative;
import cn.xy.tcmap.modules.catchup.csly.service.TouristWcNegativeService;

/**
 * 厕所负面词云Controller
 * @author wufan
 * @version 2019-08-16
 */
@Controller
@RequestMapping(value = "${adminPath}/csly/touristWcNegative")
public class TouristWcNegativeController extends BaseController {

	@Autowired
	private TouristWcNegativeService touristWcNegativeService;
	
	@ModelAttribute
	public TouristWcNegative get(@RequestParam(required=false) String id) {
		TouristWcNegative entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = touristWcNegativeService.get(id);
		}
		if (entity == null){
			entity = new TouristWcNegative();
		}
		return entity;
	}
	
	@RequiresPermissions("csly:touristWcNegative:view")
	@RequestMapping(value = {"list", ""})
	public String list(TouristWcNegative touristWcNegative, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TouristWcNegative> page = touristWcNegativeService.findPage(new Page<TouristWcNegative>(request, response), touristWcNegative); 
		model.addAttribute("page", page);
		return "catchup/csly/touristWcNegativeList";
	}

	@RequiresPermissions("csly:touristWcNegative:view")
	@RequestMapping(value = "form")
	public String form(TouristWcNegative touristWcNegative, Model model) {
		model.addAttribute("touristWcNegative", touristWcNegative);
		return "catchup/csly/touristWcNegativeForm";
	}

	@RequiresPermissions("csly:touristWcNegative:edit")
	@RequestMapping(value = "save")
	public String save(TouristWcNegative touristWcNegative, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, touristWcNegative)){
			return form(touristWcNegative, model);
		}
		touristWcNegativeService.save(touristWcNegative);
		addMessage(redirectAttributes, "保存厕所负面词云成功");
		return "redirect:"+Global.getAdminPath()+"/csly/touristWcNegative/?repage";
	}
	
	@RequiresPermissions("csly:touristWcNegative:edit")
	@RequestMapping(value = "delete")
	public String delete(TouristWcNegative touristWcNegative, RedirectAttributes redirectAttributes) {
		touristWcNegativeService.delete(touristWcNegative);
		addMessage(redirectAttributes, "删除厕所负面词云成功");
		return "redirect:"+Global.getAdminPath()+"/csly/touristWcNegative/?repage";
	}

}