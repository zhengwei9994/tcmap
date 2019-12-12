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
import cn.xy.tcmap.modules.catchup.csyx.entity.DevelopmentSituation;
import cn.xy.tcmap.modules.catchup.csyx.service.DevelopmentSituationService;

import java.util.List;

/**
 * 产业现状Controller
 * @author wufan
 * @version 2019-07-31
 */
@Controller
@RequestMapping(value = "${adminPath}/csyx/developmentSituation")
public class DevelopmentSituationController extends BaseController {

	@Autowired
	private DevelopmentSituationService developmentSituationService;
	
	@ModelAttribute
	public DevelopmentSituation get(@RequestParam(required=false) String id) {
		DevelopmentSituation entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = developmentSituationService.get(id);
		}
		if (entity == null){
			entity = new DevelopmentSituation();
		}
		return entity;
	}
	
	@RequiresPermissions("csyx:developmentSituation:view")
	@RequestMapping(value = {"list", ""})
	public String list(DevelopmentSituation developmentSituation, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<DevelopmentSituation> page = developmentSituationService.findPage(new Page<DevelopmentSituation>(request, response), developmentSituation); 
		model.addAttribute("page", page);
		return "catchup/csyx/developmentSituationList";
	}

	@RequiresPermissions("csyx:developmentSituation:view")
	@RequestMapping(value = "form")
	public String form(DevelopmentSituation developmentSituation, Model model) {
		model.addAttribute("developmentSituation", developmentSituation);
		return "catchup/csyx/developmentSituationForm";
	}

	@RequiresPermissions("csyx:developmentSituation:edit")
	@RequestMapping(value = "save")
	public String save(DevelopmentSituation developmentSituation, Model model, RedirectAttributes redirectAttributes) {
		try{
			if (!beanValidator(model, developmentSituation)){
				return form(developmentSituation, model);
			}
			if(developmentSituation.getIsNewRecord()){
				DevelopmentSituation ds = new DevelopmentSituation();
				ds.setYear(developmentSituation.getYear());
				ds.setMonth(developmentSituation.getMonth());
				ds.setIndustry(developmentSituation.getIndustry());
				List<DevelopmentSituation> list = developmentSituationService.findList(ds);
				if(list != null && list.size()>0)
					throw new Exception("数据重复");
			}
			developmentSituationService.save(developmentSituation);
			addMessage(redirectAttributes, "保存产业现状成功");
		}catch (Exception e){
			addMessage(redirectAttributes, "保存产业现状成功失败，失败原因："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/csyx/developmentSituation/?repage";
	}
	
	@RequiresPermissions("csyx:developmentSituation:edit")
	@RequestMapping(value = "delete")
	public String delete(DevelopmentSituation developmentSituation, RedirectAttributes redirectAttributes) {
		developmentSituationService.delete(developmentSituation);
		addMessage(redirectAttributes, "删除产业现状成功");
		return "redirect:"+Global.getAdminPath()+"/csyx/developmentSituation/?repage";
	}

}