/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.sfmk.web;

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
import cn.xy.tcmap.modules.catchup.sfmk.entity.AlgorithmicRoot;
import cn.xy.tcmap.modules.catchup.sfmk.service.AlgorithmicRootService;

/**
 * 模型管理主表Controller
 * @author wufan
 * @version 2019-08-22
 */
@Controller
@RequestMapping(value = "${adminPath}/sfmk/algorithmicRoot")
public class AlgorithmicRootController extends BaseController {

	@Autowired
	private AlgorithmicRootService algorithmicRootService;
	
	@ModelAttribute
	public AlgorithmicRoot get(@RequestParam(required=false) String id) {
		AlgorithmicRoot entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = algorithmicRootService.get(id);
		}
		if (entity == null){
			entity = new AlgorithmicRoot();
		}
		return entity;
	}
	
	@RequiresPermissions("sfmk:algorithmicRoot:view")
	@RequestMapping(value = {"list", ""})
	public String list(AlgorithmicRoot algorithmicRoot, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<AlgorithmicRoot> page = algorithmicRootService.findPage(new Page<AlgorithmicRoot>(request, response), algorithmicRoot); 
		model.addAttribute("page", page);
		return "catchup/sfmk/algorithmicRootList";
	}

	@RequiresPermissions("sfmk:algorithmicRoot:view")
	@RequestMapping(value = "form")
	public String form(AlgorithmicRoot algorithmicRoot, Model model) {
		model.addAttribute("algorithmicRoot", algorithmicRoot);
		return "catchup/sfmk/algorithmicRootForm";
	}

	@RequiresPermissions("sfmk:algorithmicRoot:edit")
	@RequestMapping(value = "save")
	public String save(AlgorithmicRoot algorithmicRoot, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, algorithmicRoot)){
			return form(algorithmicRoot, model);
		}
		algorithmicRootService.save(algorithmicRoot);
		addMessage(redirectAttributes, "保存模型管理主表成功");
		return "redirect:"+Global.getAdminPath()+"/sfmk/algorithmicRoot/?repage";
	}
	
	@RequiresPermissions("sfmk:algorithmicRoot:edit")
	@RequestMapping(value = "delete")
	public String delete(AlgorithmicRoot algorithmicRoot, RedirectAttributes redirectAttributes) {
		algorithmicRootService.delete(algorithmicRoot);
		addMessage(redirectAttributes, "删除模型管理主表成功");
		return "redirect:"+Global.getAdminPath()+"/sfmk/algorithmicRoot/?repage";
	}

}