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
import cn.xy.tcmap.modules.catchup.csly.entity.TouristMultiscenicVolue;
import cn.xy.tcmap.modules.catchup.csly.service.TouristMultiscenicVolueService;

/**
 * 多景区品牌聆听气泡图Controller
 * @author tuo
 * @version 2019-10-23
 */
@Controller
@RequestMapping(value = "${adminPath}/csly/touristMultiscenicVolue")
public class TouristMultiscenicVolueController extends BaseController {

	@Autowired
	private TouristMultiscenicVolueService touristMultiscenicVolueService;
	
	@ModelAttribute
	public TouristMultiscenicVolue get(@RequestParam(required=false) String id) {
		TouristMultiscenicVolue entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = touristMultiscenicVolueService.get(id);
		}
		if (entity == null){
			entity = new TouristMultiscenicVolue();
		}
		return entity;
	}
	
	@RequiresPermissions("csly:touristMultiscenicVolue:view")
	@RequestMapping(value = {"list", ""})
	public String list(TouristMultiscenicVolue touristMultiscenicVolue, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TouristMultiscenicVolue> page = touristMultiscenicVolueService.findPage(new Page<TouristMultiscenicVolue>(request, response), touristMultiscenicVolue); 
		model.addAttribute("page", page);
		return "catchup/csly/touristMultiscenicVolueList";
	}

	@RequiresPermissions("csly:touristMultiscenicVolue:view")
	@RequestMapping(value = "form")
	public String form(TouristMultiscenicVolue touristMultiscenicVolue, Model model) {
		model.addAttribute("touristMultiscenicVolue", touristMultiscenicVolue);
		return "catchup/csly/touristMultiscenicVolueForm";
	}

	@RequiresPermissions("csly:touristMultiscenicVolue:edit")
	@RequestMapping(value = "save")
	public String save(TouristMultiscenicVolue touristMultiscenicVolue, Model model, RedirectAttributes redirectAttributes) {
		try{
			if (!beanValidator(model, touristMultiscenicVolue)){
				return form(touristMultiscenicVolue, model);
			}
			if(touristMultiscenicVolue.getIsNewRecord()){
				TouristMultiscenicVolue tmv = new TouristMultiscenicVolue();
				tmv.setScenic(touristMultiscenicVolue.getScenic());
				tmv.setYear(touristMultiscenicVolue.getYear());
				tmv.setMonth(touristMultiscenicVolue.getMonth());
				List<TouristMultiscenicVolue> list = touristMultiscenicVolueService.findList(tmv);
				if(list != null && list.size()>0){
					addMessage(redirectAttributes, "保存多景区品牌聆听气泡图失败,数据重复");
					return "redirect:"+Global.getAdminPath()+"/csly/touristMultiscenicVolue/?repage";
				}
			}
			touristMultiscenicVolueService.save(touristMultiscenicVolue);
			addMessage(redirectAttributes, "保存多景区品牌聆听气泡图成功");
		}catch (Exception e){
			addMessage(redirectAttributes, "保存多景区品牌聆听气泡图失败");
		}
		return "redirect:"+Global.getAdminPath()+"/csly/touristMultiscenicVolue/?repage";
	}
	
	@RequiresPermissions("csly:touristMultiscenicVolue:edit")
	@RequestMapping(value = "delete")
	public String delete(TouristMultiscenicVolue touristMultiscenicVolue, RedirectAttributes redirectAttributes) {
		touristMultiscenicVolueService.delete(touristMultiscenicVolue);
		addMessage(redirectAttributes, "删除多景区品牌聆听气泡图成功");
		return "redirect:"+Global.getAdminPath()+"/csly/touristMultiscenicVolue/?repage";
	}
	/**
	 * 导出数据
	 * @param  touristMultiscenicVolue
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("csly:touristMultiscenicVolue:view")
    @RequestMapping(value = "export", method= RequestMethod.POST)
    public String exportFile(TouristMultiscenicVolue touristMultiscenicVolue, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
			String wjm=new String(request.getParameter("name"));
		try {
            String fileName =wjm+"数据"+ DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<TouristMultiscenicVolue> page = touristMultiscenicVolueService.findPage(new Page<TouristMultiscenicVolue>(request, response, -1),  touristMultiscenicVolue);
    		new ExportExcel(wjm+"数据", TouristMultiscenicVolue.class).setDataList(page.getList()).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出"+wjm +"数据失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/csly/touristMultiscenicVolue/?repage";
    }
/**
	 * 导入用户数据
	 * @param file
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("csly:touristMultiscenicVolue:edit")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file,TouristMultiscenicVolue touristMultiscenicVolue, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<TouristMultiscenicVolue> list = ei.getDataList(TouristMultiscenicVolue.class);
			for (TouristMultiscenicVolue t : list){

			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条，导入信息如下：");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/csly/touristMultiscenicVolue/?repage";
    }
      /**
	 * 下载导入数据模板
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("csly:touristMultiscenicVolue:view")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(TouristMultiscenicVolue touristMultiscenicVolue,HttpServletRequest request,HttpServletResponse response, RedirectAttributes redirectAttributes) {
		String wjm=request.getParameter("name");
		try {
            String fileName = wjm+"数据导入模板.xlsx";
    		List<TouristMultiscenicVolue> list = Lists.newArrayList();
    		list.add(touristMultiscenicVolue);
    		new ExportExcel(wjm+"数据", TouristMultiscenicVolue.class, 2).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/csly/touristMultiscenicVolue/?repage";
    }

}