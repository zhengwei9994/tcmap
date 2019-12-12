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
import cn.xy.tcmap.modules.catchup.csyx.entity.ShowQualitylifeConsumption;
import cn.xy.tcmap.modules.catchup.csyx.service.ShowQualitylifeConsumptionService;

import java.util.List;

/**
 * 经济消费状况Controller
 * @author wufan
 * @version 2019-07-31
 */
@Controller
@RequestMapping(value = "${adminPath}/csyx/showQualitylifeConsumption")
public class ShowQualitylifeConsumptionController extends BaseController {

	@Autowired
	private ShowQualitylifeConsumptionService showQualitylifeConsumptionService;
	
	@ModelAttribute
	public ShowQualitylifeConsumption get(@RequestParam(required=false) String id) {
		ShowQualitylifeConsumption entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = showQualitylifeConsumptionService.get(id);
		}
		if (entity == null){
			entity = new ShowQualitylifeConsumption();
		}
		return entity;
	}
	
	@RequiresPermissions("csyx:showQualitylifeConsumption:view")
	@RequestMapping(value = {"list", ""})
	public String list(ShowQualitylifeConsumption showQualitylifeConsumption, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ShowQualitylifeConsumption> page = showQualitylifeConsumptionService.findPage(new Page<ShowQualitylifeConsumption>(request, response), showQualitylifeConsumption); 
		model.addAttribute("page", page);
		return "catchup/csyx/showQualitylifeConsumptionList";
	}

	@RequiresPermissions("csyx:showQualitylifeConsumption:view")
	@RequestMapping(value = "form")
	public String form(ShowQualitylifeConsumption showQualitylifeConsumption, Model model) {
		model.addAttribute("showQualitylifeConsumption", showQualitylifeConsumption);
		return "catchup/csyx/showQualitylifeConsumptionForm";
	}

	@RequiresPermissions("csyx:showQualitylifeConsumption:edit")
	@RequestMapping(value = "save")
	public String save(ShowQualitylifeConsumption showQualitylifeConsumption, Model model, RedirectAttributes redirectAttributes) {
		try {
			if (!beanValidator(model, showQualitylifeConsumption)){
				return form(showQualitylifeConsumption, model);
			}
			if(showQualitylifeConsumption.getIsNewRecord()){
				ShowQualitylifeConsumption condition = new ShowQualitylifeConsumption();
				condition.setYear(showQualitylifeConsumption.getYear());
				condition.setMonth(showQualitylifeConsumption.getMonth());
				List<ShowQualitylifeConsumption> list = showQualitylifeConsumptionService.findList(condition);
				if(list != null && list.size()>0){
					throw new Exception("数据重复");
				}
			}
			showQualitylifeConsumptionService.save(showQualitylifeConsumption);
			addMessage(redirectAttributes, "保存经济消费状况成功");
		}catch (Exception e){
			addMessage(redirectAttributes, "保存经济消费状况失败，失败原因："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/csyx/showQualitylifeConsumption/?repage";
	}
	
	@RequiresPermissions("csyx:showQualitylifeConsumption:edit")
	@RequestMapping(value = "delete")
	public String delete(ShowQualitylifeConsumption showQualitylifeConsumption, RedirectAttributes redirectAttributes) {
		showQualitylifeConsumptionService.delete(showQualitylifeConsumption);
		addMessage(redirectAttributes, "删除经济消费状况成功");
		return "redirect:"+Global.getAdminPath()+"/csyx/showQualitylifeConsumption/?repage";
	}

}