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
import cn.xy.tcmap.modules.catchup.wlkj.entity.CatchMediaResource;
import cn.xy.tcmap.modules.catchup.wlkj.service.CatchMediaResourceService;

/**
 * 媒体来源Controller
 * @author guoxunquan
 * @version 2018-09-20
 */
@Controller
@RequestMapping(value = "${adminPath}/wlkj/catchMediaResource")
public class CatchMediaResourceController extends BaseController {

	@Autowired
	private CatchMediaResourceService catchMediaResourceService;
	
	@ModelAttribute
	public CatchMediaResource get(@RequestParam(required=false) String id) {
		CatchMediaResource entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = catchMediaResourceService.get(id);
		}
		if (entity == null){
			entity = new CatchMediaResource();
		}
		return entity;
	}
	
	@RequiresPermissions("wlkj:catchMediaResource:view")
	@RequestMapping(value = {"list", ""})
	public String list(CatchMediaResource catchMediaResource, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CatchMediaResource> page = catchMediaResourceService.findPage(new Page<CatchMediaResource>(request, response), catchMediaResource); 
		model.addAttribute("page", page);
		return "catchup/wlkj/catchMediaResourceList";
	}

	@RequiresPermissions("wlkj:catchMediaResource:view")
	@RequestMapping(value = "form")
	public String form(CatchMediaResource catchMediaResource, Model model) {
		model.addAttribute("catchMediaResource", catchMediaResource);
		return "catchup/wlkj/catchMediaResourceForm";
	}

	@RequiresPermissions("wlkj:catchMediaResource:edit")
	@RequestMapping(value = "save")
	public String save(CatchMediaResource catchMediaResource, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, catchMediaResource)){
			return form(catchMediaResource, model);
		}
		catchMediaResourceService.save(catchMediaResource);
		addMessage(redirectAttributes, "保存媒体来源成功");
		return "redirect:"+Global.getAdminPath()+"/wlkj/catchMediaResource/?repage";
	}
	
	@RequiresPermissions("wlkj:catchMediaResource:edit")
	@RequestMapping(value = "delete")
	public String delete(CatchMediaResource catchMediaResource, RedirectAttributes redirectAttributes) {
		catchMediaResourceService.delete(catchMediaResource);
		addMessage(redirectAttributes, "删除媒体来源成功");
		return "redirect:"+Global.getAdminPath()+"/wlkj/catchMediaResource/?repage";
	}

}