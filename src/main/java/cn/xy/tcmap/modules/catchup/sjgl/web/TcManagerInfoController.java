/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.sjgl.web;

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
import cn.xy.tcmap.modules.catchup.sjgl.entity.TcManagerInfo;
import cn.xy.tcmap.modules.catchup.sjgl.service.TcManagerInfoService;

/**
 * 责任人管理Controller
 * @author wh
 * @version 2019-12-05
 */
@Controller
@RequestMapping(value = "${adminPath}/sjgl/tcManagerInfo")
public class TcManagerInfoController extends BaseController {

	@Autowired
	private TcManagerInfoService tcManagerInfoService;
	
	@ModelAttribute
	public TcManagerInfo get(@RequestParam(required=false) String id) {
		TcManagerInfo entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tcManagerInfoService.get(id);
		}
		if (entity == null){
			entity = new TcManagerInfo();
		}
		return entity;
	}
	
	@RequiresPermissions("sjgl:tcManagerInfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(TcManagerInfo tcManagerInfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TcManagerInfo> page = tcManagerInfoService.findPage(new Page<TcManagerInfo>(request, response), tcManagerInfo); 
		model.addAttribute("page", page);
		return "catchup/sjgl/tcManagerInfoList";
	}

	@RequiresPermissions("sjgl:tcManagerInfo:view")
	@RequestMapping(value = "form")
	public String form(TcManagerInfo tcManagerInfo, Model model) {
		model.addAttribute("tcManagerInfo", tcManagerInfo);
		return "catchup/sjgl/tcManagerInfoForm";
	}

	@RequiresPermissions("sjgl:tcManagerInfo:edit")
	@RequestMapping(value = "save")
	public String save(TcManagerInfo tcManagerInfo, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tcManagerInfo)){
			return form(tcManagerInfo, model);
		}
		try{
			tcManagerInfoService.save(tcManagerInfo);
			addMessage(redirectAttributes, "保存责任人成功");
		} catch (Exception e) {
			addMessage(redirectAttributes, "保存责任人失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/sjgl/tcManagerInfo/?repage";
	}
	
	@RequiresPermissions("sjgl:tcManagerInfo:edit")
	@RequestMapping(value = "delete")
	public String delete(TcManagerInfo tcManagerInfo, RedirectAttributes redirectAttributes) {
		tcManagerInfoService.delete(tcManagerInfo);
		addMessage(redirectAttributes, "删除责任人成功");
		return "redirect:"+Global.getAdminPath()+"/sjgl/tcManagerInfo/?repage";
	}
	/**
	 * 导出数据
	 * @param  tcManagerInfo
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("sjgl:tcManagerInfo:view")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(TcManagerInfo tcManagerInfo, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
			String wjm=new String(request.getParameter("name"));
		try {
            String fileName =wjm+"数据"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
           // Page<TcManagerInfo> page = tcManagerInfoService.findPage(new Page<TcManagerInfo>(request, response, -1),  tcManagerInfo);
           //导出全部数据
           List<TcManagerInfo>  tcManagerInfoList= tcManagerInfoService.findList(new TcManagerInfo());
    		new ExportExcel(wjm+"数据", TcManagerInfo.class).setDataList(tcManagerInfoList).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出"+wjm +"数据失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/sjgl/tcManagerInfo/?repage";
    }
/**
	 * 导入用户数据
	 * @param file
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("sjgl:tcManagerInfo:edit")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file,TcManagerInfo tcManagerInfo, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<TcManagerInfo> list = ei.getDataList(TcManagerInfo.class);
			for (TcManagerInfo t : list){

			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条，导入信息如下：");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/sjgl/tcManagerInfo/?repage";
    }
      /**
	 * 下载导入数据模板
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("sjgl:tcManagerInfo:view")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(TcManagerInfo tcManagerInfo,HttpServletRequest request,HttpServletResponse response, RedirectAttributes redirectAttributes) {
		String wjm=request.getParameter("name");
		try {
            String fileName = wjm+"数据导入模板.xlsx";
    		List<TcManagerInfo> list = Lists.newArrayList();
    		list.add(tcManagerInfo);
    		new ExportExcel(wjm+"数据", TcManagerInfo.class, 2).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/sjgl/tcManagerInfo/?repage";
    }

}