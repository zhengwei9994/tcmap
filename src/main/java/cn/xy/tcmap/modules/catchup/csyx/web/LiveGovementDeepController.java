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
import cn.xy.tcmap.modules.catchup.csyx.entity.LiveGovementDeep;
import cn.xy.tcmap.modules.catchup.csyx.service.LiveGovementDeepService;

/**
 * 办理深度Controller
 * @author wufan
 * @version 2019-10-14
 */
@Controller
@RequestMapping(value = "${adminPath}/csyx/liveGovementDeep")
public class LiveGovementDeepController extends BaseController {

	@Autowired
	private LiveGovementDeepService liveGovementDeepService;
	
	@ModelAttribute
	public LiveGovementDeep get(@RequestParam(required=false) String id) {
		LiveGovementDeep entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = liveGovementDeepService.get(id);
		}
		if (entity == null){
			entity = new LiveGovementDeep();
		}
		return entity;
	}
	
	@RequiresPermissions("csyx:liveGovementDeep:view")
	@RequestMapping(value = {"list", ""})
	public String list(LiveGovementDeep liveGovementDeep, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<LiveGovementDeep> page = liveGovementDeepService.findPage(new Page<LiveGovementDeep>(request, response), liveGovementDeep); 
		model.addAttribute("page", page);
		return "catchup/csyx/liveGovementDeepList";
	}

	@RequiresPermissions("csyx:liveGovementDeep:view")
	@RequestMapping(value = "form")
	public String form(LiveGovementDeep liveGovementDeep, Model model) {
		model.addAttribute("liveGovementDeep", liveGovementDeep);
		return "catchup/csyx/liveGovementDeepForm";
	}

	@RequiresPermissions("csyx:liveGovementDeep:edit")
	@RequestMapping(value = "save")
	public String save(LiveGovementDeep liveGovementDeep, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, liveGovementDeep)){
			return form(liveGovementDeep, model);
		}
		liveGovementDeepService.save(liveGovementDeep);
		addMessage(redirectAttributes, "保存办理深度成功");
		return "redirect:"+Global.getAdminPath()+"/csyx/liveGovementDeep/?repage";
	}
	
	@RequiresPermissions("csyx:liveGovementDeep:edit")
	@RequestMapping(value = "delete")
	public String delete(LiveGovementDeep liveGovementDeep, RedirectAttributes redirectAttributes) {
		liveGovementDeepService.delete(liveGovementDeep);
		addMessage(redirectAttributes, "删除办理深度成功");
		return "redirect:"+Global.getAdminPath()+"/csyx/liveGovementDeep/?repage";
	}

}