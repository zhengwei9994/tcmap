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
import cn.xy.tcmap.modules.catchup.csyx.entity.CatchSpecialProject;
import cn.xy.tcmap.modules.catchup.csyx.service.CatchSpecialProjectService;

/**
 * 特色项目Controller
 * @author xuzhou
 * @version 2018-05-24
 */
@Controller
@RequestMapping(value = "${adminPath}/csyx/catchSpecialProject")
public class CatchSpecialProjectController extends BaseController {

	@Autowired
	private CatchSpecialProjectService catchSpecialProjectService;
	
	@ModelAttribute
	public CatchSpecialProject get(@RequestParam(required=false) String id) {
		CatchSpecialProject entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = catchSpecialProjectService.get(id);
		}
		if (entity == null){
			entity = new CatchSpecialProject();
		}
		return entity;
	}
	
	@RequiresPermissions("csyx:catchSpecialProject:view")
	@RequestMapping(value = {"list", ""})
	public String list(CatchSpecialProject catchSpecialProject, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CatchSpecialProject> page = catchSpecialProjectService.findPage(new Page<CatchSpecialProject>(request, response), catchSpecialProject); 
		model.addAttribute("page", page);
		return "catchup/csyx/catchSpecialProjectList";
	}

	@RequiresPermissions("csyx:catchSpecialProject:view")
	@RequestMapping(value = "form")
	public String form(CatchSpecialProject catchSpecialProject, Model model) {
		model.addAttribute("catchSpecialProject", catchSpecialProject);
		return "catchup/csyx/catchSpecialProjectForm";
	}

	@RequiresPermissions("csyx:catchSpecialProject:edit")
	@RequestMapping(value = "save")
	public String save(CatchSpecialProject catchSpecialProject, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, catchSpecialProject)){
			return form(catchSpecialProject, model);
		}
		catchSpecialProjectService.save(catchSpecialProject);
		addMessage(redirectAttributes, "保存特色项目成功");
		return "redirect:"+Global.getAdminPath()+"/csyx/catchSpecialProject/?repage";
	}
	
	@RequiresPermissions("csyx:catchSpecialProject:edit")
	@RequestMapping(value = "delete")
	public String delete(CatchSpecialProject catchSpecialProject, RedirectAttributes redirectAttributes) {
		catchSpecialProjectService.delete(catchSpecialProject);
		addMessage(redirectAttributes, "删除特色项目成功");
		return "redirect:"+Global.getAdminPath()+"/csyx/catchSpecialProject/?repage";
	}

}