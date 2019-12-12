/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.ddgl.web;

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
import cn.xy.tcmap.modules.catchup.ddgl.entity.TcSyserviceMananger;
import cn.xy.tcmap.modules.catchup.ddgl.service.TcSyserviceManangerService;

/**
 * 服务系统管理Controller
 * @author wh
 * @version 2019-12-06
 */
@Controller
@RequestMapping(value = "${adminPath}/ddgl/tcSyserviceMananger")
public class TcSyserviceManangerController extends BaseController {

	@Autowired
	private TcSyserviceManangerService tcSyserviceManangerService;
	
	@ModelAttribute
	public TcSyserviceMananger get(@RequestParam(required=false) String id) {
		TcSyserviceMananger entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tcSyserviceManangerService.get(id);
		}
		if (entity == null){
			entity = new TcSyserviceMananger();
		}
		return entity;
	}
	
	@RequiresPermissions("ddgl:tcSyserviceMananger:view")
	@RequestMapping(value = {"list", ""})
	public String list(TcSyserviceMananger tcSyserviceMananger, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TcSyserviceMananger> page = tcSyserviceManangerService.findPage(new Page<TcSyserviceMananger>(request, response), tcSyserviceMananger); 
		model.addAttribute("page", page);
		return "catchup/ddgl/tcSyserviceManangerList";
	}

	@RequiresPermissions("ddgl:tcSyserviceMananger:view")
	@RequestMapping(value = "form")
	public String form(TcSyserviceMananger tcSyserviceMananger, Model model) {
		model.addAttribute("tcSyserviceMananger", tcSyserviceMananger);
		return "catchup/ddgl/tcSyserviceManangerForm";
	}

	@RequiresPermissions("ddgl:tcSyserviceMananger:edit")
	@RequestMapping(value = "save")
	public String save(TcSyserviceMananger tcSyserviceMananger, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tcSyserviceMananger)){
			return form(tcSyserviceMananger, model);
		}
		try{
			tcSyserviceManangerService.save(tcSyserviceMananger);
			addMessage(redirectAttributes, "保存系统成功");
		} catch (Exception e) {
			addMessage(redirectAttributes, "保存系统失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/ddgl/tcSyserviceMananger/?repage";
	}
	
	@RequiresPermissions("ddgl:tcSyserviceMananger:edit")
	@RequestMapping(value = "delete")
	public String delete(TcSyserviceMananger tcSyserviceMananger, RedirectAttributes redirectAttributes) {
		tcSyserviceManangerService.delete(tcSyserviceMananger);
		addMessage(redirectAttributes, "删除系统成功");
		return "redirect:"+Global.getAdminPath()+"/ddgl/tcSyserviceMananger/?repage";
	}
	/**
	 * 导出数据
	 * @param  tcSyserviceMananger
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("ddgl:tcSyserviceMananger:view")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(TcSyserviceMananger tcSyserviceMananger, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
			String wjm=new String(request.getParameter("name"));
		try {
            String fileName =wjm+"数据"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
           // Page<TcSyserviceMananger> page = tcSyserviceManangerService.findPage(new Page<TcSyserviceMananger>(request, response, -1),  tcSyserviceMananger);
           //导出全部数据
           List<TcSyserviceMananger>  tcSyserviceManangerList= tcSyserviceManangerService.findList(new TcSyserviceMananger());
    		new ExportExcel(wjm+"数据", TcSyserviceMananger.class).setDataList(tcSyserviceManangerList).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出"+wjm +"数据失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/ddgl/tcSyserviceMananger/?repage";
    }
/**
	 * 导入用户数据
	 * @param file
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("ddgl:tcSyserviceMananger:edit")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file,TcSyserviceMananger tcSyserviceMananger, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<TcSyserviceMananger> list = ei.getDataList(TcSyserviceMananger.class);
			for (TcSyserviceMananger t : list){

			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条，导入信息如下：");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/ddgl/tcSyserviceMananger/?repage";
    }
      /**
	 * 下载导入数据模板
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("ddgl:tcSyserviceMananger:view")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(TcSyserviceMananger tcSyserviceMananger,HttpServletRequest request,HttpServletResponse response, RedirectAttributes redirectAttributes) {
		String wjm=request.getParameter("name");
		try {
            String fileName = wjm+"数据导入模板.xlsx";
    		List<TcSyserviceMananger> list = Lists.newArrayList();
    		list.add(tcSyserviceMananger);
    		new ExportExcel(wjm+"数据", TcSyserviceMananger.class, 2).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/ddgl/tcSyserviceMananger/?repage";
    }

}