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
import cn.xy.tcmap.modules.catchup.csyx.entity.TrafficStreetUp;
import cn.xy.tcmap.modules.catchup.csyx.service.TrafficStreetUpService;

/**
 * 重点道路上报事件Controller
 * @author wufan
 * @version 2019-10-18
 */
@Controller
@RequestMapping(value = "${adminPath}/csyx/trafficStreetUp")
public class TrafficStreetUpController extends BaseController {

	@Autowired
	private TrafficStreetUpService trafficStreetUpService;
	
	@ModelAttribute
	public TrafficStreetUp get(@RequestParam(required=false) String id) {
		TrafficStreetUp entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = trafficStreetUpService.get(id);
		}
		if (entity == null){
			entity = new TrafficStreetUp();
		}
		return entity;
	}
	
	@RequiresPermissions("csyx:trafficStreetUp:view")
	@RequestMapping(value = {"list", ""})
	public String list(TrafficStreetUp trafficStreetUp, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TrafficStreetUp> page = trafficStreetUpService.findPage(new Page<TrafficStreetUp>(request, response), trafficStreetUp); 
		model.addAttribute("page", page);
		return "catchup/csyx/trafficStreetUpList";
	}

	@RequiresPermissions("csyx:trafficStreetUp:view")
	@RequestMapping(value = "form")
	public String form(TrafficStreetUp trafficStreetUp, Model model) {
		model.addAttribute("trafficStreetUp", trafficStreetUp);
		return "catchup/csyx/trafficStreetUpForm";
	}

	@RequiresPermissions("csyx:trafficStreetUp:edit")
	@RequestMapping(value = "save")
	public String save(TrafficStreetUp trafficStreetUp, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, trafficStreetUp)){
			return form(trafficStreetUp, model);
		}
		trafficStreetUpService.save(trafficStreetUp);
		addMessage(redirectAttributes, "保存重点道路上报事件成功");
		return "redirect:"+Global.getAdminPath()+"/csyx/trafficStreetUp/?repage";
	}
	
	@RequiresPermissions("csyx:trafficStreetUp:edit")
	@RequestMapping(value = "delete")
	public String delete(TrafficStreetUp trafficStreetUp, RedirectAttributes redirectAttributes) {
		trafficStreetUpService.delete(trafficStreetUp);
		addMessage(redirectAttributes, "删除重点道路上报事件成功");
		return "redirect:"+Global.getAdminPath()+"/csyx/trafficStreetUp/?repage";
	}

}