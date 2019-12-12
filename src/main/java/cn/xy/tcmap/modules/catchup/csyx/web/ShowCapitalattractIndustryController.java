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
import cn.xy.tcmap.modules.catchup.csyx.entity.ShowCapitalattractIndustry;
import cn.xy.tcmap.modules.catchup.csyx.service.ShowCapitalattractIndustryService;

import java.util.List;

/**
 * 经济三产占比Controller
 * @author wufan
 * @version 2019-07-31
 */
@Controller
@RequestMapping(value = "${adminPath}/csyx/showCapitalattractIndustry")
public class ShowCapitalattractIndustryController extends BaseController {

	@Autowired
	private ShowCapitalattractIndustryService showCapitalattractIndustryService;
	
	@ModelAttribute
	public ShowCapitalattractIndustry get(@RequestParam(required=false) String id) {
		ShowCapitalattractIndustry entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = showCapitalattractIndustryService.get(id);
		}
		if (entity == null){
			entity = new ShowCapitalattractIndustry();
		}
		return entity;
	}
	
	@RequiresPermissions("csyx:showCapitalattractIndustry:view")
	@RequestMapping(value = {"list", ""})
	public String list(ShowCapitalattractIndustry showCapitalattractIndustry, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ShowCapitalattractIndustry> page = showCapitalattractIndustryService.findPage(new Page<ShowCapitalattractIndustry>(request, response), showCapitalattractIndustry); 
		model.addAttribute("page", page);
		return "catchup/csyx/showCapitalattractIndustryList";
	}

	@RequiresPermissions("csyx:showCapitalattractIndustry:view")
	@RequestMapping(value = "form")
	public String form(ShowCapitalattractIndustry showCapitalattractIndustry, Model model) {
		model.addAttribute("showCapitalattractIndustry", showCapitalattractIndustry);
		return "catchup/csyx/showCapitalattractIndustryForm";
	}

	@RequiresPermissions("csyx:showCapitalattractIndustry:edit")
	@RequestMapping(value = "save")
	public String save(ShowCapitalattractIndustry showCapitalattractIndustry, Model model, RedirectAttributes redirectAttributes) {
		try {
			if (!beanValidator(model, showCapitalattractIndustry)){
				return form(showCapitalattractIndustry, model);
			}
			if(showCapitalattractIndustry.getIsNewRecord()){
				ShowCapitalattractIndustry condition = new ShowCapitalattractIndustry();
				condition.setYear(showCapitalattractIndustry.getYear());
				condition.setIndustry(showCapitalattractIndustry.getIndustry());
				List<ShowCapitalattractIndustry> list = showCapitalattractIndustryService.findList(condition);
				if(list != null && list.size()>0){
					throw new Exception("数据重复");
				}
			}
			showCapitalattractIndustryService.save(showCapitalattractIndustry);
			addMessage(redirectAttributes, "保存经济三产占比成功");
		}catch (Exception e){
			addMessage(redirectAttributes, "保存经济三产占比失败，失败原因："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/csyx/showCapitalattractIndustry/?repage";
	}
	
	@RequiresPermissions("csyx:showCapitalattractIndustry:edit")
	@RequestMapping(value = "delete")
	public String delete(ShowCapitalattractIndustry showCapitalattractIndustry, RedirectAttributes redirectAttributes) {
		showCapitalattractIndustryService.delete(showCapitalattractIndustry);
		addMessage(redirectAttributes, "删除经济三产占比成功");
		return "redirect:"+Global.getAdminPath()+"/csyx/showCapitalattractIndustry/?repage";
	}

}