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
import cn.xy.tcmap.modules.catchup.csyx.entity.ShowEconomicGrowth;
import cn.xy.tcmap.modules.catchup.csyx.service.ShowEconomicGrowthService;

import java.util.List;

/**
 * 经济成长性Controller
 * @author wufan
 * @version 2019-07-31
 */
@Controller
@RequestMapping(value = "${adminPath}/csyx/showEconomicGrowth")
public class ShowEconomicGrowthController extends BaseController {

	@Autowired
	private ShowEconomicGrowthService showEconomicGrowthService;
	
	@ModelAttribute
	public ShowEconomicGrowth get(@RequestParam(required=false) String id) {
		ShowEconomicGrowth entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = showEconomicGrowthService.get(id);
		}
		if (entity == null){
			entity = new ShowEconomicGrowth();
		}
		return entity;
	}
	
	@RequiresPermissions("csyx:showEconomicGrowth:view")
	@RequestMapping(value = {"list", ""})
	public String list(ShowEconomicGrowth showEconomicGrowth, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ShowEconomicGrowth> page = showEconomicGrowthService.findPage(new Page<ShowEconomicGrowth>(request, response), showEconomicGrowth); 
		model.addAttribute("page", page);
		return "catchup/csyx/showEconomicGrowthList";
	}

	@RequiresPermissions("csyx:showEconomicGrowth:view")
	@RequestMapping(value = "form")
	public String form(ShowEconomicGrowth showEconomicGrowth, Model model) {
		model.addAttribute("showEconomicGrowth", showEconomicGrowth);
		return "catchup/csyx/showEconomicGrowthForm";
	}

	@RequiresPermissions("csyx:showEconomicGrowth:edit")
	@RequestMapping(value = "save")
	public String save(ShowEconomicGrowth showEconomicGrowth, Model model, RedirectAttributes redirectAttributes) {
		try{
			if (!beanValidator(model, showEconomicGrowth)){
				return form(showEconomicGrowth, model);
			}
			if(showEconomicGrowth.getIsNewRecord()){
				ShowEconomicGrowth condition = new ShowEconomicGrowth();
				condition.setYear(showEconomicGrowth.getYear());
				condition.setMonth(showEconomicGrowth.getMonth());
				List<ShowEconomicGrowth> list = showEconomicGrowthService.findList(condition);
				if(list != null && list.size()>0){
					throw new Exception("数据重复");
				}
			}
			showEconomicGrowthService.save(showEconomicGrowth);
			addMessage(redirectAttributes, "保存经济成长性成功");

		}catch (Exception e){
			addMessage(redirectAttributes, "保存经济成长性失败，原因："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/csyx/showEconomicGrowth/?repage";
	}
	
	@RequiresPermissions("csyx:showEconomicGrowth:edit")
	@RequestMapping(value = "delete")
	public String delete(ShowEconomicGrowth showEconomicGrowth, RedirectAttributes redirectAttributes) {
		showEconomicGrowthService.delete(showEconomicGrowth);
		addMessage(redirectAttributes, "删除经济成长性成功");
		return "redirect:"+Global.getAdminPath()+"/csyx/showEconomicGrowth/?repage";
	}

}