package cn.xy.tcmap.modules.catchup.csyx.web;

import cn.xy.tcmap.common.config.Global;
import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.utils.StringUtils;
import cn.xy.tcmap.common.web.BaseController;
import cn.xy.tcmap.modules.catchup.csyx.entity.CatchInvestmentProgress;
import cn.xy.tcmap.modules.catchup.csyx.service.CatchInvestmentProgressService;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * code is far away from bug with the animal protecting
 * ┏┓　　　┏┓
 * ┏┛┻━━━┛┻┓
 * ┃　　　　　　　┃
 * ┃　　　━　　　┃
 * ┃　┳┛　┗┳　┃
 * ┃　　　　　　　┃
 * ┃　　　┻　　　┃
 * ┃　　　　　　　┃
 * ┗━┓　　　┏━┛
 * 　　┃　　　┃神兽保佑
 * 　　┃　　　┃代码无BUG！
 * 　　┃　　　┗━━━┓
 * 　　┃　　　　　　　┣┓
 * 　　┃　　　　　　　┏┛
 * 　　┗┓┓┏━┳┓┏┛
 * 　　　┃┫┫　┃┫┫
 * 　　　┗┻┛　┗┻┛
 *
 * @Description : ${Desc}
 * ---------------------------------
 * @Author : dell
 * @Date : Create in 2018/9/19
 */
@Controller
@RequestMapping(value = "${adminPath}/csyx/catchInvestmentProgress")
public class CatchInvestmentProgressController extends BaseController {

    @Autowired
    private CatchInvestmentProgressService catchInvestmentProgressService;

    @ModelAttribute
	public CatchInvestmentProgress get(@RequestParam(required=false) String id) {
		CatchInvestmentProgress entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = catchInvestmentProgressService.get(id);
		}
		if (entity == null){
			entity = new CatchInvestmentProgress();
		}
		return entity;
	}

	@RequiresPermissions("csyx:CatchInvestmentProgress:view")
	@RequestMapping(value = {"list", ""})
	public String list(CatchInvestmentProgress catchInvestmentProgress, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CatchInvestmentProgress> page = catchInvestmentProgressService.findPage(new Page<CatchInvestmentProgress>(request, response), catchInvestmentProgress); 
		model.addAttribute("page", page);
		return "catchup/csyx/catchInvestmentProgressList";
	}

	@RequiresPermissions("csyx:CatchInvestmentProgress:view")
	@RequestMapping(value = "form")
	public String form(CatchInvestmentProgress catchInvestmentProgress, Model model) {
		model.addAttribute("catchInvestmentProgress", catchInvestmentProgress);
		return "catchup/csyx/catchInvestmentProgressForm";
	}

	@RequiresPermissions("csyx:CatchInvestmentProgress:edit")
	@RequestMapping(value = "save")
	public String save(CatchInvestmentProgress catchInvestmentProgress, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, catchInvestmentProgress)){
			return form(catchInvestmentProgress, model);
		}
		catchInvestmentProgressService.save(catchInvestmentProgress);
		addMessage(redirectAttributes, "保存成功");
		return "redirect:"+Global.getAdminPath()+"/csyx/catchInvestmentProgress/?repage";
	}
	
	@RequiresPermissions("csyx:CatchInvestmentProgress:edit")
	@RequestMapping(value = "delete")
	public String delete(CatchInvestmentProgress catchInvestmentProgress, RedirectAttributes redirectAttributes) {
		catchInvestmentProgressService.delete(catchInvestmentProgress);
		addMessage(redirectAttributes, "删除成功");
		return "redirect:"+Global.getAdminPath()+"/csyx/catchInvestmentProgress/?repage";
	}
}
