/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csly.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import cn.xy.tcmap.common.utils.DateUtils;
import cn.xy.tcmap.common.utils.excel.ExportExcel;
import com.google.common.collect.Lists;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import cn.xy.tcmap.common.utils.excel.ImportExcel;
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
import cn.xy.tcmap.modules.catchup.csly.entity.TouristReputation;
import cn.xy.tcmap.modules.catchup.csly.service.TouristReputationService;

/**
 * 美誉度趋势Controller
 * @author tuo
 * @version 2019-10-23
 */
@Controller
@RequestMapping(value = "${adminPath}/csly/touristReputation")
public class TouristReputationController extends BaseController {

	@Autowired
	private TouristReputationService touristReputationService;
	
	@ModelAttribute
	public TouristReputation get(@RequestParam(required=false) String id) {
		TouristReputation entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = touristReputationService.get(id);
		}
		if (entity == null){
			entity = new TouristReputation();
		}
		return entity;
	}
	
	@RequiresPermissions("csly:touristReputation:view")
	@RequestMapping(value = {"list", ""})
	public String list(TouristReputation touristReputation, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TouristReputation> page = touristReputationService.findPage(new Page<TouristReputation>(request, response), touristReputation); 
		model.addAttribute("page", page);
		return "catchup/csly/touristReputationList";
	}

	@RequiresPermissions("csly:touristReputation:view")
	@RequestMapping(value = "form")
	public String form(TouristReputation touristReputation, Model model) {
		model.addAttribute("touristReputation", touristReputation);
		return "catchup/csly/touristReputationForm";
	}

	@RequiresPermissions("csly:touristReputation:edit")
	@RequestMapping(value = "save")
	public String save(TouristReputation touristReputation, Model model, RedirectAttributes redirectAttributes) {
		try{
			if (!beanValidator(model, touristReputation)){
				return form(touristReputation, model);
			}
			if(touristReputation.getIsNewRecord()){
				TouristReputation tr = new TouristReputation();
				tr.setYear(touristReputation.getYear());
				tr.setMonth(touristReputation.getMonth());
				tr.setScenery(touristReputation.getScenery());
				List<TouristReputation> list = touristReputationService.findList(tr);
				if(list != null && list.size()>0){
					addMessage(redirectAttributes, "保存美誉度趋势失败,数据重复");
					return "redirect:"+Global.getAdminPath()+"/csly/touristMultiscenicVolue/?repage";
				}
			}
			touristReputationService.save(touristReputation);
			addMessage(redirectAttributes, "保存美誉度趋势成功");
		}catch (Exception e){
			addMessage(redirectAttributes, "保存美誉度趋势失败");
		}
		return "redirect:"+Global.getAdminPath()+"/csly/touristReputation/?repage";
	}
	
	@RequiresPermissions("csly:touristReputation:edit")
	@RequestMapping(value = "delete")
	public String delete(TouristReputation touristReputation, RedirectAttributes redirectAttributes) {
		touristReputationService.delete(touristReputation);
		addMessage(redirectAttributes, "删除美誉度趋势成功");
		return "redirect:"+Global.getAdminPath()+"/csly/touristReputation/?repage";
	}
	/**
	 * 导出数据
	 * @param  touristReputation
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("csly:touristReputation:view")
    @RequestMapping(value = "export", method= RequestMethod.POST)
    public String exportFile(TouristReputation touristReputation, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
			String wjm=new String(request.getParameter("name"));
		try {
            String fileName =wjm+"数据"+ DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<TouristReputation> page = touristReputationService.findPage(new Page<TouristReputation>(request, response, -1),  touristReputation);
    		new ExportExcel(wjm+"数据", TouristReputation.class).setDataList(page.getList()).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出"+wjm +"数据失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/csly/touristReputation/?repage";
    }
/**
	 * 导入用户数据
	 * @param file
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("csly:touristReputation:edit")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file,TouristReputation touristReputation, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<TouristReputation> list = ei.getDataList(TouristReputation.class);
			for (TouristReputation t : list){

			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条，导入信息如下：");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/csly/touristReputation/?repage";
    }
      /**
	 * 下载导入数据模板
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("csly:touristReputation:view")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(TouristReputation touristReputation,HttpServletRequest request,HttpServletResponse response, RedirectAttributes redirectAttributes) {
		String wjm=request.getParameter("name");
		try {
            String fileName = wjm+"数据导入模板.xlsx";
    		List<TouristReputation> list = Lists.newArrayList();
    		list.add(touristReputation);
    		new ExportExcel(wjm+"数据", TouristReputation.class, 2).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/csly/touristReputation/?repage";
    }

}