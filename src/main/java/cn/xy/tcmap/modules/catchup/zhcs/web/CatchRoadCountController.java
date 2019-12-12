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
import cn.xy.tcmap.modules.catchup.zhcs.entity.CatchRoadCount;
import cn.xy.tcmap.modules.catchup.zhcs.service.CatchRoadCountService;

import java.util.List;

/**
 * 公路统计Controller
 * @author liuyang
 * @version 2018-06-05
 */
@Controller
@RequestMapping(value = "${adminPath}/zhcs/catchRoadCount")
public class CatchRoadCountController extends BaseController {

	@Autowired
	private CatchRoadCountService catchRoadCountService;
	
	@ModelAttribute
	public CatchRoadCount get(@RequestParam(required=false) String id) {
		CatchRoadCount entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = catchRoadCountService.get(id);
		}
		if (entity == null){
			entity = new CatchRoadCount();
		}
		return entity;
	}
	
	@RequiresPermissions("zhcs:catchRoadCount:view")
	@RequestMapping(value = {"list", ""})
	public String list(CatchRoadCount catchRoadCount, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CatchRoadCount> page = catchRoadCountService.findPage(new Page<CatchRoadCount>(request, response), catchRoadCount); 
		model.addAttribute("page", page);
		return "catchup/zhcs/catchRoadCountList";
	}

	@RequiresPermissions("zhcs:catchRoadCount:view")
	@RequestMapping(value = "form")
	public String form(CatchRoadCount catchRoadCount, Model model) {
		model.addAttribute("catchRoadCount", catchRoadCount);
		return "catchup/zhcs/catchRoadCountForm";
	}

	@RequiresPermissions("zhcs:catchRoadCount:edit")
	@RequestMapping(value = "save")
	public String save(CatchRoadCount catchRoadCount, Model model, RedirectAttributes redirectAttributes) {
		try{
			if (!beanValidator(model, catchRoadCount)){
				return form(catchRoadCount, model);
			}
			if(catchRoadCount.getIsNewRecord()){
				CatchRoadCount crc = new CatchRoadCount();
				crc.setNyear(catchRoadCount.getNyear());
				crc.setRoadType(catchRoadCount.getRoadType());
				List<CatchRoadCount> list = catchRoadCountService.findList(crc);
				if(list != null && list.size()>0){
					throw new Exception("数据重复");
				}
			}
			catchRoadCountService.save(catchRoadCount);
			addMessage(redirectAttributes, "保存公路统计成功");
		}catch (Exception e){
			addMessage(redirectAttributes, "保存公路统计失败，失败原因："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/zhcs/catchRoadCount/?repage";
	}
	
	@RequiresPermissions("zhcs:catchRoadCount:edit")
	@RequestMapping(value = "delete")
	public String delete(CatchRoadCount catchRoadCount, RedirectAttributes redirectAttributes) {
		catchRoadCountService.delete(catchRoadCount);
		addMessage(redirectAttributes, "删除公路统计成功");
		return "redirect:"+Global.getAdminPath()+"/zhcs/catchRoadCount/?repage";
	}

}