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
import cn.xy.tcmap.modules.catchup.wlkj.entity.CatchSourceOpinion;
import cn.xy.tcmap.modules.catchup.wlkj.service.CatchSourceOpinionService;

import java.util.Date;
import java.util.List;

/**
 * 舆情来源Controller
 * @author xuzhou
 * @version 2018-05-28
 */
@Controller
@RequestMapping(value = "${adminPath}/wlkj/catchSourceOpinion")
public class CatchSourceOpinionController extends BaseController {

	@Autowired
	private CatchSourceOpinionService catchSourceOpinionService;
	
	@ModelAttribute
	public CatchSourceOpinion get(@RequestParam(required=false) String id) {
		CatchSourceOpinion entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = catchSourceOpinionService.get(id);
		}
		if (entity == null){
			entity = new CatchSourceOpinion();
		}
		return entity;
	}
	
	@RequiresPermissions("wlkj:catchSourceOpinion:view")
	@RequestMapping(value = {"list", ""})
	public String list(CatchSourceOpinion catchSourceOpinion, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CatchSourceOpinion> page = catchSourceOpinionService.findPage(new Page<CatchSourceOpinion>(request, response), catchSourceOpinion); 
		model.addAttribute("page", page);
		return "catchup/wlkj/catchSourceOpinionList";
	}

	@RequiresPermissions("wlkj:catchSourceOpinion:view")
	@RequestMapping(value = "form")
	public String form(CatchSourceOpinion catchSourceOpinion, Model model) {
		model.addAttribute("catchSourceOpinion", catchSourceOpinion);
		return "catchup/wlkj/catchSourceOpinionForm";
	}

	@RequiresPermissions("wlkj:catchSourceOpinion:edit")
	@RequestMapping(value = "save")
	public String save(CatchSourceOpinion catchSourceOpinion, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, catchSourceOpinion)){
			return form(catchSourceOpinion, model);
		}
		try {
			if(catchSourceOpinion.getIsNewRecord()){
                CatchSourceOpinion catchSourceOpinion1 = new CatchSourceOpinion();
                catchSourceOpinion1.setSourceType(catchSourceOpinion.getSourceType());
                List<CatchSourceOpinion> list = catchSourceOpinionService.findList(catchSourceOpinion1);
                if(list!=null && list.size()>0){
                    throw new Exception("数据重复");
                }
            }
			catchSourceOpinion.setDate(new Date());
			catchSourceOpinionService.save(catchSourceOpinion);
			addMessage(redirectAttributes, "保存舆情来源成功");
		}catch (Exception e){
            addMessage(redirectAttributes, "保存舆情来源失败，原因："+e.getMessage());
		}

		return "redirect:"+Global.getAdminPath()+"/wlkj/catchSourceOpinion/?repage";
	}
	
	@RequiresPermissions("wlkj:catchSourceOpinion:edit")
	@RequestMapping(value = "delete")
	public String delete(CatchSourceOpinion catchSourceOpinion, RedirectAttributes redirectAttributes) {
		catchSourceOpinionService.delete(catchSourceOpinion);
		addMessage(redirectAttributes, "删除舆情来源成功");
		return "redirect:"+Global.getAdminPath()+"/wlkj/catchSourceOpinion/?repage";
	}

}