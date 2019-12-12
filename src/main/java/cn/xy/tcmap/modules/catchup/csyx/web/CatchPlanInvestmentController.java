/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csyx.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.xy.tcmap.common.config.Global;
import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.utils.StringUtils;
import cn.xy.tcmap.common.web.BaseController;
import cn.xy.tcmap.modules.catchup.csyx.entity.CatchPlanInvestment;
import cn.xy.tcmap.modules.catchup.csyx.service.CatchPlanInvestmentService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;



/**
 * 年计划总投资Controller
 * @author xuzhou
 * @version 2018-05-02
 */
@Controller
@RequestMapping(value = "${adminPath}/csyx/catchPlanInvestment")
public class CatchPlanInvestmentController extends BaseController {

	@Autowired
	private CatchPlanInvestmentService catchPlanInvestmentService;
	
	@ModelAttribute
	public CatchPlanInvestment get(@RequestParam(required=false) String id) {
		CatchPlanInvestment entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = catchPlanInvestmentService.get(id);
		}
		if (entity == null){
			entity = new CatchPlanInvestment();
		}
		return entity;
	}
	
	@RequiresPermissions("csyx:catchPlanInvestment:view")
	@RequestMapping(value = {"list", ""})
	public String list(CatchPlanInvestment catchPlanInvestment, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CatchPlanInvestment> page = catchPlanInvestmentService.findPage(new Page<CatchPlanInvestment>(request, response), catchPlanInvestment);
		model.addAttribute("page", page);
		return "catchup/csyx/catchPlanInvestmentList";
	}

	@RequiresPermissions("csyx:catchPlanInvestment:view")
	@RequestMapping(value = "form")
	public String form(CatchPlanInvestment catchPlanInvestment, Model model) {
		model.addAttribute("catchPlanInvestment", catchPlanInvestment);
		return "catchup/csyx/catchPlanInvestmentForm";
	}

	@RequiresPermissions("csyx:catchPlanInvestment:edit")
	@RequestMapping(value = "save")
	public String save(CatchPlanInvestment catchPlanInvestment, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, catchPlanInvestment)){
			return form(catchPlanInvestment, model);
		}
		catchPlanInvestmentService.save(catchPlanInvestment);
		addMessage(redirectAttributes, "保存年计划总投资成功");
		return "redirect:"+ Global.getAdminPath()+"/csyx/catchPlanInvestment/?repage";
	}
	
	@RequiresPermissions("csyx:catchPlanInvestment:edit")
	@RequestMapping(value = "delete")
	public String delete(CatchPlanInvestment catchPlanInvestment, RedirectAttributes redirectAttributes) {
		catchPlanInvestmentService.delete(catchPlanInvestment);
		addMessage(redirectAttributes, "删除年计划总投资成功");
		return "redirect:"+Global.getAdminPath()+"/csyx/catchPlanInvestment/?repage";
	}

}