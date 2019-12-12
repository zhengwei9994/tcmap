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
import cn.xy.tcmap.modules.catchup.csly.entity.TouristWordcloud;
import cn.xy.tcmap.modules.catchup.csly.service.TouristWordcloudService;

/**
 * 词云Controller
 * @author wufan
 * @version 2019-08-16
 */
@Controller
@RequestMapping(value = "${adminPath}/csly/touristWordcloud")
public class TouristWordcloudController extends BaseController {

	@Autowired
	private TouristWordcloudService touristWordcloudService;
	
	@ModelAttribute
	public TouristWordcloud get(@RequestParam(required=false) String id) {
		TouristWordcloud entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = touristWordcloudService.get(id);
		}
		if (entity == null){
			entity = new TouristWordcloud();
		}
		return entity;
	}
	
	@RequiresPermissions("csly:touristWordcloud:view")
	@RequestMapping(value = {"list", ""})
	public String list(TouristWordcloud touristWordcloud, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TouristWordcloud> page = touristWordcloudService.findPage(new Page<TouristWordcloud>(request, response), touristWordcloud); 
		model.addAttribute("page", page);
		return "catchup/csly/touristWordcloudList";
	}

	@RequiresPermissions("csly:touristWordcloud:view")
	@RequestMapping(value = "form")
	public String form(TouristWordcloud touristWordcloud, Model model) {
		model.addAttribute("touristWordcloud", touristWordcloud);
		return "catchup/csly/touristWordcloudForm";
	}

	@RequiresPermissions("csly:touristWordcloud:edit")
	@RequestMapping(value = "save")
	public String save(TouristWordcloud touristWordcloud, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, touristWordcloud)){
			return form(touristWordcloud, model);
		}
		touristWordcloudService.save(touristWordcloud);
		addMessage(redirectAttributes, "保存词云成功");
		return "redirect:"+Global.getAdminPath()+"/csly/touristWordcloud/?repage";
	}
	
	@RequiresPermissions("csly:touristWordcloud:edit")
	@RequestMapping(value = "delete")
	public String delete(TouristWordcloud touristWordcloud, RedirectAttributes redirectAttributes) {
		touristWordcloudService.delete(touristWordcloud);
		addMessage(redirectAttributes, "删除词云成功");
		return "redirect:"+Global.getAdminPath()+"/csly/touristWordcloud/?repage";
	}

}