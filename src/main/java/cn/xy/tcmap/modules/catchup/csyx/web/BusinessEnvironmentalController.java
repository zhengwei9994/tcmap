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
import cn.xy.tcmap.modules.catchup.csyx.entity.BusinessEnvironmental;
import cn.xy.tcmap.modules.catchup.csyx.service.BusinessEnvironmentalService;

import java.util.List;

/**
 * 营商环境变量Controller
 * @author wufan
 * @version 2019-07-31
 */
@Controller
@RequestMapping(value = "${adminPath}/csyx/businessEnvironmental")
public class BusinessEnvironmentalController extends BaseController {

	@Autowired
	private BusinessEnvironmentalService businessEnvironmentalService;
	
	@ModelAttribute
	public BusinessEnvironmental get(@RequestParam(required=false) String id) {
		BusinessEnvironmental entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = businessEnvironmentalService.get(id);
		}
		if (entity == null){
			entity = new BusinessEnvironmental();
		}
		return entity;
	}
	
	@RequiresPermissions("csyx:businessEnvironmental:view")
	@RequestMapping(value = {"list", ""})
	public String list(BusinessEnvironmental businessEnvironmental, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<BusinessEnvironmental> page = businessEnvironmentalService.findPage(new Page<BusinessEnvironmental>(request, response), businessEnvironmental); 
		model.addAttribute("page", page);
		return "catchup/csyx/businessEnvironmentalList";
	}

	@RequiresPermissions("csyx:businessEnvironmental:view")
	@RequestMapping(value = "form")
	public String form(BusinessEnvironmental businessEnvironmental, Model model) {
		model.addAttribute("businessEnvironmental", businessEnvironmental);
		return "catchup/csyx/businessEnvironmentalForm";
	}

	@RequiresPermissions("csyx:businessEnvironmental:edit")
	@RequestMapping(value = "save")
	public String save(BusinessEnvironmental businessEnvironmental, Model model, RedirectAttributes redirectAttributes) {
		try{
			if (!beanValidator(model, businessEnvironmental)){
				return form(businessEnvironmental, model);
			}
			if(businessEnvironmental.getIsNewRecord()){
				BusinessEnvironmental condition = new BusinessEnvironmental();
				condition.setIndex(businessEnvironmental.getIndex());
				condition.setYear(businessEnvironmental.getYear());
				condition.setMonth(businessEnvironmental.getMonth());
				List<BusinessEnvironmental> list = businessEnvironmentalService.findList(condition);
				if(list != null && list.size()>0){
					throw new Exception("数据重复");
				}
			}
			businessEnvironmentalService.save(businessEnvironmental);
			addMessage(redirectAttributes, "保存营商环境变量成功");
		}catch (Exception e){
			addMessage(redirectAttributes, "保存营商环境变量失败，失败原因："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/csyx/businessEnvironmental/?repage";
	}
	
	@RequiresPermissions("csyx:businessEnvironmental:edit")
	@RequestMapping(value = "delete")
	public String delete(BusinessEnvironmental businessEnvironmental, RedirectAttributes redirectAttributes) {
		businessEnvironmentalService.delete(businessEnvironmental);
		addMessage(redirectAttributes, "删除营商环境变量成功");
		return "redirect:"+Global.getAdminPath()+"/csyx/businessEnvironmental/?repage";
	}

}