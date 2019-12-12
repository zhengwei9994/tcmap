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
import cn.xy.tcmap.modules.catchup.wlkj.entity.CatchHotPublic;
import cn.xy.tcmap.modules.catchup.wlkj.service.CatchHotPublicService;

import java.util.List;

/**
 * 热点舆情Controller
 * @author xuzhou
 * @version 2018-05-28
 */
@Controller
@RequestMapping(value = "${adminPath}/wlkj/catchHotPublic")
public class CatchHotPublicController extends BaseController {

	@Autowired
	private CatchHotPublicService catchHotPublicService;
	
	@ModelAttribute
	public CatchHotPublic get(@RequestParam(required=false) String id) {
		CatchHotPublic entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = catchHotPublicService.get(id);
		}
		if (entity == null){
			entity = new CatchHotPublic();
		}
		return entity;
	}
	
	@RequiresPermissions("wlkj:catchHotPublic:view")
	@RequestMapping(value = {"list", ""})
	public String list(CatchHotPublic catchHotPublic, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CatchHotPublic> page = catchHotPublicService.findPage(new Page<CatchHotPublic>(request, response), catchHotPublic); 
		model.addAttribute("page", page);
		return "catchup/wlkj/catchHotPublicList";
	}

	@RequiresPermissions("wlkj:catchHotPublic:view")
	@RequestMapping(value = "form")
	public String form(CatchHotPublic catchHotPublic, Model model) {
		model.addAttribute("catchHotPublic", catchHotPublic);
		return "catchup/wlkj/catchHotPublicForm";
	}

	@RequiresPermissions("wlkj:catchHotPublic:edit")
	@RequestMapping(value = "save")
	public String save(CatchHotPublic catchHotPublic, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, catchHotPublic)){
			return form(catchHotPublic, model);
		}
		try{
			if(catchHotPublic.getIsNewRecord()){
                CatchHotPublic catchHotPublic1 = new CatchHotPublic();
                catchHotPublic1.setSort(catchHotPublic.getSort());
                List<CatchHotPublic> list = catchHotPublicService.findList(catchHotPublic1);
                if (list!=null && list.size()>0){
                    throw new Exception("数据重复");
                }
            }
			catchHotPublicService.save(catchHotPublic);
			addMessage(redirectAttributes, "保存热点舆情成功");
		}catch (Exception e){
            addMessage(redirectAttributes, "保存热点舆情失败，原因："+e.getMessage());
		}


		return "redirect:"+Global.getAdminPath()+"/wlkj/catchHotPublic/?repage";
	}
	
	@RequiresPermissions("wlkj:catchHotPublic:edit")
	@RequestMapping(value = "delete")
	public String delete(CatchHotPublic catchHotPublic, RedirectAttributes redirectAttributes) {
		catchHotPublicService.delete(catchHotPublic);
		addMessage(redirectAttributes, "删除热点舆情成功");
		return "redirect:"+Global.getAdminPath()+"/wlkj/catchHotPublic/?repage";
	}

}