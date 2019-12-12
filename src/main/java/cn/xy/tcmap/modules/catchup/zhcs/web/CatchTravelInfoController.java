/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.zhcs.web;

import cn.xy.tcmap.common.config.Global;
import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.utils.StringUtils;
import cn.xy.tcmap.common.web.BaseController;
import cn.xy.tcmap.modules.catchup.zhcs.entity.CatchTravelInfo;
import cn.xy.tcmap.modules.catchup.zhcs.service.CatchTravelInfoService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 旅游相关信息Controller
 * @author liuyang
 * @version 2018-05-29
 */
@Controller
@RequestMapping(value = "${adminPath}/zhcs/catchTravelInfo")
public class CatchTravelInfoController extends BaseController {

	@Autowired
	private CatchTravelInfoService catchTravelInfoService;
	
	@ModelAttribute
	public CatchTravelInfo get(@RequestParam(required=false) String id) {
		CatchTravelInfo entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = catchTravelInfoService.get(id);
		}
		if (entity == null){
			entity = new CatchTravelInfo();
		}
		return entity;
	}
	
	@RequiresPermissions("zhcs:catchTravelInfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(CatchTravelInfo catchTravelInfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CatchTravelInfo> page = catchTravelInfoService.findPage(new Page<CatchTravelInfo>(request, response), catchTravelInfo); 
		model.addAttribute("page", page);
		return "catchup/zhcs/catchTravelInfoList";
	}

	@RequiresPermissions("zhcs:catchTravelInfo:view")
	@RequestMapping(value = "form")
	public String form(CatchTravelInfo catchTravelInfo, Model model) {
		model.addAttribute("catchTravelInfo", catchTravelInfo);
		return "catchup/zhcs/catchTravelInfoForm";
	}

	@RequiresPermissions("zhcs:catchTravelInfo:edit")
	@RequestMapping(value = "save")
	public String save(CatchTravelInfo catchTravelInfo, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, catchTravelInfo)){
			return form(catchTravelInfo, model);
		}
		catchTravelInfoService.save(catchTravelInfo);
		addMessage(redirectAttributes, "保存旅游相关信息成功");
		return "redirect:"+Global.getAdminPath()+"/zhcs/catchTravelInfo/?repage";
	}
	
	@RequiresPermissions("zhcs:catchTravelInfo:edit")
	@RequestMapping(value = "delete")
	public String delete(CatchTravelInfo catchTravelInfo, RedirectAttributes redirectAttributes) {
		catchTravelInfoService.delete(catchTravelInfo);
		addMessage(redirectAttributes, "删除旅游相关信息成功");
		return "redirect:"+Global.getAdminPath()+"/zhcs/catchTravelInfo/?repage";
	}

}