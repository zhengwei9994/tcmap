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
import cn.xy.tcmap.modules.catchup.zhcs.entity.CatchHotelResInfo;
import cn.xy.tcmap.modules.catchup.zhcs.service.CatchHotelResInfoService;

/**
 * 酒店资源信息Controller
 * @author wl
 * @version 2018-09-27
 */
@Controller
@RequestMapping(value = "${adminPath}/zhcs/catchHotelResInfo")
public class CatchHotelResInfoController extends BaseController {

	@Autowired
	private CatchHotelResInfoService catchHotelResInfoService;
	
	@ModelAttribute
	public CatchHotelResInfo get(@RequestParam(required=false) String id) {
		CatchHotelResInfo entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = catchHotelResInfoService.get(id);
		}
		if (entity == null){
			entity = new CatchHotelResInfo();
		}
		return entity;
	}
	
	@RequiresPermissions("zhcs:catchHotelResInfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(CatchHotelResInfo catchHotelResInfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CatchHotelResInfo> page = catchHotelResInfoService.findPage(new Page<CatchHotelResInfo>(request, response), catchHotelResInfo); 
		model.addAttribute("page", page);
		return "catchup/zhcs/catchHotelResInfoList";
	}

	@RequiresPermissions("zhcs:catchHotelResInfo:view")
	@RequestMapping(value = "form")
	public String form(CatchHotelResInfo catchHotelResInfo, Model model) {
		model.addAttribute("catchHotelResInfo", catchHotelResInfo);
		return "catchup/zhcs/catchHotelResInfoForm";
	}

	@RequiresPermissions("zhcs:catchHotelResInfo:edit")
	@RequestMapping(value = "save")
	public String save(CatchHotelResInfo catchHotelResInfo, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, catchHotelResInfo)){
			return form(catchHotelResInfo, model);
		}
		catchHotelResInfoService.save(catchHotelResInfo);
		addMessage(redirectAttributes, "保存酒店资源信息成功");
		return "redirect:"+Global.getAdminPath()+"/zhcs/catchHotelResInfo/?repage";
	}
	
	@RequiresPermissions("zhcs:catchHotelResInfo:edit")
	@RequestMapping(value = "delete")
	public String delete(CatchHotelResInfo catchHotelResInfo, RedirectAttributes redirectAttributes) {
		catchHotelResInfoService.delete(catchHotelResInfo);
		addMessage(redirectAttributes, "删除酒店资源信息成功");
		return "redirect:"+Global.getAdminPath()+"/zhcs/catchHotelResInfo/?repage";
	}

}