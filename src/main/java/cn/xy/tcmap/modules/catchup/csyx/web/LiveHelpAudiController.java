/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csyx.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.xy.tcmap.common.utils.DateUtils;
import cn.xy.tcmap.common.utils.excel.ExportExcel;
import cn.xy.tcmap.modules.catchup.csyx.entity.LivePovertyType;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.xy.tcmap.common.config.Global;
import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.web.BaseController;
import cn.xy.tcmap.common.utils.StringUtils;
import cn.xy.tcmap.modules.catchup.csyx.entity.LiveHelpAudi;
import cn.xy.tcmap.modules.catchup.csyx.service.LiveHelpAudiService;

import java.util.List;

/**
 * 帮扶受众Controller
 *
 * @author tuo
 * @version 2019-08-14
 */
@Controller
@RequestMapping(value = "${adminPath}/csyx/liveHelpAudi")
public class LiveHelpAudiController extends BaseController {

    @Autowired
    private LiveHelpAudiService liveHelpAudiService;

    @ModelAttribute
    public LiveHelpAudi get(@RequestParam(required = false) String id) {
        LiveHelpAudi entity = null;
        if (StringUtils.isNotBlank(id)) {
            entity = liveHelpAudiService.get(id);
        }
        if (entity == null) {
            entity = new LiveHelpAudi();
        }
        return entity;
    }

    @RequiresPermissions("csyx:liveHelpAudi:view")
    @RequestMapping(value = {"list", ""})
    public String list(LiveHelpAudi liveHelpAudi, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<LiveHelpAudi> page = liveHelpAudiService.findPage(new Page<LiveHelpAudi>(request, response), liveHelpAudi);
        model.addAttribute("page", page);
        return "catchup/csyx/liveHelpAudiList";
    }

    @RequiresPermissions("csyx:liveHelpAudi:view")
    @RequestMapping(value = "form")
    public String form(LiveHelpAudi liveHelpAudi, Model model) {
        model.addAttribute("liveHelpAudi", liveHelpAudi);
        return "catchup/csyx/liveHelpAudiForm";
    }

    @RequiresPermissions("csyx:liveHelpAudi:edit")
    @RequestMapping(value = "save")
    public String save(LiveHelpAudi liveHelpAudi, Model model, RedirectAttributes redirectAttributes) {
        if (!beanValidator(model, liveHelpAudi)) {
            return form(liveHelpAudi, model);
        }
        try {
            if (liveHelpAudi.getIsNewRecord()) {
                LiveHelpAudi liveHelpAudi1 = new LiveHelpAudi();
                liveHelpAudi1.setHelpType(liveHelpAudi.getHelpType());
                List<LiveHelpAudi> list = liveHelpAudiService.findList(liveHelpAudi1);
                if (list != null && list.size() > 0) {
                    throw new Exception("数据重复");
                }
            }
            liveHelpAudiService.save(liveHelpAudi);
            addMessage(redirectAttributes, "保存帮扶受众成功");
        } catch (Exception e) {
            addMessage(redirectAttributes, "保存帮扶受众失败，原因：" + e.getMessage());
        }

        return "redirect:" + Global.getAdminPath() + "/csyx/liveHelpAudi/?repage";
    }

    @RequiresPermissions("csyx:liveHelpAudi:edit")
    @RequestMapping(value = "delete")
    public String delete(LiveHelpAudi liveHelpAudi, RedirectAttributes redirectAttributes) {
        liveHelpAudiService.delete(liveHelpAudi);
        addMessage(redirectAttributes, "删除帮扶受众成功");
        return "redirect:" + Global.getAdminPath() + "/csyx/liveHelpAudi/?repage";
    }
    @RequiresPermissions("csyx:liveHelpAudi:view")
    @RequestMapping(value = "export", method= RequestMethod.POST)
    public String exportFile(LiveHelpAudi liveHelpAudi, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
        String wjm=new String(request.getParameter("name"));
        try {
            String fileName =wjm+"数据"+ DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            // Page<SafeCity> page = safeCityService.findPage(new Page<SafeCity>(request, response, -1),  safeCity);
            //导出全部数据
            List<LiveHelpAudi>  liveHelpAudiList= liveHelpAudiService.findList(liveHelpAudi);
            new ExportExcel(wjm+"数据", LiveHelpAudi.class).setDataList(liveHelpAudiList).write(response, fileName).dispose();
            return null;
        } catch (Exception e) {
            addMessage(redirectAttributes, "导出"+wjm +"数据失败！失败信息："+e.getMessage());
        }
        return "redirect:"+Global.getAdminPath()+"/csyx/liveHelpAudi/?repage";
    }
}