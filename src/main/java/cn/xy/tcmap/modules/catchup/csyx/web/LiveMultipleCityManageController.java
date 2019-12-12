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
import cn.xy.tcmap.modules.catchup.csyx.entity.LiveMultipleCityManage;
import cn.xy.tcmap.modules.catchup.csyx.service.LiveMultipleCityManageService;

/**
 * 城市综合治理Controller
 * @author tuo
 * @version 2019-08-14
 */
@Controller
@RequestMapping(value = "${adminPath}/csyx/liveMultipleCityManage")
public class LiveMultipleCityManageController extends BaseController {

	@Autowired
	private LiveMultipleCityManageService liveMultipleCityManageService;
	
	@ModelAttribute
	public LiveMultipleCityManage get(@RequestParam(required=false) String id) {
		LiveMultipleCityManage entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = liveMultipleCityManageService.get(id);
		}
		if (entity == null){
			entity = new LiveMultipleCityManage();
		}
		return entity;
	}
	
	@RequiresPermissions("csyx:liveMultipleCityManage:view")
	@RequestMapping(value = {"list", ""})
	public String list(LiveMultipleCityManage liveMultipleCityManage, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<LiveMultipleCityManage> page = liveMultipleCityManageService.findPage(new Page<LiveMultipleCityManage>(request, response), liveMultipleCityManage); 
		model.addAttribute("page", page);
		return "catchup/csyx/liveMultipleCityManageList";
	}

	@RequiresPermissions("csyx:liveMultipleCityManage:view")
	@RequestMapping(value = "form")
	public String form(LiveMultipleCityManage liveMultipleCityManage, Model model) {
		model.addAttribute("liveMultipleCityManage", liveMultipleCityManage);
		return "catchup/csyx/liveMultipleCityManageForm";
	}

	@RequiresPermissions("csyx:liveMultipleCityManage:edit")
	@RequestMapping(value = "save")
	public String save(LiveMultipleCityManage liveMultipleCityManage, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, liveMultipleCityManage)){
			return form(liveMultipleCityManage, model);
		}
		liveMultipleCityManageService.save(liveMultipleCityManage);
		addMessage(redirectAttributes, "保存城市综合治理成功");
		return "redirect:"+Global.getAdminPath()+"/csyx/liveMultipleCityManage/?repage";
	}
	
	@RequiresPermissions("csyx:liveMultipleCityManage:edit")
	@RequestMapping(value = "delete")
	public String delete(LiveMultipleCityManage liveMultipleCityManage, RedirectAttributes redirectAttributes) {
		liveMultipleCityManageService.delete(liveMultipleCityManage);
		addMessage(redirectAttributes, "删除城市综合治理成功");
		return "redirect:"+Global.getAdminPath()+"/csyx/liveMultipleCityManage/?repage";
	}

}