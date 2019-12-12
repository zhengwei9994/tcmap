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
import cn.xy.tcmap.modules.catchup.csyx.entity.IndustrialDevelopment;
import cn.xy.tcmap.modules.catchup.csyx.service.IndustrialDevelopmentService;

import java.util.List;

/**
 * 产业发展Controller
 * @author tuo
 * @version 2019-09-27
 */
@Controller
@RequestMapping(value = "${adminPath}/csyx/industrialDevelopment")
public class IndustrialDevelopmentController extends BaseController {

	@Autowired
	private IndustrialDevelopmentService industrialDevelopmentService;
	
	@ModelAttribute
	public IndustrialDevelopment get(@RequestParam(required=false) String id) {
		IndustrialDevelopment entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = industrialDevelopmentService.get(id);
		}
		if (entity == null){
			entity = new IndustrialDevelopment();
		}
		return entity;
	}
	
	@RequiresPermissions("csyx:industrialDevelopment:view")
	@RequestMapping(value = {"list", ""})
	public String list(IndustrialDevelopment industrialDevelopment, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<IndustrialDevelopment> page = industrialDevelopmentService.findPage(new Page<IndustrialDevelopment>(request, response), industrialDevelopment); 
		model.addAttribute("page", page);
		return "catchup/csyx/industrialDevelopmentList";
	}

	@RequiresPermissions("csyx:industrialDevelopment:view")
	@RequestMapping(value = "form")
	public String form(IndustrialDevelopment industrialDevelopment, Model model) {
		model.addAttribute("industrialDevelopment", industrialDevelopment);
		return "catchup/csyx/industrialDevelopmentForm";
	}

	@RequiresPermissions("csyx:industrialDevelopment:edit")
	@RequestMapping(value = "save")
	public String save(IndustrialDevelopment industrialDevelopment, Model model, RedirectAttributes redirectAttributes) {
		try{
			if (!beanValidator(model, industrialDevelopment)){
				return form(industrialDevelopment, model);
			}
			if(industrialDevelopment.getIsNewRecord()){
				IndustrialDevelopment id = new IndustrialDevelopment();
				id.setYear(industrialDevelopment.getYear());
				id.setMonth(industrialDevelopment.getMonth());
				id.setProfession(industrialDevelopment.getProfession());
				id.setProfessionson1(industrialDevelopment.getProfessionson1());
				id.setProfessionson2(industrialDevelopment.getProfessionson2());
				List<IndustrialDevelopment> list = industrialDevelopmentService.findList(id);
				if(list != null && list.size()>0){
					throw new Exception("数据重复");
				}
			}
			industrialDevelopmentService.save(industrialDevelopment);
			addMessage(redirectAttributes, "保存产业发展成功");
		}catch (Exception e){
			addMessage(redirectAttributes, "保存产业发展失败，失败原因："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/csyx/industrialDevelopment/?repage";
	}
	
	@RequiresPermissions("csyx:industrialDevelopment:edit")
	@RequestMapping(value = "delete")
	public String delete(IndustrialDevelopment industrialDevelopment, RedirectAttributes redirectAttributes) {
		industrialDevelopmentService.delete(industrialDevelopment);
		addMessage(redirectAttributes, "删除产业发展成功");
		return "redirect:"+Global.getAdminPath()+"/csyx/industrialDevelopment/?repage";
	}

}