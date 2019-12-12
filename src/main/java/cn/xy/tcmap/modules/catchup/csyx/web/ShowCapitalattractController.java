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
import cn.xy.tcmap.modules.catchup.csyx.entity.ShowCapitalattract;
import cn.xy.tcmap.modules.catchup.csyx.service.ShowCapitalattractService;

import java.util.List;

/**
 * 经济资本吸引力Controller
 *
 * @author wufan
 * @version 2019-07-31
 */
@Controller
@RequestMapping(value = "${adminPath}/csyx/showCapitalattract")
public class ShowCapitalattractController extends BaseController {

    @Autowired
    private ShowCapitalattractService showCapitalattractService;

    @ModelAttribute
    public ShowCapitalattract get(@RequestParam(required = false) String id) {
        ShowCapitalattract entity = null;
        if (StringUtils.isNotBlank(id)) {
            entity = showCapitalattractService.get(id);
        }
        if (entity == null) {
            entity = new ShowCapitalattract();
        }
        return entity;
    }

    @RequiresPermissions("csyx:showCapitalattract:view")
    @RequestMapping(value = {"list", ""})
    public String list(ShowCapitalattract showCapitalattract, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<ShowCapitalattract> page = showCapitalattractService.findPage(new Page<ShowCapitalattract>(request, response), showCapitalattract);
        model.addAttribute("page", page);
        return "catchup/csyx/showCapitalattractList";
    }

    @RequiresPermissions("csyx:showCapitalattract:view")
    @RequestMapping(value = "form")
    public String form(ShowCapitalattract showCapitalattract, Model model) {
        model.addAttribute("showCapitalattract", showCapitalattract);
        return "catchup/csyx/showCapitalattractForm";
    }

    @RequiresPermissions("csyx:showCapitalattract:edit")
    @RequestMapping(value = "save")
    public String save(ShowCapitalattract showCapitalattract, Model model, RedirectAttributes redirectAttributes) {

        if (!beanValidator(model, showCapitalattract)) {
            return form(showCapitalattract, model);
        }
        try {
            if (showCapitalattract.getIsNewRecord()) {
                ShowCapitalattract condition = new ShowCapitalattract();
                condition.setYear(showCapitalattract.getYear());
                List<ShowCapitalattract> list = showCapitalattractService.findList(condition);
                if (list != null && list.size() > 0) {
                    throw new Exception("数据重复");
                }
            }
            showCapitalattractService.save(showCapitalattract);
            addMessage(redirectAttributes, "保存经济资本吸引力成功");
        } catch (Exception e) {
            addMessage(redirectAttributes, "保存经济资本吸引力失败，原因" + e.getMessage());
        }
        return "redirect:" + Global.getAdminPath() + "/csyx/showCapitalattract/?repage";
    }

    @RequiresPermissions("csyx:showCapitalattract:edit")
    @RequestMapping(value = "delete")
    public String delete(ShowCapitalattract showCapitalattract, RedirectAttributes redirectAttributes) {
        showCapitalattractService.delete(showCapitalattract);
        addMessage(redirectAttributes, "删除经济资本吸引力成功");
        return "redirect:" + Global.getAdminPath() + "/csyx/showCapitalattract/?repage";
    }

}