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
import cn.xy.tcmap.modules.catchup.csyx.entity.DevelopmentAbsorb;
import cn.xy.tcmap.modules.catchup.csyx.service.DevelopmentAbsorbService;

import java.util.List;

/**
 * 产业发展潜力内容Controller
 * @author wufan
 * @version 2019-07-31
 */
@Controller
@RequestMapping(value = "${adminPath}/csyx/developmentAbsorb")
public class DevelopmentAbsorbController extends BaseController {

	@Autowired
	private DevelopmentAbsorbService developmentAbsorbService;
	
	@ModelAttribute
	public DevelopmentAbsorb get(@RequestParam(required=false) String id) {
		DevelopmentAbsorb entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = developmentAbsorbService.get(id);
		}
		if (entity == null){
			entity = new DevelopmentAbsorb();
		}
		return entity;
	}
	
	@RequiresPermissions("csyx:developmentAbsorb:view")
	@RequestMapping(value = {"list", ""})
	public String list(DevelopmentAbsorb developmentAbsorb, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<DevelopmentAbsorb> page = developmentAbsorbService.findPage(new Page<DevelopmentAbsorb>(request, response), developmentAbsorb); 
		model.addAttribute("page", page);
		return "catchup/csyx/developmentAbsorbList";
	}

	@RequiresPermissions("csyx:developmentAbsorb:view")
	@RequestMapping(value = "form")
	public String form(DevelopmentAbsorb developmentAbsorb, Model model) {
		model.addAttribute("developmentAbsorb", developmentAbsorb);
		return "catchup/csyx/developmentAbsorbForm";
	}

	@RequiresPermissions("csyx:developmentAbsorb:edit")
	@RequestMapping(value = "save")
	public String save(DevelopmentAbsorb developmentAbsorb, Model model, RedirectAttributes redirectAttributes) {
		try{
			if (!beanValidator(model, developmentAbsorb)){
				return form(developmentAbsorb, model);
			}
			if(developmentAbsorb.getIsNewRecord()){
				DevelopmentAbsorb da = new DevelopmentAbsorb();
				da.setYear(developmentAbsorb.getYear());
				da.setMonth(developmentAbsorb.getMonth());
				da.setItem(developmentAbsorb.getItem());
				List<DevelopmentAbsorb> list = developmentAbsorbService.findList(da);
				if(list != null && list.size()>0){
					throw new Exception("数据重复");
				}
			}
			developmentAbsorbService.save(developmentAbsorb);
			addMessage(redirectAttributes, "保存产业发展潜力内容成功");
		}catch (Exception e){
			addMessage(redirectAttributes, "保存产业发展潜力内容失败，失败原因："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/csyx/developmentAbsorb/?repage";
	}
	
	@RequiresPermissions("csyx:developmentAbsorb:edit")
	@RequestMapping(value = "delete")
	public String delete(DevelopmentAbsorb developmentAbsorb, RedirectAttributes redirectAttributes) {
		developmentAbsorbService.delete(developmentAbsorb);
		addMessage(redirectAttributes, "删除产业发展潜力内容成功");
		return "redirect:"+Global.getAdminPath()+"/csyx/developmentAbsorb/?repage";
	}

}