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
import cn.xy.tcmap.modules.catchup.zhcs.entity.CatchHingeStatistics;
import cn.xy.tcmap.modules.catchup.zhcs.service.CatchHingeStatisticsService;

import java.util.List;

/**
 * 交通枢纽统计Controller
 * @author liuyang
 * @version 2018-06-12
 */
@Controller
@RequestMapping(value = "${adminPath}/zhcs/catchHingeStatistics")
public class CatchHingeStatisticsController extends BaseController {

	@Autowired
	private CatchHingeStatisticsService catchHingeStatisticsService;
	
	@ModelAttribute
	public CatchHingeStatistics get(@RequestParam(required=false) String id) {
		CatchHingeStatistics entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = catchHingeStatisticsService.get(id);
		}
		if (entity == null){
			entity = new CatchHingeStatistics();
		}
		return entity;
	}
	
	@RequiresPermissions("zhcs:catchHingeStatistics:view")
	@RequestMapping(value = {"list", ""})
	public String list(CatchHingeStatistics catchHingeStatistics, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CatchHingeStatistics> page = catchHingeStatisticsService.findPage(new Page<CatchHingeStatistics>(request, response), catchHingeStatistics); 
		model.addAttribute("page", page);
		return "catchup/zhcs/catchHingeStatisticsList";
	}

	@RequiresPermissions("zhcs:catchHingeStatistics:view")
	@RequestMapping(value = "form")
	public String form(CatchHingeStatistics catchHingeStatistics, Model model) {
		model.addAttribute("catchHingeStatistics", catchHingeStatistics);
		return "catchup/zhcs/catchHingeStatisticsForm";
	}

	@RequiresPermissions("zhcs:catchHingeStatistics:edit")
	@RequestMapping(value = "save")
	public String save(CatchHingeStatistics catchHingeStatistics, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, catchHingeStatistics)){
			return form(catchHingeStatistics, model);
		}
		try {
			if(catchHingeStatistics.getIsNewRecord()){
				CatchHingeStatistics condition = new CatchHingeStatistics();
				condition.setNyear(catchHingeStatistics.getNyear());
				condition.setHingeType(catchHingeStatistics.getHingeType());
				List<CatchHingeStatistics> list = catchHingeStatisticsService.findList(condition);
				if(list != null && list.size()>0){
					throw new Exception("数据重复");
				}
			}
			catchHingeStatisticsService.save(catchHingeStatistics);
			addMessage(redirectAttributes, "保存交通枢纽统计成功");
		} catch (Exception e) {
			e.printStackTrace();
			addMessage(redirectAttributes, "保存交通枢纽统计失败，原因："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/zhcs/catchHingeStatistics/?repage";
	}
	
	@RequiresPermissions("zhcs:catchHingeStatistics:edit")
	@RequestMapping(value = "delete")
	public String delete(CatchHingeStatistics catchHingeStatistics, RedirectAttributes redirectAttributes) {
		catchHingeStatisticsService.delete(catchHingeStatistics);
		addMessage(redirectAttributes, "删除交通枢纽统计成功");
		return "redirect:"+Global.getAdminPath()+"/zhcs/catchHingeStatistics/?repage";
	}

}