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
import cn.xy.tcmap.modules.catchup.csyx.entity.BusinessMedical;
import cn.xy.tcmap.modules.catchup.csyx.service.BusinessMedicalService;

import java.util.List;

/**
 * 营商千人医疗Controller
 * @author wufan
 * @version 2019-07-31
 */
@Controller
@RequestMapping(value = "${adminPath}/csyx/businessMedical")
public class BusinessMedicalController extends BaseController {

	@Autowired
	private BusinessMedicalService businessMedicalService;
	
	@ModelAttribute
	public BusinessMedical get(@RequestParam(required=false) String id) {
		BusinessMedical entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = businessMedicalService.get(id);
		}
		if (entity == null){
			entity = new BusinessMedical();
		}
		return entity;
	}
	
	@RequiresPermissions("csyx:businessMedical:view")
	@RequestMapping(value = {"list", ""})
	public String list(BusinessMedical businessMedical, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<BusinessMedical> page = businessMedicalService.findPage(new Page<BusinessMedical>(request, response), businessMedical); 
		model.addAttribute("page", page);
		return "catchup/csyx/businessMedicalList";
	}

	@RequiresPermissions("csyx:businessMedical:view")
	@RequestMapping(value = "form")
	public String form(BusinessMedical businessMedical, Model model) {
		model.addAttribute("businessMedical", businessMedical);
		return "catchup/csyx/businessMedicalForm";
	}

	@RequiresPermissions("csyx:businessMedical:edit")
	@RequestMapping(value = "save")
	public String save(BusinessMedical businessMedical, Model model, RedirectAttributes redirectAttributes) {
		try{
			if (!beanValidator(model, businessMedical)){
				return form(businessMedical, model);
			}
			if(businessMedical.getIsNewRecord()){
				BusinessMedical condition = new BusinessMedical();
				condition.setIndex(businessMedical.getIndex());
				condition.setMonth(businessMedical.getMonth());
				condition.setYear(businessMedical.getYear());
				List<BusinessMedical> list = businessMedicalService.findList(condition);
				if(list != null && list.size()>0){
					throw new Exception("数据重复");
				}
			}
			businessMedicalService.save(businessMedical);
			addMessage(redirectAttributes, "保存营商千人医疗成功");
		}catch (Exception e){
			addMessage(redirectAttributes,"保存营商千人医疗失败，失败原因："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/csyx/businessMedical/?repage";
	}
	
	@RequiresPermissions("csyx:businessMedical:edit")
	@RequestMapping(value = "delete")
	public String delete(BusinessMedical businessMedical, RedirectAttributes redirectAttributes) {
		businessMedicalService.delete(businessMedical);
		addMessage(redirectAttributes, "删除营商千人医疗成功");
		return "redirect:"+Global.getAdminPath()+"/csyx/businessMedical/?repage";
	}

}