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
import cn.xy.tcmap.modules.catchup.wlkj.entity.CatchMediaHyd;
import cn.xy.tcmap.modules.catchup.wlkj.service.CatchMediaHydService;

/**
 * 媒体活跃度Controller
 * @author guoxunquan
 * @version 2018-09-20
 */
@Controller
@RequestMapping(value = "${adminPath}/wlkj/catchMediaHyd")
public class CatchMediaHydController extends BaseController {

	@Autowired
	private CatchMediaHydService catchMediaHydService;
	
	@ModelAttribute
	public CatchMediaHyd get(@RequestParam(required=false) String id) {
		CatchMediaHyd entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = catchMediaHydService.get(id);
		}
		if (entity == null){
			entity = new CatchMediaHyd();
		}
		return entity;
	}
	
	@RequiresPermissions("wlkj:catchMediaHyd:view")
	@RequestMapping(value = {"list", ""})
	public String list(CatchMediaHyd catchMediaHyd, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CatchMediaHyd> page = catchMediaHydService.findPage(new Page<CatchMediaHyd>(request, response), catchMediaHyd); 
		model.addAttribute("page", page);
		return "catchup/wlkj/catchMediaHydList";
	}

	@RequiresPermissions("wlkj:catchMediaHyd:view")
	@RequestMapping(value = "form")
	public String form(CatchMediaHyd catchMediaHyd, Model model) {
		model.addAttribute("catchMediaHyd", catchMediaHyd);
		return "catchup/wlkj/catchMediaHydForm";
	}

	@RequiresPermissions("wlkj:catchMediaHyd:edit")
	@RequestMapping(value = "save")
	public String save(CatchMediaHyd catchMediaHyd, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, catchMediaHyd)){
			return form(catchMediaHyd, model);
		}
		catchMediaHydService.save(catchMediaHyd);
		addMessage(redirectAttributes, "保存媒体活跃度成功");
		return "redirect:"+Global.getAdminPath()+"/wlkj/catchMediaHyd/?repage";
	}
	
	@RequiresPermissions("wlkj:catchMediaHyd:edit")
	@RequestMapping(value = "delete")
	public String delete(CatchMediaHyd catchMediaHyd, RedirectAttributes redirectAttributes) {
		catchMediaHydService.delete(catchMediaHyd);
		addMessage(redirectAttributes, "删除媒体活跃度成功");
		return "redirect:"+Global.getAdminPath()+"/wlkj/catchMediaHyd/?repage";
	}

}