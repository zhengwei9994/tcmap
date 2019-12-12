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
import cn.xy.tcmap.modules.catchup.csyx.entity.BusinessScore;
import cn.xy.tcmap.modules.catchup.csyx.service.BusinessScoreService;

import java.util.List;

/**
 * 营商环境评分Controller
 * @author wufan
 * @version 2019-08-01
 */
@Controller
@RequestMapping(value = "${adminPath}/csyx/businessScore")
public class BusinessScoreController extends BaseController {

	@Autowired
	private BusinessScoreService businessScoreService;
	
	@ModelAttribute
	public BusinessScore get(@RequestParam(required=false) String id) {
		BusinessScore entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = businessScoreService.get(id);
		}
		if (entity == null){
			entity = new BusinessScore();
		}
		return entity;
	}
	
	@RequiresPermissions("csyx:businessScore:view")
	@RequestMapping(value = {"list", ""})
	public String list(BusinessScore businessScore, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<BusinessScore> page = businessScoreService.findPage(new Page<BusinessScore>(request, response), businessScore); 
		model.addAttribute("page", page);
		return "catchup/csyx/businessScoreList";
	}

	@RequiresPermissions("csyx:businessScore:view")
	@RequestMapping(value = "form")
	public String form(BusinessScore businessScore, Model model) {
		model.addAttribute("businessScore", businessScore);
		return "catchup/csyx/businessScoreForm";
	}

	@RequiresPermissions("csyx:businessScore:edit")
	@RequestMapping(value = "save")
	public String save(BusinessScore businessScore, Model model, RedirectAttributes redirectAttributes) {
		try {
			if (!beanValidator(model, businessScore)){
				return form(businessScore, model);
			}

			if(businessScore.getIsNewRecord()){
				BusinessScore bs = new BusinessScore();
				bs.setMonth(businessScore.getMonth());
				bs.setYear(businessScore.getYear());
				bs.setItem(businessScore.getItem());
				List<BusinessScore> list = businessScoreService.findList(bs);
				if(list != null && list.size()>0){
					addMessage(redirectAttributes, "保存失败，数据重复");
					return "redirect:"+Global.getAdminPath()+"/csyx/businessScore/?repage";

				}
			}
			businessScoreService.save(businessScore);
			addMessage(redirectAttributes, "保存营商环境评分成功");
		}catch (Exception e){
			e.printStackTrace();
			addMessage(redirectAttributes, "保存失败");
		}
		return "redirect:"+Global.getAdminPath()+"/csyx/businessScore/?repage";
	}
	
	@RequiresPermissions("csyx:businessScore:edit")
	@RequestMapping(value = "delete")
	public String delete(BusinessScore businessScore, RedirectAttributes redirectAttributes) {
		businessScoreService.delete(businessScore);
		addMessage(redirectAttributes, "删除营商环境评分成功");
		return "redirect:"+Global.getAdminPath()+"/csyx/businessScore/?repage";
	}

}