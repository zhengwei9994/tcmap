/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.fzjc.web;

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
import cn.xy.tcmap.modules.catchup.fzjc.entity.CatchElectronicEvidence;
import cn.xy.tcmap.modules.catchup.fzjc.service.CatchElectronicEvidenceService;

/**
 * 电子证照使用率Controller
 * @author gxq
 * @version 2018-10-19
 */
@Controller
@RequestMapping(value = "${adminPath}/fzjc/catchElectronicEvidence")
public class CatchElectronicEvidenceController extends BaseController {

	@Autowired
	private CatchElectronicEvidenceService catchElectronicEvidenceService;
	
	@ModelAttribute
	public CatchElectronicEvidence get(@RequestParam(required=false) String id) {
		CatchElectronicEvidence entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = catchElectronicEvidenceService.get(id);
		}
		if (entity == null){
			entity = new CatchElectronicEvidence();
		}
		return entity;
	}
	
	@RequiresPermissions("fzjc:catchElectronicEvidence:view")
	@RequestMapping(value = {"list", ""})
	public String list(CatchElectronicEvidence catchElectronicEvidence, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CatchElectronicEvidence> page = catchElectronicEvidenceService.findPage(new Page<CatchElectronicEvidence>(request, response), catchElectronicEvidence); 
		model.addAttribute("page", page);
		return "catchup/fzjc/catchElectronicEvidenceList";
	}

	@RequiresPermissions("fzjc:catchElectronicEvidence:view")
	@RequestMapping(value = "form")
	public String form(CatchElectronicEvidence catchElectronicEvidence, Model model) {
		model.addAttribute("catchElectronicEvidence", catchElectronicEvidence);
		return "catchup/fzjc/catchElectronicEvidenceForm";
	}

	@RequiresPermissions("fzjc:catchElectronicEvidence:edit")
	@RequestMapping(value = "save")
	public String save(CatchElectronicEvidence catchElectronicEvidence, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, catchElectronicEvidence)){
			return form(catchElectronicEvidence, model);
		}
		catchElectronicEvidenceService.save(catchElectronicEvidence);
		addMessage(redirectAttributes, "保存电子证照使用率成功");
		return "redirect:"+Global.getAdminPath()+"/fzjc/catchElectronicEvidence/?repage";
	}
	
	@RequiresPermissions("fzjc:catchElectronicEvidence:edit")
	@RequestMapping(value = "delete")
	public String delete(CatchElectronicEvidence catchElectronicEvidence, RedirectAttributes redirectAttributes) {
		catchElectronicEvidenceService.delete(catchElectronicEvidence);
		addMessage(redirectAttributes, "删除电子证照使用率成功");
		return "redirect:"+Global.getAdminPath()+"/fzjc/catchElectronicEvidence/?repage";
	}

}