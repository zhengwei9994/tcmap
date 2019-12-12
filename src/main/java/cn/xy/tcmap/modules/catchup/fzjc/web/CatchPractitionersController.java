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
import cn.xy.tcmap.modules.catchup.fzjc.entity.CatchPractitioners;
import cn.xy.tcmap.modules.catchup.fzjc.service.CatchPractitionersService;

/**
 * 劳动就业占比Controller
 * @author gxq
 * @version 2018-10-18
 */
@Controller
@RequestMapping(value = "${adminPath}/fzjc/catchPractitioners")
public class CatchPractitionersController extends BaseController {

	@Autowired
	private CatchPractitionersService catchPractitionersService;
	
	@ModelAttribute
	public CatchPractitioners get(@RequestParam(required=false) String id) {
		CatchPractitioners entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = catchPractitionersService.get(id);
		}
		if (entity == null){
			entity = new CatchPractitioners();
		}
		return entity;
	}
	
	@RequiresPermissions("fzjc:catchPractitioners:view")
	@RequestMapping(value = {"list", ""})
	public String list(CatchPractitioners catchPractitioners, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CatchPractitioners> page = catchPractitionersService.findPage(new Page<CatchPractitioners>(request, response), catchPractitioners); 
		model.addAttribute("page", page);
		return "catchup/fzjc/catchPractitionersList";
	}

	@RequiresPermissions("fzjc:catchPractitioners:view")
	@RequestMapping(value = "form")
	public String form(CatchPractitioners catchPractitioners, Model model) {
		model.addAttribute("catchPractitioners", catchPractitioners);
		return "catchup/fzjc/catchPractitionersForm";
	}

	@RequiresPermissions("fzjc:catchPractitioners:edit")
	@RequestMapping(value = "save")
	public String save(CatchPractitioners catchPractitioners, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, catchPractitioners)){
			return form(catchPractitioners, model);
		}
		catchPractitionersService.save(catchPractitioners);
		addMessage(redirectAttributes, "保存劳动就业占比成功");
		return "redirect:"+Global.getAdminPath()+"/fzjc/catchPractitioners/?repage";
	}
	
	@RequiresPermissions("fzjc:catchPractitioners:edit")
	@RequestMapping(value = "delete")
	public String delete(CatchPractitioners catchPractitioners, RedirectAttributes redirectAttributes) {
		catchPractitionersService.delete(catchPractitioners);
		addMessage(redirectAttributes, "删除劳动就业占比成功");
		return "redirect:"+Global.getAdminPath()+"/fzjc/catchPractitioners/?repage";
	}

}