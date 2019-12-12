/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csyx.web;

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
import cn.xy.tcmap.modules.catchup.csyx.entity.ShowQualityife;
import cn.xy.tcmap.modules.catchup.csyx.service.ShowQualityifeService;

import java.util.List;

/**
 * 经济生活质量Controller
 * @author wufan
 * @version 2019-07-31
 */
@Controller
@RequestMapping(value = "${adminPath}/csyx/showQualityife")
public class ShowQualityifeController extends BaseController {

	@Autowired
	private ShowQualityifeService showQualityifeService;
	
	@ModelAttribute
	public ShowQualityife get(@RequestParam(required=false) String id) {
		ShowQualityife entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = showQualityifeService.get(id);
		}
		if (entity == null){
			entity = new ShowQualityife();
		}
		return entity;
	}
	
	@RequiresPermissions("csyx:showQualityife:view")
	@RequestMapping(value = {"list", ""})
	public String list(ShowQualityife showQualityife, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ShowQualityife> page = showQualityifeService.findPage(new Page<ShowQualityife>(request, response), showQualityife); 
		model.addAttribute("page", page);
		return "catchup/csyx/showQualityifeList";
	}

	@RequiresPermissions("csyx:showQualityife:view")
	@RequestMapping(value = "form")
	public String form(ShowQualityife showQualityife, Model model) {
		model.addAttribute("showQualityife", showQualityife);
		return "catchup/csyx/showQualityifeForm";
	}

	@RequiresPermissions("csyx:showQualityife:edit")
	@RequestMapping(value = "save")
	public String save(ShowQualityife showQualityife, Model model, RedirectAttributes redirectAttributes) {
		try{
			if (!beanValidator(model, showQualityife)){
				return form(showQualityife, model);
			}
			if(showQualityife.getIsNewRecord()){
				ShowQualityife condition = new ShowQualityife();
				condition.setYear(showQualityife.getYear());
				condition.setIndexName(showQualityife.getIndexName());
				List<ShowQualityife> list = showQualityifeService.findList(condition);
				if(list != null && list.size()>0){
					throw new Exception("数据重复");
				}
			}
			showQualityifeService.save(showQualityife);
			addMessage(redirectAttributes, "保存经济生活质量成功");
		}catch (Exception e){
			addMessage(redirectAttributes, "保存经济生活质量失败，失败原因："+e.getMessage());
		}

		return "redirect:"+Global.getAdminPath()+"/csyx/showQualityife/?repage";
	}
	
	@RequiresPermissions("csyx:showQualityife:edit")
	@RequestMapping(value = "delete")
	public String delete(ShowQualityife showQualityife, RedirectAttributes redirectAttributes) {
		showQualityifeService.delete(showQualityife);
		addMessage(redirectAttributes, "删除经济生活质量成功");
		return "redirect:"+Global.getAdminPath()+"/csyx/showQualityife/?repage";
	}

}