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
import cn.xy.tcmap.modules.catchup.csly.entity.TouristHotArea;
import cn.xy.tcmap.modules.catchup.csly.service.TouristHotAreaService;

/**
 * 地区游客密度Controller
 * @author tuo
 * @version 2019-09-10
 */
@Controller
@RequestMapping(value = "${adminPath}/csly/touristHotArea")
public class TouristHotAreaController extends BaseController {

	@Autowired
	private TouristHotAreaService touristHotAreaService;
	
	@ModelAttribute
	public TouristHotArea get(@RequestParam(required=false) String id) {
		TouristHotArea entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = touristHotAreaService.get(id);
		}
		if (entity == null){
			entity = new TouristHotArea();
		}
		return entity;
	}
	
	@RequiresPermissions("csly:touristHotArea:view")
	@RequestMapping(value = {"list", ""})
	public String list(TouristHotArea touristHotArea, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TouristHotArea> page = touristHotAreaService.findPage(new Page<TouristHotArea>(request, response), touristHotArea); 
		model.addAttribute("page", page);
		return "catchup/csly/touristHotAreaList";
	}

	@RequiresPermissions("csly:touristHotArea:view")
	@RequestMapping(value = "form")
	public String form(TouristHotArea touristHotArea, Model model) {
		model.addAttribute("touristHotArea", touristHotArea);
		return "catchup/csly/touristHotAreaForm";
	}

	@RequiresPermissions("csly:touristHotArea:edit")
	@RequestMapping(value = "save")
	public String save(TouristHotArea touristHotArea, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, touristHotArea)){
			return form(touristHotArea, model);
		}
		touristHotAreaService.save(touristHotArea);
		addMessage(redirectAttributes, "保存地区游客密度成功");
		return "redirect:"+Global.getAdminPath()+"/csly/touristHotArea/?repage";
	}
	
	@RequiresPermissions("csly:touristHotArea:edit")
	@RequestMapping(value = "delete")
	public String delete(TouristHotArea touristHotArea, RedirectAttributes redirectAttributes) {
		touristHotAreaService.delete(touristHotArea);
		addMessage(redirectAttributes, "删除地区游客密度成功");
		return "redirect:"+Global.getAdminPath()+"/csly/touristHotArea/?repage";
	}

}