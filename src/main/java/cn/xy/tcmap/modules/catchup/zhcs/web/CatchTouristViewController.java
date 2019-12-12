/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.zhcs.web;

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
import cn.xy.tcmap.modules.catchup.zhcs.entity.CatchTouristView;
import cn.xy.tcmap.modules.catchup.zhcs.service.CatchTouristViewService;

/**
 * 旅游资产Controller
 * @author zzc
 * @version 2018-11-07
 */
@Controller
@RequestMapping(value = "${adminPath}/zhcs/catchTouristView")
public class CatchTouristViewController extends BaseController {

	@Autowired
	private CatchTouristViewService catchTouristViewService;
	
	@ModelAttribute
	public CatchTouristView get(@RequestParam(required=false) String id) {
		CatchTouristView entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = catchTouristViewService.get(id);
		}
		if (entity == null){
			entity = new CatchTouristView();
		}
		return entity;
	}
	
	@RequiresPermissions("zhcs:catchTouristView:view")
	@RequestMapping(value = {"list", ""})
	public String list(CatchTouristView catchTouristView, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CatchTouristView> page = catchTouristViewService.findPage(new Page<CatchTouristView>(request, response), catchTouristView); 
		model.addAttribute("page", page);
		return "catchup/zhcs/catchTouristViewList";
	}

	@RequiresPermissions("zhcs:catchTouristView:view")
	@RequestMapping(value = "form")
	public String form(CatchTouristView catchTouristView, Model model) {
		model.addAttribute("catchTouristView", catchTouristView);
		return "catchup/zhcs/catchTouristViewForm";
	}

	@RequiresPermissions("zhcs:catchTouristView:edit")
	@RequestMapping(value = "save")
	public String save(CatchTouristView catchTouristView, Model model, RedirectAttributes redirectAttributes) {
		try{
			if (!beanValidator(model, catchTouristView)){
				return form(catchTouristView, model);
			}
			catchTouristViewService.save(catchTouristView);
			addMessage(redirectAttributes, "保存旅游资产成功");
		}catch (Exception e){
			addMessage(redirectAttributes,"保存旅游资产失败");
		}

		return "redirect:"+Global.getAdminPath()+"/zhcs/catchTouristView/?repage";
	}
	
	@RequiresPermissions("zhcs:catchTouristView:edit")
	@RequestMapping(value = "delete")
	public String delete(CatchTouristView catchTouristView, RedirectAttributes redirectAttributes) {
		catchTouristViewService.delete(catchTouristView);
		addMessage(redirectAttributes, "删除旅游资产成功");
		return "redirect:"+Global.getAdminPath()+"/zhcs/catchTouristView/?repage";
	}

}