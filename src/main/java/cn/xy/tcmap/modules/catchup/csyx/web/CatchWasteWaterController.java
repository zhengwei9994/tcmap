/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csyx.web;

import java.util.List;

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
import cn.xy.tcmap.modules.catchup.csyx.entity.CatchWasteWater;
import cn.xy.tcmap.modules.catchup.csyx.service.CatchWasteWaterService;

/**
 * 污水排放量趋势分析Controller
 * @author guoxunquan
 * @version 2018-09-20
 */
@Controller
@RequestMapping(value = "${adminPath}/csyx/catchWasteWater")
public class CatchWasteWaterController extends BaseController {

	@Autowired
	private CatchWasteWaterService catchWasteWaterService;
	
	@ModelAttribute
	public CatchWasteWater get(@RequestParam(required=false) String id) {
		CatchWasteWater entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = catchWasteWaterService.get(id);
		}
		if (entity == null){
			entity = new CatchWasteWater();
		}
		return entity;
	}
	
	@RequiresPermissions("csyx:catchWasteWater:view")
	@RequestMapping(value = {"list", ""})
	public String list(CatchWasteWater catchWasteWater, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CatchWasteWater> page = catchWasteWaterService.findPage(new Page<CatchWasteWater>(request, response), catchWasteWater); 
		model.addAttribute("page", page);
		return "catchup/csyx/catchWasteWaterList";
	}

	@RequiresPermissions("csyx:catchWasteWater:view")
	@RequestMapping(value = "form")
	public String form(CatchWasteWater catchWasteWater, Model model) {
		model.addAttribute("catchWasteWater", catchWasteWater);
		return "catchup/csyx/catchWasteWaterForm";
	}

	@RequiresPermissions("csyx:catchWasteWater:edit")
	@RequestMapping(value = "save")
	public String save(CatchWasteWater catchWasteWater, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, catchWasteWater)){
			return form(catchWasteWater, model);
		}
		if(catchWasteWater != null && "".equals(catchWasteWater.getId())){
			List<CatchWasteWater> list = catchWasteWaterService.findList(catchWasteWater);
			if(list !=null && list.size() > 0){
				addMessage(redirectAttributes, "该月份下已有数据，请重新添加");
			}else{
				catchWasteWaterService.save(catchWasteWater);
				addMessage(redirectAttributes, "保存成功");
			}
		}else{
			catchWasteWaterService.save(catchWasteWater);
			addMessage(redirectAttributes, "保存成功");
		}
		
		return "redirect:"+Global.getAdminPath()+"/csyx/catchWasteWater/?repage";
	}
	
	@RequiresPermissions("csyx:catchWasteWater:edit")
	@RequestMapping(value = "delete")
	public String delete(CatchWasteWater catchWasteWater, RedirectAttributes redirectAttributes) {
		catchWasteWaterService.delete(catchWasteWater);
		addMessage(redirectAttributes, "删除污水排放量趋势分析成功");
		return "redirect:"+Global.getAdminPath()+"/csyx/catchWasteWater/?repage";
	}

}