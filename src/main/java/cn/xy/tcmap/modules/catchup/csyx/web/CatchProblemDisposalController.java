package cn.xy.tcmap.modules.catchup.csyx.web;

import cn.xy.tcmap.common.web.BaseController;
import cn.xy.tcmap.modules.catchup.csyx.entity.CatchProblemDisposal;
import cn.xy.tcmap.modules.catchup.csyx.service.CatchProblemDisposalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.xy.tcmap.common.config.Global;
import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.utils.StringUtils;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "${adminPath}/csyx/catchProblemDisposal")
public class CatchProblemDisposalController extends BaseController {

    @Autowired
    private CatchProblemDisposalService catchProblemDisposalService;

    @ModelAttribute
	public CatchProblemDisposal get(@RequestParam(required=false) String id) {
		CatchProblemDisposal entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = catchProblemDisposalService.get(id);
		}
		if (entity == null){
			entity = new CatchProblemDisposal();
		}
		return entity;
	}

	@RequiresPermissions("csyx:catchProblemDisposal:view")
	@RequestMapping(value = {"list", ""})
	public String list(CatchProblemDisposal catchProblemDisposal, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CatchProblemDisposal> page = catchProblemDisposalService.findPage(new Page<CatchProblemDisposal>(request, response), catchProblemDisposal); 
		model.addAttribute("page", page);
		return "catchup/csyx/catchProblemDisposalList";
	}

	@RequiresPermissions("csyx:catchProblemDisposal:view")
	@RequestMapping(value = "form")
	public String form(CatchProblemDisposal catchProblemDisposal, Model model) {
		model.addAttribute("catchProblemDisposal", catchProblemDisposal);
		return "catchup/csyx/catchProblemDisposalForm";
	}

	@RequiresPermissions("csyx:catchProblemDisposal:edit")
	@RequestMapping(value = "save")
	public String save(CatchProblemDisposal catchProblemDisposal, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, catchProblemDisposal)){
			return form(catchProblemDisposal, model);
		}
		catchProblemDisposalService.save(catchProblemDisposal);
		addMessage(redirectAttributes, "保存问题处理率管理成功");
		return "redirect:"+Global.getAdminPath()+"/csyx/catchProblemDisposal/?repage";
	}
	
	@RequiresPermissions("csyx:catchProblemDisposal:edit")
	@RequestMapping(value = "delete")
	public String delete(CatchProblemDisposal catchProblemDisposal, RedirectAttributes redirectAttributes) {
		catchProblemDisposalService.delete(catchProblemDisposal);
		addMessage(redirectAttributes, "删除问题处理率管理成功");
		return "redirect:"+Global.getAdminPath()+"/csyx/catchProblemDisposal/?repage";
	}
}
