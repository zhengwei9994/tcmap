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
import cn.xy.tcmap.modules.catchup.csly.entity.TouristWcSound;
import cn.xy.tcmap.modules.catchup.csly.service.TouristWcSoundService;

/**
 * 厕所声量Controller
 * @author wufan
 * @version 2019-08-16
 */
@Controller
@RequestMapping(value = "${adminPath}/csly/touristWcSound")
public class TouristWcSoundController extends BaseController {

	@Autowired
	private TouristWcSoundService touristWcSoundService;
	
	@ModelAttribute
	public TouristWcSound get(@RequestParam(required=false) String id) {
		TouristWcSound entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = touristWcSoundService.get(id);
		}
		if (entity == null){
			entity = new TouristWcSound();
		}
		return entity;
	}
	
	@RequiresPermissions("csly:touristWcSound:view")
	@RequestMapping(value = {"list", ""})
	public String list(TouristWcSound touristWcSound, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TouristWcSound> page = touristWcSoundService.findPage(new Page<TouristWcSound>(request, response), touristWcSound); 
		model.addAttribute("page", page);
		return "catchup/csly/touristWcSoundList";
	}

	@RequiresPermissions("csly:touristWcSound:view")
	@RequestMapping(value = "form")
	public String form(TouristWcSound touristWcSound, Model model) {
		model.addAttribute("touristWcSound", touristWcSound);
		return "catchup/csly/touristWcSoundForm";
	}

	@RequiresPermissions("csly:touristWcSound:edit")
	@RequestMapping(value = "save")
	public String save(TouristWcSound touristWcSound, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, touristWcSound)){
			return form(touristWcSound, model);
		}
		touristWcSoundService.save(touristWcSound);
		addMessage(redirectAttributes, "保存厕所声量成功");
		return "redirect:"+Global.getAdminPath()+"/csly/touristWcSound/?repage";
	}
	
	@RequiresPermissions("csly:touristWcSound:edit")
	@RequestMapping(value = "delete")
	public String delete(TouristWcSound touristWcSound, RedirectAttributes redirectAttributes) {
		touristWcSoundService.delete(touristWcSound);
		addMessage(redirectAttributes, "删除厕所声量成功");
		return "redirect:"+Global.getAdminPath()+"/csly/touristWcSound/?repage";
	}

}