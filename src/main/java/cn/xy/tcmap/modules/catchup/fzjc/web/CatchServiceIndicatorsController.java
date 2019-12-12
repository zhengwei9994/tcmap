/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.fzjc.web;

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
import cn.xy.tcmap.modules.catchup.fzjc.entity.CatchServiceIndicators;
import cn.xy.tcmap.modules.catchup.fzjc.service.CatchServiceIndicatorsService;

/**
 * 区县综合政府服务指标Controller
 * @author gxq
 * @version 2018-10-22
 */
@Controller
@RequestMapping(value = "${adminPath}/fzjc/catchServiceIndicators")
public class CatchServiceIndicatorsController extends BaseController {

	@Autowired
	private CatchServiceIndicatorsService catchServiceIndicatorsService;
	
	@ModelAttribute
	public CatchServiceIndicators get(@RequestParam(required=false) String id) {
		CatchServiceIndicators entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = catchServiceIndicatorsService.get(id);
		}
		if (entity == null){
			entity = new CatchServiceIndicators();
		}
		return entity;
	}
	
	@RequiresPermissions("fzjc:catchServiceIndicators:view")
	@RequestMapping(value = {"list", ""})
	public String list(CatchServiceIndicators catchServiceIndicators, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CatchServiceIndicators> page = catchServiceIndicatorsService.findPage(new Page<CatchServiceIndicators>(request, response), catchServiceIndicators); 
		model.addAttribute("page", page);
		return "catchup/fzjc/catchServiceIndicatorsList";
	}

	@RequiresPermissions("fzjc:catchServiceIndicators:view")
	@RequestMapping(value = "form")
	public String form(CatchServiceIndicators catchServiceIndicators, Model model) {
		model.addAttribute("catchServiceIndicators", catchServiceIndicators);
		return "catchup/fzjc/catchServiceIndicatorsForm";
	}

	@RequiresPermissions("fzjc:catchServiceIndicators:edit")
	@RequestMapping(value = "save")
	public String save(CatchServiceIndicators catchServiceIndicators, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, catchServiceIndicators)){
			return form(catchServiceIndicators, model);
		}
		catchServiceIndicatorsService.save(catchServiceIndicators);
		addMessage(redirectAttributes, "保存区县综合政府服务指标成功");
		return "redirect:"+Global.getAdminPath()+"/fzjc/catchServiceIndicators/?repage";
	}
	
	@RequiresPermissions("fzjc:catchServiceIndicators:edit")
	@RequestMapping(value = "delete")
	public String delete(CatchServiceIndicators catchServiceIndicators, RedirectAttributes redirectAttributes) {
		catchServiceIndicatorsService.delete(catchServiceIndicators);
		addMessage(redirectAttributes, "删除区县综合政府服务指标成功");
		return "redirect:"+Global.getAdminPath()+"/fzjc/catchServiceIndicators/?repage";
	}

}