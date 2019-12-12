/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.jkgl.web;

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
import cn.xy.tcmap.modules.catchup.jkgl.entity.TcRoadMonitor;
import cn.xy.tcmap.modules.catchup.jkgl.service.TcRoadMonitorService;

/**
 * 路况监控Controller
 * @author wufan
 * @version 2019-12-05
 */
@Controller
@RequestMapping(value = "${adminPath}/jkgl/tcRoadMonitor")
public class TcRoadMonitorController extends BaseController {

	@Autowired
	private TcRoadMonitorService tcRoadMonitorService;
	
	@ModelAttribute
	public TcRoadMonitor get(@RequestParam(required=false) String id) {
		TcRoadMonitor entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tcRoadMonitorService.get(id);
		}
		if (entity == null){
			entity = new TcRoadMonitor();
		}
		return entity;
	}
	
	@RequiresPermissions("jkgl:tcRoadMonitor:view")
	@RequestMapping(value = {"list", ""})
	public String list(TcRoadMonitor tcRoadMonitor, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TcRoadMonitor> page = tcRoadMonitorService.findPage(new Page<TcRoadMonitor>(request, response), tcRoadMonitor); 
		model.addAttribute("page", page);
		return "catchup/jkgl/tcRoadMonitorList";
	}

	@RequiresPermissions("jkgl:tcRoadMonitor:view")
	@RequestMapping(value = "form")
	public String form(TcRoadMonitor tcRoadMonitor, Model model) {
		model.addAttribute("tcRoadMonitor", tcRoadMonitor);
		return "catchup/jkgl/tcRoadMonitorForm";
	}

	@RequiresPermissions("jkgl:tcRoadMonitor:edit")
	@RequestMapping(value = "save")
	public String save(TcRoadMonitor tcRoadMonitor, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tcRoadMonitor)){
			return form(tcRoadMonitor, model);
		}
		try{
			tcRoadMonitorService.save(tcRoadMonitor);
			addMessage(redirectAttributes, "保存路况监控成功");
		} catch (Exception e) {
			addMessage(redirectAttributes, "保存路况监控失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/jkgl/tcRoadMonitor/?repage";
	}
	
	@RequiresPermissions("jkgl:tcRoadMonitor:edit")
	@RequestMapping(value = "delete")
	public String delete(TcRoadMonitor tcRoadMonitor, RedirectAttributes redirectAttributes) {
		tcRoadMonitorService.delete(tcRoadMonitor);
		addMessage(redirectAttributes, "删除路况监控成功");
		return "redirect:"+Global.getAdminPath()+"/jkgl/tcRoadMonitor/?repage";
	}
	/**
	 * 导出数据
	 * @param  tcRoadMonitor
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("jkgl:tcRoadMonitor:view")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(TcRoadMonitor tcRoadMonitor, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
			String wjm=new String(request.getParameter("name"));
		try {
            String fileName =wjm+"数据"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
           // Page<TcRoadMonitor> page = tcRoadMonitorService.findPage(new Page<TcRoadMonitor>(request, response, -1),  tcRoadMonitor);
           //导出全部数据
           List<TcRoadMonitor>  tcRoadMonitorList= tcRoadMonitorService.findList(new TcRoadMonitor());
    		new ExportExcel(wjm+"数据", TcRoadMonitor.class).setDataList(tcRoadMonitorList).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出"+wjm +"数据失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/jkgl/tcRoadMonitor/?repage";
    }
/**
	 * 导入用户数据
	 * @param file
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("jkgl:tcRoadMonitor:edit")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file,TcRoadMonitor tcRoadMonitor, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<TcRoadMonitor> list = ei.getDataList(TcRoadMonitor.class);
			for (TcRoadMonitor t : list){

			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条，导入信息如下：");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/jkgl/tcRoadMonitor/?repage";
    }
      /**
	 * 下载导入数据模板
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("jkgl:tcRoadMonitor:view")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(TcRoadMonitor tcRoadMonitor,HttpServletRequest request,HttpServletResponse response, RedirectAttributes redirectAttributes) {
		String wjm=request.getParameter("name");
		try {
            String fileName = wjm+"数据导入模板.xlsx";
    		List<TcRoadMonitor> list = Lists.newArrayList();
    		list.add(tcRoadMonitor);
    		new ExportExcel(wjm+"数据", TcRoadMonitor.class, 2).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/jkgl/tcRoadMonitor/?repage";
    }

}