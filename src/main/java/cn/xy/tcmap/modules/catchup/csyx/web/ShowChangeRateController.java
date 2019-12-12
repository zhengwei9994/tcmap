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
import cn.xy.tcmap.modules.catchup.csyx.entity.ShowChangeRate;
import cn.xy.tcmap.modules.catchup.csyx.service.ShowChangeRateService;

import java.util.List;

/**
 * 变化率Controller
 * @author 变化率
 * @version 2019-08-22
 */
@Controller
@RequestMapping(value = "${adminPath}/csyx/showChangeRate")
public class ShowChangeRateController extends BaseController {

	@Autowired
	private ShowChangeRateService showChangeRateService;
	
	@ModelAttribute
	public ShowChangeRate get(@RequestParam(required=false) String id) {
		ShowChangeRate entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = showChangeRateService.get(id);
		}
		if (entity == null){
			entity = new ShowChangeRate();
		}
		return entity;
	}
	
	@RequiresPermissions("csyx:showChangeRate:view")
	@RequestMapping(value = {"list", ""})
	public String list(ShowChangeRate showChangeRate, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ShowChangeRate> page = showChangeRateService.findPage(new Page<ShowChangeRate>(request, response), showChangeRate); 
		model.addAttribute("page", page);
		return "catchup/csyx/showChangeRateList";
	}

	@RequiresPermissions("csyx:showChangeRate:view")
	@RequestMapping(value = "form")
	public String form(ShowChangeRate showChangeRate, Model model) {
		model.addAttribute("showChangeRate", showChangeRate);
		return "catchup/csyx/showChangeRateForm";
	}

	@RequiresPermissions("csyx:showChangeRate:edit")
	@RequestMapping(value = "save")
	public String save(ShowChangeRate showChangeRate, Model model, RedirectAttributes redirectAttributes) {
		try{
			if (!beanValidator(model, showChangeRate)){
				return form(showChangeRate, model);
			}
			if(showChangeRate.getIsNewRecord()){
				ShowChangeRate condition = showChangeRateService.CheckByYear(showChangeRate);
//				condition.setYear(showChangeRate.getYear());
//				List<ShowChangeRate> list = showChangeRateService.findList(condition);
				if(condition != null ){
					throw new Exception("数据重复添加！");
				}
			}
			showChangeRateService.save(showChangeRate);
			addMessage(redirectAttributes, "保存变化率成功");
		}catch (Exception e){
			addMessage(redirectAttributes, "保存变化率失败："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/csyx/showChangeRate/?repage";
	}
	
	@RequiresPermissions("csyx:showChangeRate:edit")
	@RequestMapping(value = "delete")
	public String delete(ShowChangeRate showChangeRate, RedirectAttributes redirectAttributes) {
		showChangeRateService.delete(showChangeRate);
		addMessage(redirectAttributes, "删除变化率成功");
		return "redirect:"+Global.getAdminPath()+"/csyx/showChangeRate/?repage";
	}

}