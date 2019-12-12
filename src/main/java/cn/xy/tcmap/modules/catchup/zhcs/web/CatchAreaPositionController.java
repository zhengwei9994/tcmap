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
import cn.xy.tcmap.modules.catchup.zhcs.entity.CatchAreaPosition;
import cn.xy.tcmap.modules.catchup.zhcs.service.CatchAreaPositionService;

/**
 * 铜川区域管理Controller
 * @author gxq
 * @version 2018-11-08
 */
@Controller
@RequestMapping(value = "${adminPath}/zhcs/catchAreaPosition")
public class CatchAreaPositionController extends BaseController {

	@Autowired
	private CatchAreaPositionService catchAreaPositionService;
	
	@ModelAttribute
	public CatchAreaPosition get(@RequestParam(required=false) String id) {
		CatchAreaPosition entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = catchAreaPositionService.get(id);
		}
		if (entity == null){
			entity = new CatchAreaPosition();
		}
		return entity;
	}
	
	@RequiresPermissions("zhcs:catchAreaPosition:view")
	@RequestMapping(value = {"list", ""})
	public String list(CatchAreaPosition catchAreaPosition, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CatchAreaPosition> page = catchAreaPositionService.findPage(new Page<CatchAreaPosition>(request, response), catchAreaPosition); 
		model.addAttribute("page", page);
		return "catchup/zhcs/catchAreaPositionList";
	}

	@RequiresPermissions("zhcs:catchAreaPosition:view")
	@RequestMapping(value = "form")
	public String form(CatchAreaPosition catchAreaPosition, Model model) {
		model.addAttribute("catchAreaPosition", catchAreaPosition);
		return "catchup/zhcs/catchAreaPositionForm";
	}

	@RequiresPermissions("zhcs:catchAreaPosition:edit")
	@RequestMapping(value = "save")
	public String save(CatchAreaPosition catchAreaPosition, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, catchAreaPosition)){
			return form(catchAreaPosition, model);
		}
		catchAreaPositionService.save(catchAreaPosition);
		addMessage(redirectAttributes, "保存铜川区域管理成功");
		return "redirect:"+Global.getAdminPath()+"/zhcs/catchAreaPosition/?repage";
	}
	
	@RequiresPermissions("zhcs:catchAreaPosition:edit")
	@RequestMapping(value = "delete")
	public String delete(CatchAreaPosition catchAreaPosition, RedirectAttributes redirectAttributes) {
		catchAreaPositionService.delete(catchAreaPosition);
		addMessage(redirectAttributes, "删除铜川区域管理成功");
		return "redirect:"+Global.getAdminPath()+"/zhcs/catchAreaPosition/?repage";
	}

}