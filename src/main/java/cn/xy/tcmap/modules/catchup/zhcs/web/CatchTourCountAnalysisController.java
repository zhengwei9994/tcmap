/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.zhcs.web;

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
import cn.xy.tcmap.modules.catchup.zhcs.entity.CatchTourCountAnalysis;
import cn.xy.tcmap.modules.catchup.zhcs.service.CatchTourCountAnalysisService;

/**
 * 旅游趋势分析Controller
 * @author wl
 * @version 2018-09-28
 */
@Controller
@RequestMapping(value = "${adminPath}/zhcs/catchTourCountAnalysis")
public class CatchTourCountAnalysisController extends BaseController {

	@Autowired
	private CatchTourCountAnalysisService catchTourCountAnalysisService;
	
	@ModelAttribute
	public CatchTourCountAnalysis get(@RequestParam(required=false) String id) {
		CatchTourCountAnalysis entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = catchTourCountAnalysisService.get(id);
		}
		if (entity == null){
			entity = new CatchTourCountAnalysis();
		}
		return entity;
	}
	
	@RequiresPermissions("zhcs:catchTourCountAnalysis:view")
	@RequestMapping(value = {"list", ""})
	public String list(CatchTourCountAnalysis catchTourCountAnalysis, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CatchTourCountAnalysis> page = catchTourCountAnalysisService.findPage(new Page<CatchTourCountAnalysis>(request, response), catchTourCountAnalysis); 
		model.addAttribute("page", page);
		return "catchup/zhcs/catchTourCountAnalysisList";
	}

	@RequiresPermissions("zhcs:catchTourCountAnalysis:view")
	@RequestMapping(value = "form")
	public String form(CatchTourCountAnalysis catchTourCountAnalysis, Model model) {
		model.addAttribute("catchTourCountAnalysis", catchTourCountAnalysis);
		return "catchup/zhcs/catchTourCountAnalysisForm";
	}

	@RequiresPermissions("zhcs:catchTourCountAnalysis:edit")
	@RequestMapping(value = "save")
	public String save(CatchTourCountAnalysis catchTourCountAnalysis, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, catchTourCountAnalysis)){
			return form(catchTourCountAnalysis, model);
		}
		catchTourCountAnalysisService.save(catchTourCountAnalysis);
		addMessage(redirectAttributes, "保存旅游趋势分析成功");
		return "redirect:"+Global.getAdminPath()+"/zhcs/catchTourCountAnalysis/?repage";
	}
	
	@RequiresPermissions("zhcs:catchTourCountAnalysis:edit")
	@RequestMapping(value = "delete")
	public String delete(CatchTourCountAnalysis catchTourCountAnalysis, RedirectAttributes redirectAttributes) {
		catchTourCountAnalysisService.delete(catchTourCountAnalysis);
		addMessage(redirectAttributes, "删除旅游趋势分析成功");
		return "redirect:"+Global.getAdminPath()+"/zhcs/catchTourCountAnalysis/?repage";
	}

}