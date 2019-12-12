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
import cn.xy.tcmap.modules.catchup.csly.entity.TouristInformationOnly;
import cn.xy.tcmap.modules.catchup.csly.service.TouristInformationOnlyService;

/**
 * 单一景区资讯Controller
 * @author tuo
 * @version 2019-10-23
 */
@Controller
@RequestMapping(value = "${adminPath}/csly/touristInformationOnly")
public class TouristInformationOnlyController extends BaseController {

	@Autowired
	private TouristInformationOnlyService touristInformationOnlyService;
	
	@ModelAttribute
	public TouristInformationOnly get(@RequestParam(required=false) String id) {
		TouristInformationOnly entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = touristInformationOnlyService.get(id);
		}
		if (entity == null){
			entity = new TouristInformationOnly();
		}
		return entity;
	}
	
	@RequiresPermissions("csly:touristInformationOnly:view")
	@RequestMapping(value = {"list", ""})
	public String list(TouristInformationOnly touristInformationOnly, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TouristInformationOnly> page = touristInformationOnlyService.findPage(new Page<TouristInformationOnly>(request, response), touristInformationOnly); 
		model.addAttribute("page", page);
		return "catchup/csly/touristInformationOnlyList";
	}

	@RequiresPermissions("csly:touristInformationOnly:view")
	@RequestMapping(value = "form")
	public String form(TouristInformationOnly touristInformationOnly, Model model) {
		model.addAttribute("touristInformationOnly", touristInformationOnly);
		return "catchup/csly/touristInformationOnlyForm";
	}

	@RequiresPermissions("csly:touristInformationOnly:edit")
	@RequestMapping(value = "save")
	public String save(TouristInformationOnly touristInformationOnly, Model model, RedirectAttributes redirectAttributes) {
		try{
			if (!beanValidator(model, touristInformationOnly)){
				return form(touristInformationOnly, model);
			}
			if(touristInformationOnly.getIsNewRecord()){
				TouristInformationOnly tio = new TouristInformationOnly();
				tio.setArea(touristInformationOnly.getArea());

				List<TouristInformationOnly> list = touristInformationOnlyService.findList(tio);
				if(list != null && list.size()>0){
					addMessage(redirectAttributes, "保存单一景区资讯失败,数据重复");
					return "redirect:"+Global.getAdminPath()+"/csly/touristInformationOnly/?repage";
				}
			}
			touristInformationOnlyService.save(touristInformationOnly);
			addMessage(redirectAttributes, "保存单一景区资讯成功");
		}catch (Exception e){
			addMessage(redirectAttributes, "保存单一景区资讯失败");
		}
		return "redirect:"+Global.getAdminPath()+"/csly/touristInformationOnly/?repage";
	}
	
	@RequiresPermissions("csly:touristInformationOnly:edit")
	@RequestMapping(value = "delete")
	public String delete(TouristInformationOnly touristInformationOnly, RedirectAttributes redirectAttributes) {
		touristInformationOnlyService.delete(touristInformationOnly);
		addMessage(redirectAttributes, "删除单一景区资讯成功");
		return "redirect:"+Global.getAdminPath()+"/csly/touristInformationOnly/?repage";
	}
	/**
	 * 导出数据
	 * @param  touristInformationOnly
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("csly:touristInformationOnly:view")
    @RequestMapping(value = "export", method= RequestMethod.POST)
    public String exportFile(TouristInformationOnly touristInformationOnly, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
			String wjm=new String(request.getParameter("name"));
		try {
            String fileName =wjm+"数据"+ DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<TouristInformationOnly> page = touristInformationOnlyService.findPage(new Page<TouristInformationOnly>(request, response, -1),  touristInformationOnly);
    		new ExportExcel(wjm+"数据", TouristInformationOnly.class).setDataList(page.getList()).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出"+wjm +"数据失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/csly/touristInformationOnly/?repage";
    }
/**
	 * 导入用户数据
	 * @param file
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("csly:touristInformationOnly:edit")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file,TouristInformationOnly touristInformationOnly, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<TouristInformationOnly> list = ei.getDataList(TouristInformationOnly.class);
			for (TouristInformationOnly t : list){

			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条，导入信息如下：");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/csly/touristInformationOnly/?repage";
    }
      /**
	 * 下载导入数据模板
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("csly:touristInformationOnly:view")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(TouristInformationOnly touristInformationOnly,HttpServletRequest request,HttpServletResponse response, RedirectAttributes redirectAttributes) {
		String wjm=request.getParameter("name");
		try {
            String fileName = wjm+"数据导入模板.xlsx";
    		List<TouristInformationOnly> list = Lists.newArrayList();
    		list.add(touristInformationOnly);
    		new ExportExcel(wjm+"数据", TouristInformationOnly.class, 2).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/csly/touristInformationOnly/?repage";
    }

}