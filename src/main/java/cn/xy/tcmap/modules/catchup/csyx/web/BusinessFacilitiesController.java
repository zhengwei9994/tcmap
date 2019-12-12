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
import cn.xy.tcmap.modules.catchup.csyx.entity.BusinessFacilities;
import cn.xy.tcmap.modules.catchup.csyx.service.BusinessFacilitiesService;

import java.util.List;

/**
 * 营商设施完成度Controller
 * @author wufan
 * @version 2019-07-31
 */
@Controller
@RequestMapping(value = "${adminPath}/csyx/businessFacilities")
public class BusinessFacilitiesController extends BaseController {

	@Autowired
	private BusinessFacilitiesService businessFacilitiesService;
	
	@ModelAttribute
	public BusinessFacilities get(@RequestParam(required=false) String id) {
		BusinessFacilities entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = businessFacilitiesService.get(id);
		}
		if (entity == null){
			entity = new BusinessFacilities();
		}
		return entity;
	}
	
	@RequiresPermissions("csyx:businessFacilities:view")
	@RequestMapping(value = {"list", ""})
	public String list(BusinessFacilities businessFacilities, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<BusinessFacilities> page = businessFacilitiesService.findPage(new Page<BusinessFacilities>(request, response), businessFacilities); 
		model.addAttribute("page", page);
		return "catchup/csyx/businessFacilitiesList";
	}

	@RequiresPermissions("csyx:businessFacilities:view")
	@RequestMapping(value = "form")
	public String form(BusinessFacilities businessFacilities, Model model) {
		model.addAttribute("businessFacilities", businessFacilities);
		return "catchup/csyx/businessFacilitiesForm";
	}

	@RequiresPermissions("csyx:businessFacilities:edit")
	@RequestMapping(value = "save")
	public String save(BusinessFacilities businessFacilities, Model model, RedirectAttributes redirectAttributes) {
		try{
			if (!beanValidator(model, businessFacilities)){
				return form(businessFacilities, model);
			}
			if(businessFacilities.getIsNewRecord()){
				BusinessFacilities bf = new BusinessFacilities();
				bf.setYear(businessFacilities.getYear());
				bf.setMonth(businessFacilities.getMonth());
				bf.setItem(businessFacilities.getItem());
				List<BusinessFacilities> list = businessFacilitiesService.findList(bf);
				if(list != null && list.size()>0){
					throw new Exception("数据重复");
				}
			}
			businessFacilitiesService.save(businessFacilities);
			addMessage(redirectAttributes, "保存营商设施完成度成功");
		}catch (Exception e){
			addMessage(redirectAttributes, "保存营商设施完成度失败，失败原因："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/csyx/businessFacilities/?repage";
	}
	
	@RequiresPermissions("csyx:businessFacilities:edit")
	@RequestMapping(value = "delete")
	public String delete(BusinessFacilities businessFacilities, RedirectAttributes redirectAttributes) {
		businessFacilitiesService.delete(businessFacilities);
		addMessage(redirectAttributes, "删除营商设施完成度成功");
		return "redirect:"+Global.getAdminPath()+"/csyx/businessFacilities/?repage";
	}

}