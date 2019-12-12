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
import cn.xy.tcmap.modules.catchup.csyx.entity.ItemProportion;
import cn.xy.tcmap.modules.catchup.csyx.service.ItemProportionService;

/**
 * 项目完成投资占比Controller
 * @author wufan
 * @version 2019-07-31
 */
@Controller
@RequestMapping(value = "${adminPath}/csyx/itemProportion")
public class ItemProportionController extends BaseController {

	@Autowired
	private ItemProportionService itemProportionService;
	
	@ModelAttribute
	public ItemProportion get(@RequestParam(required=false) String id) {
		ItemProportion entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = itemProportionService.get(id);
		}
		if (entity == null){
			entity = new ItemProportion();
		}
		return entity;
	}
	
	@RequiresPermissions("csyx:itemProportion:view")
	@RequestMapping(value = {"list", ""})
	public String list(ItemProportion itemProportion, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ItemProportion> page = itemProportionService.findPage(new Page<ItemProportion>(request, response), itemProportion); 
		model.addAttribute("page", page);
		return "catchup/csyx/itemProportionList";
	}

	@RequiresPermissions("csyx:itemProportion:view")
	@RequestMapping(value = "form")
	public String form(ItemProportion itemProportion, Model model) {
		model.addAttribute("itemProportion", itemProportion);
		return "catchup/csyx/itemProportionForm";
	}

	@RequiresPermissions("csyx:itemProportion:edit")
	@RequestMapping(value = "save")
	public String save(ItemProportion itemProportion, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, itemProportion)){
			return form(itemProportion, model);
		}
		itemProportionService.save(itemProportion);
		addMessage(redirectAttributes, "保存项目完成投资占比成功");
		return "redirect:"+Global.getAdminPath()+"/csyx/itemProportion/?repage";
	}
	
	@RequiresPermissions("csyx:itemProportion:edit")
	@RequestMapping(value = "delete")
	public String delete(ItemProportion itemProportion, RedirectAttributes redirectAttributes) {
		itemProportionService.delete(itemProportion);
		addMessage(redirectAttributes, "删除项目完成投资占比成功");
		return "redirect:"+Global.getAdminPath()+"/csyx/itemProportion/?repage";
	}

}