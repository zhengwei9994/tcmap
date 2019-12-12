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
import cn.xy.tcmap.modules.catchup.jkgl.entity.TcDangerMonitor;
import cn.xy.tcmap.modules.catchup.jkgl.service.TcDangerMonitorService;

/**
 * 危险源监控Controller
 * @author wufan
 * @version 2019-12-05
 */
@Controller
@RequestMapping(value = "${adminPath}/jkgl/tcDangerMonitor")
public class TcDangerMonitorController extends BaseController {

	@Autowired
	private TcDangerMonitorService tcDangerMonitorService;
	
	@ModelAttribute
	public TcDangerMonitor get(@RequestParam(required=false) String id) {
		TcDangerMonitor entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tcDangerMonitorService.get(id);
		}
		if (entity == null){
			entity = new TcDangerMonitor();
		}
		return entity;
	}
	
	@RequiresPermissions("jkgl:tcDangerMonitor:view")
	@RequestMapping(value = {"list", ""})
	public String list(TcDangerMonitor tcDangerMonitor, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TcDangerMonitor> page = tcDangerMonitorService.findPage(new Page<TcDangerMonitor>(request, response), tcDangerMonitor); 
		model.addAttribute("page", page);
		return "catchup/jkgl/tcDangerMonitorList";
	}

	@RequiresPermissions("jkgl:tcDangerMonitor:view")
	@RequestMapping(value = "form")
	public String form(TcDangerMonitor tcDangerMonitor, Model model) {
		model.addAttribute("tcDangerMonitor", tcDangerMonitor);
		return "catchup/jkgl/tcDangerMonitorForm";
	}

	@RequiresPermissions("jkgl:tcDangerMonitor:edit")
	@RequestMapping(value = "save")
	public String save(TcDangerMonitor tcDangerMonitor, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tcDangerMonitor)){
			return form(tcDangerMonitor, model);
		}
		try{
			tcDangerMonitorService.save(tcDangerMonitor);
			addMessage(redirectAttributes, "保存危险源监控成功");
		} catch (Exception e) {
			addMessage(redirectAttributes, "保存危险源监控失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/jkgl/tcDangerMonitor/?repage";
	}
	
	@RequiresPermissions("jkgl:tcDangerMonitor:edit")
	@RequestMapping(value = "delete")
	public String delete(TcDangerMonitor tcDangerMonitor, RedirectAttributes redirectAttributes) {
		tcDangerMonitorService.delete(tcDangerMonitor);
		addMessage(redirectAttributes, "删除危险源监控成功");
		return "redirect:"+Global.getAdminPath()+"/jkgl/tcDangerMonitor/?repage";
	}
	/**
	 * 导出数据
	 * @param  tcDangerMonitor
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("jkgl:tcDangerMonitor:view")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(TcDangerMonitor tcDangerMonitor, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
			String wjm=new String(request.getParameter("name"));
		try {
            String fileName =wjm+"数据"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
           // Page<TcDangerMonitor> page = tcDangerMonitorService.findPage(new Page<TcDangerMonitor>(request, response, -1),  tcDangerMonitor);
           //导出全部数据
           List<TcDangerMonitor>  tcDangerMonitorList= tcDangerMonitorService.findList(new TcDangerMonitor());
    		new ExportExcel(wjm+"数据", TcDangerMonitor.class).setDataList(tcDangerMonitorList).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出"+wjm +"数据失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/jkgl/tcDangerMonitor/?repage";
    }
/**
	 * 导入用户数据
	 * @param file
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("jkgl:tcDangerMonitor:edit")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file,TcDangerMonitor tcDangerMonitor, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<TcDangerMonitor> list = ei.getDataList(TcDangerMonitor.class);
			for (TcDangerMonitor t : list){

			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条，导入信息如下：");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/jkgl/tcDangerMonitor/?repage";
    }
      /**
	 * 下载导入数据模板
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("jkgl:tcDangerMonitor:view")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(TcDangerMonitor tcDangerMonitor,HttpServletRequest request,HttpServletResponse response, RedirectAttributes redirectAttributes) {
		String wjm=request.getParameter("name");
		try {
            String fileName = wjm+"数据导入模板.xlsx";
    		List<TcDangerMonitor> list = Lists.newArrayList();
    		list.add(tcDangerMonitor);
    		new ExportExcel(wjm+"数据", TcDangerMonitor.class, 2).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/jkgl/tcDangerMonitor/?repage";
    }

}