/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.zhcs.web;

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
import cn.xy.tcmap.modules.catchup.zhcs.entity.CatchAssetDetails;
import cn.xy.tcmap.modules.catchup.zhcs.service.CatchAssetDetailsService;

/**
 * 资源详情Controller
 * @author liuyang
 * @version 2018-06-05
 */
@Controller
@RequestMapping(value = "${adminPath}/zhcs/catchAssetDetails")
public class CatchAssetDetailsController extends BaseController {

	@Autowired
	private CatchAssetDetailsService catchAssetDetailsService;
	
	@ModelAttribute
	public CatchAssetDetails get(@RequestParam(required=false) String id) {
		CatchAssetDetails entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = catchAssetDetailsService.get(id);
		}
		if (entity == null){
			entity = new CatchAssetDetails();
		}
		return entity;
	}
	
	@RequiresPermissions("zhcs:catchAssetDetails:view")
	@RequestMapping(value = {"list", ""})
	public String list(CatchAssetDetails catchAssetDetails, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CatchAssetDetails> page = catchAssetDetailsService.findPage(new Page<CatchAssetDetails>(request, response), catchAssetDetails); 
		model.addAttribute("page", page);
		return "catchup/zhcs/catchAssetDetailsList";
	}

	@RequiresPermissions("zhcs:catchAssetDetails:view")
	@RequestMapping(value = "form")
	public String form(CatchAssetDetails catchAssetDetails, Model model) {
		model.addAttribute("catchAssetDetails", catchAssetDetails);
		return "catchup/zhcs/catchAssetDetailsForm";
	}

	@RequiresPermissions("zhcs:catchAssetDetails:edit")
	@RequestMapping(value = "save")
	public String save(CatchAssetDetails catchAssetDetails, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, catchAssetDetails)){
			return form(catchAssetDetails, model);
		}
		catchAssetDetailsService.save(catchAssetDetails);
		addMessage(redirectAttributes, "保存资源详情成功");
		return "redirect:"+Global.getAdminPath()+"/zhcs/catchAssetDetails/?repage";
	}
	
	@RequiresPermissions("zhcs:catchAssetDetails:edit")
	@RequestMapping(value = "delete")
	public String delete(CatchAssetDetails catchAssetDetails, RedirectAttributes redirectAttributes) {
		catchAssetDetailsService.delete(catchAssetDetails);
		addMessage(redirectAttributes, "删除资源详情成功");
		return "redirect:"+Global.getAdminPath()+"/zhcs/catchAssetDetails/?repage";
	}

}