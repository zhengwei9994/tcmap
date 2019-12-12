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
import cn.xy.tcmap.modules.catchup.fzjc.entity.CatchElectronicCategory;
import cn.xy.tcmap.modules.catchup.fzjc.service.CatchElectronicCategoryService;

/**
 * 电子证照类别管理Controller
 * @author gxq
 * @version 2018-10-19
 */
@Controller
@RequestMapping(value = "${adminPath}/fzjc/catchElectronicCategory")
public class CatchElectronicCategoryController extends BaseController {

	@Autowired
	private CatchElectronicCategoryService catchElectronicCategoryService;
	
	@ModelAttribute
	public CatchElectronicCategory get(@RequestParam(required=false) String id) {
		CatchElectronicCategory entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = catchElectronicCategoryService.get(id);
		}
		if (entity == null){
			entity = new CatchElectronicCategory();
		}
		return entity;
	}
	
	@RequiresPermissions("fzjc:catchElectronicCategory:view")
	@RequestMapping(value = {"list", ""})
	public String list(CatchElectronicCategory catchElectronicCategory, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CatchElectronicCategory> page = catchElectronicCategoryService.findPage(new Page<CatchElectronicCategory>(request, response), catchElectronicCategory); 
		model.addAttribute("page", page);
		return "catchup/fzjc/catchElectronicCategoryList";
	}

	@RequiresPermissions("fzjc:catchElectronicCategory:view")
	@RequestMapping(value = "form")
	public String form(CatchElectronicCategory catchElectronicCategory, Model model) {
		model.addAttribute("catchElectronicCategory", catchElectronicCategory);
		return "catchup/fzjc/catchElectronicCategoryForm";
	}

	@RequiresPermissions("fzjc:catchElectronicCategory:edit")
	@RequestMapping(value = "save")
	public String save(CatchElectronicCategory catchElectronicCategory, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, catchElectronicCategory)){
			return form(catchElectronicCategory, model);
		}
		catchElectronicCategoryService.save(catchElectronicCategory);
		addMessage(redirectAttributes, "保存电子证照类别管理成功");
		return "redirect:"+Global.getAdminPath()+"/fzjc/catchElectronicCategory/?repage";
	}
	
	@RequiresPermissions("fzjc:catchElectronicCategory:edit")
	@RequestMapping(value = "delete")
	public String delete(CatchElectronicCategory catchElectronicCategory, RedirectAttributes redirectAttributes) {
		catchElectronicCategoryService.delete(catchElectronicCategory);
		addMessage(redirectAttributes, "删除电子证照类别管理成功");
		return "redirect:"+Global.getAdminPath()+"/fzjc/catchElectronicCategory/?repage";
	}

}