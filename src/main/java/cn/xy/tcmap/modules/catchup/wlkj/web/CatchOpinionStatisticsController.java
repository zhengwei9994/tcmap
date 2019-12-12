/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.wlkj.web;

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
import cn.xy.tcmap.modules.catchup.wlkj.entity.CatchOpinionStatistics;
import cn.xy.tcmap.modules.catchup.wlkj.service.CatchOpinionStatisticsService;

import java.util.Date;
import java.util.List;

/**
 * 舆情统计Controller
 * @author xuzhou
 * @version 2018-05-28
 */
@Controller
@RequestMapping(value = "${adminPath}/wlkj/catchOpinionStatistics")
public class CatchOpinionStatisticsController extends BaseController {

	@Autowired
	private CatchOpinionStatisticsService catchOpinionStatisticsService;
	
	@ModelAttribute
	public CatchOpinionStatistics get(@RequestParam(required=false) String id) {
		CatchOpinionStatistics entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = catchOpinionStatisticsService.get(id);
		}
		if (entity == null){
			entity = new CatchOpinionStatistics();
		}
		return entity;
	}
	
	@RequiresPermissions("wlkj:catchOpinionStatistics:view")
	@RequestMapping(value = {"list", ""})
	public String list(CatchOpinionStatistics catchOpinionStatistics, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CatchOpinionStatistics> page = catchOpinionStatisticsService.findPage(new Page<CatchOpinionStatistics>(request, response), catchOpinionStatistics); 
		model.addAttribute("page", page);
		return "catchup/wlkj/catchOpinionStatisticsList";
	}

	@RequiresPermissions("wlkj:catchOpinionStatistics:view")
	@RequestMapping(value = "form")
	public String form(CatchOpinionStatistics catchOpinionStatistics, Model model) {
		model.addAttribute("catchOpinionStatistics", catchOpinionStatistics);
		return "catchup/wlkj/catchOpinionStatisticsForm";
	}

	@RequiresPermissions("wlkj:catchOpinionStatistics:edit")
	@RequestMapping(value = "save")
	public String save(CatchOpinionStatistics catchOpinionStatistics, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, catchOpinionStatistics)){
			return form(catchOpinionStatistics, model);
		}
		try {
			if(catchOpinionStatistics.getIsNewRecord()){
                CatchOpinionStatistics catchOpinionStatistics1 = new CatchOpinionStatistics();
                catchOpinionStatistics.setStatisticalType(catchOpinionStatistics1.getStatisticalType());
                List<CatchOpinionStatistics> list = catchOpinionStatisticsService.findList(catchOpinionStatistics1);
                if (list!=null && list.size()>0){
                    throw new Exception("数据重复");
                }
            }
            catchOpinionStatistics.setDateEntry(new Date());
            catchOpinionStatisticsService.save(catchOpinionStatistics);
            addMessage(redirectAttributes, "保存舆情统计成功");
		}catch (Exception e){
            addMessage(redirectAttributes,"保存舆情统计失败，原因："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/wlkj/catchOpinionStatistics/?repage";
	}
	
	@RequiresPermissions("wlkj:catchOpinionStatistics:edit")
	@RequestMapping(value = "delete")
	public String delete(CatchOpinionStatistics catchOpinionStatistics, RedirectAttributes redirectAttributes) {
		catchOpinionStatisticsService.delete(catchOpinionStatistics);
		addMessage(redirectAttributes, "删除舆情统计成功");
		return "redirect:"+Global.getAdminPath()+"/wlkj/catchOpinionStatistics/?repage";
	}

}