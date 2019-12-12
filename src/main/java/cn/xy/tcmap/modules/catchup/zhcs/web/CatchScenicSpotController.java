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
import cn.xy.tcmap.modules.catchup.zhcs.entity.CatchScenicSpot;
import cn.xy.tcmap.modules.catchup.zhcs.service.CatchScenicSpotService;

/**
 * 旅游资产Controller
 * @author liuyang
 * @version 2018-06-11
 */
@Controller
@RequestMapping(value = "${adminPath}/zhcs/catchScenicSpot")
public class CatchScenicSpotController extends BaseController {

	@Autowired
	private CatchScenicSpotService catchScenicSpotService;
	
	@ModelAttribute
	public CatchScenicSpot get(@RequestParam(required=false) String id) {
		CatchScenicSpot entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = catchScenicSpotService.get(id);
		}
		if (entity == null){
			entity = new CatchScenicSpot();
		}
		return entity;
	}
	
	@RequiresPermissions("zhcs:catchScenicSpot:view")
	@RequestMapping(value = {"list", ""})
	public String list(CatchScenicSpot catchScenicSpot, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CatchScenicSpot> page = catchScenicSpotService.findPage(new Page<CatchScenicSpot>(request, response), catchScenicSpot); 
		model.addAttribute("page", page);
		return "catchup/zhcs/catchScenicSpotList";
	}

	@RequiresPermissions("zhcs:catchScenicSpot:view")
	@RequestMapping(value = "form")
	public String form(CatchScenicSpot catchScenicSpot, Model model) {
		model.addAttribute("catchScenicSpot", catchScenicSpot);
		return "catchup/zhcs/catchScenicSpotForm";
	}

	@RequiresPermissions("zhcs:catchScenicSpot:edit")
	@RequestMapping(value = "save")
	public String save(CatchScenicSpot catchScenicSpot, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, catchScenicSpot)){
			return form(catchScenicSpot, model);
		}
		catchScenicSpotService.save(catchScenicSpot);
		addMessage(redirectAttributes, "保存旅游资产成功");
		return "redirect:"+Global.getAdminPath()+"/zhcs/catchScenicSpot/?repage";
	}
	
	@RequiresPermissions("zhcs:catchScenicSpot:edit")
	@RequestMapping(value = "delete")
	public String delete(CatchScenicSpot catchScenicSpot, RedirectAttributes redirectAttributes) {
		catchScenicSpotService.delete(catchScenicSpot);
		addMessage(redirectAttributes, "删除旅游资产成功");
		return "redirect:"+Global.getAdminPath()+"/zhcs/catchScenicSpot/?repage";
	}

}