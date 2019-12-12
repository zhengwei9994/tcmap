/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.safecity.web;

import cn.xy.tcmap.common.config.Global;
import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.utils.DateUtils;
import cn.xy.tcmap.common.utils.StringUtils;
import cn.xy.tcmap.common.utils.excel.ExportExcel;
import cn.xy.tcmap.common.utils.excel.ImportExcel;
import cn.xy.tcmap.common.web.BaseController;
import cn.xy.tcmap.modules.safecity.entity.SafeTraffic;
import cn.xy.tcmap.modules.safecity.service.SafeCityService;
import cn.xy.tcmap.modules.safecity.service.SafeTrafficService;
import com.google.common.collect.Lists;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 交通违法案件Controller
 * @author tuo
 * @version 2019-12-05
 */
@Controller
@RequestMapping(value = "${adminPath}/safecity/safeTraffic")
public class SafeTrafficController extends BaseController {

	@Autowired
	private SafeTrafficService safeTrafficService;

	@Autowired
	private SafeCityService safeCityService;
	
	@ModelAttribute
	public SafeTraffic get(@RequestParam(required=false) String id) {
		SafeTraffic entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = safeTrafficService.get(id);
		}
		if (entity == null){
			entity = new SafeTraffic();
		}
		return entity;
	}
	
	@RequiresPermissions("safecity:safeTraffic:view")
	@RequestMapping(value = {"list", ""})
	public String list(SafeTraffic safeTraffic, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SafeTraffic> page = safeTrafficService.findPage(new Page<SafeTraffic>(request, response), safeTraffic); 
		model.addAttribute("page", page);
		model.addAttribute("safeCityList", safeCityService.findList(null));
		return "modules/safecity/safeTrafficList";
	}

	@RequiresPermissions("safecity:safeTraffic:view")
	@RequestMapping(value = "form")
	public String form(SafeTraffic safeTraffic, Model model) {
		model.addAttribute("safeTraffic", safeTraffic);
		model.addAttribute("safeCityList", safeCityService.findList(null));
		return "modules/safecity/safeTrafficForm";
	}

	@RequiresPermissions("safecity:safeTraffic:edit")
	@RequestMapping(value = "save")
	public String save(SafeTraffic safeTraffic, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, safeTraffic)){
			return form(safeTraffic, model);
		}
		try{
			if(safeTraffic.getArea() == null){
				safeTraffic.setArea(safeTraffic.getSafeCity());
			}
			safeTrafficService.save(safeTraffic);
			addMessage(redirectAttributes, "保存交通违法案件成功");
		} catch (Exception e) {
			addMessage(redirectAttributes, "保存交通违法案件失败！");
		}
		return "redirect:"+Global.getAdminPath()+"/safecity/safeTraffic/?repage";
	}
	
	@RequiresPermissions("safecity:safeTraffic:edit")
	@RequestMapping(value = "delete")
	public String delete(SafeTraffic safeTraffic, RedirectAttributes redirectAttributes) {
		safeTrafficService.delete(safeTraffic);
		addMessage(redirectAttributes, "删除交通违法案件成功");
		return "redirect:"+Global.getAdminPath()+"/safecity/safeTraffic/?repage";
	}
	/**
	 * 导出数据
	 * @param  safeTraffic
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("safecity:safeTraffic:view")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(SafeTraffic safeTraffic, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
			String wjm=new String(request.getParameter("name"));
		try {
            String fileName =wjm+"数据"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
           // Page<SafeTraffic> page = safeTrafficService.findPage(new Page<SafeTraffic>(request, response, -1),  safeTraffic);
           //导出全部数据
           List<SafeTraffic>  safeTrafficList= safeTrafficService.findList(new SafeTraffic());
    		new ExportExcel(wjm+"数据", SafeTraffic.class).setDataList(safeTrafficList).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出"+wjm +"数据失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/safecity/safeTraffic/?repage";
    }
/**
	 * 导入用户数据
	 * @param file
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("safecity:safeTraffic:edit")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file,SafeTraffic safeTraffic, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<SafeTraffic> list = ei.getDataList(SafeTraffic.class);
			for (SafeTraffic t : list){

			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条，导入信息如下：");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/safecity/safeTraffic/?repage";
    }
      /**
	 * 下载导入数据模板
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("safecity:safeTraffic:view")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(SafeTraffic safeTraffic,HttpServletRequest request,HttpServletResponse response, RedirectAttributes redirectAttributes) {
		String wjm=request.getParameter("name");
		try {
            String fileName = wjm+"数据导入模板.xlsx";
    		List<SafeTraffic> list = Lists.newArrayList();
    		list.add(safeTraffic);
    		new ExportExcel(wjm+"数据", SafeTraffic.class, 2).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/safecity/safeTraffic/?repage";
    }

}