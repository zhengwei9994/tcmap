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
import cn.xy.tcmap.modules.catchup.csyx.entity.ShowInnovate;
import cn.xy.tcmap.modules.catchup.csyx.service.ShowInnovateService;

import java.util.List;

/**
 * 经济创新能力Controller
 * @author wufan
 * @version 2019-07-31
 */
@Controller
@RequestMapping(value = "${adminPath}/csyx/showInnovate")
public class ShowInnovateController extends BaseController {

	@Autowired
	private ShowInnovateService showInnovateService;
	
	@ModelAttribute
	public ShowInnovate get(@RequestParam(required=false) String id) {
		ShowInnovate entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = showInnovateService.get(id);
		}
		if (entity == null){
			entity = new ShowInnovate();
		}
		return entity;
	}
	
	@RequiresPermissions("csyx:showInnovate:view")
	@RequestMapping(value = {"list", ""})
	public String list(ShowInnovate showInnovate, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ShowInnovate> page = showInnovateService.findPage(new Page<ShowInnovate>(request, response), showInnovate); 
		model.addAttribute("page", page);
		return "catchup/csyx/showInnovateList";
	}

	@RequiresPermissions("csyx:showInnovate:view")
	@RequestMapping(value = "form")
	public String form(ShowInnovate showInnovate, Model model) {
		model.addAttribute("showInnovate", showInnovate);
		return "catchup/csyx/showInnovateForm";
	}

	@RequiresPermissions("csyx:showInnovate:edit")
	@RequestMapping(value = "save")
	public String save(ShowInnovate showInnovate, Model model, RedirectAttributes redirectAttributes) {
		try {
			if (!beanValidator(model, showInnovate)){
				return form(showInnovate, model);
			}
			if(showInnovate.getIsNewRecord()){
				ShowInnovate condition = new ShowInnovate();
				condition.setYear(showInnovate.getYear());
				List<ShowInnovate> list =showInnovateService.findList(condition);
				if(list != null && list.size()>0){
					addMessage(redirectAttributes, "保存经济创新能力失败，"+showInnovate.getYear()+"年数据已经存在，请重新添加");
					return "redirect:"+Global.getAdminPath()+"/csyx/showInnovate/?repage";
				}
			}
			showInnovateService.save(showInnovate);
			addMessage(redirectAttributes, "保存经济创新能力成功");
		}catch (Exception e){
			addMessage(redirectAttributes, "保存经济创新能力失败");
		}
		return "redirect:"+Global.getAdminPath()+"/csyx/showInnovate/?repage";
	}
	
	@RequiresPermissions("csyx:showInnovate:edit")
	@RequestMapping(value = "delete")
	public String delete(ShowInnovate showInnovate, RedirectAttributes redirectAttributes) {
		showInnovateService.delete(showInnovate);
		addMessage(redirectAttributes, "删除经济创新能力成功");
		return "redirect:"+Global.getAdminPath()+"/csyx/showInnovate/?repage";
	}

}