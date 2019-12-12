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
import cn.xy.tcmap.modules.catchup.csyx.entity.LiveGovermentType;
import cn.xy.tcmap.modules.catchup.csyx.service.LiveGovermentTypeService;

import java.util.List;

/**
 * 办理类型Controller
 * @author wufan
 * @version 2019-10-14
 */
@Controller
@RequestMapping(value = "${adminPath}/csyx/liveGovermentType")
public class LiveGovermentTypeController extends BaseController {

	@Autowired
	private LiveGovermentTypeService liveGovermentTypeService;
	
	@ModelAttribute
	public LiveGovermentType get(@RequestParam(required=false) String id) {
		LiveGovermentType entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = liveGovermentTypeService.get(id);
		}
		if (entity == null){
			entity = new LiveGovermentType();
		}
		return entity;
	}
	
	@RequiresPermissions("csyx:liveGovermentType:view")
	@RequestMapping(value = {"list", ""})
	public String list(LiveGovermentType liveGovermentType, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<LiveGovermentType> page = liveGovermentTypeService.findPage(new Page<LiveGovermentType>(request, response), liveGovermentType); 
		model.addAttribute("page", page);
		return "catchup/csyx/liveGovermentTypeList";
	}

	@RequiresPermissions("csyx:liveGovermentType:view")
	@RequestMapping(value = "form")
	public String form(LiveGovermentType liveGovermentType, Model model) {
		model.addAttribute("liveGovermentType", liveGovermentType);
		return "catchup/csyx/liveGovermentTypeForm";
	}

	@RequiresPermissions("csyx:liveGovermentType:edit")
	@RequestMapping(value = "save")
	public String save(LiveGovermentType liveGovermentType, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, liveGovermentType)){
			return form(liveGovermentType, model);
		}
		try{
			if(liveGovermentType.getIsNewRecord()){
                LiveGovermentType liveGovermentType1 = new LiveGovermentType();
                liveGovermentType1.setType(liveGovermentType.getType());
                List<LiveGovermentType> list = liveGovermentTypeService.findList(liveGovermentType1);
                if(list!=null&& list.size()>0){
                    throw new Exception("数据重复");
                }
            }
			liveGovermentTypeService.save(liveGovermentType);
			addMessage(redirectAttributes, "保存办理类型成功");
		}catch (Exception e){
            addMessage(redirectAttributes, "保存办理类型失败，原因："+e.getMessage());
		}

		return "redirect:"+Global.getAdminPath()+"/csyx/liveGovermentType/?repage";
	}
	
	@RequiresPermissions("csyx:liveGovermentType:edit")
	@RequestMapping(value = "delete")
	public String delete(LiveGovermentType liveGovermentType, RedirectAttributes redirectAttributes) {
		liveGovermentTypeService.delete(liveGovermentType);
		addMessage(redirectAttributes, "删除办理类型成功");
		return "redirect:"+Global.getAdminPath()+"/csyx/liveGovermentType/?repage";
	}

}