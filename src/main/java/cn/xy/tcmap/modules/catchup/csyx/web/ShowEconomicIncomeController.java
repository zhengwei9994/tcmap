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
import cn.xy.tcmap.modules.catchup.csyx.entity.ShowEconomicIncome;
import cn.xy.tcmap.modules.catchup.csyx.service.ShowEconomicIncomeService;

import java.util.List;

/**
 * 经济财政收入Controller
 * @author wufan
 * @version 2019-07-31
 */
@Controller
@RequestMapping(value = "${adminPath}/csyx/showEconomicIncome")
public class ShowEconomicIncomeController extends BaseController {

	@Autowired
	private ShowEconomicIncomeService showEconomicIncomeService;
	
	@ModelAttribute
	public ShowEconomicIncome get(@RequestParam(required=false) String id) {
		ShowEconomicIncome entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = showEconomicIncomeService.get(id);
		}
		if (entity == null){
			entity = new ShowEconomicIncome();
		}
		return entity;
	}
	
	@RequiresPermissions("csyx:showEconomicIncome:view")
	@RequestMapping(value = {"list", ""})
	public String list(ShowEconomicIncome showEconomicIncome, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ShowEconomicIncome> page = showEconomicIncomeService.findPage(new Page<ShowEconomicIncome>(request, response), showEconomicIncome); 
		model.addAttribute("page", page);
		return "catchup/csyx/showEconomicIncomeList";
	}

	@RequiresPermissions("csyx:showEconomicIncome:view")
	@RequestMapping(value = "form")
	public String form(ShowEconomicIncome showEconomicIncome, Model model) {
		model.addAttribute("showEconomicIncome", showEconomicIncome);
		return "catchup/csyx/showEconomicIncomeForm";
	}

	@RequiresPermissions("csyx:showEconomicIncome:edit")
	@RequestMapping(value = "save")
	public String save(ShowEconomicIncome showEconomicIncome, Model model, RedirectAttributes redirectAttributes) {
		try{
			if (!beanValidator(model, showEconomicIncome)){
				return form(showEconomicIncome, model);
			}
			if(showEconomicIncome.getIsNewRecord()){
				ShowEconomicIncome condition = new ShowEconomicIncome();
				condition.setYear(showEconomicIncome.getYear());
				condition.setMonth(showEconomicIncome.getMonth());
				List<ShowEconomicIncome> list = showEconomicIncomeService.findList(condition);
				if(list != null && list.size()>0){
					throw new Exception("数据重复");
				}
			}
			showEconomicIncomeService.save(showEconomicIncome);
			addMessage(redirectAttributes, "保存经济财政收入成功");
		}catch (Exception e){
			addMessage(redirectAttributes, "保存经济财政收入失败,失败原因："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/csyx/showEconomicIncome/?repage";
	}
	
	@RequiresPermissions("csyx:showEconomicIncome:edit")
	@RequestMapping(value = "delete")
	public String delete(ShowEconomicIncome showEconomicIncome, RedirectAttributes redirectAttributes) {
		showEconomicIncomeService.delete(showEconomicIncome);
		addMessage(redirectAttributes, "删除经济财政收入成功");
		return "redirect:"+Global.getAdminPath()+"/csyx/showEconomicIncome/?repage";
	}

}