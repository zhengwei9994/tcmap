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
import cn.xy.tcmap.modules.catchup.csyx.entity.BusinessHighschool;
import cn.xy.tcmap.modules.catchup.csyx.service.BusinessHighschoolService;

import java.util.List;

/**
 * 营商高等院校数量Controller
 * @author wufan
 * @version 2019-07-31
 */
@Controller
@RequestMapping(value = "${adminPath}/csyx/businessHighschool")
public class BusinessHighschoolController extends BaseController {

	@Autowired
	private BusinessHighschoolService businessHighschoolService;
	
	@ModelAttribute
	public BusinessHighschool get(@RequestParam(required=false) String id) {
		BusinessHighschool entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = businessHighschoolService.get(id);
		}
		if (entity == null){
			entity = new BusinessHighschool();
		}
		return entity;
	}
	
	@RequiresPermissions("csyx:businessHighschool:view")
	@RequestMapping(value = {"list", ""})
	public String list(BusinessHighschool businessHighschool, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<BusinessHighschool> page = businessHighschoolService.findPage(new Page<BusinessHighschool>(request, response), businessHighschool); 
		model.addAttribute("page", page);
		return "catchup/csyx/businessHighschoolList";
	}

	@RequiresPermissions("csyx:businessHighschool:view")
	@RequestMapping(value = "form")
	public String form(BusinessHighschool businessHighschool, Model model) {
		model.addAttribute("businessHighschool", businessHighschool);
		return "catchup/csyx/businessHighschoolForm";
	}

	@RequiresPermissions("csyx:businessHighschool:edit")
	@RequestMapping(value = "save")
	public String save(BusinessHighschool businessHighschool, Model model, RedirectAttributes redirectAttributes) {
		try{
			if (!beanValidator(model, businessHighschool)){
				return form(businessHighschool, model);
			}
			if(businessHighschool.getIsNewRecord()){
				BusinessHighschool bh = new BusinessHighschool();
				bh.setYear(businessHighschool.getYear());
				bh.setMonth(businessHighschool.getMonth());
				bh.setMechanism(businessHighschool.getMechanism());
				List<BusinessHighschool> list = businessHighschoolService.findList(bh);
				if(list != null && list.size()>0){
					throw new Exception("数据重复");
				}
			}
			businessHighschoolService.save(businessHighschool);
			addMessage(redirectAttributes, "保存营商高等院校数量成功");
		}catch (Exception e){
			addMessage(redirectAttributes, "保存营商高等院校数量失败，失败原因："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/csyx/businessHighschool/?repage";
	}
	
	@RequiresPermissions("csyx:businessHighschool:edit")
	@RequestMapping(value = "delete")
	public String delete(BusinessHighschool businessHighschool, RedirectAttributes redirectAttributes) {
		businessHighschoolService.delete(businessHighschool);
		addMessage(redirectAttributes, "删除营商高等院校数量成功");
		return "redirect:"+Global.getAdminPath()+"/csyx/businessHighschool/?repage";
	}

}