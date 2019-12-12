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
import cn.xy.tcmap.modules.catchup.zhcs.entity.CatchEducationAssets;
import cn.xy.tcmap.modules.catchup.zhcs.service.CatchEducationAssetsService;

import java.util.List;

/**
 * 教育资产Controller
 * @author liuyang
 * @version 2018-06-05
 */
@Controller
@RequestMapping(value = "${adminPath}/zhcs/catchEducationAssets")
public class CatchEducationAssetsController extends BaseController {

	@Autowired
	private CatchEducationAssetsService catchEducationAssetsService;
	
	@ModelAttribute
	public CatchEducationAssets get(@RequestParam(required=false) String id) {
		CatchEducationAssets entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = catchEducationAssetsService.get(id);
		}
		if (entity == null){
			entity = new CatchEducationAssets();
		}
		return entity;
	}
	
	@RequiresPermissions("zhcs:catchEducationAssets:view")
	@RequestMapping(value = {"list", ""})
	public String list(CatchEducationAssets catchEducationAssets, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CatchEducationAssets> page = catchEducationAssetsService.findPage(new Page<CatchEducationAssets>(request, response), catchEducationAssets); 
		model.addAttribute("page", page);
		return "catchup/zhcs/catchEducationAssetsList";
	}

	@RequiresPermissions("zhcs:catchEducationAssets:view")
	@RequestMapping(value = "form")
	public String form(CatchEducationAssets catchEducationAssets, Model model) {
		model.addAttribute("catchEducationAssets", catchEducationAssets);
		return "catchup/zhcs/catchEducationAssetsForm";
	}

	@RequiresPermissions("zhcs:catchEducationAssets:edit")
	@RequestMapping(value = "save")
	public String save(CatchEducationAssets catchEducationAssets, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, catchEducationAssets)){
			return form(catchEducationAssets, model);
		}
		try {
			if(catchEducationAssets.getIsNewRecord()){
				CatchEducationAssets condition = new CatchEducationAssets();
				condition.setNyear(catchEducationAssets.getNyear());
				condition.setMonth(catchEducationAssets.getMonth());
				condition.setEducationType(catchEducationAssets.getEducationType());
				List<CatchEducationAssets> list = catchEducationAssetsService.findList(condition);
				if(list != null && list.size()>0){
					throw new Exception("数据重复");
				}
			}
			catchEducationAssetsService.save(catchEducationAssets);
			addMessage(redirectAttributes, "保存教育资产成功");
		} catch (Exception e) {
			addMessage(redirectAttributes, "保存教育资产失败"+e.getMessage());
			e.printStackTrace();
		}
		return "redirect:"+Global.getAdminPath()+"/zhcs/catchEducationAssets/?repage";
	}
	
	@RequiresPermissions("zhcs:catchEducationAssets:edit")
	@RequestMapping(value = "delete")
	public String delete(CatchEducationAssets catchEducationAssets, RedirectAttributes redirectAttributes) {
		catchEducationAssetsService.delete(catchEducationAssets);
		addMessage(redirectAttributes, "删除教育资产成功");
		return "redirect:"+Global.getAdminPath()+"/zhcs/catchEducationAssets/?repage";
	}

}