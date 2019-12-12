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
import cn.xy.tcmap.modules.catchup.csyx.entity.LiveGovementSituation;
import cn.xy.tcmap.modules.catchup.csyx.service.LiveGovementSituationService;

/**
 * 网上办理情况Controller
 * @author wufan
 * @version 2019-10-14
 */
@Controller
@RequestMapping(value = "${adminPath}/csyx/liveGovementSituation")
public class LiveGovementSituationController extends BaseController {

	@Autowired
	private LiveGovementSituationService liveGovementSituationService;
	
	@ModelAttribute
	public LiveGovementSituation get(@RequestParam(required=false) String id) {
		LiveGovementSituation entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = liveGovementSituationService.get(id);
		}
		if (entity == null){
			entity = new LiveGovementSituation();
		}
		return entity;
	}
	
	@RequiresPermissions("csyx:liveGovementSituation:view")
	@RequestMapping(value = {"list", ""})
	public String list(LiveGovementSituation liveGovementSituation, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<LiveGovementSituation> page = liveGovementSituationService.findPage(new Page<LiveGovementSituation>(request, response), liveGovementSituation); 
		model.addAttribute("page", page);
		return "catchup/csyx/liveGovementSituationList";
	}

	@RequiresPermissions("csyx:liveGovementSituation:view")
	@RequestMapping(value = "form")
	public String form(LiveGovementSituation liveGovementSituation, Model model) {
		model.addAttribute("liveGovementSituation", liveGovementSituation);
		return "catchup/csyx/liveGovementSituationForm";
	}

	@RequiresPermissions("csyx:liveGovementSituation:edit")
	@RequestMapping(value = "save")
	public String save(LiveGovementSituation liveGovementSituation, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, liveGovementSituation)){
			return form(liveGovementSituation, model);
		}
		liveGovementSituationService.save(liveGovementSituation);
		addMessage(redirectAttributes, "保存网上办理情况成功");
		return "redirect:"+Global.getAdminPath()+"/csyx/liveGovementSituation/?repage";
	}
	
	@RequiresPermissions("csyx:liveGovementSituation:edit")
	@RequestMapping(value = "delete")
	public String delete(LiveGovementSituation liveGovementSituation, RedirectAttributes redirectAttributes) {
		liveGovementSituationService.delete(liveGovementSituation);
		addMessage(redirectAttributes, "删除网上办理情况成功");
		return "redirect:"+Global.getAdminPath()+"/csyx/liveGovementSituation/?repage";
	}

}