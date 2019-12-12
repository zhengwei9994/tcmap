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
import cn.xy.tcmap.modules.catchup.csly.entity.TouristsSources;
import cn.xy.tcmap.modules.catchup.csly.service.TouristsSourcesService;

import java.util.List;

/**
 * 游客来源地Controller
 * @author wufan
 * @version 2019-08-16
 */
@Controller
@RequestMapping(value = "${adminPath}/csly/touristsSources")
public class TouristsSourcesController extends BaseController {

	@Autowired
	private TouristsSourcesService touristsSourcesService;
	
	@ModelAttribute
	public TouristsSources get(@RequestParam(required=false) String id) {
		TouristsSources entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = touristsSourcesService.get(id);
		}
		if (entity == null){
			entity = new TouristsSources();
		}
		return entity;
	}
	
	@RequiresPermissions("csly:touristsSources:view")
	@RequestMapping(value = {"list", ""})
	public String list(TouristsSources touristsSources, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TouristsSources> page = touristsSourcesService.findPage(new Page<TouristsSources>(request, response), touristsSources); 
		model.addAttribute("page", page);
		return "catchup/csly/touristsSourcesList";
	}

	@RequiresPermissions("csly:touristsSources:view")
	@RequestMapping(value = "form")
	public String form(TouristsSources touristsSources, Model model) {
		model.addAttribute("touristsSources", touristsSources);
		return "catchup/csly/touristsSourcesForm";
	}

	@RequiresPermissions("csly:touristsSources:edit")
	@RequestMapping(value = "save")
	public String save(TouristsSources touristsSources, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, touristsSources)){
			return form(touristsSources, model);
		}
		try{
			if (touristsSources.getIsNewRecord()){
                TouristsSources touristsSources1 = new TouristsSources();
                touristsSources1.setProvince(touristsSources.getProvince());
                List<TouristsSources> list = touristsSourcesService.findList(touristsSources1);
                if (list !=null && list.size() >0){
                    throw new Exception("省份已存在，请勿重复添加");
                }
            }
			touristsSourcesService.save(touristsSources);
			addMessage(redirectAttributes, "保存游客来源地成功");
		}catch (Exception e){
            addMessage(redirectAttributes, e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/csly/touristsSources/?repage";
	}
	
	@RequiresPermissions("csly:touristsSources:edit")
	@RequestMapping(value = "delete")
	public String delete(TouristsSources touristsSources, RedirectAttributes redirectAttributes) {
		touristsSourcesService.delete(touristsSources);
		addMessage(redirectAttributes, "删除游客来源地成功");
		return "redirect:"+Global.getAdminPath()+"/csly/touristsSources/?repage";
	}

}