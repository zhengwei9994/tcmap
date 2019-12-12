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
import cn.xy.tcmap.modules.catchup.wlkj.entity.CatchSensitivityIndex;
import cn.xy.tcmap.modules.catchup.wlkj.service.CatchSensitivityIndexService;

/**
 * 敏感指数Controller
 * @author guoxunquan
 * @version 2018-09-20
 */
@Controller
@RequestMapping(value = "${adminPath}/wlkj/catchSensitivityIndex")
public class CatchSensitivityIndexController extends BaseController {

	@Autowired
	private CatchSensitivityIndexService catchSensitivityIndexService;
	
	@ModelAttribute
	public CatchSensitivityIndex get(@RequestParam(required=false) String id) {
		CatchSensitivityIndex entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = catchSensitivityIndexService.get(id);
		}
		if (entity == null){
			entity = new CatchSensitivityIndex();
		}
		return entity;
	}
	
	@RequiresPermissions("wlkj:catchSensitivityIndex:view")
	@RequestMapping(value = {"list", ""})
	public String list(CatchSensitivityIndex catchSensitivityIndex, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CatchSensitivityIndex> page = catchSensitivityIndexService.findPage(new Page<CatchSensitivityIndex>(request, response), catchSensitivityIndex); 
		model.addAttribute("page", page);
		return "catchup/wlkj/catchSensitivityIndexList";
	}

	@RequiresPermissions("wlkj:catchSensitivityIndex:view")
	@RequestMapping(value = "form")
	public String form(CatchSensitivityIndex catchSensitivityIndex, Model model) {
		model.addAttribute("catchSensitivityIndex", catchSensitivityIndex);
		return "catchup/wlkj/catchSensitivityIndexForm";
	}

	@RequiresPermissions("wlkj:catchSensitivityIndex:edit")
	@RequestMapping(value = "save")
	public String save(CatchSensitivityIndex catchSensitivityIndex, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, catchSensitivityIndex)){
			return form(catchSensitivityIndex, model);
		}
		catchSensitivityIndexService.save(catchSensitivityIndex);
		addMessage(redirectAttributes, "保存敏感指数成功");
		return "redirect:"+Global.getAdminPath()+"/wlkj/catchSensitivityIndex/?repage";
	}
	
	@RequiresPermissions("wlkj:catchSensitivityIndex:edit")
	@RequestMapping(value = "delete")
	public String delete(CatchSensitivityIndex catchSensitivityIndex, RedirectAttributes redirectAttributes) {
		catchSensitivityIndexService.delete(catchSensitivityIndex);
		addMessage(redirectAttributes, "删除敏感指数成功");
		return "redirect:"+Global.getAdminPath()+"/wlkj/catchSensitivityIndex/?repage";
	}

}