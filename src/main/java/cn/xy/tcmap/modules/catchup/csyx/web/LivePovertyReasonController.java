/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csyx.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.xy.tcmap.common.utils.DateUtils;
import cn.xy.tcmap.common.utils.excel.ExportExcel;
import cn.xy.tcmap.common.utils.excel.ImportExcel;
import cn.xy.tcmap.modules.catchup.khgl.entity.CatchAssessmentManagement;
import cn.xy.tcmap.modules.sys.entity.Dict;
import cn.xy.tcmap.modules.sys.utils.DictUtils;
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

import cn.xy.tcmap.common.config.Global;
import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.web.BaseController;
import cn.xy.tcmap.common.utils.StringUtils;
import cn.xy.tcmap.modules.catchup.csyx.entity.LivePovertyReason;
import cn.xy.tcmap.modules.catchup.csyx.service.LivePovertyReasonService;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * 致贫原因Controller
 * @author tuo
 * @version 2019-08-14
 */
@Controller
@RequestMapping(value = "${adminPath}/csyx/livePovertyReason")
public class LivePovertyReasonController extends BaseController {

	@Autowired
	private LivePovertyReasonService livePovertyReasonService;
	
	@ModelAttribute
	public LivePovertyReason get(@RequestParam(required=false) String id) {
		LivePovertyReason entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = livePovertyReasonService.get(id);
		}
		if (entity == null){
			entity = new LivePovertyReason();
		}
		return entity;
	}
	
	@RequiresPermissions("csyx:livePovertyReason:view")
	@RequestMapping(value = {"list", ""})
	public String list(LivePovertyReason livePovertyReason, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<LivePovertyReason> page = livePovertyReasonService.findPage(new Page<LivePovertyReason>(request, response), livePovertyReason); 
		model.addAttribute("page", page);
		return "catchup/csyx/livePovertyReasonList";
	}

	@RequiresPermissions("csyx:livePovertyReason:view")
	@RequestMapping(value = "form")
	public String form(LivePovertyReason livePovertyReason, Model model) {
		model.addAttribute("livePovertyReason", livePovertyReason);
		return "catchup/csyx/livePovertyReasonForm";
	}

	@RequiresPermissions("csyx:livePovertyReason:edit")
	@RequestMapping(value = "save")
	public String save(LivePovertyReason livePovertyReason, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, livePovertyReason)){
			return form(livePovertyReason, model);
		}
		livePovertyReasonService.save(livePovertyReason);
		addMessage(redirectAttributes, "保存致贫原因成功");
		return "redirect:"+Global.getAdminPath()+"/csyx/livePovertyReason/?repage";
	}
	
	@RequiresPermissions("csyx:livePovertyReason:edit")
	@RequestMapping(value = "delete")
	public String delete(LivePovertyReason livePovertyReason, RedirectAttributes redirectAttributes) {
		livePovertyReasonService.delete(livePovertyReason);
		addMessage(redirectAttributes, "删除致贫原因成功");
		return "redirect:"+Global.getAdminPath()+"/csyx/livePovertyReason/?repage";
	}


	/**
	 * 导出数据
	 * @param  livePovertyReason
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("csyx:livePovertyReason:view")
	@RequestMapping(value = "export", method= RequestMethod.POST)
	public String exportFile(LivePovertyReason livePovertyReason, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		String wjm=new String(request.getParameter("name"));
		try {
			String fileName =wjm+"数据"+ DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
			// Page<CatchAssessmentManagement> page = catchAssessmentManagementService.findPage(new Page<CatchAssessmentManagement>(request, response, -1),  catchAssessmentManagement);
			//导出全部数据
			List<LivePovertyReason> livePovertyReasonList= livePovertyReasonService.findList(new LivePovertyReason());
			new ExportExcel(wjm+"数据", LivePovertyReason.class).setDataList(livePovertyReasonList).write(response, fileName).dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出"+wjm +"数据失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/csyx/livePovertyReason/?repage";
	}

	/**
	 * 导入用户数据
	 * @param file
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("csyx:livePovertyReason:edit")
	@RequestMapping(value = "import", method=RequestMethod.POST)
	public String importFile(MultipartFile file, LivePovertyReason livePovertyReason, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<LivePovertyReason> list = ei.getDataList(LivePovertyReason.class);
			for (LivePovertyReason t : list){
				livePovertyReasonService.save(t);
				successNum++;
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条，导入信息如下：");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/csyx/livePovertyReason/?repage";
	}

	/**
	 * 下载导入数据模板
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("csyx:livePovertyReason:view")
	@RequestMapping(value = "import/template")
	public String importFileTemplate(LivePovertyReason livePovertyReason,HttpServletRequest request,HttpServletResponse response, RedirectAttributes redirectAttributes) {
		String wjm=request.getParameter("name");
		try {
			String fileName = wjm+"数据导入模板.xlsx";
			List<LivePovertyReason> list = Lists.newArrayList();
			list.add(livePovertyReason);
			new ExportExcel(wjm+"数据", LivePovertyReason.class, 2).setDataList(list).write(response, fileName).dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/csyx/livePovertyReason/?repage";
	}
}