package cn.xy.tcmap.modules.catchup.csyx.web;

import cn.xy.tcmap.common.config.Global;
import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.utils.StringUtils;
import cn.xy.tcmap.common.web.BaseController;
import cn.xy.tcmap.modules.catchup.csyx.entity.CatchEconomicRate;
import cn.xy.tcmap.modules.catchup.csyx.service.CatchEconomicRateService;
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
 * @Title CatchEconomicRateController
 * @Description 经济指标32项
 * @Package cn.xy.tcmap.modules.catchup.csyx.web
 * @Date 2018-09-18 13:50
 **/
@Controller
@RequestMapping(value = "${adminPath}/csyx/catchEconomicRate")
public class CatchEconomicRateController extends BaseController {
    @Autowired
    private CatchEconomicRateService catchEconomicRateService;

    @ModelAttribute
    public CatchEconomicRate get(@RequestParam(required=false) String id) {
        CatchEconomicRate entity = null;
        if (StringUtils.isNotBlank(id)){
            entity = catchEconomicRateService.get(id);
        }
        if (entity == null){
            entity = new CatchEconomicRate();
        }
        return entity;
    }

    @RequiresPermissions("csyx:catchEconomicRate:view")
    @RequestMapping(value = {"list", ""})
    public String list(CatchEconomicRate catchEconomicRate, HttpServletRequest request, HttpServletResponse response, Model model) {
        if(catchEconomicRate == null){
            catchEconomicRate = new CatchEconomicRate();
        }
        Page<CatchEconomicRate> page = catchEconomicRateService.findPage(new Page<CatchEconomicRate>(request, response), catchEconomicRate);
        model.addAttribute("page", page);
        return "catchup/csyx/catchEconomicRateList";
    }

    @RequiresPermissions("csyx:catchEconomicRate:view")
    @RequestMapping(value = "form")
    public String form(CatchEconomicRate CatchEconomicRate, Model model) {
        model.addAttribute("catchEconomicRate", CatchEconomicRate);
        return "catchup/csyx/catchEconomicRateForm";
    }

    @RequiresPermissions("csyx:catchEconomicRate:edit")
    @RequestMapping(value = "save")
    public String save(CatchEconomicRate CatchEconomicRate, Model model, RedirectAttributes redirectAttributes) {
        if (!beanValidator(model, CatchEconomicRate)){
            return form(CatchEconomicRate, model);
        }
        catchEconomicRateService.save(CatchEconomicRate);
        addMessage(redirectAttributes, "保存成功");
        return "redirect:"+ Global.getAdminPath()+"/csyx/catchEconomicRate/?repage";

    }

    @RequiresPermissions("csyx:catchEconomicRate:edit")
    @RequestMapping(value = "delete")
    public String delete(CatchEconomicRate CatchEconomicRate, RedirectAttributes redirectAttributes) {
        catchEconomicRateService.delete(CatchEconomicRate);
        addMessage(redirectAttributes, "删除成功");
        return "redirect:"+Global.getAdminPath()+"/csyx/catchEconomicRate/?repage";
    }
}
