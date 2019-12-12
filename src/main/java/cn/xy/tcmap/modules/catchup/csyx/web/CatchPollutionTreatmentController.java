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
import cn.xy.tcmap.modules.catchup.csyx.entity.CatchPollutionTreatment;
import cn.xy.tcmap.modules.catchup.csyx.service.CatchPollutionTreatmentService;

import java.util.List;

/**
 * 重拳治污染Controller
 * @author xuzhou
 * @version 2018-05-24
 */
@Controller
@RequestMapping(value = "${adminPath}/csyx/catchPollutionTreatment")
public class CatchPollutionTreatmentController extends BaseController {

	@Autowired
	private CatchPollutionTreatmentService catchPollutionTreatmentService;
	
	@ModelAttribute
	public CatchPollutionTreatment get(@RequestParam(required=false) String id) {
		CatchPollutionTreatment entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = catchPollutionTreatmentService.get(id);
		}
		if (entity == null){
			entity = new CatchPollutionTreatment();
		}
		return entity;
	}
	
	@RequiresPermissions("csyx:catchPollutionTreatment:view")
	@RequestMapping(value = {"list", ""})
	public String list(CatchPollutionTreatment catchPollutionTreatment, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CatchPollutionTreatment> page = catchPollutionTreatmentService.findPage(new Page<CatchPollutionTreatment>(request, response), catchPollutionTreatment); 
		model.addAttribute("page", page);
		return "catchup/csyx/catchPollutionTreatmentList";
	}

	@RequiresPermissions("csyx:catchPollutionTreatment:view")
	@RequestMapping(value = "form")
	public String form(CatchPollutionTreatment catchPollutionTreatment, Model model) {
		model.addAttribute("catchPollutionTreatment", catchPollutionTreatment);
		return "catchup/csyx/catchPollutionTreatmentForm";
	}

	@RequiresPermissions("csyx:catchPollutionTreatment:edit")
	@RequestMapping(value = "save")
	public String save(CatchPollutionTreatment catchPollutionTreatment, Model model, RedirectAttributes redirectAttributes) {
		try {
            if (!beanValidator(model, catchPollutionTreatment)) {
                return form(catchPollutionTreatment, model);
            }
            if (catchPollutionTreatment.getIsNewRecord()) {
                CatchPollutionTreatment catchPollutionTreatment1 = new CatchPollutionTreatment();
				catchPollutionTreatment1.setNyear(catchPollutionTreatment.getNyear());
                List<CatchPollutionTreatment> list = catchPollutionTreatmentService.findList(catchPollutionTreatment1);
                if ( list != null && list.size() > 0 ) {
                    throw new Exception("数据重复");
                }
            }
            catchPollutionTreatmentService.save(catchPollutionTreatment);
            addMessage(redirectAttributes, "保存重拳治污染成功");
        }
        catch (Exception e){
            addMessage(redirectAttributes,"保存重拳治污染失败，原因："+e.getMessage());
        }
		return "redirect:"+Global.getAdminPath()+"/csyx/catchPollutionTreatment/?repage";
	}
	
	@RequiresPermissions("csyx:catchPollutionTreatment:edit")
	@RequestMapping(value = "delete")
	public String delete(CatchPollutionTreatment catchPollutionTreatment, RedirectAttributes redirectAttributes) {
		catchPollutionTreatmentService.delete(catchPollutionTreatment);
		addMessage(redirectAttributes, "删除重拳治污染成功");
		return "redirect:"+Global.getAdminPath()+"/csyx/catchPollutionTreatment/?repage";
	}

}