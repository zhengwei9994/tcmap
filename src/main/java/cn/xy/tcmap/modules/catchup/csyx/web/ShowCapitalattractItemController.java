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
import cn.xy.tcmap.modules.catchup.csyx.entity.ShowCapitalattractItem;
import cn.xy.tcmap.modules.catchup.csyx.service.ShowCapitalattractItemService;

import java.util.List;

/**
 * 经济财政支出Controller
 * @author wufan
 * @version 2019-07-31
 */
@Controller
@RequestMapping(value = "${adminPath}/csyx/showCapitalattractItem")
public class ShowCapitalattractItemController extends BaseController {

	@Autowired
	private ShowCapitalattractItemService showCapitalattractItemService;
	
	@ModelAttribute
	public ShowCapitalattractItem get(@RequestParam(required=false) String id) {
		ShowCapitalattractItem entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = showCapitalattractItemService.get(id);
		}
		if (entity == null){
			entity = new ShowCapitalattractItem();
		}
		return entity;
	}
	
	@RequiresPermissions("csyx:showCapitalattractItem:view")
	@RequestMapping(value = {"list", ""})
	public String list(ShowCapitalattractItem showCapitalattractItem, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ShowCapitalattractItem> page = showCapitalattractItemService.findPage(new Page<ShowCapitalattractItem>(request, response), showCapitalattractItem); 
		model.addAttribute("page", page);
		return "catchup/csyx/showCapitalattractItemList";
	}

	@RequiresPermissions("csyx:showCapitalattractItem:view")
	@RequestMapping(value = "form")
	public String form(ShowCapitalattractItem showCapitalattractItem, Model model) {
		model.addAttribute("showCapitalattractItem", showCapitalattractItem);
		return "catchup/csyx/showCapitalattractItemForm";
	}

	@RequiresPermissions("csyx:showCapitalattractItem:edit")
	@RequestMapping(value = "save")
	public String save(ShowCapitalattractItem showCapitalattractItem, Model model, RedirectAttributes redirectAttributes) {
		try{
			if (!beanValidator(model, showCapitalattractItem)){
				return form(showCapitalattractItem, model);
			}
			if(showCapitalattractItem.getIsNewRecord()){
				ShowCapitalattractItem condition = new ShowCapitalattractItem();
				condition.setItem(showCapitalattractItem.getItem());
				condition.setYear(showCapitalattractItem.getYear());
				List<ShowCapitalattractItem> list = showCapitalattractItemService.findList(condition);
				if(list != null && list.size()>0){
					throw new Exception("数据重复");
				}
			}
			showCapitalattractItemService.save(showCapitalattractItem);
			addMessage(redirectAttributes, "保存经济财政支出成功");
		}catch (Exception e){
			addMessage(redirectAttributes, "保存经济财政支出失败，原因："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/csyx/showCapitalattractItem/?repage";
	}
	
	@RequiresPermissions("csyx:showCapitalattractItem:edit")
	@RequestMapping(value = "delete")
	public String delete(ShowCapitalattractItem showCapitalattractItem, RedirectAttributes redirectAttributes) {
		showCapitalattractItemService.delete(showCapitalattractItem);
		addMessage(redirectAttributes, "删除经济财政支出成功");
		return "redirect:"+Global.getAdminPath()+"/csyx/showCapitalattractItem/?repage";
	}

}