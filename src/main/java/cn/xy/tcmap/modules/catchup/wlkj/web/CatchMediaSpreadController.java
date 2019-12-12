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
import cn.xy.tcmap.modules.catchup.wlkj.entity.CatchMediaSpread;
import cn.xy.tcmap.modules.catchup.wlkj.service.CatchMediaSpreadService;

/**
 * 媒体分布Controller
 * @author guoxunquan
 * @version 2018-09-20
 */
@Controller
@RequestMapping(value = "${adminPath}/wlkj/catchMediaSpread")
public class CatchMediaSpreadController extends BaseController {

	@Autowired
	private CatchMediaSpreadService catchMediaSpreadService;
	
	@ModelAttribute
	public CatchMediaSpread get(@RequestParam(required=false) String id) {
		CatchMediaSpread entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = catchMediaSpreadService.get(id);
		}
		if (entity == null){
			entity = new CatchMediaSpread();
		}
		return entity;
	}
	
	@RequiresPermissions("wlkj:catchMediaSpread:view")
	@RequestMapping(value = {"list", ""})
	public String list(CatchMediaSpread catchMediaSpread, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CatchMediaSpread> page = catchMediaSpreadService.findPage(new Page<CatchMediaSpread>(request, response), catchMediaSpread); 
		model.addAttribute("page", page);
		return "catchup/wlkj/catchMediaSpreadList";
	}

	@RequiresPermissions("wlkj:catchMediaSpread:view")
	@RequestMapping(value = "form")
	public String form(CatchMediaSpread catchMediaSpread, Model model) {
		model.addAttribute("catchMediaSpread", catchMediaSpread);
		return "catchup/wlkj/catchMediaSpreadForm";
	}

	@RequiresPermissions("wlkj:catchMediaSpread:edit")
	@RequestMapping(value = "save")
	public String save(CatchMediaSpread catchMediaSpread, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, catchMediaSpread)){
			return form(catchMediaSpread, model);
		}
		catchMediaSpreadService.save(catchMediaSpread);
		addMessage(redirectAttributes, "保存媒体分布成功");
		return "redirect:"+Global.getAdminPath()+"/wlkj/catchMediaSpread/?repage";
	}
	
	@RequiresPermissions("wlkj:catchMediaSpread:edit")
	@RequestMapping(value = "delete")
	public String delete(CatchMediaSpread catchMediaSpread, RedirectAttributes redirectAttributes) {
		catchMediaSpreadService.delete(catchMediaSpread);
		addMessage(redirectAttributes, "删除媒体分布成功");
		return "redirect:"+Global.getAdminPath()+"/wlkj/catchMediaSpread/?repage";
	}

}