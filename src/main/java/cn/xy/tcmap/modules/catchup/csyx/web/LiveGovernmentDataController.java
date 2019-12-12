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
import cn.xy.tcmap.modules.catchup.csyx.entity.LiveGovernmentData;
import cn.xy.tcmap.modules.catchup.csyx.service.LiveGovernmentDataService;

/**
 * 政务服务Controller
 * @author wufan
 * @version 2019-10-14
 */
@Controller
@RequestMapping(value = "${adminPath}/csyx/liveGovernmentData")
public class LiveGovernmentDataController extends BaseController {

	@Autowired
	private LiveGovernmentDataService liveGovernmentDataService;
	
	@ModelAttribute
	public LiveGovernmentData get(@RequestParam(required=false) String id) {
		LiveGovernmentData entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = liveGovernmentDataService.get(id);
		}
		if (entity == null){
			entity = new LiveGovernmentData();
		}
		return entity;
	}
	
	@RequiresPermissions("csyx:liveGovernmentData:view")
	@RequestMapping(value = {"list", ""})
	public String list(LiveGovernmentData liveGovernmentData, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<LiveGovernmentData> page = liveGovernmentDataService.findPage(new Page<LiveGovernmentData>(request, response), liveGovernmentData); 
		model.addAttribute("page", page);
		return "catchup/csyx/liveGovernmentDataList";
	}

	@RequiresPermissions("csyx:liveGovernmentData:view")
	@RequestMapping(value = "form")
	public String form(LiveGovernmentData liveGovernmentData, Model model) {
		model.addAttribute("liveGovernmentData", liveGovernmentData);
		return "catchup/csyx/liveGovernmentDataForm";
	}

	@RequiresPermissions("csyx:liveGovernmentData:edit")
	@RequestMapping(value = "save")
	public String save(LiveGovernmentData liveGovernmentData, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, liveGovernmentData)){
			return form(liveGovernmentData, model);
		}
		liveGovernmentDataService.save(liveGovernmentData);
		addMessage(redirectAttributes, "保存政务服务成功");
		return "redirect:"+Global.getAdminPath()+"/csyx/liveGovernmentData/?repage";
	}
	
	@RequiresPermissions("csyx:liveGovernmentData:edit")
	@RequestMapping(value = "delete")
	public String delete(LiveGovernmentData liveGovernmentData, RedirectAttributes redirectAttributes) {
		liveGovernmentDataService.delete(liveGovernmentData);
		addMessage(redirectAttributes, "删除政务服务成功");
		return "redirect:"+Global.getAdminPath()+"/csyx/liveGovernmentData/?repage";
	}

}