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
import cn.xy.tcmap.modules.catchup.csyx.entity.CatchAqiparam;
import cn.xy.tcmap.modules.catchup.csyx.service.CatchAqiparamService;

import java.util.List;

/**
 * 空气质量Controller
 * @author xuzhou
 * @version 2018-05-24
 */
@Controller
@RequestMapping(value = "${adminPath}/csyx/catchAqiparam")
public class CatchAqiparamController extends BaseController {

	@Autowired
	private CatchAqiparamService catchAqiparamService;
	
	@ModelAttribute
	public CatchAqiparam get(@RequestParam(required=false) String id) {
		CatchAqiparam entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = catchAqiparamService.get(id);
		}
		if (entity == null){
			entity = new CatchAqiparam();
		}
		return entity;
	}
	
	@RequiresPermissions("csyx:catchAqiparam:view")
	@RequestMapping(value = {"list", ""})
	public String list(CatchAqiparam catchAqiparam, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CatchAqiparam> page = catchAqiparamService.findPage(new Page<CatchAqiparam>(request, response), catchAqiparam); 
		model.addAttribute("page", page);
		return "catchup/csyx/catchAqiparamList";
	}

	@RequiresPermissions("csyx:catchAqiparam:view")
	@RequestMapping(value = "form")
	public String form(CatchAqiparam catchAqiparam, Model model) {
		model.addAttribute("catchAqiparam", catchAqiparam);
		return "catchup/csyx/catchAqiparamForm";
	}

	@RequiresPermissions("csyx:catchAqiparam:edit")
	@RequestMapping(value = "save")
	public String save(CatchAqiparam catchAqiparam, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, catchAqiparam)){
			return form(catchAqiparam, model);
		}
		try {
			if(catchAqiparam.getIsNewRecord()){
                CatchAqiparam catchAqiparam1 = new CatchAqiparam();
                catchAqiparam1.setAreaName(catchAqiparam.getAreaName());
                List<CatchAqiparam> list = catchAqiparamService.findList(catchAqiparam1);
                if (list!=null&& list.size()>0){
                    throw new Exception("数据重复");
                }
            }
			catchAqiparamService.save(catchAqiparam);
			addMessage(redirectAttributes, "保存空气质量成功");
		}catch (Exception e){
            addMessage(redirectAttributes, "保存空气质量失败，原因："+e.getMessage());
		}

		return "redirect:"+Global.getAdminPath()+"/csyx/catchAqiparam/?repage";
	}
	
	@RequiresPermissions("csyx:catchAqiparam:edit")
	@RequestMapping(value = "delete")
	public String delete(CatchAqiparam catchAqiparam, RedirectAttributes redirectAttributes) {
		catchAqiparamService.delete(catchAqiparam);
		addMessage(redirectAttributes, "删除空气质量成功");
		return "redirect:"+Global.getAdminPath()+"/csyx/catchAqiparam/?repage";
	}

}