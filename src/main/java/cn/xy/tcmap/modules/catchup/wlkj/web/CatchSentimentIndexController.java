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
import cn.xy.tcmap.modules.catchup.wlkj.entity.CatchSentimentIndex;
import cn.xy.tcmap.modules.catchup.wlkj.service.CatchSentimentIndexService;

/**
 * 舆情指数Controller
 * @author guoxunquan
 * @version 2018-09-20
 */
@Controller
@RequestMapping(value = "${adminPath}/wlkj/catchSentimentIndex")
public class CatchSentimentIndexController extends BaseController {

	@Autowired
	private CatchSentimentIndexService catchSentimentIndexService;
	
	@ModelAttribute
	public CatchSentimentIndex get(@RequestParam(required=false) String id) {
		CatchSentimentIndex entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = catchSentimentIndexService.get(id);
		}
		if (entity == null){
			entity = new CatchSentimentIndex();
		}
		return entity;
	}
	
	@RequiresPermissions("wlkj:catchSentimentIndex:view")
	@RequestMapping(value = {"list", ""})
	public String list(CatchSentimentIndex catchSentimentIndex, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CatchSentimentIndex> page = catchSentimentIndexService.findPage(new Page<CatchSentimentIndex>(request, response), catchSentimentIndex); 
		model.addAttribute("page", page);
		return "catchup/wlkj/catchSentimentIndexList";
	}

	@RequiresPermissions("wlkj:catchSentimentIndex:view")
	@RequestMapping(value = "form")
	public String form(CatchSentimentIndex catchSentimentIndex, Model model) {
		model.addAttribute("catchSentimentIndex", catchSentimentIndex);
		return "catchup/wlkj/catchSentimentIndexForm";
	}

	@RequiresPermissions("wlkj:catchSentimentIndex:edit")
	@RequestMapping(value = "save")
	public String save(CatchSentimentIndex catchSentimentIndex, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, catchSentimentIndex)){
			return form(catchSentimentIndex, model);
		}
		catchSentimentIndexService.save(catchSentimentIndex);
		addMessage(redirectAttributes, "保存舆情指数成功");
		return "redirect:"+Global.getAdminPath()+"/wlkj/catchSentimentIndex/?repage";
	}
	
	@RequiresPermissions("wlkj:catchSentimentIndex:edit")
	@RequestMapping(value = "delete")
	public String delete(CatchSentimentIndex catchSentimentIndex, RedirectAttributes redirectAttributes) {
		catchSentimentIndexService.delete(catchSentimentIndex);
		addMessage(redirectAttributes, "删除舆情指数成功");
		return "redirect:"+Global.getAdminPath()+"/wlkj/catchSentimentIndex/?repage";
	}

}