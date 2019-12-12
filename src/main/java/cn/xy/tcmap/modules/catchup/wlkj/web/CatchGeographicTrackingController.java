package cn.xy.tcmap.modules.catchup.wlkj.web;

import cn.xy.tcmap.common.config.Global;
import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.utils.StringUtils;
import cn.xy.tcmap.common.web.BaseController;
import cn.xy.tcmap.modules.catchup.wlkj.entity.CatchGeographicTracking;
import cn.xy.tcmap.modules.catchup.wlkj.entity.CatchGeographicTracking;
import cn.xy.tcmap.modules.catchup.wlkj.service.CatchGeographicTrackingService;
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
import java.util.List;

/**
 * @Author guoxunquan
 * @Title CatchGeographicTrackingController
 * @Description 地理舆情追踪
 * @Package cn.xy.tcmap.modules.catchup.wlkj.web
 * @Date 2018-09-17 14:26
 **/
@Controller
@RequestMapping(value = "${adminPath}/wlkj/catchGeographicTracking")
public class CatchGeographicTrackingController  extends BaseController {
    @Autowired
    private CatchGeographicTrackingService catchGeographicTrackingService;

    @ModelAttribute
    public CatchGeographicTracking get(@RequestParam(required=false) String id) {
        CatchGeographicTracking entity = null;
        if (StringUtils.isNotBlank(id)){
            entity = catchGeographicTrackingService.get(id);
        }
        if (entity == null){
            entity = new CatchGeographicTracking();
        }
        return entity;
    }

    @RequiresPermissions("wlkj:catchGeographicTracking:view")
    @RequestMapping(value = {"list", ""})
    public String list(CatchGeographicTracking catchGeographicTracking, HttpServletRequest request, HttpServletResponse response, Model model) {
        if(catchGeographicTracking == null){
            catchGeographicTracking = new CatchGeographicTracking();
        }
        Page<CatchGeographicTracking> page = catchGeographicTrackingService.findPage(new Page<CatchGeographicTracking>(request, response), catchGeographicTracking);
        model.addAttribute("page", page);
        return "catchup/wlkj/catchGeographicTrackingList";
    }

    @RequiresPermissions("wlkj:catchGeographicTracking:view")
    @RequestMapping(value = "form")
    public String form(CatchGeographicTracking CatchGeographicTracking, Model model) {
        model.addAttribute("CatchGeographicTracking", CatchGeographicTracking);
        return "catchup/wlkj/catchGeographicTrackingForm";
    }

    @RequiresPermissions("wlkj:catchGeographicTracking:edit")
    @RequestMapping(value = "save")
    public String save(CatchGeographicTracking catchGeographicTracking, Model model, RedirectAttributes redirectAttributes) {
        if (!beanValidator(model, catchGeographicTracking)){
            return form(catchGeographicTracking, model);
        }
        try {
            if(catchGeographicTracking.getIsNewRecord()){
                CatchGeographicTracking catchGeographicTracking1 = new CatchGeographicTracking();
                catchGeographicTracking1.setAreaName(catchGeographicTracking.getAreaName());
                List<CatchGeographicTracking> list = catchGeographicTrackingService.findList(catchGeographicTracking1);
                if (list!=null && list.size()>0){
                    throw new Exception("数据重复");
                }
            }
            catchGeographicTrackingService.save(catchGeographicTracking);
            addMessage(redirectAttributes, "保存地理舆情追踪成功");
        }catch (Exception e){
            addMessage(redirectAttributes, "保存地理舆情追踪失败，原因："+e.getMessage());
        }

        return "redirect:"+ Global.getAdminPath()+"/wlkj/catchGeographicTracking/?repage";
    }

    @RequiresPermissions("wlkj:catchGeographicTracking:edit")
    @RequestMapping(value = "delete")
    public String delete(CatchGeographicTracking CatchGeographicTracking, RedirectAttributes redirectAttributes) {
        catchGeographicTrackingService.delete(CatchGeographicTracking);
        addMessage(redirectAttributes, "删除理舆情追踪成功");
        return "redirect:"+Global.getAdminPath()+"/wlkj/catchGeographicTracking/?repage";
    }
}
