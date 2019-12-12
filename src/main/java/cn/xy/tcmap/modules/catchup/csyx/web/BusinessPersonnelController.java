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
import cn.xy.tcmap.modules.catchup.csyx.entity.BusinessPersonnel;
import cn.xy.tcmap.modules.catchup.csyx.service.BusinessPersonnelService;

import java.util.List;

/**
 * 营商人才结构Controller
 *
 * @author wufan
 * @version 2019-07-31
 */
@Controller
@RequestMapping(value = "${adminPath}/csyx/businessPersonnel")
public class BusinessPersonnelController extends BaseController {

    @Autowired
    private BusinessPersonnelService businessPersonnelService;

    @ModelAttribute
    public BusinessPersonnel get(@RequestParam(required = false) String id) {
        BusinessPersonnel entity = null;
        if (StringUtils.isNotBlank(id)) {
            entity = businessPersonnelService.get(id);
        }
        if (entity == null) {
            entity = new BusinessPersonnel();
        }
        return entity;
    }

    @RequiresPermissions("csyx:businessPersonnel:view")
    @RequestMapping(value = {"list", ""})
    public String list(BusinessPersonnel businessPersonnel, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<BusinessPersonnel> page = businessPersonnelService.findPage(new Page<BusinessPersonnel>(request, response), businessPersonnel);
        model.addAttribute("page", page);
        return "catchup/csyx/businessPersonnelList";
    }

    @RequiresPermissions("csyx:businessPersonnel:view")
    @RequestMapping(value = "form")
    public String form(BusinessPersonnel businessPersonnel, Model model) {
        model.addAttribute("businessPersonnel", businessPersonnel);
        return "catchup/csyx/businessPersonnelForm";
    }

    @RequiresPermissions("csyx:businessPersonnel:edit")
    @RequestMapping(value = "save")
    public String save(BusinessPersonnel businessPersonnel, Model model, RedirectAttributes redirectAttributes) {
        if (!beanValidator(model, businessPersonnel)) {
            return form(businessPersonnel, model);
        }
        try {
            if (businessPersonnel.getIsNewRecord()) {
                BusinessPersonnel bp = new BusinessPersonnel();
                bp.setMonth(businessPersonnel.getMonth());
                bp.setYear(businessPersonnel.getYear());
                bp.setEducation(businessPersonnel.getEducation());
                List<BusinessPersonnel> list = businessPersonnelService.findList(bp);
                if (list != null && list.size() > 0) {
                    throw new Exception("数据重复");
                }
            }
            businessPersonnelService.save(businessPersonnel);
            addMessage(redirectAttributes, "保存营商人才结构成功");
        } catch (Exception e) {
            addMessage(redirectAttributes, "保存营商人才结构失败，失败原因：" + e.getMessage());
        }
        return "redirect:" + Global.getAdminPath() + "/csyx/businessPersonnel/?repage";
    }

    @RequiresPermissions("csyx:businessPersonnel:edit")
    @RequestMapping(value = "delete")
    public String delete(BusinessPersonnel businessPersonnel, RedirectAttributes redirectAttributes) {
        businessPersonnelService.delete(businessPersonnel);
        addMessage(redirectAttributes, "删除营商人才结构成功");
        return "redirect:" + Global.getAdminPath() + "/csyx/businessPersonnel/?repage";
    }

}