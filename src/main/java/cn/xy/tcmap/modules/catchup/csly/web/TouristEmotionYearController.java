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
import cn.xy.tcmap.modules.catchup.csly.entity.TouristEmotionYear;
import cn.xy.tcmap.modules.catchup.csly.service.TouristEmotionYearService;

/**
 * 一年各来源情绪占比Controller
 * @author wufan
 * @version 2019-08-16
 */
@Controller
@RequestMapping(value = "${adminPath}/csly/touristEmotionYear")
public class TouristEmotionYearController extends BaseController {

	@Autowired
	private TouristEmotionYearService touristEmotionYearService;
	
	@ModelAttribute
	public TouristEmotionYear get(@RequestParam(required=false) String id) {
		TouristEmotionYear entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = touristEmotionYearService.get(id);
		}
		if (entity == null){
			entity = new TouristEmotionYear();
		}
		return entity;
	}
	
	@RequiresPermissions("csly:touristEmotionYear:view")
	@RequestMapping(value = {"list", ""})
	public String list(TouristEmotionYear touristEmotionYear, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TouristEmotionYear> page = touristEmotionYearService.findPage(new Page<TouristEmotionYear>(request, response), touristEmotionYear); 
		model.addAttribute("page", page);
		return "catchup/csly/touristEmotionYearList";
	}

	@RequiresPermissions("csly:touristEmotionYear:view")
	@RequestMapping(value = "form")
	public String form(TouristEmotionYear touristEmotionYear, Model model) {
		model.addAttribute("touristEmotionYear", touristEmotionYear);
		return "catchup/csly/touristEmotionYearForm";
	}

	@RequiresPermissions("csly:touristEmotionYear:edit")
	@RequestMapping(value = "save")
	public String save(TouristEmotionYear touristEmotionYear, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, touristEmotionYear)){
			return form(touristEmotionYear, model);
		}
		touristEmotionYearService.save(touristEmotionYear);
		addMessage(redirectAttributes, "保存一年各来源情绪占比成功");
		return "redirect:"+Global.getAdminPath()+"/csly/touristEmotionYear/?repage";
	}
	
	@RequiresPermissions("csly:touristEmotionYear:edit")
	@RequestMapping(value = "delete")
	public String delete(TouristEmotionYear touristEmotionYear, RedirectAttributes redirectAttributes) {
		touristEmotionYearService.delete(touristEmotionYear);
		addMessage(redirectAttributes, "删除一年各来源情绪占比成功");
		return "redirect:"+Global.getAdminPath()+"/csly/touristEmotionYear/?repage";
	}

}