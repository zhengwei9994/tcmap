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
import cn.xy.tcmap.modules.catchup.csyx.entity.ShowTotalPopulation;
import cn.xy.tcmap.modules.catchup.csyx.service.ShowTotalPopulationService;

import java.util.List;

/**
 * 人口总量Controller
 * @author tuo
 * @version 2019-08-22
 */
@Controller
@RequestMapping(value = "${adminPath}/csyx/showTotalPopulation")
public class ShowTotalPopulationController extends BaseController {

	@Autowired
	private ShowTotalPopulationService showTotalPopulationService;
	
	@ModelAttribute
	public ShowTotalPopulation get(@RequestParam(required=false) String id) {
		ShowTotalPopulation entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = showTotalPopulationService.get(id);
		}
		if (entity == null){
			entity = new ShowTotalPopulation();
		}
		return entity;
	}
	
	@RequiresPermissions("csyx:showTotalPopulation:view")
	@RequestMapping(value = {"list", ""})
	public String list(ShowTotalPopulation showTotalPopulation, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ShowTotalPopulation> page = showTotalPopulationService.findPage(new Page<ShowTotalPopulation>(request, response), showTotalPopulation); 
		model.addAttribute("page", page);
		return "catchup/csyx/showTotalPopulationList";
	}

	@RequiresPermissions("csyx:showTotalPopulation:view")
	@RequestMapping(value = "form")
	public String form(ShowTotalPopulation showTotalPopulation, Model model) {
		model.addAttribute("showTotalPopulation", showTotalPopulation);
		return "catchup/csyx/showTotalPopulationForm";
	}

	@RequiresPermissions("csyx:showTotalPopulation:edit")
	@RequestMapping(value = "save")
	public String save(ShowTotalPopulation showTotalPopulation, Model model, RedirectAttributes redirectAttributes) {
		try{
			if (!beanValidator(model, showTotalPopulation)){
				return form(showTotalPopulation, model);
			}
			if(showTotalPopulation.getIsNewRecord()){
				ShowTotalPopulation condition = new ShowTotalPopulation();
				condition.setYear(showTotalPopulation.getYear());
				List<ShowTotalPopulation> list =showTotalPopulationService.findList(condition);
				if(list != null && list.size()>0){
					throw new Exception("数据重复添加！");
				}
			}
			showTotalPopulationService.save(showTotalPopulation);
			addMessage(redirectAttributes, "保存人口总量成功");
		}catch (Exception e){
			addMessage(redirectAttributes, "保存人口总量失败:"+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/csyx/showTotalPopulation/?repage";
	}
	
	@RequiresPermissions("csyx:showTotalPopulation:edit")
	@RequestMapping(value = "delete")
	public String delete(ShowTotalPopulation showTotalPopulation, RedirectAttributes redirectAttributes) {
		showTotalPopulationService.delete(showTotalPopulation);
		addMessage(redirectAttributes, "删除人口总量成功");
		return "redirect:"+Global.getAdminPath()+"/csyx/showTotalPopulation/?repage";
	}

}