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
import cn.xy.tcmap.modules.catchup.csly.entity.TouristSentimentSource;
import cn.xy.tcmap.modules.catchup.csly.service.TouristSentimentSourceService;

/**
 * 舆情数据来源Controller
 * @author wufan
 * @version 2019-08-16
 */
@Controller
@RequestMapping(value = "${adminPath}/csly/touristSentimentSource")
public class TouristSentimentSourceController extends BaseController {

	@Autowired
	private TouristSentimentSourceService touristSentimentSourceService;
	
	@ModelAttribute
	public TouristSentimentSource get(@RequestParam(required=false) String id) {
		TouristSentimentSource entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = touristSentimentSourceService.get(id);
		}
		if (entity == null){
			entity = new TouristSentimentSource();
		}
		return entity;
	}
	
	@RequiresPermissions("csly:touristSentimentSource:view")
	@RequestMapping(value = {"list", ""})
	public String list(TouristSentimentSource touristSentimentSource, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TouristSentimentSource> page = touristSentimentSourceService.findPage(new Page<TouristSentimentSource>(request, response), touristSentimentSource); 
		model.addAttribute("page", page);
		return "catchup/csly/touristSentimentSourceList";
	}

	@RequiresPermissions("csly:touristSentimentSource:view")
	@RequestMapping(value = "form")
	public String form(TouristSentimentSource touristSentimentSource, Model model) {
		model.addAttribute("touristSentimentSource", touristSentimentSource);
		return "catchup/csly/touristSentimentSourceForm";
	}

	@RequiresPermissions("csly:touristSentimentSource:edit")
	@RequestMapping(value = "save")
	public String save(TouristSentimentSource touristSentimentSource, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, touristSentimentSource)){
			return form(touristSentimentSource, model);
		}
		touristSentimentSourceService.save(touristSentimentSource);
		addMessage(redirectAttributes, "保存舆情数据来源成功");
		return "redirect:"+Global.getAdminPath()+"/csly/touristSentimentSource/?repage";
	}
	
	@RequiresPermissions("csly:touristSentimentSource:edit")
	@RequestMapping(value = "delete")
	public String delete(TouristSentimentSource touristSentimentSource, RedirectAttributes redirectAttributes) {
		touristSentimentSourceService.delete(touristSentimentSource);
		addMessage(redirectAttributes, "删除舆情数据来源成功");
		return "redirect:"+Global.getAdminPath()+"/csly/touristSentimentSource/?repage";
	}

}