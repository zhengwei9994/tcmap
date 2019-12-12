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
import cn.xy.tcmap.modules.catchup.fzjc.entity.CatchResidentIncome;
import cn.xy.tcmap.modules.catchup.fzjc.service.CatchResidentIncomeService;

/**
 * 居民收入Controller
 * @author FSH
 * @version 2018-10-18
 */
@Controller
@RequestMapping(value = "${adminPath}/fzjc/catchResidentIncome")
public class CatchResidentIncomeController extends BaseController {

	@Autowired
	private CatchResidentIncomeService catchResidentIncomeService;
	
	@ModelAttribute
	public CatchResidentIncome get(@RequestParam(required=false) String id) {
		CatchResidentIncome entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = catchResidentIncomeService.get(id);
		}
		if (entity == null){
			entity = new CatchResidentIncome();
		}
		return entity;
	}
	
	@RequiresPermissions("fzjc:catchResidentIncome:view")
	@RequestMapping(value = {"list", ""})
	public String list(CatchResidentIncome catchResidentIncome, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CatchResidentIncome> page = catchResidentIncomeService.findPage(new Page<CatchResidentIncome>(request, response), catchResidentIncome); 
		model.addAttribute("page", page);
		return "catchup/fzjc/catchResidentIncomeList";
	}

	@RequiresPermissions("fzjc:catchResidentIncome:view")
	@RequestMapping(value = "form")
	public String form(CatchResidentIncome catchResidentIncome, Model model) {
		model.addAttribute("catchResidentIncome", catchResidentIncome);
		return "catchup/fzjc/catchResidentIncomeForm";
	}

	@RequiresPermissions("fzjc:catchResidentIncome:edit")
	@RequestMapping(value = "save")
	public String save(CatchResidentIncome catchResidentIncome, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, catchResidentIncome)){
			return form(catchResidentIncome, model);
		}
		catchResidentIncomeService.save(catchResidentIncome);
		addMessage(redirectAttributes, "保存居民收入成功");
		return "redirect:"+Global.getAdminPath()+"/fzjc/catchResidentIncome/?repage";
	}
	
	@RequiresPermissions("fzjc:catchResidentIncome:edit")
	@RequestMapping(value = "delete")
	public String delete(CatchResidentIncome catchResidentIncome, RedirectAttributes redirectAttributes) {
		catchResidentIncomeService.delete(catchResidentIncome);
		addMessage(redirectAttributes, "删除居民收入成功");
		return "redirect:"+Global.getAdminPath()+"/fzjc/catchResidentIncome/?repage";
	}

}