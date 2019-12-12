/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.safecity.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import cn.xy.tcmap.modules.safecity.entity.SafeFire;
import cn.xy.tcmap.modules.safecity.service.SafeFireService;

/**
 * 消防安全Controller
 * @author tuo
 * @version 2019-12-05
 */
@Controller
@RequestMapping(value = "${adminPath}/safecity/safeFire")
public class SafeFireController extends BaseController {

	@Autowired
	private SafeFireService safeFireService;
	@Autowired
	private SafeCityService safeCityService;
	
	@ModelAttribute
	public SafeFire get(@RequestParam(required=false) String id) {
		SafeFire entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = safeFireService.get(id);
		}
		if (entity == null){
			entity = new SafeFire();
		}
		return entity;
	}
	
	@RequiresPermissions("safecity:safeFire:view")
	@RequestMapping(value = {"list", ""})
	public String list(SafeFire safeFire, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SafeFire> page = safeFireService.findPage(new Page<SafeFire>(request, response), safeFire); 
		model.addAttribute("page", page);
		model.addAttribute("safeCityList", safeCityService.findList(null));
		return "modules/safecity/safeFireList";
	}

	@RequiresPermissions("safecity:safeFire:view")
	@RequestMapping(value = "form")
	public String form(SafeFire safeFire, Model model) {
		model.addAttribute("safeFire", safeFire);
		model.addAttribute("safeCityList", safeCityService.findList(null));
		return "modules/safecity/safeFireForm";
	}

	@RequiresPermissions("safecity:safeFire:edit")
	@RequestMapping(value = "save")
	public String save(SafeFire safeFire, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, safeFire)){
			return form(safeFire, model);
		}
		try{
			safeFireService.save(safeFire);
			addMessage(redirectAttributes, "保存消防安全成功");
		} catch (Exception e) {
			addMessage(redirectAttributes, "保存消防安全失败！");
		}
		return "redirect:"+Global.getAdminPath()+"/safecity/safeFire/?repage";
	}
	
	@RequiresPermissions("safecity:safeFire:edit")
	@RequestMapping(value = "delete")
	public String delete(SafeFire safeFire, RedirectAttributes redirectAttributes) {
		safeFireService.delete(safeFire);
		addMessage(redirectAttributes, "删除消防安全成功");
		return "redirect:"+Global.getAdminPath()+"/safecity/safeFire/?repage";
	}
	/**
	 * 导出数据
	 * @param  safeFire
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("safecity:safeFire:view")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(SafeFire safeFire, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
			String wjm=new String(request.getParameter("name"));
		try {
            String fileName =wjm+"数据"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
           // Page<SafeFire> page = safeFireService.findPage(new Page<SafeFire>(request, response, -1),  safeFire);
           //导出全部数据
           List<SafeFire>  safeFireList= safeFireService.findList(new SafeFire());
    		new ExportExcel(wjm+"数据", SafeFire.class).setDataList(safeFireList).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出"+wjm +"数据失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/safecity/safeFire/?repage";
    }
/**
	 * 导入用户数据
	 * @param file
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("safecity:safeFire:edit")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file,SafeFire safeFire, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<SafeFire> list = ei.getDataList(SafeFire.class);
			for (SafeFire t : list){

			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条，导入信息如下：");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/safecity/safeFire/?repage";
    }
      /**
	 * 下载导入数据模板
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("safecity:safeFire:view")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(SafeFire safeFire,HttpServletRequest request,HttpServletResponse response, RedirectAttributes redirectAttributes) {
		String wjm=request.getParameter("name");
		try {
            String fileName = wjm+"数据导入模板.xlsx";
    		List<SafeFire> list = Lists.newArrayList();
    		list.add(safeFire);
    		new ExportExcel(wjm+"数据", SafeFire.class, 2).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/safecity/safeFire/?repage";
    }

}