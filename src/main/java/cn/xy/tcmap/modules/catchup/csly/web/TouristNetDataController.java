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
import cn.xy.tcmap.modules.catchup.csly.entity.TouristNetData;
import cn.xy.tcmap.modules.catchup.csly.service.TouristNetDataService;

/**
 * 互联网数据流Controller
 * @author wufan
 * @version 2019-08-16
 */
@Controller
@RequestMapping(value = "${adminPath}/csly/touristNetData")
public class TouristNetDataController extends BaseController {

	@Autowired
	private TouristNetDataService touristNetDataService;
	
	@ModelAttribute
	public TouristNetData get(@RequestParam(required=false) String id) {
		TouristNetData entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = touristNetDataService.get(id);
		}
		if (entity == null){
			entity = new TouristNetData();
		}
		return entity;
	}
	
	@RequiresPermissions("csly:touristNetData:view")
	@RequestMapping(value = {"list", ""})
	public String list(TouristNetData touristNetData, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TouristNetData> page = touristNetDataService.findPage(new Page<TouristNetData>(request, response), touristNetData); 
		model.addAttribute("page", page);
		return "catchup/csly/touristNetDataList";
	}

	@RequiresPermissions("csly:touristNetData:view")
	@RequestMapping(value = "form")
	public String form(TouristNetData touristNetData, Model model) {
		model.addAttribute("touristNetData", touristNetData);
		return "catchup/csly/touristNetDataForm";
	}

	@RequiresPermissions("csly:touristNetData:edit")
	@RequestMapping(value = "save")
	public String save(TouristNetData touristNetData, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, touristNetData)){
			return form(touristNetData, model);
		}
		touristNetDataService.save(touristNetData);
		addMessage(redirectAttributes, "保存互联网数据流成功");
		return "redirect:"+Global.getAdminPath()+"/csly/touristNetData/?repage";
	}
	
	@RequiresPermissions("csly:touristNetData:edit")
	@RequestMapping(value = "delete")
	public String delete(TouristNetData touristNetData, RedirectAttributes redirectAttributes) {
		touristNetDataService.delete(touristNetData);
		addMessage(redirectAttributes, "删除互联网数据流成功");
		return "redirect:"+Global.getAdminPath()+"/csly/touristNetData/?repage";
	}

}