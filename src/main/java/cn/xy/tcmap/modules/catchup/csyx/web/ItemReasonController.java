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
import cn.xy.tcmap.modules.catchup.csyx.entity.ItemReason;
import cn.xy.tcmap.modules.catchup.csyx.service.ItemReasonService;

/**
 * 项目问题原因Controller
 * @author wufan
 * @version 2019-07-31
 */
@Controller
@RequestMapping(value = "${adminPath}/csyx/itemReason")
public class ItemReasonController extends BaseController {

	@Autowired
	private ItemReasonService itemReasonService;
	
	@ModelAttribute
	public ItemReason get(@RequestParam(required=false) String id) {
		ItemReason entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = itemReasonService.get(id);
		}
		if (entity == null){
			entity = new ItemReason();
		}
		return entity;
	}
	
	@RequiresPermissions("csyx:itemReason:view")
	@RequestMapping(value = {"list", ""})
	public String list(ItemReason itemReason, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ItemReason> page = itemReasonService.findPage(new Page<ItemReason>(request, response), itemReason); 
		model.addAttribute("page", page);
		return "catchup/csyx/itemReasonList";
	}

	@RequiresPermissions("csyx:itemReason:view")
	@RequestMapping(value = "form")
	public String form(ItemReason itemReason, Model model) {
		model.addAttribute("itemReason", itemReason);
		return "catchup/csyx/itemReasonForm";
	}

	@RequiresPermissions("csyx:itemReason:edit")
	@RequestMapping(value = "save")
	public String save(ItemReason itemReason, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, itemReason)){
			return form(itemReason, model);
		}
		itemReasonService.save(itemReason);
		addMessage(redirectAttributes, "保存项目问题原因成功");
		return "redirect:"+Global.getAdminPath()+"/csyx/itemReason/?repage";
	}
	
	@RequiresPermissions("csyx:itemReason:edit")
	@RequestMapping(value = "delete")
	public String delete(ItemReason itemReason, RedirectAttributes redirectAttributes) {
		itemReasonService.delete(itemReason);
		addMessage(redirectAttributes, "删除项目问题原因成功");
		return "redirect:"+Global.getAdminPath()+"/csyx/itemReason/?repage";
	}

}