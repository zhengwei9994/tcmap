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
import cn.xy.tcmap.modules.catchup.fzjc.entity.CatchLaborEmployment;
import cn.xy.tcmap.modules.catchup.fzjc.service.CatchLaborEmploymentService;

/**
 * 劳动就业检索Controller
 * @author gxq
 * @version 2018-10-18
 */
@Controller
@RequestMapping(value = "${adminPath}/fzjc/catchLaborEmployment")
public class CatchLaborEmploymentController extends BaseController {

	@Autowired
	private CatchLaborEmploymentService catchLaborEmploymentService;
	
	@ModelAttribute
	public CatchLaborEmployment get(@RequestParam(required=false) String id) {
		CatchLaborEmployment entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = catchLaborEmploymentService.get(id);
		}
		if (entity == null){
			entity = new CatchLaborEmployment();
		}
		return entity;
	}
	
	@RequiresPermissions("fzjc:catchLaborEmployment:view")
	@RequestMapping(value = {"list", ""})
	public String list(CatchLaborEmployment catchLaborEmployment, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CatchLaborEmployment> page = catchLaborEmploymentService.findPage(new Page<CatchLaborEmployment>(request, response), catchLaborEmployment); 
		model.addAttribute("page", page);
		return "catchup/fzjc/catchLaborEmploymentList";
	}

	@RequiresPermissions("fzjc:catchLaborEmployment:view")
	@RequestMapping(value = "form")
	public String form(CatchLaborEmployment catchLaborEmployment, Model model) {
		model.addAttribute("catchLaborEmployment", catchLaborEmployment);
		return "catchup/fzjc/catchLaborEmploymentForm";
	}

	@RequiresPermissions("fzjc:catchLaborEmployment:edit")
	@RequestMapping(value = "save")
	public String save(CatchLaborEmployment catchLaborEmployment, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, catchLaborEmployment)){
			return form(catchLaborEmployment, model);
		}
		catchLaborEmploymentService.save(catchLaborEmployment);
		addMessage(redirectAttributes, "保存劳动就业检索成功");
		return "redirect:"+Global.getAdminPath()+"/fzjc/catchLaborEmployment/?repage";
	}
	
	@RequiresPermissions("fzjc:catchLaborEmployment:edit")
	@RequestMapping(value = "delete")
	public String delete(CatchLaborEmployment catchLaborEmployment, RedirectAttributes redirectAttributes) {
		catchLaborEmploymentService.delete(catchLaborEmployment);
		addMessage(redirectAttributes, "删除劳动就业检索成功");
		return "redirect:"+Global.getAdminPath()+"/fzjc/catchLaborEmployment/?repage";
	}

}