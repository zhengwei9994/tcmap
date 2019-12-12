/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.safecity.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
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
import cn.xy.tcmap.modules.safecity.entity.SafeCity;
import cn.xy.tcmap.modules.safecity.service.SafeCityService;

/**
 * 平安城市Controller
 * @author tuo
 * @version 2019-12-04
 */
@Controller
@RequestMapping(value = "${adminPath}/safecity/safeCity")
public class SafeCityController extends BaseController {

	@Autowired
	private SafeCityService safeCityService;
	
	@ModelAttribute
	public SafeCity get(@RequestParam(required=false) String id) {
		SafeCity entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = safeCityService.get(id);
		}
		if (entity == null){
			entity = new SafeCity();
		}
		return entity;
	}
	
	@RequiresPermissions("safecity:safeCity:view")
	@RequestMapping(value = {"list", ""})
	public String list(SafeCity safeCity, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SafeCity> page = safeCityService.findPage(new Page<SafeCity>(request, response), safeCity); 
		model.addAttribute("page", page);
		return "modules/safecity/safeCityList";
	}

	@RequiresPermissions("safecity:safeCity:view")
	@RequestMapping(value = "form")
	public String form(SafeCity safeCity, Model model) {
		model.addAttribute("safeCity", safeCity);
		return "modules/safecity/safeCityForm";
	}

	@RequiresPermissions("safecity:safeCity:edit")
	@RequestMapping(value = "save")
	public String save(SafeCity safeCity, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, safeCity)){
			return form(safeCity, model);
		}
		try{
			safeCityService.save(safeCity);
			addMessage(redirectAttributes, "保存平安城市成功");
		} catch (Exception e) {
			addMessage(redirectAttributes, "保存平安城市失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/safecity/safeCity/?repage";
	}
	
	@RequiresPermissions("safecity:safeCity:edit")
	@RequestMapping(value = "delete")
	public String delete(SafeCity safeCity, RedirectAttributes redirectAttributes) {
		safeCityService.delete(safeCity);
		addMessage(redirectAttributes, "删除平安城市成功");
		return "redirect:"+Global.getAdminPath()+"/safecity/safeCity/?repage";
	}
	/**
	 * 导出数据
	 * @param  safeCity
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("safecity:safeCity:view")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(SafeCity safeCity, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
			String wjm=new String(request.getParameter("name"));
		try {
            String fileName =wjm+"数据"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
           // Page<SafeCity> page = safeCityService.findPage(new Page<SafeCity>(request, response, -1),  safeCity);
           //导出全部数据
           List<SafeCity>  safeCityList= safeCityService.findList(new SafeCity());
    		new ExportExcel(wjm+"数据", SafeCity.class).setDataList(safeCityList).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出"+wjm +"数据失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/safecity/safeCity/?repage";
    }
/**
	 * 导入用户数据
	 * @param file
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("safecity:safeCity:edit")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file,SafeCity safeCity, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<SafeCity> list = ei.getDataList(SafeCity.class);
			for (SafeCity t : list){

			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条，导入信息如下：");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/safecity/safeCity/?repage";
    }
      /**
	 * 下载导入数据模板
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("safecity:safeCity:view")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(SafeCity safeCity,HttpServletRequest request,HttpServletResponse response, RedirectAttributes redirectAttributes) {
		String wjm=request.getParameter("name");
		try {
            String fileName = wjm+"数据导入模板.xlsx";
    		List<SafeCity> list = Lists.newArrayList();
    		list.add(safeCity);
    		new ExportExcel(wjm+"数据", SafeCity.class, 2).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/safecity/safeCity/?repage";
    }

}