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
import cn.xy.tcmap.modules.catchup.zhcs.entity.CatchAreaRanking;
import cn.xy.tcmap.modules.catchup.zhcs.service.CatchAreaRankingService;

/**
 * 地区景点排名Controller
 * @author wl
 * @version 2018-09-27
 */
@Controller
@RequestMapping(value = "${adminPath}/zhcs/catchAreaRanking")
public class CatchAreaRankingController extends BaseController {

	@Autowired
	private CatchAreaRankingService catchAreaRankingService;
	
	@ModelAttribute
	public CatchAreaRanking get(@RequestParam(required=false) String id) {
		CatchAreaRanking entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = catchAreaRankingService.get(id);
		}
		if (entity == null){
			entity = new CatchAreaRanking();
		}
		return entity;
	}
	
	@RequiresPermissions("zhcs:catchAreaRanking:view")
	@RequestMapping(value = {"list", ""})
	public String list(CatchAreaRanking catchAreaRanking, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CatchAreaRanking> page = catchAreaRankingService.findPage(new Page<CatchAreaRanking>(request, response), catchAreaRanking); 
		model.addAttribute("page", page);
		return "catchup/zhcs/catchAreaRankingList";
	}

	@RequiresPermissions("zhcs:catchAreaRanking:view")
	@RequestMapping(value = "form")
	public String form(CatchAreaRanking catchAreaRanking, Model model) {
		model.addAttribute("catchAreaRanking", catchAreaRanking);
		return "catchup/zhcs/catchAreaRankingForm";
	}

	@RequiresPermissions("zhcs:catchAreaRanking:edit")
	@RequestMapping(value = "save")
	public String save(CatchAreaRanking catchAreaRanking, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, catchAreaRanking)){
			return form(catchAreaRanking, model);
		}
		catchAreaRankingService.save(catchAreaRanking);
		addMessage(redirectAttributes, "保存地区景点排名成功");
		return "redirect:"+Global.getAdminPath()+"/zhcs/catchAreaRanking/?repage";
	}
	
	@RequiresPermissions("zhcs:catchAreaRanking:edit")
	@RequestMapping(value = "delete")
	public String delete(CatchAreaRanking catchAreaRanking, RedirectAttributes redirectAttributes) {
		catchAreaRankingService.delete(catchAreaRanking);
		addMessage(redirectAttributes, "删除地区景点排名成功");
		return "redirect:"+Global.getAdminPath()+"/zhcs/catchAreaRanking/?repage";
	}

}