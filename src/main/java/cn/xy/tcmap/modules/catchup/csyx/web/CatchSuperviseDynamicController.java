package cn.xy.tcmap.modules.catchup.csyx.web;

import cn.xy.tcmap.common.config.Global;
import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.utils.StringUtils;
import cn.xy.tcmap.common.web.BaseController;
import cn.xy.tcmap.modules.catchup.csyx.entity.CatchSuperviseDynamic;
import cn.xy.tcmap.modules.catchup.csyx.service.CatchSuperviseDynamicService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * code is far away from bug with the animal protecting
 * @Description : ${Desc}
 * ---------------------------------
 * @Author : dell
 * @Date : Create in 2018/9/19
 */
@Controller
@RequestMapping(value = "${adminPath}/csyx/catchSuperviseDynamics")
public class CatchSuperviseDynamicController extends BaseController {

    @Autowired
    private CatchSuperviseDynamicService superviseDynamicService;

    @RequestMapping(value = "findAll",method = RequestMethod.GET)
    @ResponseBody
    public List<CatchSuperviseDynamic> findAll(@RequestBody(required = false) CatchSuperviseDynamic catchSuperviseDynamic){
        return superviseDynamicService.findAll(catchSuperviseDynamic);
    }
    
    @ModelAttribute
   	public CatchSuperviseDynamic get(@RequestParam(required=false) String id) {
   		CatchSuperviseDynamic entity = null;
   		if (StringUtils.isNotBlank(id)){
   			entity = superviseDynamicService.get(id);
   		}
   		if (entity == null){
   			entity = new CatchSuperviseDynamic();
   		}
   		return entity;
   	}

   	@RequiresPermissions("csyx:catchSuperviseDynamic:view")
   	@RequestMapping(value = {"list", ""})
   	public String list(CatchSuperviseDynamic catchSuperviseDynamic, HttpServletRequest request, HttpServletResponse response, Model model) {
   		Page<CatchSuperviseDynamic> pg = new Page<CatchSuperviseDynamic>(request, response);
   		pg.setOrderBy("createTime");
   		Page<CatchSuperviseDynamic> page = superviseDynamicService.findPage(pg, catchSuperviseDynamic); 
   		model.addAttribute("page", page);
   		return "catchup/csyx/catchSuperviseDynamicsList";
   	}

   	@RequiresPermissions("csyx:catchSuperviseDynamic:view")
   	@RequestMapping(value = "form")
   	public String form(CatchSuperviseDynamic catchSuperviseDynamic, Model model) {
   		model.addAttribute("catchSuperviseDynamic", catchSuperviseDynamic);
   		return "catchup/csyx/catchSuperviseDynamicsForm";
   	}

   	@RequiresPermissions("csyx:catchSuperviseDynamic:edit")
   	@RequestMapping(value = "save")
   	public String save(CatchSuperviseDynamic catchSuperviseDynamic, Model model, RedirectAttributes redirectAttributes) {
   		if (!beanValidator(model, catchSuperviseDynamic)){
   			return form(catchSuperviseDynamic, model);
   		}
   		superviseDynamicService.save(catchSuperviseDynamic);
   		addMessage(redirectAttributes, "保存成功");
   		return "redirect:"+Global.getAdminPath()+"/csyx/catchSuperviseDynamics/?repage";
   	}
   	
   	@RequiresPermissions("csyx:catchSuperviseDynamic:edit")
   	@RequestMapping(value = "delete")
   	public String delete(CatchSuperviseDynamic catchSuperviseDynamic, RedirectAttributes redirectAttributes) {
   		superviseDynamicService.delete(catchSuperviseDynamic);
   		addMessage(redirectAttributes, "删除成功");
   		return "redirect:"+Global.getAdminPath()+"/csyx/catchSuperviseDynamics/?repage";
   	}
}
