/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.safecity.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

import cn.xy.tcmap.modules.safecity.service.SafeCityService;
import com.google.common.collect.Lists;
import org.springframework.web.multipart.MultipartFile;
import cn.xy.tcmap.common.utils.excel.ImportExcel;
import org.springframework.web.bind.annotation.RequestMethod;
import cn.xy.tcmap.common.utils.DateUtils;
import cn.xy.tcmap.common.utils.excel.ExportExcel;
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
import cn.xy.tcmap.modules.safecity.entity.SafetyProfile;
import cn.xy.tcmap.modules.safecity.service.SafetyProfileService;

/**
 * 安全概况Controller
 * @author tuo
 * @version 2019-12-04
 */
@Controller
@RequestMapping(value = "${adminPath}/safecity/safetyProfile")
public class SafetyProfileController extends BaseController {

	@Autowired
	private SafetyProfileService safetyProfileService;
	@Autowired
	private SafeCityService safeCityService;
	
	@ModelAttribute
	public SafetyProfile get(@RequestParam(required=false) String id) {
		SafetyProfile entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = safetyProfileService.get(id);
		}
		if (entity == null){
			entity = new SafetyProfile();
		}
		return entity;
	}
	
	@RequiresPermissions("safecity:safetyProfile:view")
	@RequestMapping(value = {"list", ""})
	public String list(SafetyProfile safetyProfile, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SafetyProfile> page = safetyProfileService.findPage(new Page<SafetyProfile>(request, response), safetyProfile);
		model.addAttribute("safeCityList", safeCityService.findList(null));
		model.addAttribute("page", page);
		return "modules/safecity/safetyProfileList";
	}

	@RequiresPermissions("safecity:safetyProfile:view")
	@RequestMapping(value = "form")
	public String form(SafetyProfile safetyProfile, Model model) {
		model.addAttribute("safeCityList", safeCityService.findList(null));
		model.addAttribute("safetyProfile", safetyProfile);
		return "modules/safecity/safetyProfileForm";
	}

	@RequiresPermissions("safecity:safetyProfile:edit")
	@RequestMapping(value = "save")
	public String save(SafetyProfile safetyProfile, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, safetyProfile)){
			return form(safetyProfile, model);
		}
		try{
			safetyProfile.setStatisticDate(new Date());
			safetyProfileService.save(safetyProfile);
			addMessage(redirectAttributes, "保存安全概况成功");
		} catch (Exception e) {
			addMessage(redirectAttributes, "保存安全概况失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/safecity/safetyProfile/?repage";
	}
	
	@RequiresPermissions("safecity:safetyProfile:edit")
	@RequestMapping(value = "delete")
	public String delete(SafetyProfile safetyProfile, RedirectAttributes redirectAttributes) {
		safetyProfileService.delete(safetyProfile);
		addMessage(redirectAttributes, "删除安全概况成功");
		return "redirect:"+Global.getAdminPath()+"/safecity/safetyProfile/?repage";
	}
	/**
	 * 导出数据
	 * @param  safetyProfile
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("safecity:safetyProfile:view")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(SafetyProfile safetyProfile, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
			String wjm=new String(request.getParameter("name"));
		try {
            String fileName =wjm+"数据"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
           // Page<SafetyProfile> page = safetyProfileService.findPage(new Page<SafetyProfile>(request, response, -1),  safetyProfile);
           //导出全部数据
           List<SafetyProfile>  safetyProfileList= safetyProfileService.findList(new SafetyProfile());
    		new ExportExcel(wjm+"数据", SafetyProfile.class).setDataList(safetyProfileList).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出"+wjm +"数据失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/safecity/safetyProfile/?repage";
    }
/**
	 * 导入用户数据
	 * @param file
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("safecity:safetyProfile:edit")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file,SafetyProfile safetyProfile, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<SafetyProfile> list = ei.getDataList(SafetyProfile.class);
			for (SafetyProfile t : list){

			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条，导入信息如下：");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/safecity/safetyProfile/?repage";
    }
      /**
	 * 下载导入数据模板
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("safecity:safetyProfile:view")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(SafetyProfile safetyProfile,HttpServletRequest request,HttpServletResponse response, RedirectAttributes redirectAttributes) {
		String wjm=request.getParameter("name");
		try {
            String fileName = wjm+"数据导入模板.xlsx";
    		List<SafetyProfile> list = Lists.newArrayList();
    		list.add(safetyProfile);
    		new ExportExcel(wjm+"数据", SafetyProfile.class, 2).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/safecity/safetyProfile/?repage";
    }

}