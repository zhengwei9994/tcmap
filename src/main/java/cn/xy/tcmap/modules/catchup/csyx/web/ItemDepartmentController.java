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
import cn.xy.tcmap.modules.catchup.csyx.entity.ItemDepartment;
import cn.xy.tcmap.modules.catchup.csyx.service.ItemDepartmentService;

/**
 * 项目部委问题Controller
 * @author wufan
 * @version 2019-07-31
 */
@Controller
@RequestMapping(value = "${adminPath}/csyx/itemDepartment")
public class ItemDepartmentController extends BaseController {

	@Autowired
	private ItemDepartmentService itemDepartmentService;
	
	@ModelAttribute
	public ItemDepartment get(@RequestParam(required=false) String id) {
		ItemDepartment entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = itemDepartmentService.get(id);
		}
		if (entity == null){
			entity = new ItemDepartment();
		}
		return entity;
	}
	
	@RequiresPermissions("csyx:itemDepartment:view")
	@RequestMapping(value = {"list", ""})
	public String list(ItemDepartment itemDepartment, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ItemDepartment> page = itemDepartmentService.findPage(new Page<ItemDepartment>(request, response), itemDepartment); 
		model.addAttribute("page", page);
		return "catchup/csyx/itemDepartmentList";
	}

	@RequiresPermissions("csyx:itemDepartment:view")
	@RequestMapping(value = "form")
	public String form(ItemDepartment itemDepartment, Model model) {
		model.addAttribute("itemDepartment", itemDepartment);
		return "catchup/csyx/itemDepartmentForm";
	}

	@RequiresPermissions("csyx:itemDepartment:edit")
	@RequestMapping(value = "save")
	public String save(ItemDepartment itemDepartment, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, itemDepartment)){
			return form(itemDepartment, model);
		}
		itemDepartmentService.save(itemDepartment);
		addMessage(redirectAttributes, "保存项目部委问题成功");
		return "redirect:"+Global.getAdminPath()+"/csyx/itemDepartment/?repage";
	}
	
	@RequiresPermissions("csyx:itemDepartment:edit")
	@RequestMapping(value = "delete")
	public String delete(ItemDepartment itemDepartment, RedirectAttributes redirectAttributes) {
		itemDepartmentService.delete(itemDepartment);
		addMessage(redirectAttributes, "删除项目部委问题成功");
		return "redirect:"+Global.getAdminPath()+"/csyx/itemDepartment/?repage";
	}

}