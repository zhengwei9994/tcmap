/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.sfmk.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.xy.tcmap.modules.catchup.sfmk.entity.AlgorithmicExampleList;
import cn.xy.tcmap.modules.catchup.sfmk.entity.AlgorithmicParameter;
import cn.xy.tcmap.modules.catchup.sfmk.entity.AlgorithmicRoot;
import cn.xy.tcmap.modules.catchup.sfmk.service.AlgorithmicParameterService;
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
import cn.xy.tcmap.modules.catchup.sfmk.entity.AlgorithmicExample;
import cn.xy.tcmap.modules.catchup.sfmk.service.AlgorithmicExampleService;

import java.util.ArrayList;
import java.util.List;

/**
 * 模型实例管理表Controller
 * @author wufan
 * @version 2019-08-26
 */
@Controller
@RequestMapping(value = "${adminPath}/sfmk/algorithmicExample")
public class AlgorithmicExampleController extends BaseController {

	@Autowired
	private AlgorithmicExampleService algorithmicExampleService;

	@Autowired
	private AlgorithmicParameterService algorithmicParameterService;

	@Autowired
	private AlgorithmicRootService algorithmicRootService;
	
	@ModelAttribute
	public AlgorithmicExample get(@RequestParam(required=false) String id) {
		AlgorithmicExample entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = algorithmicExampleService.get(id);
		}
		if (entity == null){
			entity = new AlgorithmicExample();
		}
		return entity;
	}
	
	@RequiresPermissions("sfmk:algorithmicExample:view")
	@RequestMapping(value = {"list", ""})
	public String list(AlgorithmicExample algorithmicExample,AlgorithmicParameter algorithmicParameter, HttpServletRequest request, HttpServletResponse response, Model model) {
		//初始化时查询所有数据
		Page<AlgorithmicExample> page = algorithmicExampleService.findPage(new Page<AlgorithmicExample>(request, response), algorithmicExample);
		Page<AlgorithmicRoot> rootpage = algorithmicRootService.findPage(new Page<AlgorithmicRoot>(request, response), new AlgorithmicRoot());
		//根据Name查找所有对象
		Page<AlgorithmicParameter> parameterpage = algorithmicParameterService.findPage(new Page<AlgorithmicParameter>(request, response), algorithmicParameter);
		List<AlgorithmicParameter>  parameterList=parameterpage.getList();

		//存放筛选后的数据
		List<AlgorithmicExample> examplePage=new ArrayList<AlgorithmicExample>();
		List<AlgorithmicParameter> algorithmicParameterList=new ArrayList<AlgorithmicParameter>();
 		if(algorithmicParameter.getParametername()!=null && algorithmicParameter.getParametername()!=""){
             for(AlgorithmicExample example:page.getList()){
                     for(AlgorithmicParameter parameter:parameterList){
                     	if(example.getParameterid().equals(parameter.getId())){
                     		examplePage.add(example);
							algorithmicParameterList.add(parameter);
						}
					 }
			 }
			model.addAttribute("rootpage", rootpage);
			model.addAttribute("page", new Page<AlgorithmicExample>().setList(examplePage));
			model.addAttribute("parameterpage", new Page<AlgorithmicParameter>().setList(algorithmicParameterList));
		}else {
			model.addAttribute("rootpage", rootpage);
			model.addAttribute("page", page);
			model.addAttribute("parameterpage", parameterpage);
		}

		return "catchup/sfmk/algorithmicExampleList";
	}

	@RequiresPermissions("sfmk:algorithmicExample:view")
	@RequestMapping(value = "form")
	public String form(AlgorithmicExample algorithmicExample, HttpServletRequest request, HttpServletResponse response, Model model) {
//		model.addAttribute("algorithmicExample", algorithmicExample);
		Page<AlgorithmicRoot> rootpage = algorithmicRootService.findPage(new Page<AlgorithmicRoot>(request, response), new AlgorithmicRoot());
		model.addAttribute("rootpage", rootpage);
        AlgorithmicParameter algorithmicParameter = new AlgorithmicParameter();
        algorithmicParameter.setAlgorithmicid(algorithmicExample.getRootid());
        Page<AlgorithmicParameter> parameterpage = algorithmicParameterService.findPage(new Page<AlgorithmicParameter>(request, response), algorithmicParameter);
		model.addAttribute("parameterpage", parameterpage);
        Page<AlgorithmicExample> examplepage = algorithmicExampleService.findPage(new Page<AlgorithmicExample>(request, response), algorithmicExample);
        if(examplepage!= null && examplepage.getList().size()>0){
			model.addAttribute("name",examplepage.getList().get(0).getName());
		}

        model.addAttribute("examplepage", examplepage);
		return "catchup/sfmk/algorithmicExampleForm";
	}

	@RequiresPermissions("sfmk:algorithmicExample:view")
	@RequestMapping(value = "formList")
	public String getList(AlgorithmicExample algorithmicExample, HttpServletRequest request, HttpServletResponse response, Model model){
		model.addAttribute("algorithmicExample", algorithmicExample);
        Page<AlgorithmicRoot> rootpage = algorithmicRootService.findPage(new Page<AlgorithmicRoot>(request, response), new AlgorithmicRoot());
        model.addAttribute("rootpage", rootpage);
		return "catchup/sfmk/algorithmicExampleFormList";
	}


	@RequiresPermissions("sfmk:algorithmicExample:edit")
	@RequestMapping(value = "save")
	public String save(AlgorithmicExample algorithmicExample, HttpServletRequest request, HttpServletResponse response, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, algorithmicExample)){
			return form(algorithmicExample,request,response, model);
		}
		algorithmicExampleService.save(algorithmicExample);
        addMessage(redirectAttributes, "保存模型实例管理表成功");
		return "redirect:"+Global.getAdminPath()+"/sfmk/algorithmicExample/?repage";
	}

	@RequiresPermissions("sfmk:algorithmicExample:edit")
	@RequestMapping(value = "saveList")
	public String save(String name,AlgorithmicExampleList algorithmicExampleList, HttpServletRequest request, HttpServletResponse response, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, algorithmicExampleList)){
			return form(new AlgorithmicExample(),request,response, model);
		}
        List<AlgorithmicExample> algorithmicExampleList1 = algorithmicExampleList.getAlgorithmicExampleList();
        algorithmicExampleService.saveList(algorithmicExampleList1,name);
		addMessage(redirectAttributes, "保存模型实例管理表成功");
		return "redirect:"+Global.getAdminPath()+"/sfmk/algorithmicExample/?repage";
	}

	@RequiresPermissions("sfmk:algorithmicExample:edit")
	@RequestMapping(value = "delete")
	public String delete(AlgorithmicExample algorithmicExample, RedirectAttributes redirectAttributes) {
		algorithmicExampleService.delete(algorithmicExample);
		addMessage(redirectAttributes, "删除模型实例管理表成功");
		return "redirect:"+Global.getAdminPath()+"/sfmk/algorithmicExample/?repage";
	}

}