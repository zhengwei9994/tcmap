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
import cn.xy.tcmap.modules.catchup.wlkj.entity.CatchTradeIndex;
import cn.xy.tcmap.modules.catchup.wlkj.service.CatchTradeIndexService;

/**
 * 行业指数Controller
 * @author guoxunquan
 * @version 2018-09-20
 */
@Controller
@RequestMapping(value = "${adminPath}/wlkj/catchTradeIndex")
public class CatchTradeIndexController extends BaseController {

	@Autowired
	private CatchTradeIndexService catchTradeIndexService;
	
	@ModelAttribute
	public CatchTradeIndex get(@RequestParam(required=false) String id) {
		CatchTradeIndex entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = catchTradeIndexService.get(id);
		}
		if (entity == null){
			entity = new CatchTradeIndex();
		}
		return entity;
	}
	
	@RequiresPermissions("wlkj:catchTradeIndex:view")
	@RequestMapping(value = {"list", ""})
	public String list(CatchTradeIndex catchTradeIndex, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CatchTradeIndex> page = catchTradeIndexService.findPage(new Page<CatchTradeIndex>(request, response), catchTradeIndex); 
		model.addAttribute("page", page);
		return "catchup/wlkj/catchTradeIndexList";
	}

	@RequiresPermissions("wlkj:catchTradeIndex:view")
	@RequestMapping(value = "form")
	public String form(CatchTradeIndex catchTradeIndex, Model model) {
		model.addAttribute("catchTradeIndex", catchTradeIndex);
		return "catchup/wlkj/catchTradeIndexForm";
	}

	@RequiresPermissions("wlkj:catchTradeIndex:edit")
	@RequestMapping(value = "save")
	public String save(CatchTradeIndex catchTradeIndex, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, catchTradeIndex)){
			return form(catchTradeIndex, model);
		}
		catchTradeIndexService.save(catchTradeIndex);
		addMessage(redirectAttributes, "保存行业指数成功");
		return "redirect:"+Global.getAdminPath()+"/wlkj/catchTradeIndex/?repage";
	}
	
	@RequiresPermissions("wlkj:catchTradeIndex:edit")
	@RequestMapping(value = "delete")
	public String delete(CatchTradeIndex catchTradeIndex, RedirectAttributes redirectAttributes) {
		catchTradeIndexService.delete(catchTradeIndex);
		addMessage(redirectAttributes, "删除行业指数成功");
		return "redirect:"+Global.getAdminPath()+"/wlkj/catchTradeIndex/?repage";
	}

}