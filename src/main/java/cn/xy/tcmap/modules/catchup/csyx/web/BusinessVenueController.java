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
import cn.xy.tcmap.modules.catchup.csyx.entity.BusinessVenue;
import cn.xy.tcmap.modules.catchup.csyx.service.BusinessVenueService;

import java.util.List;

/**
 * 营商文化场馆Controller
 * @author wufan
 * @version 2019-07-31
 */
@Controller
@RequestMapping(value = "${adminPath}/csyx/businessVenue")
public class BusinessVenueController extends BaseController {

	@Autowired
	private BusinessVenueService businessVenueService;
	
	@ModelAttribute
	public BusinessVenue get(@RequestParam(required=false) String id) {
		BusinessVenue entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = businessVenueService.get(id);
		}
		if (entity == null){
			entity = new BusinessVenue();
		}
		return entity;
	}
	
	@RequiresPermissions("csyx:businessVenue:view")
	@RequestMapping(value = {"list", ""})
	public String list(BusinessVenue businessVenue, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<BusinessVenue> page = businessVenueService.findPage(new Page<BusinessVenue>(request, response), businessVenue); 
		model.addAttribute("page", page);
		return "catchup/csyx/businessVenueList";
	}

	@RequiresPermissions("csyx:businessVenue:view")
	@RequestMapping(value = "form")
	public String form(BusinessVenue businessVenue, Model model) {
		model.addAttribute("businessVenue", businessVenue);
		return "catchup/csyx/businessVenueForm";
	}

	@RequiresPermissions("csyx:businessVenue:edit")
	@RequestMapping(value = "save")
	public String save(BusinessVenue businessVenue, Model model, RedirectAttributes redirectAttributes) {
		try{
			if (!beanValidator(model, businessVenue)){
				return form(businessVenue, model);
			}
			if(businessVenue.getIsNewRecord()){
				BusinessVenue bv = new BusinessVenue();
				bv.setMonth(businessVenue.getMonth());
				bv.setYear(businessVenue.getYear());
				bv.setVenue(businessVenue.getVenue());
				List<BusinessVenue> list = businessVenueService.findList(bv);
				if(list != null && list.size()>0){
					throw new Exception("数据重复");
				}
			}
			businessVenueService.save(businessVenue);
			addMessage(redirectAttributes, "保存营商文化场馆成功");
		}catch (Exception e){
			addMessage(redirectAttributes, "保存营商文化场馆失败，失败原因："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/csyx/businessVenue/?repage";
	}
	
	@RequiresPermissions("csyx:businessVenue:edit")
	@RequestMapping(value = "delete")
	public String delete(BusinessVenue businessVenue, RedirectAttributes redirectAttributes) {
		businessVenueService.delete(businessVenue);
		addMessage(redirectAttributes, "删除营商文化场馆成功");
		return "redirect:"+Global.getAdminPath()+"/csyx/businessVenue/?repage";
	}

}