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
import cn.xy.tcmap.modules.catchup.csyx.entity.CatchShakePoverty;
import cn.xy.tcmap.modules.catchup.csyx.service.CatchShakePovertyService;

import java.util.List;

/**
 * 精准务实抓脱贫Controller
 * @author xuzhou
 * @version 2018-05-24
 */
@Controller
@RequestMapping(value = "${adminPath}/csyx/catchShakePoverty")
public class CatchShakePovertyController extends BaseController {

	@Autowired
	private CatchShakePovertyService catchShakePovertyService;
	
	@ModelAttribute
	public CatchShakePoverty get(@RequestParam(required=false) String id) {
		CatchShakePoverty entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = catchShakePovertyService.get(id);
		}
		if (entity == null){
			entity = new CatchShakePoverty();
		}
		return entity;
	}
	
	@RequiresPermissions("csyx:catchShakePoverty:view")
	@RequestMapping(value = {"list", ""})
	public String list(CatchShakePoverty catchShakePoverty, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CatchShakePoverty> page = catchShakePovertyService.findPage(new Page<CatchShakePoverty>(request, response), catchShakePoverty); 
		model.addAttribute("page", page);
		return "catchup/csyx/catchShakePovertyList";
	}

	@RequiresPermissions("csyx:catchShakePoverty:view")
	@RequestMapping(value = "form")
	public String form(CatchShakePoverty catchShakePoverty, Model model) {
		model.addAttribute("catchShakePoverty", catchShakePoverty);
		return "catchup/csyx/catchShakePovertyForm";
	}

	@RequiresPermissions("csyx:catchShakePoverty:edit")
	@RequestMapping(value = "save")
	public String save(CatchShakePoverty catchShakePoverty, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, catchShakePoverty)){
			return form(catchShakePoverty, model);
		}
		try {
			if(catchShakePoverty.getIsNewRecord()){
                CatchShakePoverty catchShakePoverty1 = new CatchShakePoverty();
                catchShakePoverty1.setNyear(catchShakePoverty.getNyear());
                catchShakePoverty1.setAreaName(catchShakePoverty.getAreaName());
                List<CatchShakePoverty> list = catchShakePovertyService.findList(catchShakePoverty1);
                if(list!=null&&list.size()>0){
                    throw new Exception("数据重复");
                }
            }
			catchShakePovertyService.save(catchShakePoverty);
			addMessage(redirectAttributes, "保存精准务实抓脱贫成功");
		}catch (Exception e){
            addMessage(redirectAttributes, "保存精准务实抓脱贫失败，原因："+e.getMessage());
		}

		return "redirect:"+Global.getAdminPath()+"/csyx/catchShakePoverty/?repage";
	}
	
	@RequiresPermissions("csyx:catchShakePoverty:edit")
	@RequestMapping(value = "delete")
	public String delete(CatchShakePoverty catchShakePoverty, RedirectAttributes redirectAttributes) {
		catchShakePovertyService.delete(catchShakePoverty);
		addMessage(redirectAttributes, "删除精准务实抓脱贫成功");
		return "redirect:"+Global.getAdminPath()+"/csyx/catchShakePoverty/?repage";
	}

}