/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csyx.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import cn.xy.tcmap.common.utils.StringUtils;
import cn.xy.tcmap.common.config.Global;
import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.web.BaseController;
import cn.xy.tcmap.modules.catchup.csyx.entity.CatchKeyproject;
import cn.xy.tcmap.modules.catchup.csyx.service.CatchKeyprojectService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


/**
 * 重点项目Controller
 * @author xuzhou
 * @version 2018-05-02
 */
@Controller
@RequestMapping(value = "${adminPath}/csyx/catchKeyproject")
public class CatchKeyprojectController extends BaseController {

	@Autowired
	private CatchKeyprojectService catchKeyprojectService;
	
	@ModelAttribute
	public CatchKeyproject get(@RequestParam(required=false) String id) {
		CatchKeyproject entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = catchKeyprojectService.get(id);
		}
		if (entity == null){
			entity = new CatchKeyproject();
		}
		return entity;
	}
	
	@RequiresPermissions("csyx:catchKeyproject:view")
	@RequestMapping(value = {"list", ""})
	public String list(CatchKeyproject catchKeyproject, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CatchKeyproject> page = catchKeyprojectService.findPage(new Page<CatchKeyproject>(request, response), catchKeyproject);
		model.addAttribute("page", page);
		return "catchup/csyx/catchKeyprojectList";
	}

	@RequiresPermissions("csyx:catchKeyproject:view")
	@RequestMapping(value = "form")
	public String form(CatchKeyproject catchKeyproject, Model model) {
		model.addAttribute("catchKeyproject", catchKeyproject);
		return "catchup/csyx/catchKeyprojectForm";
	}

	@RequiresPermissions("csyx:catchKeyproject:edit")
	@RequestMapping(value = "save")
	public String save(CatchKeyproject catchKeyproject, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, catchKeyproject)){
			return form(catchKeyproject, model);
		}
		catchKeyprojectService.save(catchKeyproject);
		addMessage(redirectAttributes, "保存重点项目成功");
		return "redirect:"+ Global.getAdminPath()+"/csyx/catchKeyproject/?repage";
	}
	
	@RequiresPermissions("csyx:catchKeyproject:edit")
	@RequestMapping(value = "delete")
	public String delete(CatchKeyproject catchKeyproject, RedirectAttributes redirectAttributes) {
		catchKeyprojectService.delete(catchKeyproject);
		addMessage(redirectAttributes, "删除重点项目成功");
		return "redirect:"+Global.getAdminPath()+"/csyx/catchKeyproject/?repage";
	}

}