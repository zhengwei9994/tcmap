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
import cn.xy.tcmap.modules.catchup.csyx.entity.CatchServiceIndustry;
import cn.xy.tcmap.modules.catchup.csyx.service.CatchServiceIndustryService;

/**
 * 服务业稳重向好Controller
 * @author xuzhou
 * @version 2018-05-24
 */
@Controller
@RequestMapping(value = "${adminPath}/csyx/catchServiceIndustry")
public class CatchServiceIndustryController extends BaseController {

	@Autowired
	private CatchServiceIndustryService catchServiceIndustryService;
	
	@ModelAttribute
	public CatchServiceIndustry get(@RequestParam(required=false) String id) {
		CatchServiceIndustry entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = catchServiceIndustryService.get(id);
		}
		if (entity == null){
			entity = new CatchServiceIndustry();
		}
		return entity;
	}
	
	@RequiresPermissions("csyx:catchServiceIndustry:view")
	@RequestMapping(value = {"list", ""})
	public String list(CatchServiceIndustry catchServiceIndustry, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CatchServiceIndustry> page = catchServiceIndustryService.findPage(new Page<CatchServiceIndustry>(request, response), catchServiceIndustry); 
		model.addAttribute("page", page);
		return "catchup/csyx/catchServiceIndustryList";
	}

	@RequiresPermissions("csyx:catchServiceIndustry:view")
	@RequestMapping(value = "form")
	public String form(CatchServiceIndustry catchServiceIndustry, Model model) {
		model.addAttribute("catchServiceIndustry", catchServiceIndustry);
		return "catchup/csyx/catchServiceIndustryForm";
	}

	@RequiresPermissions("csyx:catchServiceIndustry:edit")
	@RequestMapping(value = "save")
	public String save(CatchServiceIndustry catchServiceIndustry, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, catchServiceIndustry)){
			return form(catchServiceIndustry, model);
		}
		catchServiceIndustryService.save(catchServiceIndustry);
		addMessage(redirectAttributes, "保存服务业稳重向好成功");
		return "redirect:"+Global.getAdminPath()+"/csyx/catchServiceIndustry/?repage";
	}
	
	@RequiresPermissions("csyx:catchServiceIndustry:edit")
	@RequestMapping(value = "delete")
	public String delete(CatchServiceIndustry catchServiceIndustry, RedirectAttributes redirectAttributes) {
		catchServiceIndustryService.delete(catchServiceIndustry);
		addMessage(redirectAttributes, "删除服务业稳重向好成功");
		return "redirect:"+Global.getAdminPath()+"/csyx/catchServiceIndustry/?repage";
	}

}