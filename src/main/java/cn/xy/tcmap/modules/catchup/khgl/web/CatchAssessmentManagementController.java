/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.khgl.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import cn.xy.tcmap.modules.sys.entity.Dict;
import cn.xy.tcmap.modules.sys.utils.DictUtils;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringEscapeUtils;
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
import cn.xy.tcmap.modules.catchup.khgl.entity.CatchAssessmentManagement;
import cn.xy.tcmap.modules.catchup.khgl.service.CatchAssessmentManagementService;

/**
 * 考核管理Controller
 * @author wufan
 * @version 2019-12-03
 */
@Controller
@RequestMapping(value = "${adminPath}/csyx/catchAssessmentManagement")
public class CatchAssessmentManagementController extends BaseController {

	@Autowired
	private CatchAssessmentManagementService catchAssessmentManagementService;
	
	@ModelAttribute
	public CatchAssessmentManagement get(@RequestParam(required=false) String id) {
		CatchAssessmentManagement entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = catchAssessmentManagementService.get(id);
		}
		if (entity == null){
			entity = new CatchAssessmentManagement();
		}
		return entity;
	}
	
	@RequiresPermissions("csyx:catchAssessmentManagement:view")
	@RequestMapping(value = {"list", ""})
	public String list(CatchAssessmentManagement catchAssessmentManagement, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CatchAssessmentManagement> page = catchAssessmentManagementService.findPage(new Page<CatchAssessmentManagement>(request, response), catchAssessmentManagement); 
		model.addAttribute("page", page);
		return "catchup/khgl/catchAssessmentManagementList";
	}

	@RequiresPermissions("csyx:catchAssessmentManagement:view")
	@RequestMapping(value = "form")
	public String form(CatchAssessmentManagement catchAssessmentManagement, Model model) {
		model.addAttribute("catchAssessmentManagement", catchAssessmentManagement);
		return "catchup/khgl/catchAssessmentManagementForm";
	}

	@RequiresPermissions("csyx:catchAssessmentManagement:edit")
	@RequestMapping(value = "save")
	public String save(CatchAssessmentManagement catchAssessmentManagement, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, catchAssessmentManagement)){
			return form(catchAssessmentManagement, model);
		}
		try{
			if(catchAssessmentManagement.getMission()!=null){
				catchAssessmentManagement.setMission(StringEscapeUtils.unescapeHtml4(catchAssessmentManagement.getMission()));
			}
			catchAssessmentManagementService.save(catchAssessmentManagement);
			addMessage(redirectAttributes, "保存考核管理成功");
		} catch (Exception e) {
			addMessage(redirectAttributes, "保存考核管理失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/csyx/catchAssessmentManagement/?repage";
	}
	
	@RequiresPermissions("csyx:catchAssessmentManagement:edit")
	@RequestMapping(value = "delete")
	public String delete(CatchAssessmentManagement catchAssessmentManagement, RedirectAttributes redirectAttributes) {
		catchAssessmentManagementService.delete(catchAssessmentManagement);
		addMessage(redirectAttributes, "删除考核管理成功");
		return "redirect:"+Global.getAdminPath()+"/csyx/catchAssessmentManagement/?repage";
	}
	/**
	 * 导出数据
	 * @param  catchAssessmentManagement
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("csyx:catchAssessmentManagement:view")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(CatchAssessmentManagement catchAssessmentManagement, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
			String wjm=new String(request.getParameter("name"));
		try {
            String fileName =wjm+"数据"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
           // Page<CatchAssessmentManagement> page = catchAssessmentManagementService.findPage(new Page<CatchAssessmentManagement>(request, response, -1),  catchAssessmentManagement);
           //导出全部数据
           List<CatchAssessmentManagement>  catchAssessmentManagementList= catchAssessmentManagementService.findList(new CatchAssessmentManagement());
    		new ExportExcel(wjm+"数据", CatchAssessmentManagement.class).setDataList(catchAssessmentManagementList).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出"+wjm +"数据失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/csyx/catchAssessmentManagement/?repage";
    }

	/**
	 * 导入用户数据
	 * @param file
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("csyx:catchAssessmentManagement:edit")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file,CatchAssessmentManagement catchAssessmentManagement, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<CatchAssessmentManagement> list = ei.getDataList(CatchAssessmentManagement.class);
			for (int i = 0; i < list.size(); i++) {
				for (int j = 0; j < list.size(); j++) {
					if (i!=j&&list.get(i).equals(list.get(j))){
						throw new Exception("excel有重复项");
					}
				}
			}
			List<Dict> dictList = DictUtils.getDictList("municipal_department");
			List<String> collect = dictList.stream().map(Dict::getValue).collect(Collectors.toList());
			List<CatchAssessmentManagement> allList = catchAssessmentManagementService.findAllList();
			for (CatchAssessmentManagement t : list){
				if(!collect.contains(t.getDepartment())){
					failureMsg.append("考核部门："+t.getDepartment()+" 不存在，请先添加部门");
					failureNum++;
					continue;
				}
				if(!collect.contains(t.getSector())){
					failureMsg.append("下发部门："+t.getDepartment()+" 不存在，请先添加部门");
					failureNum++;
					continue;
				}
				if(t.getName().length()>20||t.getName().length()==0){
					failureMsg.append(t.getName()+": 任务名称长度有误");
					failureNum++;
					continue;
				}
				if(t.getMission().length()>255||t.getName().length()==0){
					failureMsg.append(t.getName()+": 任务内容长度有误");
					failureNum++;
					continue;
				}
				Pattern startTimePattern = Pattern.compile("^[1-9]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])\\s+(20|21|22|23|[0-1]\\d):[0-5]\\d:[0-5]\\d$");
				String s = DateUtils.formatDateTime(t.getStartDate());
				if(!startTimePattern.matcher(DateUtils.formatDateTime(t.getStartDate())).matches()){
					failureMsg.append(t.getName()+": 任务开始时间格式有误");
					failureNum++;
					continue;
				}
				Pattern endTimePattern = Pattern.compile("^[1-9]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])\\s+(20|21|22|23|[0-1]\\d):[0-5]\\d:[0-5]\\d$");
				if(!endTimePattern.matcher(DateUtils.formatDateTime(t.getEndDate())).matches()){
					failureMsg.append(t.getName()+": 任务结束时间格式有误");
					failureNum++;
					continue;
				}
				Pattern scorePattern = Pattern.compile("^100$|^(\\d|[1-9]\\d)$|^$");
				if(!scorePattern.matcher(t.getScore()).matches()){
					failureMsg.append(t.getName()+": 分值有误");
					failureNum++;
					continue;
				}
				CatchAssessmentManagement catchAssessmentManagement1 = allList.get(0);
				boolean flag = catchAssessmentManagement1.equals(t);
				if(allList.contains(t)){
					failureMsg.append(t.getName()+": 为重复内容，请删除重试");
					failureNum++;
					continue;
				}
				catchAssessmentManagementService.save(t);
				successNum++;
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条，导入信息如下：");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入失败！失败信息："+e.getMessage());
			return "redirect:"+Global.getAdminPath()+"/csyx/catchAssessmentManagement/?repage";
		}
		return "redirect:"+Global.getAdminPath()+"/csyx/catchAssessmentManagement/?repage";
    }
      /**
	 * 下载导入数据模板
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("csyx:catchAssessmentManagement:view")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(CatchAssessmentManagement catchAssessmentManagement,HttpServletRequest request,HttpServletResponse response, RedirectAttributes redirectAttributes) {
		String wjm=request.getParameter("name");
		try {
            String fileName = wjm+"数据导入模板.xlsx";
    		List<CatchAssessmentManagement> list = Lists.newArrayList();
    		list.add(catchAssessmentManagement);
    		new ExportExcel(wjm+"数据", CatchAssessmentManagement.class, 2).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/csyx/catchAssessmentManagement/?repage";
    }

}