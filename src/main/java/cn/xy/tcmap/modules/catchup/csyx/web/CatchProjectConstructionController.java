package cn.xy.tcmap.modules.catchup.csyx.web;

import cn.xy.tcmap.common.config.Global;
import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.utils.DateUtils;
import cn.xy.tcmap.common.utils.StringUtils;
import cn.xy.tcmap.common.web.BaseController;
import cn.xy.tcmap.modules.catchup.csyx.entity.CatchProjectConstruction;
import cn.xy.tcmap.modules.catchup.csyx.service.CatchProjectConstructionService;

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
 * 重点项目建设Controller
 * @author tuo
 * @version 2018-05-24
 */
@Controller
@RequestMapping(value = "${adminPath}/csyx/catchProjectConstruction")
public class CatchProjectConstructionController extends BaseController {

    @Autowired
    private CatchProjectConstructionService catchProjectConstructionService;

    
    @ModelAttribute
   	public CatchProjectConstruction get(@RequestParam(required=false) String id) {
   		CatchProjectConstruction entity = null;
   		if (StringUtils.isNotBlank(id)){
   			entity = catchProjectConstructionService.get(id);
   		}
   		if (entity == null){
   			entity = new CatchProjectConstruction();
   		}
   		return entity;
   	}
   	
       @RequestMapping(value = "findAll",method = RequestMethod.GET)
       @ResponseBody
       public List<CatchProjectConstruction> findAll(@RequestBody(required = false) CatchProjectConstruction CatchProjectConstruction){
           return catchProjectConstructionService.findList(CatchProjectConstruction);
       }
   	
   	@RequiresPermissions("csyx:catchProjectConstruction:view")
   	@RequestMapping(value = {"list", ""})
   	public String list(CatchProjectConstruction CatchProjectConstruction, HttpServletRequest request, HttpServletResponse response, Model model) {
   		Page<CatchProjectConstruction> page = catchProjectConstructionService.findPage(new Page<CatchProjectConstruction>(request, response), CatchProjectConstruction); 
   		List<CatchProjectConstruction> list = page.getList();
/*   		//对日期进行转换
   		if(list != null && list.size() > 0){
   			for (CatchProjectConstruction construction : list) {
   				construction.setStartTime(DateUtils.formatDate(construction.getStartTime(), "yyyy-MM-dd HH:mm:ss"));
			}
   		}*/
   		model.addAttribute("page", page);
   		return "catchup/csyx/catchProjectConstructionList";
   	}

   	@RequiresPermissions("csyx:catchProjectConstruction:view")
   	@RequestMapping(value = "form")
   	public String form(CatchProjectConstruction CatchProjectConstruction, Model model) {
   		model.addAttribute("catchProjectConstruction", CatchProjectConstruction);
   		return "catchup/csyx/catchProjectConstructionForm";
   	}

   	@RequiresPermissions("csyx:catchProjectConstruction:edit")
   	@RequestMapping(value = "save")
   	public String save(CatchProjectConstruction catchProjectConstruction, Model model, RedirectAttributes redirectAttributes) {
   		if (!beanValidator(model, catchProjectConstruction)){
   			return form(catchProjectConstruction, model);
   		}
   		catchProjectConstructionService.save(catchProjectConstruction);
   		addMessage(redirectAttributes, "保存成功");
   		return "redirect:"+Global.getAdminPath()+"/csyx/catchProjectConstruction/?repage";
   	}
   	
   	@RequiresPermissions("csyx:catchProjectConstruction:edit")
   	@RequestMapping(value = "delete")
   	public String delete(CatchProjectConstruction catchProjectConstruction, RedirectAttributes redirectAttributes) {
   		catchProjectConstructionService.delete(catchProjectConstruction);
   		addMessage(redirectAttributes, "删除成功");
   		return "redirect:"+Global.getAdminPath()+"/csyx/catchProjectConstruction/?repage";
   	}
}
