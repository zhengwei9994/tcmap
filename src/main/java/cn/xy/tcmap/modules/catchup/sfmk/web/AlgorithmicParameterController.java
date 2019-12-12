/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.sfmk.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.xy.tcmap.modules.catchup.sfmk.entity.AlgorithmicRoot;
import cn.xy.tcmap.modules.catchup.sfmk.service.AlgorithmicRootService;
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
import cn.xy.tcmap.modules.catchup.sfmk.entity.AlgorithmicParameter;
import cn.xy.tcmap.modules.catchup.sfmk.service.AlgorithmicParameterService;

/**
 * 模型因子管理表Controller
 * @author wufan
 * @version 2019-08-22
 */
@Controller
@RequestMapping(value = "${adminPath}/sfmk/algorithmicParameter")
public class AlgorithmicParameterController extends BaseController {

	@Autowired
	private AlgorithmicParameterService algorithmicParameterService;

	@Autowired
	private AlgorithmicRootService algorithmicRootService;

	@ModelAttribute
	public AlgorithmicParameter get(@RequestParam(required=false) String id) {
		AlgorithmicParameter entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = algorithmicParameterService.get(id);
		}
		if (entity == null){
			entity = new AlgorithmicParameter();
		}
		return entity;
	}
	
	@RequiresPermissions("sfmk:algorithmicParameter:view")
	@RequestMapping(value = {"list", ""})
	public String list(AlgorithmicParameter algorithmicParameter, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<AlgorithmicParameter> page = algorithmicParameterService.findPage(new Page<AlgorithmicParameter>(request, response), algorithmicParameter);
		model.addAttribute("page", page);
		Page<AlgorithmicRoot> rootpage = algorithmicRootService.findPage(new Page<AlgorithmicRoot>(request, response), new AlgorithmicRoot());
		model.addAttribute("rootpage", rootpage);
		return "catchup/sfmk/algorithmicParameterList";
	}

	@RequiresPermissions("sfmk:algorithmicParameter:view")
	@RequestMapping(value = "form")
	public String form(AlgorithmicParameter algorithmicParameter, HttpServletRequest request, HttpServletResponse response, Model model) {
		model.addAttribute("algorithmicParameter", algorithmicParameter);
		Page<AlgorithmicRoot> rootpage = algorithmicRootService.findPage(new Page<AlgorithmicRoot>(request, response), new AlgorithmicRoot());
		model.addAttribute("rootpage", rootpage);
		return "catchup/sfmk/algorithmicParameterForm";
	}

	@RequiresPermissions("sfmk:algorithmicParameter:edit")
	@RequestMapping(value = "save")
	public String save(AlgorithmicParameter algorithmicParameter, HttpServletRequest request, HttpServletResponse response, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, algorithmicParameter)){
			return form(algorithmicParameter,request,response, model);
		}
		algorithmicParameterService.save(algorithmicParameter);
		addMessage(redirectAttributes, "保存模型因子管理表成功");
		return "redirect:"+Global.getAdminPath()+"/sfmk/algorithmicParameter/?repage";
	}
	
	@RequiresPermissions("sfmk:algorithmicParameter:edit")
	@RequestMapping(value = "delete")
	public String delete(AlgorithmicParameter algorithmicParameter, RedirectAttributes redirectAttributes) {
		algorithmicParameterService.delete(algorithmicParameter);
		addMessage(redirectAttributes, "删除模型因子管理表成功");
		return "redirect:"+Global.getAdminPath()+"/sfmk/algorithmicParameter/?repage";
	}

}