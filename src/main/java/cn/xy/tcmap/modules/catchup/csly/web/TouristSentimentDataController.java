/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csly.web;

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
import cn.xy.tcmap.modules.catchup.csly.entity.TouristSentimentData;
import cn.xy.tcmap.modules.catchup.csly.service.TouristSentimentDataService;

import java.util.List;

/**
 * 舆情数据Controller
 * @author wufan
 * @version 2019-08-16
 */
@Controller
@RequestMapping(value = "${adminPath}/csly/touristSentimentData")
public class TouristSentimentDataController extends BaseController {

	@Autowired
	private TouristSentimentDataService touristSentimentDataService;
	
	@ModelAttribute
	public TouristSentimentData get(@RequestParam(required=false) String id) {
		TouristSentimentData entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = touristSentimentDataService.get(id);
		}
		if (entity == null){
			entity = new TouristSentimentData();
		}
		return entity;
	}
	
	@RequiresPermissions("csly:touristSentimentData:view")
	@RequestMapping(value = {"list", ""})
	public String list(TouristSentimentData touristSentimentData, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TouristSentimentData> page = touristSentimentDataService.findPage(new Page<TouristSentimentData>(request, response), touristSentimentData); 
		model.addAttribute("page", page);
		return "catchup/csly/touristSentimentDataList";
	}

	@RequiresPermissions("csly:touristSentimentData:view")
	@RequestMapping(value = "form")
	public String form(TouristSentimentData touristSentimentData, Model model) {
		model.addAttribute("touristSentimentData", touristSentimentData);
		return "catchup/csly/touristSentimentDataForm";
	}

	@RequiresPermissions("csly:touristSentimentData:edit")
	@RequestMapping(value = "save")
	public String save(TouristSentimentData touristSentimentData, Model model, RedirectAttributes redirectAttributes) {
		try{
			if (!beanValidator(model, touristSentimentData)){
				return form(touristSentimentData, model);
			}
			if(touristSentimentData.getIsNewRecord()){
				TouristSentimentData tsd = new TouristSentimentData();
				tsd.setArea(touristSentimentData.getArea());
				tsd.setName(touristSentimentData.getName());
				tsd.setNmonth(touristSentimentData.getNmonth());
				tsd.setNyear(touristSentimentData.getNyear());
				List<TouristSentimentData> list = touristSentimentDataService.findList(tsd);
				if(list != null && list.size()>0){
					addMessage(redirectAttributes,"数据重复，保存失败");
					return "redirect:"+Global.getAdminPath()+"/csly/touristSentimentData/?repage";
				}
			}
			touristSentimentDataService.save(touristSentimentData);
			addMessage(redirectAttributes, "保存舆情数据成功");
		}catch (Exception e){
			addMessage(redirectAttributes, "保存舆情数据失败");
		}
		return "redirect:"+Global.getAdminPath()+"/csly/touristSentimentData/?repage";
	}
	
	@RequiresPermissions("csly:touristSentimentData:edit")
	@RequestMapping(value = "delete")
	public String delete(TouristSentimentData touristSentimentData, RedirectAttributes redirectAttributes) {
		touristSentimentDataService.delete(touristSentimentData);
		addMessage(redirectAttributes, "删除舆情数据成功");
		return "redirect:"+Global.getAdminPath()+"/csly/touristSentimentData/?repage";
	}

}