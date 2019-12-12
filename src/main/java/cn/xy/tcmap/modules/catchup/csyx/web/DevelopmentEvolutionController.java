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
import cn.xy.tcmap.modules.catchup.csyx.entity.DevelopmentEvolution;
import cn.xy.tcmap.modules.catchup.csyx.service.DevelopmentEvolutionService;

import java.util.List;

/**
 * 产业演进Controller
 * @author wufan
 * @version 2019-07-31
 */
@Controller
@RequestMapping(value = "${adminPath}/csyx/developmentEvolution")
public class DevelopmentEvolutionController extends BaseController {

	@Autowired
	private DevelopmentEvolutionService developmentEvolutionService;
	
	@ModelAttribute
	public DevelopmentEvolution get(@RequestParam(required=false) String id) {
		DevelopmentEvolution entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = developmentEvolutionService.get(id);
		}
		if (entity == null){
			entity = new DevelopmentEvolution();
		}
		return entity;
	}
	
	@RequiresPermissions("csyx:developmentEvolution:view")
	@RequestMapping(value = {"list", ""})
	public String list(DevelopmentEvolution developmentEvolution, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<DevelopmentEvolution> page = developmentEvolutionService.findPage(new Page<DevelopmentEvolution>(request, response), developmentEvolution); 
		model.addAttribute("page", page);
		return "catchup/csyx/developmentEvolutionList";
	}

	@RequiresPermissions("csyx:developmentEvolution:view")
	@RequestMapping(value = "form")
	public String form(DevelopmentEvolution developmentEvolution, Model model) {
		model.addAttribute("developmentEvolution", developmentEvolution);
		return "catchup/csyx/developmentEvolutionForm";
	}

	@RequiresPermissions("csyx:developmentEvolution:edit")
	@RequestMapping(value = "save")
	public String save(DevelopmentEvolution developmentEvolution, Model model, RedirectAttributes redirectAttributes) {
		try {
			if (!beanValidator(model, developmentEvolution)){
				return form(developmentEvolution, model);
			}
			if(developmentEvolution.getIsNewRecord()){
				DevelopmentEvolution de = new DevelopmentEvolution();
				de.setIndustry(developmentEvolution.getIndustry());
				de.setYear(developmentEvolution.getYear());
				List<DevelopmentEvolution> list = developmentEvolutionService.findList(de);
				if(list != null && list.size()>0){
					throw new Exception("数据重复");
				}
			}
			developmentEvolutionService.save(developmentEvolution);
			addMessage(redirectAttributes, "保存产业演进成功");
		}catch (Exception e){
			addMessage(redirectAttributes, "保存产业演进失败，失败原因："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/csyx/developmentEvolution/?repage";
	}
	
	@RequiresPermissions("csyx:developmentEvolution:edit")
	@RequestMapping(value = "delete")
	public String delete(DevelopmentEvolution developmentEvolution, RedirectAttributes redirectAttributes) {
		developmentEvolutionService.delete(developmentEvolution);
		addMessage(redirectAttributes, "删除产业演进成功");
		return "redirect:"+Global.getAdminPath()+"/csyx/developmentEvolution/?repage";
	}

}