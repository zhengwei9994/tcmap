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
import cn.xy.tcmap.modules.catchup.wlkj.entity.CatchMediaMtlx;
import cn.xy.tcmap.modules.catchup.wlkj.service.CatchMediaMtlxService;

/**
 * 媒体类型Controller
 * @author guoxunquan
 * @version 2018-09-20
 */
@Controller
@RequestMapping(value = "${adminPath}/wlkj/catchMediaMtlx")
public class CatchMediaMtlxController extends BaseController {

	@Autowired
	private CatchMediaMtlxService catchMediaMtlxService;
	
	@ModelAttribute
	public CatchMediaMtlx get(@RequestParam(required=false) String id) {
		CatchMediaMtlx entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = catchMediaMtlxService.get(id);
		}
		if (entity == null){
			entity = new CatchMediaMtlx();
		}
		return entity;
	}
	
	@RequiresPermissions("wlkj:catchMediaMtlx:view")
	@RequestMapping(value = {"list", ""})
	public String list(CatchMediaMtlx catchMediaMtlx, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CatchMediaMtlx> page = catchMediaMtlxService.findPage(new Page<CatchMediaMtlx>(request, response), catchMediaMtlx); 
		model.addAttribute("page", page);
		return "catchup/wlkj/catchMediaMtlxList";
	}

	@RequiresPermissions("wlkj:catchMediaMtlx:view")
	@RequestMapping(value = "form")
	public String form(CatchMediaMtlx catchMediaMtlx, Model model) {
		model.addAttribute("catchMediaMtlx", catchMediaMtlx);
		return "catchup/wlkj/catchMediaMtlxForm";
	}

	@RequiresPermissions("wlkj:catchMediaMtlx:edit")
	@RequestMapping(value = "save")
	public String save(CatchMediaMtlx catchMediaMtlx, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, catchMediaMtlx)){
			return form(catchMediaMtlx, model);
		}
		catchMediaMtlxService.save(catchMediaMtlx);
		addMessage(redirectAttributes, "保存媒体类型成功");
		return "redirect:"+Global.getAdminPath()+"/wlkj/catchMediaMtlx/?repage";
	}
	
	@RequiresPermissions("wlkj:catchMediaMtlx:edit")
	@RequestMapping(value = "delete")
	public String delete(CatchMediaMtlx catchMediaMtlx, RedirectAttributes redirectAttributes) {
		catchMediaMtlxService.delete(catchMediaMtlx);
		addMessage(redirectAttributes, "删除媒体类型成功");
		return "redirect:"+Global.getAdminPath()+"/wlkj/catchMediaMtlx/?repage";
	}

}