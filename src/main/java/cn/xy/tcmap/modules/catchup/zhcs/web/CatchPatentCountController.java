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
import cn.xy.tcmap.modules.catchup.zhcs.entity.CatchPatentCount;
import cn.xy.tcmap.modules.catchup.zhcs.service.CatchPatentCountService;

import java.util.List;

/**
 * 专利数量统计Controller
 * @author liuyang
 * @version 2018-06-05
 */
@Controller
@RequestMapping(value = "${adminPath}/zhcs/catchPatentCount")
public class CatchPatentCountController extends BaseController {

	@Autowired
	private CatchPatentCountService catchPatentCountService;
	
	@ModelAttribute
	public CatchPatentCount get(@RequestParam(required=false) String id) {
		CatchPatentCount entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = catchPatentCountService.get(id);
		}
		if (entity == null){
			entity = new CatchPatentCount();
		}
		return entity;
	}
	
	@RequiresPermissions("zhcs:catchPatentCount:view")
	@RequestMapping(value = {"list", ""})
	public String list(CatchPatentCount catchPatentCount, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CatchPatentCount> page = catchPatentCountService.findPage(new Page<CatchPatentCount>(request, response), catchPatentCount); 
		model.addAttribute("page", page);
		return "catchup/zhcs/catchPatentCountList";
	}

	@RequiresPermissions("zhcs:catchPatentCount:view")
	@RequestMapping(value = "form")
	public String form(CatchPatentCount catchPatentCount, Model model) {
		model.addAttribute("catchPatentCount", catchPatentCount);
		return "catchup/zhcs/catchPatentCountForm";
	}

	@RequiresPermissions("zhcs:catchPatentCount:edit")
	@RequestMapping(value = "save")
	public String save(CatchPatentCount catchPatentCount, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, catchPatentCount)){
			return form(catchPatentCount, model);
		}
		try {
			if(catchPatentCount.getIsNewRecord()){
				CatchPatentCount condition = new CatchPatentCount();
				condition.setNyear(catchPatentCount.getNyear());
				List<CatchPatentCount> list = catchPatentCountService.findList(condition);
				if(list != null && list.size()>0){
					throw new Exception("数据重复");
				}
			}
			catchPatentCountService.save(catchPatentCount);
			addMessage(redirectAttributes, "保存专利数量统计成功");
		} catch (Exception e) {
			e.printStackTrace();
			addMessage(redirectAttributes, "保存专利数量统计失败"+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/zhcs/catchPatentCount/?repage";
	}
	
	@RequiresPermissions("zhcs:catchPatentCount:edit")
	@RequestMapping(value = "delete")
	public String delete(CatchPatentCount catchPatentCount, RedirectAttributes redirectAttributes) {
		catchPatentCountService.delete(catchPatentCount);
		addMessage(redirectAttributes, "删除专利数量统计成功");
		return "redirect:"+Global.getAdminPath()+"/zhcs/catchPatentCount/?repage";
	}

}