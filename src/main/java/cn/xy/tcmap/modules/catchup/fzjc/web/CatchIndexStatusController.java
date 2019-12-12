/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.fzjc.web;

import cn.xy.tcmap.common.config.Global;
import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.utils.StringUtils;
import cn.xy.tcmap.common.web.BaseController;
import cn.xy.tcmap.modules.catchup.fzjc.entity.CatchIndexStatus;
import cn.xy.tcmap.modules.catchup.fzjc.service.CatchIndexStatusService;
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
 * 运行指标完成率Controller
 * @author liuyang
 * @version 2018-05-29
 */
@Controller
@RequestMapping(value = "${adminPath}/fzjc/catchIndexStatus")
public class CatchIndexStatusController extends BaseController {

	@Autowired
	private CatchIndexStatusService catchIndexStatusService;
	
	@ModelAttribute
	public CatchIndexStatus get(@RequestParam(required=false) String id) {
		CatchIndexStatus entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = catchIndexStatusService.get(id);
		}
		if (entity == null){
			entity = new CatchIndexStatus();
		}
		return entity;
	}
	
	@RequiresPermissions("fzjc:catchIndexStatus:view")
	@RequestMapping(value = {"list", ""})
	public String list(CatchIndexStatus catchIndexStatus, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CatchIndexStatus> page = catchIndexStatusService.findPage(new Page<CatchIndexStatus>(request, response), catchIndexStatus); 
		model.addAttribute("page", page);
		return "catchup/fzjc/catchIndexStatusList";
	}

	@RequiresPermissions("fzjc:catchIndexStatus:view")
	@RequestMapping(value = "form")
	public String form(CatchIndexStatus catchIndexStatus, Model model) {
		model.addAttribute("catchIndexStatus", catchIndexStatus);
		return "catchup/fzjc/catchIndexStatusForm";
	}

	@RequiresPermissions("fzjc:catchIndexStatus:edit")
	@RequestMapping(value = "save")
	public String save(CatchIndexStatus catchIndexStatus, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, catchIndexStatus)){
			return form(catchIndexStatus, model);
		}
		catchIndexStatusService.save(catchIndexStatus);
		addMessage(redirectAttributes, "保存指标数据运行状态成功");
		return "redirect:"+Global.getAdminPath()+"/fzjc/catchIndexStatus/?repage";
	}
	
	@RequiresPermissions("fzjc:catchIndexStatus:edit")
	@RequestMapping(value = "delete")
	public String delete(CatchIndexStatus catchIndexStatus, RedirectAttributes redirectAttributes) {
		catchIndexStatusService.delete(catchIndexStatus);
		addMessage(redirectAttributes, "删除指标数据运行状态成功");
		return "redirect:"+Global.getAdminPath()+"/fzjc/catchIndexStatus/?repage";
	}

}