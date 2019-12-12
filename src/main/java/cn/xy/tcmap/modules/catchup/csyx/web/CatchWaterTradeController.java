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
import cn.xy.tcmap.modules.catchup.csyx.entity.CatchWaterTrade;
import cn.xy.tcmap.modules.catchup.csyx.service.CatchWaterTradeService;

/**
 * 污水处置能力趋势分析Controller
 * @author guoxunquan
 * @version 2018-09-20
 */
@Controller
@RequestMapping(value = "${adminPath}/csyx/catchWaterTrade")
public class CatchWaterTradeController extends BaseController {

	@Autowired
	private CatchWaterTradeService catchWaterTradeService;
	
	@ModelAttribute
	public CatchWaterTrade get(@RequestParam(required=false) String id) {
		CatchWaterTrade entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = catchWaterTradeService.get(id);
		}
		if (entity == null){
			entity = new CatchWaterTrade();
		}
		return entity;
	}
	
	@RequiresPermissions("csyx:catchWaterTrade:view")
	@RequestMapping(value = {"list", ""})
	public String list(CatchWaterTrade catchWaterTrade, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CatchWaterTrade> page = catchWaterTradeService.findPage(new Page<CatchWaterTrade>(request, response), catchWaterTrade); 
		model.addAttribute("page", page);
		return "catchup/csyx/catchWaterTradeList";
	}

	@RequiresPermissions("csyx:catchWaterTrade:view")
	@RequestMapping(value = "form")
	public String form(CatchWaterTrade catchWaterTrade, Model model) {
		model.addAttribute("catchWaterTrade", catchWaterTrade);
		return "catchup/csyx/catchWaterTradeForm";
	}

	@RequiresPermissions("csyx:catchWaterTrade:edit")
	@RequestMapping(value = "save")
	public String save(CatchWaterTrade catchWaterTrade, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, catchWaterTrade)){
			return form(catchWaterTrade, model);
		}
		if(catchWaterTrade !=null && "".equals(catchWaterTrade.getId())){
			List<CatchWaterTrade> list = catchWaterTradeService.findList(catchWaterTrade);
			if(list != null && list.size() > 0){
				addMessage(redirectAttributes, "该月份下已有数据，请重新添加");
			}else{
				catchWaterTradeService.save(catchWaterTrade);
				addMessage(redirectAttributes, "保存污水处置能力趋势分析成功");
			}
		}else{
			catchWaterTradeService.save(catchWaterTrade);
			addMessage(redirectAttributes, "保存污水处置能力趋势分析成功");
		}
		return "redirect:"+Global.getAdminPath()+"/csyx/catchWaterTrade/?repage";
	}
	
	@RequiresPermissions("csyx:catchWaterTrade:edit")
	@RequestMapping(value = "delete")
	public String delete(CatchWaterTrade catchWaterTrade, RedirectAttributes redirectAttributes) {
		catchWaterTradeService.delete(catchWaterTrade);
		addMessage(redirectAttributes, "删除污水处置能力趋势分析成功");
		return "redirect:"+Global.getAdminPath()+"/csyx/catchWaterTrade/?repage";
	}

}