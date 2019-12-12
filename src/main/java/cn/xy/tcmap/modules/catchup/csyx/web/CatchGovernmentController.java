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
import cn.xy.tcmap.modules.catchup.csyx.entity.CatchGovernment;
import cn.xy.tcmap.modules.catchup.csyx.service.CatchGovernmentService;

/**
 * 社会治理Controller
 * @author xuzhou
 * @version 2018-05-24
 */
@Controller
@RequestMapping(value = "${adminPath}/csyx/catchGovernment")
public class CatchGovernmentController extends BaseController {

	@Autowired
	private CatchGovernmentService catchGovernmentService;
	
	@ModelAttribute
	public CatchGovernment get(@RequestParam(required=false) String id) {
		CatchGovernment entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = catchGovernmentService.get(id);
		}
		if (entity == null){
			entity = new CatchGovernment();
		}
		return entity;
	}
	
	@RequiresPermissions("csyx:catchGovernment:view")
	@RequestMapping(value = {"list", ""})
	public String list(CatchGovernment catchGovernment, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CatchGovernment> page = catchGovernmentService.findPage(new Page<CatchGovernment>(request, response), catchGovernment); 
		model.addAttribute("page", page);
		return "catchup/csyx/catchGovernmentList";
	}

	@RequiresPermissions("csyx:catchGovernment:view")
	@RequestMapping(value = "form")
	public String form(CatchGovernment catchGovernment, Model model) {
		model.addAttribute("catchGovernment", catchGovernment);
		return "catchup/csyx/catchGovernmentForm";
	}

	@RequiresPermissions("csyx:catchGovernment:edit")
	@RequestMapping(value = "save")
	public String save(CatchGovernment catchGovernment, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, catchGovernment)){
			return form(catchGovernment, model);
		}
		catchGovernmentService.save(catchGovernment);
		addMessage(redirectAttributes, "保存社会治理成功");
		return "redirect:"+Global.getAdminPath()+"/csyx/catchGovernment/?repage";
	}
	
	@RequiresPermissions("csyx:catchGovernment:edit")
	@RequestMapping(value = "delete")
	public String delete(CatchGovernment catchGovernment, RedirectAttributes redirectAttributes) {
		catchGovernmentService.delete(catchGovernment);
		addMessage(redirectAttributes, "删除社会治理成功");
		return "redirect:"+Global.getAdminPath()+"/csyx/catchGovernment/?repage";
	}

}