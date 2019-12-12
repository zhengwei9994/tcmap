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
import cn.xy.tcmap.modules.catchup.csly.entity.TouristSoundVolume;
import cn.xy.tcmap.modules.catchup.csly.service.TouristSoundVolumeService;

/**
 * 游客声量Controller
 * @author wufan
 * @version 2019-08-16
 */
@Controller
@RequestMapping(value = "${adminPath}/csly/touristSoundVolume")
public class TouristSoundVolumeController extends BaseController {

	@Autowired
	private TouristSoundVolumeService touristSoundVolumeService;
	
	@ModelAttribute
	public TouristSoundVolume get(@RequestParam(required=false) String id) {
		TouristSoundVolume entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = touristSoundVolumeService.get(id);
		}
		if (entity == null){
			entity = new TouristSoundVolume();
		}
		return entity;
	}
	
	@RequiresPermissions("csly:touristSoundVolume:view")
	@RequestMapping(value = {"list", ""})
	public String list(TouristSoundVolume touristSoundVolume, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TouristSoundVolume> page = touristSoundVolumeService.findPage(new Page<TouristSoundVolume>(request, response), touristSoundVolume); 
		model.addAttribute("page", page);
		return "catchup/csly/touristSoundVolumeList";
	}

	@RequiresPermissions("csly:touristSoundVolume:view")
	@RequestMapping(value = "form")
	public String form(TouristSoundVolume touristSoundVolume, Model model) {
		model.addAttribute("touristSoundVolume", touristSoundVolume);
		return "catchup/csly/touristSoundVolumeForm";
	}

	@RequiresPermissions("csly:touristSoundVolume:edit")
	@RequestMapping(value = "save")
	public String save(TouristSoundVolume touristSoundVolume, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, touristSoundVolume)){
			return form(touristSoundVolume, model);
		}
		touristSoundVolumeService.save(touristSoundVolume);
		addMessage(redirectAttributes, "保存游客声量成功");
		return "redirect:"+Global.getAdminPath()+"/csly/touristSoundVolume/?repage";
	}
	
	@RequiresPermissions("csly:touristSoundVolume:edit")
	@RequestMapping(value = "delete")
	public String delete(TouristSoundVolume touristSoundVolume, RedirectAttributes redirectAttributes) {
		touristSoundVolumeService.delete(touristSoundVolume);
		addMessage(redirectAttributes, "删除游客声量成功");
		return "redirect:"+Global.getAdminPath()+"/csly/touristSoundVolume/?repage";
	}

}