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
import cn.xy.tcmap.modules.catchup.csly.entity.TouristEmotionStack;
import cn.xy.tcmap.modules.catchup.csly.service.TouristEmotionStackService;

import java.util.List;

/**
 * 情绪堆叠图Controller
 * @author wufan
 * @version 2019-08-16
 */
@Controller
@RequestMapping(value = "${adminPath}/csly/touristEmotionStack")
public class TouristEmotionStackController extends BaseController {

	@Autowired
	private TouristEmotionStackService touristEmotionStackService;
	
	@ModelAttribute
	public TouristEmotionStack get(@RequestParam(required=false) String id) {
		TouristEmotionStack entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = touristEmotionStackService.get(id);
		}
		if (entity == null){
			entity = new TouristEmotionStack();
		}
		return entity;
	}
	
	@RequiresPermissions("csly:touristEmotionStack:view")
	@RequestMapping(value = {"list", ""})
	public String list(TouristEmotionStack touristEmotionStack, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TouristEmotionStack> page = touristEmotionStackService.findPage(new Page<TouristEmotionStack>(request, response), touristEmotionStack); 
		model.addAttribute("page", page);
		return "catchup/csly/touristEmotionStackList";
	}

	@RequiresPermissions("csly:touristEmotionStack:view")
	@RequestMapping(value = "form")
	public String form(TouristEmotionStack touristEmotionStack, Model model) {
		model.addAttribute("touristEmotionStack", touristEmotionStack);
		return "catchup/csly/touristEmotionStackForm";
	}

	@RequiresPermissions("csly:touristEmotionStack:edit")
	@RequestMapping(value = "save")
	public String save(TouristEmotionStack touristEmotionStack, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, touristEmotionStack)){
			return form(touristEmotionStack, model);
		}
		try {
			if(touristEmotionStack.getIsNewRecord()){
				List<TouristEmotionStack> list = touristEmotionStackService.findList(touristEmotionStack);
				if(list != null && list.size()>0){
					throw new Exception("数据重复");
				}
			}
			touristEmotionStackService.save(touristEmotionStack);
			addMessage(redirectAttributes, "保存情绪堆叠图成功");
		} catch (Exception e) {
			e.printStackTrace();
			addMessage(redirectAttributes, "情绪堆叠图已存在，请勿重复添加");
		}
//		touristEmotionStackService.save(touristEmotionStack);
//		addMessage(redirectAttributes, "保存情绪堆叠图成功");
		return "redirect:"+Global.getAdminPath()+"/csly/touristEmotionStack/?repage";
	}
	
	@RequiresPermissions("csly:touristEmotionStack:edit")
	@RequestMapping(value = "delete")
	public String delete(TouristEmotionStack touristEmotionStack, RedirectAttributes redirectAttributes) {
		touristEmotionStackService.delete(touristEmotionStack);
		addMessage(redirectAttributes, "删除情绪堆叠图成功");
		return "redirect:"+Global.getAdminPath()+"/csly/touristEmotionStack/?repage";
	}

}