package cn.xy.tcmap.modules.catchup.csyx.web;

import cn.xy.tcmap.common.config.Global;
import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.utils.StringUtils;
import cn.xy.tcmap.common.web.BaseController;
import cn.xy.tcmap.modules.catchup.csyx.entity.CatchSwageTreat;
import cn.xy.tcmap.modules.catchup.csyx.service.CatchSwageTreatService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author guoxunquan
 * @Title CatchSwageTreatContrller
 * @Description TODO
 * @Package cn.xy.tcmap.modules.catchup.csyx.web
 * @Date 2018-09-19 14:32
 **/
@Controller
@RequestMapping(value = "${adminPath}/csyx/catchSwageTreat")
public class CatchSwageTreatContrller extends BaseController {
    @Autowired
    private CatchSwageTreatService catchSwageTreatService;
    @ModelAttribute
    public CatchSwageTreat get(@RequestParam(required=false) String id) {
        CatchSwageTreat entity = null;
        if (StringUtils.isNotBlank(id)){
            entity = catchSwageTreatService.get(id);
        }
        if (entity == null){
            entity = new CatchSwageTreat();
        }
        return entity;
    }

    @RequiresPermissions("csyx:catchSwageTreat:view")
    @RequestMapping(value = {"list", ""})
    public String list(CatchSwageTreat catchSwageTreat, HttpServletRequest request, HttpServletResponse response, Model model) {
        if(catchSwageTreat == null){
            catchSwageTreat = new CatchSwageTreat();
        }
        Page<CatchSwageTreat> page = catchSwageTreatService.findPage(new Page<CatchSwageTreat>(request, response), catchSwageTreat);
        model.addAttribute("page", page);
        return "catchup/csyx/catchSwageTreatList";
    }

    @RequiresPermissions("csyx:catchSwageTreat:view")
    @RequestMapping(value = "form")
    public String form(CatchSwageTreat catchSwageTreat, Model model) {
        model.addAttribute("catchSwageTreat", catchSwageTreat);
        return "catchup/csyx/catchSwageTreatForm";
    }

    @RequiresPermissions("csyx:catchSwageTreat:edit")
    @RequestMapping(value = "save")
    public String save(CatchSwageTreat catchSwageTreat, Model model, RedirectAttributes redirectAttributes) {
        if (!beanValidator(model, catchSwageTreat)){
            return form(catchSwageTreat, model);
        }
        catchSwageTreatService.save(catchSwageTreat);
        addMessage(redirectAttributes, "保存成功");
        return "redirect:"+ Global.getAdminPath()+"/csyx/catchSwageTreat/?repage";
    }

    @RequiresPermissions("csyx:catchSwageTreat:edit")
    @RequestMapping(value = "delete")
    public String delete(CatchSwageTreat catchSwageTreat, RedirectAttributes redirectAttributes) {
        catchSwageTreatService.delete(catchSwageTreat);
        addMessage(redirectAttributes, "删除成功");
        return "redirect:"+Global.getAdminPath()+"/csyx/catchSwageTreat/?repage";
    }
}
