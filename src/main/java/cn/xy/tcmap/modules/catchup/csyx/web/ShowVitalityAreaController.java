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
import cn.xy.tcmap.modules.catchup.csyx.entity.ShowVitalityArea;
import cn.xy.tcmap.modules.catchup.csyx.service.ShowVitalityAreaService;

import java.util.List;

/**
 * 经济活力地图Controller
 * @author wufan
 * @version 2019-07-31
 */
@Controller
@RequestMapping(value = "${adminPath}/csyx/showVitalityArea")
public class ShowVitalityAreaController extends BaseController {

	@Autowired
	private ShowVitalityAreaService showVitalityAreaService;
	
	@ModelAttribute
	public ShowVitalityArea get(@RequestParam(required=false) String id) {
		ShowVitalityArea entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = showVitalityAreaService.get(id);
		}
		if (entity == null){
			entity = new ShowVitalityArea();
		}
		return entity;
	}
	
	@RequiresPermissions("csyx:showVitalityArea:view")
	@RequestMapping(value = {"list", ""})
	public String list(ShowVitalityArea showVitalityArea, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ShowVitalityArea> page = showVitalityAreaService.findPage(new Page<ShowVitalityArea>(request, response), showVitalityArea); 
		model.addAttribute("page", page);
		return "catchup/csyx/showVitalityAreaList";
	}

	@RequiresPermissions("csyx:showVitalityArea:view")
	@RequestMapping(value = "form")
	public String form(ShowVitalityArea showVitalityArea, Model model) {
		model.addAttribute("showVitalityArea", showVitalityArea);
		return "catchup/csyx/showVitalityAreaForm";
	}

	@RequiresPermissions("csyx:showVitalityArea:edit")
	@RequestMapping(value = "save")
	public String save(ShowVitalityArea showVitalityArea, Model model, RedirectAttributes redirectAttributes) {
		try{
			if (!beanValidator(model, showVitalityArea)){
				return form(showVitalityArea, model);
			}
			if(showVitalityArea.getIsNewRecord()){
				ShowVitalityArea condition = new ShowVitalityArea();
				condition.setYear(showVitalityArea.getYear());
				condition.setArea(showVitalityArea.getArea());
				List<ShowVitalityArea> list = showVitalityAreaService.findList(condition);
				if(list != null && list.size()>0){
					throw new Exception("数据重复");
				}
			}
			showVitalityAreaService.save(showVitalityArea);
			addMessage(redirectAttributes, "保存经济活力地图成功");
		}catch (Exception e){
			addMessage(redirectAttributes, "保存经济活力地图失败，失败原因："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/csyx/showVitalityArea/?repage";
	}
	
	@RequiresPermissions("csyx:showVitalityArea:edit")
	@RequestMapping(value = "delete")
	public String delete(ShowVitalityArea showVitalityArea, RedirectAttributes redirectAttributes) {
		showVitalityAreaService.delete(showVitalityArea);
		addMessage(redirectAttributes, "删除经济活力地图成功");
		return "redirect:"+Global.getAdminPath()+"/csyx/showVitalityArea/?repage";
	}

}