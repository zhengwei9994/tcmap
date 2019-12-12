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
import cn.xy.tcmap.modules.catchup.csyx.entity.LiveGovementDepartment;
import cn.xy.tcmap.modules.catchup.csyx.service.LiveGovementDepartmentService;

/**
 * 行业主管部门事项分布Controller
 * @author wufan
 * @version 2019-10-14
 */
@Controller
@RequestMapping(value = "${adminPath}/csyx/liveGovementDepartment")
public class LiveGovementDepartmentController extends BaseController {

	@Autowired
	private LiveGovementDepartmentService liveGovementDepartmentService;
	
	@ModelAttribute
	public LiveGovementDepartment get(@RequestParam(required=false) String id) {
		LiveGovementDepartment entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = liveGovementDepartmentService.get(id);
		}
		if (entity == null){
			entity = new LiveGovementDepartment();
		}
		return entity;
	}
	
	@RequiresPermissions("csyx:liveGovementDepartment:view")
	@RequestMapping(value = {"list", ""})
	public String list(LiveGovementDepartment liveGovementDepartment, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<LiveGovementDepartment> page = liveGovementDepartmentService.findPage(new Page<LiveGovementDepartment>(request, response), liveGovementDepartment); 
		model.addAttribute("page", page);
		return "catchup/csyx/liveGovementDepartmentList";
	}

	@RequiresPermissions("csyx:liveGovementDepartment:view")
	@RequestMapping(value = "form")
	public String form(LiveGovementDepartment liveGovementDepartment, Model model) {
		model.addAttribute("liveGovementDepartment", liveGovementDepartment);
		return "catchup/csyx/liveGovementDepartmentForm";
	}

	@RequiresPermissions("csyx:liveGovementDepartment:edit")
	@RequestMapping(value = "save")
	public String save(LiveGovementDepartment liveGovementDepartment, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, liveGovementDepartment)){
			return form(liveGovementDepartment, model);
		}
		liveGovementDepartmentService.save(liveGovementDepartment);
		addMessage(redirectAttributes, "保存行业主管部门事项分布成功");
		return "redirect:"+Global.getAdminPath()+"/csyx/liveGovementDepartment/?repage";
	}
	
	@RequiresPermissions("csyx:liveGovementDepartment:edit")
	@RequestMapping(value = "delete")
	public String delete(LiveGovementDepartment liveGovementDepartment, RedirectAttributes redirectAttributes) {
		liveGovementDepartmentService.delete(liveGovementDepartment);
		addMessage(redirectAttributes, "删除行业主管部门事项分布成功");
		return "redirect:"+Global.getAdminPath()+"/csyx/liveGovementDepartment/?repage";
	}

}