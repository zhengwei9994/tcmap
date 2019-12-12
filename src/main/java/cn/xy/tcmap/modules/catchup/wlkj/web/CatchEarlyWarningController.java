/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.wlkj.web;

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
import cn.xy.tcmap.modules.catchup.wlkj.entity.CatchEarlyWarning;
import cn.xy.tcmap.modules.catchup.wlkj.service.CatchEarlyWarningService;

import java.util.Date;

/**
 * 红色预警Controller
 * @author xuzhou
 * @version 2018-05-28
 */
@Controller
@RequestMapping(value = "${adminPath}/wlkj/catchEarlyWarning")
public class CatchEarlyWarningController extends BaseController {

	@Autowired
	private CatchEarlyWarningService catchEarlyWarningService;
	
	@ModelAttribute
	public CatchEarlyWarning get(@RequestParam(required=false) String id) {
		CatchEarlyWarning entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = catchEarlyWarningService.get(id);
		}
		if (entity == null){
			entity = new CatchEarlyWarning();
		}
		return entity;
	}
	
	@RequiresPermissions("wlkj:catchEarlyWarning:view")
	@RequestMapping(value = {"list", ""})
	public String list(CatchEarlyWarning catchEarlyWarning, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CatchEarlyWarning> page = catchEarlyWarningService.findPage(new Page<CatchEarlyWarning>(request, response), catchEarlyWarning); 
		model.addAttribute("page", page);
		return "catchup/wlkj/catchEarlyWarningList";
	}

	@RequiresPermissions("wlkj:catchEarlyWarning:view")
	@RequestMapping(value = "form")
	public String form(CatchEarlyWarning catchEarlyWarning, Model model) {
		model.addAttribute("catchEarlyWarning", catchEarlyWarning);
		return "catchup/wlkj/catchEarlyWarningForm";
	}

	@RequiresPermissions("wlkj:catchEarlyWarning:edit")
	@RequestMapping(value = "save")
	public String save(CatchEarlyWarning catchEarlyWarning, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, catchEarlyWarning)){
			return form(catchEarlyWarning, model);
		}
		catchEarlyWarningService.save(catchEarlyWarning);
		addMessage(redirectAttributes, "保存红色预警成功");
		return "redirect:"+Global.getAdminPath()+"/wlkj/catchEarlyWarning/?repage";
	}
	
	@RequiresPermissions("wlkj:catchEarlyWarning:edit")
	@RequestMapping(value = "delete")
	public String delete(CatchEarlyWarning catchEarlyWarning, RedirectAttributes redirectAttributes) {
		catchEarlyWarningService.delete(catchEarlyWarning);
		addMessage(redirectAttributes, "删除红色预警成功");
		return "redirect:"+Global.getAdminPath()+"/wlkj/catchEarlyWarning/?repage";
	}

}