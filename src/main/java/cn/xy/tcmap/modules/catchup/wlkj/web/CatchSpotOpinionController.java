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
import cn.xy.tcmap.modules.catchup.wlkj.entity.CatchSpotOpinion;
import cn.xy.tcmap.modules.catchup.wlkj.service.CatchSpotOpinionService;

import java.util.Date;
import java.util.List;

/**
 * 全国舆情热点Controller
 * @author xuzhou
 * @version 2018-05-28
 */
@Controller
@RequestMapping(value = "${adminPath}/wlkj/catchSpotOpinion")
public class CatchSpotOpinionController extends BaseController {

	@Autowired
	private CatchSpotOpinionService catchSpotOpinionService;
	
	@ModelAttribute
	public CatchSpotOpinion get(@RequestParam(required=false) String id) {
		CatchSpotOpinion entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = catchSpotOpinionService.get(id);
		}
		if (entity == null){
			entity = new CatchSpotOpinion();
		}
		return entity;
	}
	
	@RequiresPermissions("wlkj:catchSpotOpinion:view")
	@RequestMapping(value = {"list", ""})
	public String list(CatchSpotOpinion catchSpotOpinion, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CatchSpotOpinion> page = catchSpotOpinionService.findPage(new Page<CatchSpotOpinion>(request, response), catchSpotOpinion); 
		model.addAttribute("page", page);
		return "catchup/wlkj/catchSpotOpinionList";
	}

	@RequiresPermissions("wlkj:catchSpotOpinion:view")
	@RequestMapping(value = "form")
	public String form(CatchSpotOpinion catchSpotOpinion, Model model) {
		model.addAttribute("catchSpotOpinion", catchSpotOpinion);
		return "catchup/wlkj/catchSpotOpinionForm";
	}

	@RequiresPermissions("wlkj:catchSpotOpinion:edit")
	@RequestMapping(value = "save")
	public String save(CatchSpotOpinion catchSpotOpinion, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, catchSpotOpinion)){
			return form(catchSpotOpinion, model);
		}
		try {
			if(catchSpotOpinion.getIsNewRecord()){
                CatchSpotOpinion catchSpotOpinion1 = new CatchSpotOpinion();
                catchSpotOpinion1.setChinaType(catchSpotOpinion.getChinaType());
                List<CatchSpotOpinion> list = catchSpotOpinionService.findList(catchSpotOpinion1);
                if(list!=null&&list.size()>0){
                    throw new Exception("数据重复");
                }
            }
			catchSpotOpinion.setDate(new Date());
			catchSpotOpinionService.save(catchSpotOpinion);
			addMessage(redirectAttributes, "保存全国舆情热点成功");
		}catch (Exception e){
            addMessage(redirectAttributes, "保存全国舆情热点失败，原因："+e.getMessage());
		}

		return "redirect:"+Global.getAdminPath()+"/wlkj/catchSpotOpinion/?repage";
	}
	
	@RequiresPermissions("wlkj:catchSpotOpinion:edit")
	@RequestMapping(value = "delete")
	public String delete(CatchSpotOpinion catchSpotOpinion, RedirectAttributes redirectAttributes) {
		catchSpotOpinionService.delete(catchSpotOpinion);
		addMessage(redirectAttributes, "删除全国舆情热点成功");
		return "redirect:"+Global.getAdminPath()+"/wlkj/catchSpotOpinion/?repage";
	}

}