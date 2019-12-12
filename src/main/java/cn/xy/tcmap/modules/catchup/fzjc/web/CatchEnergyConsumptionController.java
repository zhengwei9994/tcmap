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
import cn.xy.tcmap.modules.catchup.fzjc.entity.CatchEnergyConsumption;
import cn.xy.tcmap.modules.catchup.fzjc.service.CatchEnergyConsumptionService;

import java.util.List;

/**
 * 万元GDP能耗降低率Controller
 * @author liuyang
 * @version 2018-05-29
 */
@Controller
@RequestMapping(value = "${adminPath}/fzjc/catchEnergyConsumption")
public class CatchEnergyConsumptionController extends BaseController {

	@Autowired
	private CatchEnergyConsumptionService catchEnergyConsumptionService;
	
	@ModelAttribute
	public CatchEnergyConsumption get(@RequestParam(required=false) String id) {
		CatchEnergyConsumption entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = catchEnergyConsumptionService.get(id);
		}
		if (entity == null){
			entity = new CatchEnergyConsumption();
		}
		return entity;
	}
	
	@RequiresPermissions("fzjc:catchEnergyConsumption:view")
	@RequestMapping(value = {"list", ""})
	public String list(CatchEnergyConsumption catchEnergyConsumption, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CatchEnergyConsumption> page = catchEnergyConsumptionService.findPage(new Page<CatchEnergyConsumption>(request, response), catchEnergyConsumption); 
		model.addAttribute("page", page);
		return "catchup/fzjc/catchEnergyConsumptionList";
	}

	@RequiresPermissions("fzjc:catchEnergyConsumption:view")
	@RequestMapping(value = "form")
	public String form(CatchEnergyConsumption catchEnergyConsumption, Model model) {
		model.addAttribute("catchEnergyConsumption", catchEnergyConsumption);
		return "catchup/fzjc/catchEnergyConsumptionForm";
	}

	@RequiresPermissions("fzjc:catchEnergyConsumption:edit")
	@RequestMapping(value = "save")
	public String save(CatchEnergyConsumption catchEnergyConsumption, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, catchEnergyConsumption)){
			return form(catchEnergyConsumption, model);
		}
		try{
			if(catchEnergyConsumption.getIsNewRecord()){
                CatchEnergyConsumption catchEnergyConsumption1 = new CatchEnergyConsumption();
                catchEnergyConsumption1.setYear(catchEnergyConsumption.getYear());
                catchEnergyConsumption1.setQuarter(catchEnergyConsumption.getQuarter());
                List<CatchEnergyConsumption> list = catchEnergyConsumptionService.findList(catchEnergyConsumption1);
                if(list!=null && list.size()>0){
                    throw new Exception("数据重复");
                }
            }
			catchEnergyConsumptionService.save(catchEnergyConsumption);
			addMessage(redirectAttributes, "保存万元GDP能耗降低率成功");
		}catch (Exception e){
            addMessage(redirectAttributes, "保存万元GDP能耗降低率失败，原因："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/fzjc/catchEnergyConsumption/?repage";
	}
	
	@RequiresPermissions("fzjc:catchEnergyConsumption:edit")
	@RequestMapping(value = "delete")
	public String delete(CatchEnergyConsumption catchEnergyConsumption, RedirectAttributes redirectAttributes) {
		catchEnergyConsumptionService.delete(catchEnergyConsumption);
		addMessage(redirectAttributes, "删除万元GDP能耗降低率成功");
		return "redirect:"+Global.getAdminPath()+"/fzjc/catchEnergyConsumption/?repage";
	}

}