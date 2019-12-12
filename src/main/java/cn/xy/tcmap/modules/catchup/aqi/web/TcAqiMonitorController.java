/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.aqi.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import com.google.common.collect.Lists;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import cn.xy.tcmap.common.utils.excel.ImportExcel;
import cn.xy.tcmap.common.utils.DateUtils;
import cn.xy.tcmap.common.utils.excel.ExportExcel;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.xy.tcmap.common.config.Global;
import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.web.BaseController;
import cn.xy.tcmap.common.utils.StringUtils;
import cn.xy.tcmap.modules.catchup.aqi.entity.TcAqiMonitor;
import cn.xy.tcmap.modules.catchup.aqi.service.TcAqiMonitorService;

/**
 * 气象数据Controller
 * @author 王浩
 * @version 2019-12-03
 */
@Controller
@RequestMapping(value = "${adminPath}/aqi/tcAqiMonitor")
public class TcAqiMonitorController extends BaseController {

	@Autowired
	private TcAqiMonitorService tcAqiMonitorService;
	
	@ModelAttribute
	public TcAqiMonitor get(@RequestParam(required=false) String id) {
		TcAqiMonitor entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tcAqiMonitorService.get(id);
		}
		if (entity == null){
			entity = new TcAqiMonitor();
		}
		return entity;
	}
	
	@RequiresPermissions("aqi:tcAqiMonitor:view")
	@RequestMapping(value = {"list", ""})
	public String list(TcAqiMonitor tcAqiMonitor, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TcAqiMonitor> page = tcAqiMonitorService.findPage(new Page<TcAqiMonitor>(request, response), tcAqiMonitor);
		model.addAttribute("page", page);
		return "catchup/aqi/tcAqiMonitorList";
	}
	@RequiresPermissions("aqi:tcAqiMonitor:view")
	@RequestMapping(value = "form")
	public String form(TcAqiMonitor tcAqiMonitor, Model model) {
		model.addAttribute("tcAqiMonitor", tcAqiMonitor);
		return "catchup/aqi/tcAqiMonitorForm";
	}

	@RequiresPermissions("aqi:tcAqiMonitor:edit")
	@RequestMapping(value = "save")
	public String save(TcAqiMonitor tcAqiMonitor, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tcAqiMonitor)){
			return form(tcAqiMonitor, model);
		}
		tcAqiMonitorService.save(tcAqiMonitor);
		addMessage(redirectAttributes, "保存气象数据成功");
		return "redirect:"+Global.getAdminPath()+"/aqi/tcAqiMonitor/?repage";
	}
	
	@RequiresPermissions("aqi:tcAqiMonitor:edit")
	@RequestMapping(value = "delete")
	public String delete(TcAqiMonitor tcAqiMonitor, RedirectAttributes redirectAttributes) {
		tcAqiMonitorService.delete(tcAqiMonitor);
		addMessage(redirectAttributes, "删除气象数据成功");
		return "redirect:"+Global.getAdminPath()+"/aqi/tcAqiMonitor/?repage";
	}

	/**
	 * 导出数据
	 * @param  tcAqiMonitor
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("aqi:tcAqiMonitor:view")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(TcAqiMonitor tcAqiMonitor, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
			String wjm=new String(request.getParameter("name"));
		try {
            String fileName =wjm+"数据"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
           // Page<TcAqiMonitor> page = tcAqiMonitorService.findPage(new Page<TcAqiMonitor>(request, response, -1),  tcAqiMonitor);
           //导出全部数据
           List<TcAqiMonitor>  tcAqiMonitorList= tcAqiMonitorService.findList(new TcAqiMonitor());
    		new ExportExcel(wjm+"数据", TcAqiMonitor.class).setDataList(tcAqiMonitorList).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出"+wjm +"数据失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/aqi/tcAqiMonitor/?repage";
    }


	/**
	 * 导入用户数据
	 * @param file
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("aqi:tcAqiMonitor:edit")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file,TcAqiMonitor tcAqiMonitor, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<TcAqiMonitor> list = ei.getDataList(TcAqiMonitor.class);
			for (TcAqiMonitor t : list){

			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条，导入信息如下：");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/aqi/tcAqiMonitor/?repage";
    }



      /**
	 * 下载导入数据模板
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("aqi:tcAqiMonitor:view")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(TcAqiMonitor tcAqiMonitor,HttpServletRequest request,HttpServletResponse response, RedirectAttributes redirectAttributes) {
		String wjm=request.getParameter("name");
		try {
            String fileName = wjm+"数据导入模板.xlsx";
    		List<TcAqiMonitor> list = Lists.newArrayList();
    		list.add(tcAqiMonitor);
    		new ExportExcel(wjm+"", TcAqiMonitor.class, 2).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/aqi/tcAqiMonitor/?repage";
    }
}