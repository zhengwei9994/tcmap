/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csyx.web;

import cn.xy.tcmap.common.config.Global;
import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.utils.StringUtils;
import cn.xy.tcmap.common.web.BaseController;
import cn.xy.tcmap.modules.catchup.csyx.entity.CatchEconomicGrowth;
import cn.xy.tcmap.modules.catchup.csyx.service.CatchEconomicGrowthService;
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
import java.util.List;

/**
 * 区域经济增长Controller
 * @author gxq
 * @version 2018-11-05
 */
@Controller
@RequestMapping(value = "${adminPath}/csyx/catchEconomicGrowth")
public class CatchEconomicGrowthController extends BaseController {

	@Autowired
	private CatchEconomicGrowthService catchEconomicGrowthService;
	
	@ModelAttribute
	public CatchEconomicGrowth get(@RequestParam(required=false) String id) {
		CatchEconomicGrowth entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = catchEconomicGrowthService.get(id);
		}
		if (entity == null){
			entity = new CatchEconomicGrowth();
		}
		return entity;
	}
	
	@RequiresPermissions("csyx:catchEconomicGrowth:view")
	@RequestMapping(value = {"list", ""})
	public String list(CatchEconomicGrowth catchEconomicGrowth, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CatchEconomicGrowth> page = catchEconomicGrowthService.findPage(new Page<CatchEconomicGrowth>(request, response), catchEconomicGrowth); 
		model.addAttribute("page", page);
		return "catchup/csyx/catchEconomicGrowthList";
	}

	@RequiresPermissions("csyx:catchEconomicGrowth:view")
	@RequestMapping(value = "form")
	public String form(CatchEconomicGrowth catchEconomicGrowth, Model model) {
		model.addAttribute("catchEconomicGrowth", catchEconomicGrowth);
		return "catchup/csyx/catchEconomicGrowthForm";
	}

	@RequiresPermissions("csyx:catchEconomicGrowth:edit")
	@RequestMapping(value = "save")
	public String save(CatchEconomicGrowth catchEconomicGrowth, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, catchEconomicGrowth)){
			return form(catchEconomicGrowth, model);
		}
		if(catchEconomicGrowth != null && "".equals(catchEconomicGrowth.getId())){
	        	 List<CatchEconomicGrowth> list = catchEconomicGrowthService.findList(catchEconomicGrowth);
	        	 if(list != null && list.size() > 0){
	        		 addMessage(redirectAttributes, "该指标下已有数据，请重新添加");
	        	 }else{
	        		 catchEconomicGrowthService.save(catchEconomicGrowth);
	        	     addMessage(redirectAttributes, "保存成功");
	        	 }
	        }else{
	        	catchEconomicGrowthService.save(catchEconomicGrowth);
	   	        addMessage(redirectAttributes, "保存成功");
	   	    }
		return "redirect:"+Global.getAdminPath()+"/csyx/catchEconomicGrowth/?repage";
	}
	
	@RequiresPermissions("csyx:catchEconomicGrowth:edit")
	@RequestMapping(value = "delete")
	public String delete(CatchEconomicGrowth catchEconomicGrowth, RedirectAttributes redirectAttributes) {
		catchEconomicGrowthService.delete(catchEconomicGrowth);
		addMessage(redirectAttributes, "删除区域经济增长成功");
		return "redirect:"+Global.getAdminPath()+"/csyx/catchEconomicGrowth/?repage";
	}

}