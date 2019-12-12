/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.fzjc.web;

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
import cn.xy.tcmap.modules.catchup.fzjc.entity.CatchHumanResources;
import cn.xy.tcmap.modules.catchup.fzjc.service.CatchHumanResourcesService;

import java.util.List;

/**
 * 人力资源统计Controller
 * @author liuyang
 * @version 2018-05-29
 */
@Controller
@RequestMapping(value = "${adminPath}/fzjc/catchHumanResources")
public class CatchHumanResourcesController extends BaseController {

	@Autowired
	private CatchHumanResourcesService catchHumanResourcesService;
	
	@ModelAttribute
	public CatchHumanResources get(@RequestParam(required=false) String id) {
		CatchHumanResources entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = catchHumanResourcesService.get(id);
		}
		if (entity == null){
			entity = new CatchHumanResources();
		}
		return entity;
	}
	
	@RequiresPermissions("fzjc:catchHumanResources:view")
	@RequestMapping(value = {"list", ""})
	public String list(CatchHumanResources catchHumanResources, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CatchHumanResources> page = catchHumanResourcesService.findPage(new Page<CatchHumanResources>(request, response), catchHumanResources); 
		model.addAttribute("page", page);
		return "catchup/fzjc/catchHumanResourcesList";
	}

	@RequiresPermissions("fzjc:catchHumanResources:view")
	@RequestMapping(value = "form")
	public String form(CatchHumanResources catchHumanResources, Model model) {
		model.addAttribute("catchHumanResources", catchHumanResources);
		return "catchup/fzjc/catchHumanResourcesForm";
	}

	@RequiresPermissions("fzjc:catchHumanResources:edit")
	@RequestMapping(value = "save")
	public String save(CatchHumanResources catchHumanResources, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, catchHumanResources)){
			return form(catchHumanResources, model);
		}
		try {
			if(catchHumanResources.getIsNewRecord()){
                CatchHumanResources catchHumanResources1 = new CatchHumanResources();
                catchHumanResources1.setNyear(catchHumanResources.getNyear());
                catchHumanResources1.setCompanyType(catchHumanResources.getCompanyType());
                List<CatchHumanResources> list = catchHumanResourcesService.findList(catchHumanResources1);
                if(list!=null&&list.size()>0){
                    throw new  Exception("数据重复");
                }
            }
			catchHumanResourcesService.save(catchHumanResources);
			addMessage(redirectAttributes, "保存人力资源统计成功");
		}catch (Exception e){
            addMessage(redirectAttributes, "保存人力资源统计失败，原因："+e.getMessage());
		}

		return "redirect:"+Global.getAdminPath()+"/fzjc/catchHumanResources/?repage";
	}
	
	@RequiresPermissions("fzjc:catchHumanResources:edit")
	@RequestMapping(value = "delete")
	public String delete(CatchHumanResources catchHumanResources, RedirectAttributes redirectAttributes) {
		catchHumanResourcesService.delete(catchHumanResources);
		addMessage(redirectAttributes, "删除人力资源统计成功");
		return "redirect:"+Global.getAdminPath()+"/fzjc/catchHumanResources/?repage";
	}

}