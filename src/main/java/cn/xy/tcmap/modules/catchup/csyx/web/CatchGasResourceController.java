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
import cn.xy.tcmap.modules.catchup.csyx.entity.CatchGasResource;
import cn.xy.tcmap.modules.catchup.csyx.service.CatchGasResourceService;

/**
 * 废气排放指标来源构成分析Controller
 * @author guoxunquan
 * @version 2018-09-20
 */
@Controller
@RequestMapping(value = "${adminPath}/csyx/catchGasResource")
public class CatchGasResourceController extends BaseController {

	@Autowired
	private CatchGasResourceService catchGasResourceService;
	
	@ModelAttribute
	public CatchGasResource get(@RequestParam(required=false) String id) {
		CatchGasResource entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = catchGasResourceService.get(id);
		}
		if (entity == null){
			entity = new CatchGasResource();
		}
		return entity;
	}
	
	@RequiresPermissions("csyx:catchGasResource:view")
	@RequestMapping(value = {"list", ""})
	public String list(CatchGasResource catchGasResource, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CatchGasResource> page = catchGasResourceService.findPage(new Page<CatchGasResource>(request, response), catchGasResource); 
		model.addAttribute("page", page);
		return "catchup/csyx/catchGasResourceList";
	}

	@RequiresPermissions("csyx:catchGasResource:view")
	@RequestMapping(value = "form")
	public String form(CatchGasResource catchGasResource, Model model) {
		model.addAttribute("catchGasResource", catchGasResource);
		return "catchup/csyx/catchGasResourceForm";
	}

	@RequiresPermissions("csyx:catchGasResource:edit")
	@RequestMapping(value = "save")
	public String save(CatchGasResource catchGasResource, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, catchGasResource)){
			return form(catchGasResource, model);
		}
		catchGasResourceService.save(catchGasResource);
		addMessage(redirectAttributes, "保存废气排放指标成功");
		return "redirect:"+Global.getAdminPath()+"/csyx/catchGasResource/?repage";
	}
	
	@RequiresPermissions("csyx:catchGasResource:edit")
	@RequestMapping(value = "delete")
	public String delete(CatchGasResource catchGasResource, RedirectAttributes redirectAttributes) {
		catchGasResourceService.delete(catchGasResource);
		addMessage(redirectAttributes, "删除废气排放指标成功");
		return "redirect:"+Global.getAdminPath()+"/csyx/catchGasResource/?repage";
	}

}