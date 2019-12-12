/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csly.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.xy.tcmap.modules.catchup.csly.entity.TouristsSources;
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
import cn.xy.tcmap.modules.catchup.csly.entity.TouristsWhereabouts;
import cn.xy.tcmap.modules.catchup.csly.service.TouristsWhereaboutsService;

import java.util.List;

/**
 * 游客去向地Controller
 * @author wufan
 * @version 2019-08-16
 */
@Controller
@RequestMapping(value = "${adminPath}/csly/touristsWhereabouts")
public class TouristsWhereaboutsController extends BaseController {

	@Autowired
	private TouristsWhereaboutsService touristsWhereaboutsService;
	
	@ModelAttribute
	public TouristsWhereabouts get(@RequestParam(required=false) String id) {
		TouristsWhereabouts entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = touristsWhereaboutsService.get(id);
		}
		if (entity == null){
			entity = new TouristsWhereabouts();
		}
		return entity;
	}
	
	@RequiresPermissions("csly:touristsWhereabouts:view")
	@RequestMapping(value = {"list", ""})
	public String list(TouristsWhereabouts touristsWhereabouts, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TouristsWhereabouts> page = touristsWhereaboutsService.findPage(new Page<TouristsWhereabouts>(request, response), touristsWhereabouts); 
		model.addAttribute("page", page);
		return "catchup/csly/touristsWhereaboutsList";
	}

	@RequiresPermissions("csly:touristsWhereabouts:view")
	@RequestMapping(value = "form")
	public String form(TouristsWhereabouts touristsWhereabouts, Model model) {
		model.addAttribute("touristsWhereabouts", touristsWhereabouts);
		return "catchup/csly/touristsWhereaboutsForm";
	}

	@RequiresPermissions("csly:touristsWhereabouts:edit")
	@RequestMapping(value = "save")
	public String save(TouristsWhereabouts touristsWhereabouts, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, touristsWhereabouts)){
			return form(touristsWhereabouts, model);
		}
		try{
			if (touristsWhereabouts.getIsNewRecord()){
				TouristsWhereabouts touristsSources1 = new TouristsWhereabouts();
				touristsSources1.setProvince(touristsWhereabouts.getProvince());
				List<TouristsWhereabouts> list = touristsWhereaboutsService.findList(touristsSources1);
				if (list !=null && list.size() >0){
					throw new Exception("省份已存在，请勿重复添加");
				}
			}
			touristsWhereaboutsService.save(touristsWhereabouts);
			addMessage(redirectAttributes, "保存游客来源地成功");
		}catch (Exception e){
			addMessage(redirectAttributes, e.getMessage());
		}
//		touristsWhereaboutsService.save(touristsWhereabouts);
//		addMessage(redirectAttributes, "保存游客去向地成功");
		return "redirect:"+Global.getAdminPath()+"/csly/touristsWhereabouts/?repage";
	}
	
	@RequiresPermissions("csly:touristsWhereabouts:edit")
	@RequestMapping(value = "delete")
	public String delete(TouristsWhereabouts touristsWhereabouts, RedirectAttributes redirectAttributes) {
		touristsWhereaboutsService.delete(touristsWhereabouts);
		addMessage(redirectAttributes, "删除游客去向地成功");
		return "redirect:"+Global.getAdminPath()+"/csly/touristsWhereabouts/?repage";
	}

}