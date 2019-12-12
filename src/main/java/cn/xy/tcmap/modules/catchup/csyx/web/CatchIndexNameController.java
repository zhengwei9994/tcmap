/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csyx.web;

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
import cn.xy.tcmap.modules.catchup.csyx.entity.CatchIndexName;
import cn.xy.tcmap.modules.catchup.csyx.service.CatchIndexNameService;

/**
 * 经济指标名称Controller
 * @author xuzhou
 * @version 2018-05-24
 */
@Controller
@RequestMapping(value = "${adminPath}/csyx/catchIndexName")
public class CatchIndexNameController extends BaseController {

	@Autowired
	private CatchIndexNameService catchIndexNameService;
	
	@ModelAttribute
	public CatchIndexName get(@RequestParam(required=false) String id) {
		CatchIndexName entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = catchIndexNameService.get(id);
		}
		if (entity == null){
			entity = new CatchIndexName();
		}
		return entity;
	}
	
	@RequiresPermissions("csyx:catchIndexName:view")
	@RequestMapping(value = {"list", ""})
	public String list(CatchIndexName catchIndexName, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CatchIndexName> page = catchIndexNameService.findPage(new Page<CatchIndexName>(request, response), catchIndexName); 
		model.addAttribute("page", page);
		return "catchup/csyx/catchIndexNameList";
	}

	@RequiresPermissions("csyx:catchIndexName:view")
	@RequestMapping(value = "form")
	public String form(CatchIndexName catchIndexName, Model model) {
		model.addAttribute("catchIndexName", catchIndexName);
		return "catchup/csyx/catchIndexNameForm";
	}

	@RequiresPermissions("csyx:catchIndexName:edit")
	@RequestMapping(value = "save")
	public String save(CatchIndexName catchIndexName, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, catchIndexName)){
			return form(catchIndexName, model);
		}
		catchIndexNameService.save(catchIndexName);
		addMessage(redirectAttributes, "保存经济指标名称成功");
		return "redirect:"+Global.getAdminPath()+"/csyx/catchIndexName/?repage";
	}
	
	@RequiresPermissions("csyx:catchIndexName:edit")
	@RequestMapping(value = "delete")
	public String delete(CatchIndexName catchIndexName, RedirectAttributes redirectAttributes) {
		catchIndexNameService.delete(catchIndexName);
		addMessage(redirectAttributes, "删除经济指标名称成功");
		return "redirect:"+Global.getAdminPath()+"/csyx/catchIndexName/?repage";
	}

}