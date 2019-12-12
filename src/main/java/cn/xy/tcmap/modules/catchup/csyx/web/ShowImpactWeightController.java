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
import cn.xy.tcmap.modules.catchup.csyx.entity.ShowImpactWeight;
import cn.xy.tcmap.modules.catchup.csyx.service.ShowImpactWeightService;

import java.util.List;

/**
 * 影响权重Controller
 * @author tuo
 * @version 2019-08-22
 */
@Controller
@RequestMapping(value = "${adminPath}/csyx/showImpactWeight")
public class ShowImpactWeightController extends BaseController {

	@Autowired
	private ShowImpactWeightService showImpactWeightService;
	
	@ModelAttribute
	public ShowImpactWeight get(@RequestParam(required=false) String id) {
		ShowImpactWeight entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = showImpactWeightService.get(id);
		}
		if (entity == null){
			entity = new ShowImpactWeight();
		}
		return entity;
	}
	
	@RequiresPermissions("csyx:showImpactWeight:view")
	@RequestMapping(value = {"list", ""})
	public String list(ShowImpactWeight showImpactWeight, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ShowImpactWeight> page = showImpactWeightService.findPage(new Page<ShowImpactWeight>(request, response), showImpactWeight); 
		model.addAttribute("page", page);
		return "catchup/csyx/showImpactWeightList";
	}

	@RequiresPermissions("csyx:showImpactWeight:view")
	@RequestMapping(value = "form")
	public String form(ShowImpactWeight showImpactWeight, Model model) {
		model.addAttribute("showImpactWeight", showImpactWeight);
		return "catchup/csyx/showImpactWeightForm";
	}

	@RequiresPermissions("csyx:showImpactWeight:edit")
	@RequestMapping(value = "save")
	public String save(ShowImpactWeight showImpactWeight, Model model, RedirectAttributes redirectAttributes) {
		try{
			if (!beanValidator(model, showImpactWeight)){
				return form(showImpactWeight, model);
			}
			if(showImpactWeight.getIsNewRecord()){
				ShowImpactWeight condition = new ShowImpactWeight();
				condition.setIndustry(showImpactWeight.getIndustry());
				List<ShowImpactWeight> list = showImpactWeightService.findList(condition);
				if(list != null && list.size()>0){
					addMessage(redirectAttributes,"保存影响权重失败,数据重复添加");
					return "redirect:"+Global.getAdminPath()+"/csyx/showImpactWeight/?repage";
				}
			}
			showImpactWeightService.save(showImpactWeight);
			addMessage(redirectAttributes, "保存影响权重成功");
		}catch (Exception e){
			addMessage(redirectAttributes, "保存影响权重失败");
		}
		return "redirect:"+Global.getAdminPath()+"/csyx/showImpactWeight/?repage";
	}
	
	@RequiresPermissions("csyx:showImpactWeight:edit")
	@RequestMapping(value = "delete")
	public String delete(ShowImpactWeight showImpactWeight, RedirectAttributes redirectAttributes) {
		showImpactWeightService.delete(showImpactWeight);
		addMessage(redirectAttributes, "删除影响权重成功");
		return "redirect:"+Global.getAdminPath()+"/csyx/showImpactWeight/?repage";
	}

}