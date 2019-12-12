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
import cn.xy.tcmap.modules.catchup.fzjc.entity.CacheEnterpriseBasicData;
import cn.xy.tcmap.modules.catchup.fzjc.service.CacheEnterpriseBasicDataService;

/**
 * 企业结构基础数据分析Controller
 * @author wl
 * @version 2018-09-29
 */
@Controller
@RequestMapping(value = "${adminPath}/fzjc/cacheEnterpriseBasicData")
public class CacheEnterpriseBasicDataController extends BaseController {

	@Autowired
	private CacheEnterpriseBasicDataService cacheEnterpriseBasicDataService;
	
	@ModelAttribute
	public CacheEnterpriseBasicData get(@RequestParam(required=false) String id) {
		CacheEnterpriseBasicData entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = cacheEnterpriseBasicDataService.get(id);
		}
		if (entity == null){
			entity = new CacheEnterpriseBasicData();
		}
		return entity;
	}
	
	@RequiresPermissions("fzjc:cacheEnterpriseBasicData:view")
	@RequestMapping(value = {"list", ""})
	public String list(CacheEnterpriseBasicData cacheEnterpriseBasicData, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CacheEnterpriseBasicData> page = cacheEnterpriseBasicDataService.findPage(new Page<CacheEnterpriseBasicData>(request, response), cacheEnterpriseBasicData); 
		model.addAttribute("page", page);
		return "catchup/fzjc/cacheEnterpriseBasicDataList";
	}

	@RequiresPermissions("fzjc:cacheEnterpriseBasicData:view")
	@RequestMapping(value = "form")
	public String form(CacheEnterpriseBasicData cacheEnterpriseBasicData, Model model) {
		model.addAttribute("cacheEnterpriseBasicData", cacheEnterpriseBasicData);
		return "catchup/fzjc/cacheEnterpriseBasicDataForm";
	}

	@RequiresPermissions("fzjc:cacheEnterpriseBasicData:edit")
	@RequestMapping(value = "save")
	public String save(CacheEnterpriseBasicData cacheEnterpriseBasicData, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, cacheEnterpriseBasicData)){
			return form(cacheEnterpriseBasicData, model);
		}
		cacheEnterpriseBasicDataService.save(cacheEnterpriseBasicData);
		addMessage(redirectAttributes, "保存企业结构基础数据分析成功");
		return "redirect:"+Global.getAdminPath()+"/fzjc/cacheEnterpriseBasicData/?repage";
	}
	
	@RequiresPermissions("fzjc:cacheEnterpriseBasicData:edit")
	@RequestMapping(value = "delete")
	public String delete(CacheEnterpriseBasicData cacheEnterpriseBasicData, RedirectAttributes redirectAttributes) {
		cacheEnterpriseBasicDataService.delete(cacheEnterpriseBasicData);
		addMessage(redirectAttributes, "删除企业结构基础数据分析成功");
		return "redirect:"+Global.getAdminPath()+"/fzjc/cacheEnterpriseBasicData/?repage";
	}

}