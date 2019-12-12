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
import cn.xy.tcmap.modules.catchup.zhcs.entity.CatchStayTime;
import cn.xy.tcmap.modules.catchup.zhcs.service.CatchStayTimeService;

/**
 * 停留时长分布Controller
 * @author wl
 * @version 2018-09-29
 */
@Controller
@RequestMapping(value = "${adminPath}/zhcs/catchStayTime")
public class CatchStayTimeController extends BaseController {

	@Autowired
	private CatchStayTimeService catchStayTimeService;
	
	@ModelAttribute
	public CatchStayTime get(@RequestParam(required=false) String id) {
		CatchStayTime entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = catchStayTimeService.get(id);
		}
		if (entity == null){
			entity = new CatchStayTime();
		}
		return entity;
	}
	
	@RequiresPermissions("zhcs:catchStayTime:view")
	@RequestMapping(value = {"list", ""})
	public String list(CatchStayTime catchStayTime, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CatchStayTime> page = catchStayTimeService.findPage(new Page<CatchStayTime>(request, response), catchStayTime); 
		model.addAttribute("page", page);
		return "catchup/zhcs/catchStayTimeList";
	}

	@RequiresPermissions("zhcs:catchStayTime:view")
	@RequestMapping(value = "form")
	public String form(CatchStayTime catchStayTime, Model model) {
		model.addAttribute("catchStayTime", catchStayTime);
		return "catchup/zhcs/catchStayTimeForm";
	}

	@RequiresPermissions("zhcs:catchStayTime:edit")
	@RequestMapping(value = "save")
	public String save(CatchStayTime catchStayTime, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, catchStayTime)){
			return form(catchStayTime, model);
		}
		catchStayTimeService.save(catchStayTime);
		addMessage(redirectAttributes, "保存停留时长分布成功");
		return "redirect:"+Global.getAdminPath()+"/zhcs/catchStayTime/?repage";
	}
	
	@RequiresPermissions("zhcs:catchStayTime:edit")
	@RequestMapping(value = "delete")
	public String delete(CatchStayTime catchStayTime, RedirectAttributes redirectAttributes) {
		catchStayTimeService.delete(catchStayTime);
		addMessage(redirectAttributes, "删除停留时长分布成功");
		return "redirect:"+Global.getAdminPath()+"/zhcs/catchStayTime/?repage";
	}

}