package cn.xy.tcmap.modules.catchup.csyx.web;

import cn.xy.tcmap.common.config.Global;
import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.utils.StringUtils;
import cn.xy.tcmap.common.web.BaseController;
import cn.xy.tcmap.modules.catchup.csyx.entity.CatchAirQuality;
import cn.xy.tcmap.modules.catchup.csyx.service.CatchAirQualityService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author guoxunquan
 * @Title CatchAirQualityController
 * @Description TODO
 * @Package cn.xy.tcmap.modules.catchup.csyx.web
 * @Date 2018-09-19 10:33
 **/
@Controller
@RequestMapping(value = "${adminPath}/csyx/catchAirQuality")
public class CatchAirQualityController extends BaseController {
    @Autowired
    private CatchAirQualityService catchAirQualityService;
    @ModelAttribute
    public CatchAirQuality get(@RequestParam(required=false) String id) {
        CatchAirQuality entity = null;
        if (StringUtils.isNotBlank(id)){
            entity = catchAirQualityService.get(id);
        }
        if (entity == null){
            entity = new CatchAirQuality();
        }
        return entity;
    }

    @RequiresPermissions("csyx:catchAirQuality:view")
    @RequestMapping(value = {"list", ""})
    public String list(CatchAirQuality catchAirQuality, HttpServletRequest request, HttpServletResponse response, Model model) {
        if(catchAirQuality == null){
            catchAirQuality = new CatchAirQuality();
        }
        Page<CatchAirQuality> page = catchAirQualityService.findPage(new Page<CatchAirQuality>(request, response), catchAirQuality);
        model.addAttribute("page", page);
        return "catchup/csyx/catchAirQualityList";
    }

    @RequiresPermissions("csyx:catchAirQuality:view")
    @RequestMapping(value = "form")
    public String form(CatchAirQuality catchAirQuality, Model model) {
        model.addAttribute("CatchAirQuality", catchAirQuality);
        return "catchup/csyx/catchAirQualityForm";
    }

    @RequiresPermissions("csyx:catchAirQuality:edit")
    @RequestMapping(value = "save")
    public String save(CatchAirQuality catchAirQuality, Model model, RedirectAttributes redirectAttributes) {
        if (!beanValidator(model, catchAirQuality)){
            return form(catchAirQuality, model);
        }
        if(catchAirQuality != null && "".equals(catchAirQuality.getId())){
        	 List<CatchAirQuality> list = catchAirQualityService.findList(catchAirQuality);
        	 if(list != null && list.size() > 0){
        		 addMessage(redirectAttributes, "该月份下已有数据，请重新添加");
        	 }else{
        		 catchAirQualityService.save(catchAirQuality);
        	     addMessage(redirectAttributes, "保存成功");
        	 }
        }else{
   	        catchAirQualityService.save(catchAirQuality);
   	        addMessage(redirectAttributes, "保存成功");
   	    }
        return "redirect:"+ Global.getAdminPath()+"/csyx/catchAirQuality/?repage";
    }

    @RequiresPermissions("csyx:catchAirQuality:edit")
    @RequestMapping(value = "delete")
    public String delete(CatchAirQuality catchAirQuality, RedirectAttributes redirectAttributes) {
        catchAirQualityService.delete(catchAirQuality);
        addMessage(redirectAttributes, "删除成功");
        return "redirect:"+Global.getAdminPath()+"/csyx/catchAirQuality/?repage";
    }
}
