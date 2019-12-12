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
import cn.xy.tcmap.modules.catchup.csly.entity.TouristVolumeTime;
import cn.xy.tcmap.modules.catchup.csly.service.TouristVolumeTimeService;

/**
 * 当日游客量Controller
 * @author wufan
 * @version 2019-08-16
 */
@Controller
@RequestMapping(value = "${adminPath}/csly/touristVolumeTime")
public class TouristVolumeTimeController extends BaseController {

	@Autowired
	private TouristVolumeTimeService touristVolumeTimeService;
	
	@ModelAttribute
	public TouristVolumeTime get(@RequestParam(required=false) String id) {
		TouristVolumeTime entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = touristVolumeTimeService.get(id);
		}
		if (entity == null){
			entity = new TouristVolumeTime();
		}
		return entity;
	}
	
	@RequiresPermissions("csly:touristVolumeTime:view")
	@RequestMapping(value = {"list", ""})
	public String list(TouristVolumeTime touristVolumeTime, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TouristVolumeTime> page = touristVolumeTimeService.findPage(new Page<TouristVolumeTime>(request, response), touristVolumeTime); 
		model.addAttribute("page", page);
		return "catchup/csly/touristVolumeTimeList";
	}

	@RequiresPermissions("csly:touristVolumeTime:view")
	@RequestMapping(value = "form")
	public String form(TouristVolumeTime touristVolumeTime, Model model) {
		model.addAttribute("touristVolumeTime", touristVolumeTime);
		return "catchup/csly/touristVolumeTimeForm";
	}

	@RequiresPermissions("csly:touristVolumeTime:edit")
	@RequestMapping(value = "save")
	public String save(TouristVolumeTime touristVolumeTime, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, touristVolumeTime)){
			return form(touristVolumeTime, model);
		}
		touristVolumeTimeService.save(touristVolumeTime);
		addMessage(redirectAttributes, "保存当日游客量成功");
		return "redirect:"+Global.getAdminPath()+"/csly/touristVolumeTime/?repage";
	}
	
	@RequiresPermissions("csly:touristVolumeTime:edit")
	@RequestMapping(value = "delete")
	public String delete(TouristVolumeTime touristVolumeTime, RedirectAttributes redirectAttributes) {
		touristVolumeTimeService.delete(touristVolumeTime);
		addMessage(redirectAttributes, "删除当日游客量成功");
		return "redirect:"+Global.getAdminPath()+"/csly/touristVolumeTime/?repage";
	}

}