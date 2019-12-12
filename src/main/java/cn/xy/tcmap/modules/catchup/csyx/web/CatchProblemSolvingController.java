package cn.xy.tcmap.modules.catchup.csyx.web;

import cn.xy.tcmap.common.config.Global;
import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.utils.StringUtils;
import cn.xy.tcmap.common.web.BaseController;
import cn.xy.tcmap.modules.catchup.csyx.entity.CatchProblemSolving;
import cn.xy.tcmap.modules.catchup.csyx.service.CatchProblemSolvingService;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
@RequestMapping(value = "${adminPath}/csyx/catchProblemSolving")
public class CatchProblemSolvingController extends BaseController {

    @Autowired
    private CatchProblemSolvingService catchProblemSolvingService;

    @RequestMapping(value = "findEchartData",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> findEchartData(){
        return catchProblemSolvingService.findEchartData();
    }
    

    @ModelAttribute
	public CatchProblemSolving get(@RequestParam(required=false) String id) {
    	CatchProblemSolving entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = catchProblemSolvingService.get(id);
		}
		if (entity == null){
			entity = new CatchProblemSolving();
		}
		return entity;
	}

	@RequiresPermissions("csyx:catchProblemSolving:view")
	@RequestMapping(value = {"list", ""})
	public String list(CatchProblemSolving catchProblemSolving, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CatchProblemSolving> page = catchProblemSolvingService.findPage(new Page<CatchProblemSolving>(request, response), catchProblemSolving); 
		model.addAttribute("page", page);
		return "catchup/csyx/catchProblemSolvingList";
	}

	@RequiresPermissions("csyx:catchProblemSolving:view")
	@RequestMapping(value = "form")
	public String form(CatchProblemSolving catchProblemSolving, Model model) {
		model.addAttribute("catchProblemSolving", catchProblemSolving);
		return "catchup/csyx/catchProblemSolvingForm";
	}

	@RequiresPermissions("csyx:catchProblemSolving:edit")
	@RequestMapping(value = "save")
	public String save(CatchProblemSolving catchProblemSolving, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, catchProblemSolving)){
			return form(catchProblemSolving, model);
		}
		catchProblemSolvingService.save(catchProblemSolving);
		addMessage(redirectAttributes, "保存成功");
		return "redirect:"+Global.getAdminPath()+"/csyx/catchProblemSolving/?repage";
	}
	
	@RequiresPermissions("csyx:catchProblemSolving:edit")
	@RequestMapping(value = "delete")
	public String delete(CatchProblemSolving catchProblemSolving, RedirectAttributes redirectAttributes) {
		catchProblemSolvingService.delete(catchProblemSolving);
		addMessage(redirectAttributes, "删除成功");
		return "redirect:"+Global.getAdminPath()+"/csyx/catchProblemSolving/?repage";
	}
}
