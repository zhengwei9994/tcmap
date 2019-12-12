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
import cn.xy.tcmap.modules.catchup.sfmk.entity.AlgorithmicResult;
import cn.xy.tcmap.modules.catchup.sfmk.service.AlgorithmicResultService;

/**
 * 算法结果表Controller
 * @author tuo
 * @version 2019-09-03
 */
@Controller
@RequestMapping(value = "${adminPath}/sfmk/algorithmicResult")
public class AlgorithmicResultController extends BaseController {

	@Autowired
	private AlgorithmicResultService algorithmicResultService;
	
	@ModelAttribute
	public AlgorithmicResult get(@RequestParam(required=false) String id) {
		AlgorithmicResult entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = algorithmicResultService.get(id);
		}
		if (entity == null){
			entity = new AlgorithmicResult();
		}
		return entity;
	}
	
	@RequiresPermissions("sfmk:algorithmicResult:view")
	@RequestMapping(value = {"list", ""})
	public String list(AlgorithmicResult algorithmicResult, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<AlgorithmicResult> page = algorithmicResultService.findPage(new Page<AlgorithmicResult>(request, response), algorithmicResult); 
		model.addAttribute("page", page);
		return "catchup/sfmk/algorithmicResultList";
	}

	@RequiresPermissions("sfmk:algorithmicResult:view")
	@RequestMapping(value = "form")
	public String form(AlgorithmicResult algorithmicResult, Model model) {
		model.addAttribute("algorithmicResult", algorithmicResult);
		return "catchup/sfmk/algorithmicResultForm";
	}

	@RequiresPermissions("sfmk:algorithmicResult:edit")
	@RequestMapping(value = "save")
	public String save(AlgorithmicResult algorithmicResult, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, algorithmicResult)){
			return form(algorithmicResult, model);
		}
		algorithmicResultService.save(algorithmicResult);
		addMessage(redirectAttributes, "保存算法结果表成功");
		return "redirect:"+Global.getAdminPath()+"/sfmk/algorithmicResult/?repage";
	}
	
	@RequiresPermissions("sfmk:algorithmicResult:edit")
	@RequestMapping(value = "delete")
	public String delete(AlgorithmicResult algorithmicResult, RedirectAttributes redirectAttributes) {
		algorithmicResultService.delete(algorithmicResult);
		addMessage(redirectAttributes, "删除算法结果表成功");
		return "redirect:"+Global.getAdminPath()+"/sfmk/algorithmicResult/?repage";
	}

}