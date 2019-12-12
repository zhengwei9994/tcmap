/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csly.web;

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
import cn.xy.tcmap.modules.catchup.csly.entity.TouristsTransfer;
import cn.xy.tcmap.modules.catchup.csly.service.TouristsTransferService;

/**
 * 客流迁移Controller
 * @author wufan
 * @version 2019-08-16
 */
@Controller
@RequestMapping(value = "${adminPath}/csly/touristsTransfer")
public class TouristsTransferController extends BaseController {

	@Autowired
	private TouristsTransferService touristsTransferService;
	
	@ModelAttribute
	public TouristsTransfer get(@RequestParam(required=false) String id) {
		TouristsTransfer entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = touristsTransferService.get(id);
		}
		if (entity == null){
			entity = new TouristsTransfer();
		}
		return entity;
	}
	
	@RequiresPermissions("csly:touristsTransfer:view")
	@RequestMapping(value = {"list", ""})
	public String list(TouristsTransfer touristsTransfer, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TouristsTransfer> page = touristsTransferService.findPage(new Page<TouristsTransfer>(request, response), touristsTransfer); 
		model.addAttribute("page", page);
		return "catchup/csly/touristsTransferList";
	}

	@RequiresPermissions("csly:touristsTransfer:view")
	@RequestMapping(value = "form")
	public String form(TouristsTransfer touristsTransfer, Model model) {
		model.addAttribute("touristsTransfer", touristsTransfer);
		return "catchup/csly/touristsTransferForm";
	}

	@RequiresPermissions("csly:touristsTransfer:edit")
	@RequestMapping(value = "save")
	public String save(TouristsTransfer touristsTransfer, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, touristsTransfer)){
			return form(touristsTransfer, model);
		}
		touristsTransferService.save(touristsTransfer);
		addMessage(redirectAttributes, "保存客流迁移成功");
		return "redirect:"+Global.getAdminPath()+"/csly/touristsTransfer/?repage";
	}
	
	@RequiresPermissions("csly:touristsTransfer:edit")
	@RequestMapping(value = "delete")
	public String delete(TouristsTransfer touristsTransfer, RedirectAttributes redirectAttributes) {
		touristsTransferService.delete(touristsTransfer);
		addMessage(redirectAttributes, "删除客流迁移成功");
		return "redirect:"+Global.getAdminPath()+"/csly/touristsTransfer/?repage";
	}

}