/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.fzjc.web;

import cn.xy.tcmap.common.config.Global;
import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.utils.StringUtils;
import cn.xy.tcmap.common.web.BaseController;
import cn.xy.tcmap.modules.catchup.fzjc.entity.CatchGrowthSituation;
import cn.xy.tcmap.modules.catchup.fzjc.service.CatchGrowthSituationService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 区县经济增长情况Controller
 * @author liuyang
 * @version 2018-05-29
 */
@Controller
@RequestMapping(value = "${adminPath}/fzjc/catchGrowthSituation")
public class CatchGrowthSituationController extends BaseController {

	@Autowired
	private CatchGrowthSituationService catchGrowthSituationService;
	
	@ModelAttribute
	public CatchGrowthSituation get(@RequestParam(required=false) String id) {
		CatchGrowthSituation entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = catchGrowthSituationService.get(id);
		}
		if (entity == null){
			entity = new CatchGrowthSituation();
		}
		return entity;
	}
	
	@RequiresPermissions("fzjc:catchGrowthSituation:view")
	@RequestMapping(value = {"list", ""})
	public String list(CatchGrowthSituation catchGrowthSituation, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CatchGrowthSituation> page = catchGrowthSituationService.findPage(new Page<CatchGrowthSituation>(request, response), catchGrowthSituation); 
		model.addAttribute("page", page);
		return "catchup/fzjc/catchGrowthSituationList";
	}

	@RequiresPermissions("fzjc:catchGrowthSituation:view")
	@RequestMapping(value = "form")
	public String form(CatchGrowthSituation catchGrowthSituation, Model model) {
		model.addAttribute("catchGrowthSituation", catchGrowthSituation);
		return "catchup/fzjc/catchGrowthSituationForm";
	}

	@RequiresPermissions("fzjc:catchGrowthSituation:edit")
	@RequestMapping(value = "save")
	public String save(CatchGrowthSituation catchGrowthSituation, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, catchGrowthSituation)){
			return form(catchGrowthSituation, model);
		}
		catchGrowthSituationService.save(catchGrowthSituation);
		addMessage(redirectAttributes, "保存区县经济增长情况成功");
		return "redirect:"+Global.getAdminPath()+"/fzjc/catchGrowthSituation/?repage";
	}
	
	@RequiresPermissions("fzjc:catchGrowthSituation:edit")
	@RequestMapping(value = "delete")
	public String delete(CatchGrowthSituation catchGrowthSituation, RedirectAttributes redirectAttributes) {
		catchGrowthSituationService.delete(catchGrowthSituation);
		addMessage(redirectAttributes, "删除区县经济增长情况成功");
		return "redirect:"+Global.getAdminPath()+"/fzjc/catchGrowthSituation/?repage";
	}

}