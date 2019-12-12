/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.fzjc.web;

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
import cn.xy.tcmap.modules.catchup.fzjc.entity.CatchTalentAnalysis;
import cn.xy.tcmap.modules.catchup.fzjc.service.CatchTalentAnalysisService;

/**
 * 人才结构现状分析Controller
 * @author wl
 * @version 2018-09-29
 */
@Controller
@RequestMapping(value = "${adminPath}/fzjc/catchTalentAnalysis")
public class CatchTalentAnalysisController extends BaseController {

	@Autowired
	private CatchTalentAnalysisService catchTalentAnalysisService;
	
	@ModelAttribute
	public CatchTalentAnalysis get(@RequestParam(required=false) String id) {
		CatchTalentAnalysis entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = catchTalentAnalysisService.get(id);
		}
		if (entity == null){
			entity = new CatchTalentAnalysis();
		}
		return entity;
	}
	
	@RequiresPermissions("fzjc:catchTalentAnalysis:view")
	@RequestMapping(value = {"list", ""})
	public String list(CatchTalentAnalysis catchTalentAnalysis, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CatchTalentAnalysis> page = catchTalentAnalysisService.findPage(new Page<CatchTalentAnalysis>(request, response), catchTalentAnalysis); 
		model.addAttribute("page", page);
		return "catchup/fzjc/catchTalentAnalysisList";
	}

	@RequiresPermissions("fzjc:catchTalentAnalysis:view")
	@RequestMapping(value = "form")
	public String form(CatchTalentAnalysis catchTalentAnalysis, Model model) {
		model.addAttribute("catchTalentAnalysis", catchTalentAnalysis);
		return "catchup/fzjc/catchTalentAnalysisForm";
	}

	@RequiresPermissions("fzjc:catchTalentAnalysis:edit")
	@RequestMapping(value = "save")
	public String save(CatchTalentAnalysis catchTalentAnalysis, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, catchTalentAnalysis)){
			return form(catchTalentAnalysis, model);
		}
		catchTalentAnalysisService.save(catchTalentAnalysis);
		addMessage(redirectAttributes, "保存人才结构现状分析成功");
		return "redirect:"+Global.getAdminPath()+"/fzjc/catchTalentAnalysis/?repage";
	}
	
	@RequiresPermissions("fzjc:catchTalentAnalysis:edit")
	@RequestMapping(value = "delete")
	public String delete(CatchTalentAnalysis catchTalentAnalysis, RedirectAttributes redirectAttributes) {
		catchTalentAnalysisService.delete(catchTalentAnalysis);
		addMessage(redirectAttributes, "删除人才结构现状分析成功");
		return "redirect:"+Global.getAdminPath()+"/fzjc/catchTalentAnalysis/?repage";
	}

}