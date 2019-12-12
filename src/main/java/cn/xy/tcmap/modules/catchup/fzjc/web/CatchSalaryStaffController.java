/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.fzjc.web;

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
import cn.xy.tcmap.modules.catchup.fzjc.entity.CatchSalaryStaff;
import cn.xy.tcmap.modules.catchup.fzjc.service.CatchSalaryStaffService;

import java.util.List;

/**
 * 薪资及人员分析Controller
 * @author liuyang
 * @version 2018-05-29
 */
@Controller
@RequestMapping(value = "${adminPath}/fzjc/catchSalaryStaff")
public class CatchSalaryStaffController extends BaseController {

	@Autowired
	private CatchSalaryStaffService catchSalaryStaffService;
	
	@ModelAttribute
	public CatchSalaryStaff get(@RequestParam(required=false) String id) {
		CatchSalaryStaff entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = catchSalaryStaffService.get(id);
		}
		if (entity == null){
			entity = new CatchSalaryStaff();
		}
		return entity;
	}
	
	@RequiresPermissions("fzjc:catchSalaryStaff:view")
	@RequestMapping(value = {"list", ""})
	public String list(CatchSalaryStaff catchSalaryStaff, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CatchSalaryStaff> page = catchSalaryStaffService.findPage(new Page<CatchSalaryStaff>(request, response), catchSalaryStaff); 
		model.addAttribute("page", page);
		return "catchup/fzjc/catchSalaryStaffList";
	}

	@RequiresPermissions("fzjc:catchSalaryStaff:view")
	@RequestMapping(value = "form")
	public String form(CatchSalaryStaff catchSalaryStaff, Model model) {
		model.addAttribute("catchSalaryStaff", catchSalaryStaff);
		return "catchup/fzjc/catchSalaryStaffForm";
	}

	@RequiresPermissions("fzjc:catchSalaryStaff:edit")
	@RequestMapping(value = "save")
	public String save(CatchSalaryStaff catchSalaryStaff, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, catchSalaryStaff)){
			return form(catchSalaryStaff, model);
		}
		try {
            if(catchSalaryStaff.getIsNewRecord()){
                CatchSalaryStaff catchSalaryStaff1 = new CatchSalaryStaff();
                catchSalaryStaff1.setNyear(catchSalaryStaff.getNyear());
                catchSalaryStaff1.setStatisticalContent(catchSalaryStaff.getStatisticalContent());
                List<CatchSalaryStaff> list = catchSalaryStaffService.findList(catchSalaryStaff1);
                if(list!=null&&list.size()>0){
                    throw new Exception("数据重复");
                }
            }
            catchSalaryStaffService.save(catchSalaryStaff);
            addMessage(redirectAttributes, "保存薪资及人员分析成功");
        }catch (Exception e){
            addMessage(redirectAttributes,"保存薪资及人员分析失败，原因："+e.getMessage());
        }

		return "redirect:"+Global.getAdminPath()+"/fzjc/catchSalaryStaff/?repage";
	}
	
	@RequiresPermissions("fzjc:catchSalaryStaff:edit")
	@RequestMapping(value = "delete")
	public String delete(CatchSalaryStaff catchSalaryStaff, RedirectAttributes redirectAttributes) {
		catchSalaryStaffService.delete(catchSalaryStaff);
		addMessage(redirectAttributes, "删除薪资及人员分析成功");
		return "redirect:"+Global.getAdminPath()+"/fzjc/catchSalaryStaff/?repage";
	}

}