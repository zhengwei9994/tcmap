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
import cn.xy.tcmap.modules.catchup.csyx.entity.BusinessEducation;
import cn.xy.tcmap.modules.catchup.csyx.service.BusinessEducationService;

import java.util.List;

/**
 * 营商义务教育Controller
 * @author wufan
 * @version 2019-07-31
 */
@Controller
@RequestMapping(value = "${adminPath}/csyx/businessEducation")
public class BusinessEducationController extends BaseController {

	@Autowired
	private BusinessEducationService businessEducationService;
	
	@ModelAttribute
	public BusinessEducation get(@RequestParam(required=false) String id) {
		BusinessEducation entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = businessEducationService.get(id);
		}
		if (entity == null){
			entity = new BusinessEducation();
		}
		return entity;
	}
	
	@RequiresPermissions("csyx:businessEducation:view")
	@RequestMapping(value = {"list", ""})
	public String list(BusinessEducation businessEducation, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<BusinessEducation> page = businessEducationService.findPage(new Page<BusinessEducation>(request, response), businessEducation); 
		model.addAttribute("page", page);
		return "catchup/csyx/businessEducationList";
	}

	@RequiresPermissions("csyx:businessEducation:view")
	@RequestMapping(value = "form")
	public String form(BusinessEducation businessEducation, Model model) {
		model.addAttribute("businessEducation", businessEducation);
		return "catchup/csyx/businessEducationForm";
	}

	@RequiresPermissions("csyx:businessEducation:edit")
	@RequestMapping(value = "save")
	public String save(BusinessEducation businessEducation, Model model, RedirectAttributes redirectAttributes) {
		try{
			if (!beanValidator(model, businessEducation)){
				return form(businessEducation, model);
			}
			if(businessEducation.getIsNewRecord()){
				BusinessEducation be = new BusinessEducation();
				be.setYear(businessEducation.getYear());
				be.setSchool(businessEducation.getSchool());
				List<BusinessEducation> list = businessEducationService.findList(be);
				if(list != null && list.size()>0){
					throw new Exception("数据重复");
				}
			}
			businessEducationService.save(businessEducation);
			addMessage(redirectAttributes, "保存营商义务教育成功");
		}catch (Exception e){
			addMessage(redirectAttributes, "保存营商义务教育失败，失败原因："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/csyx/businessEducation/?repage";
	}
	
	@RequiresPermissions("csyx:businessEducation:edit")
	@RequestMapping(value = "delete")
	public String delete(BusinessEducation businessEducation, RedirectAttributes redirectAttributes) {
		businessEducationService.delete(businessEducation);
		addMessage(redirectAttributes, "删除营商义务教育成功");
		return "redirect:"+Global.getAdminPath()+"/csyx/businessEducation/?repage";
	}

}