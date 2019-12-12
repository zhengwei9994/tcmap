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
import cn.xy.tcmap.modules.catchup.zhcs.entity.CatchMedicalAssets;
import cn.xy.tcmap.modules.catchup.zhcs.service.CatchMedicalAssetsService;

import java.util.List;

/**
 * 医疗资源统计Controller
 * @author liuyang
 * @version 2018-06-05
 */
@Controller
@RequestMapping(value = "${adminPath}/zhcs/catchMedicalAssets")
public class CatchMedicalAssetsController extends BaseController {

	@Autowired
	private CatchMedicalAssetsService catchMedicalAssetsService;
	
	@ModelAttribute
	public CatchMedicalAssets get(@RequestParam(required=false) String id) {
		CatchMedicalAssets entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = catchMedicalAssetsService.get(id);
		}
		if (entity == null){
			entity = new CatchMedicalAssets();
		}
		return entity;
	}
	
	@RequiresPermissions("zhcs:catchMedicalAssets:view")
	@RequestMapping(value = {"list", ""})
	public String list(CatchMedicalAssets catchMedicalAssets, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CatchMedicalAssets> page = catchMedicalAssetsService.findPage(new Page<CatchMedicalAssets>(request, response), catchMedicalAssets); 
		model.addAttribute("page", page);
		return "catchup/zhcs/catchMedicalAssetsList";
	}

	@RequiresPermissions("zhcs:catchMedicalAssets:view")
	@RequestMapping(value = "form")
	public String form(CatchMedicalAssets catchMedicalAssets, Model model) {
		model.addAttribute("catchMedicalAssets", catchMedicalAssets);
		return "catchup/zhcs/catchMedicalAssetsForm";
	}

	@RequiresPermissions("zhcs:catchMedicalAssets:edit")
	@RequestMapping(value = "save")
	public String save(CatchMedicalAssets catchMedicalAssets, Model model, RedirectAttributes redirectAttributes) {
		try {
			if (!beanValidator(model, catchMedicalAssets)){
				return form(catchMedicalAssets, model);
			}
			if(catchMedicalAssets.getIsNewRecord()){
				CatchMedicalAssets cma = new CatchMedicalAssets();
				cma.setMonth(catchMedicalAssets.getMonth());
				cma.setNyear(catchMedicalAssets.getNyear());
				cma.setHospitalGrade(catchMedicalAssets.getHospitalGrade());
				List<CatchMedicalAssets> list = catchMedicalAssetsService.findList(cma);
				if(list != null && list.size()>0){
					throw new Exception("数据重复");
				}
			}
			catchMedicalAssetsService.save(catchMedicalAssets);
			addMessage(redirectAttributes, "保存医疗资源统计成功");
		}catch (Exception e){
			addMessage(redirectAttributes, "保存医疗资源统计失败，失败原因："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/zhcs/catchMedicalAssets/?repage";
	}
	
	@RequiresPermissions("zhcs:catchMedicalAssets:edit")
	@RequestMapping(value = "delete")
	public String delete(CatchMedicalAssets catchMedicalAssets, RedirectAttributes redirectAttributes) {
		catchMedicalAssetsService.delete(catchMedicalAssets);
		addMessage(redirectAttributes, "删除医疗资源统计成功");
		return "redirect:"+Global.getAdminPath()+"/zhcs/catchMedicalAssets/?repage";
	}

}