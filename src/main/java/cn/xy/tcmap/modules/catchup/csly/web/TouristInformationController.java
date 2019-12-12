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
import cn.xy.tcmap.modules.catchup.csly.entity.TouristInformation;
import cn.xy.tcmap.modules.catchup.csly.service.TouristInformationService;

/**
 * 景区资讯排行Controller
 * @author wufan
 * @version 2019-10-23
 */
@Controller
@RequestMapping(value = "${adminPath}/csly/touristInformation")
public class TouristInformationController extends BaseController {

	@Autowired
	private TouristInformationService touristInformationService;
	
	@ModelAttribute
	public TouristInformation get(@RequestParam(required=false) String id) {
		TouristInformation entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = touristInformationService.get(id);
		}
		if (entity == null){
			entity = new TouristInformation();
		}
		return entity;
	}
	
	@RequiresPermissions("csly:touristInformation:view")
	@RequestMapping(value = {"list", ""})
	public String list(TouristInformation touristInformation, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TouristInformation> page = touristInformationService.findPage(new Page<TouristInformation>(request, response), touristInformation); 
		model.addAttribute("page", page);
		return "catchup/csly/touristInformationList";
	}

	@RequiresPermissions("csly:touristInformation:view")
	@RequestMapping(value = "form")
	public String form(TouristInformation touristInformation, Model model) {
		model.addAttribute("touristInformation", touristInformation);
		return "catchup/csly/touristInformationForm";
	}

	@RequiresPermissions("csly:touristInformation:edit")
	@RequestMapping(value = "save")
	public String save(TouristInformation touristInformation, Model model, RedirectAttributes redirectAttributes) {
		try {
			if (!beanValidator(model, touristInformation)){
				return form(touristInformation, model);
			}
			if(touristInformation.getIsNewRecord()){
				TouristInformation ti = new TouristInformation();
				ti.setInformation(touristInformation.getInformation());

				List<TouristInformation> list = touristInformationService.findList(ti);
				if(list != null && list.size()>0){
					addMessage(redirectAttributes, "保存景区资讯排行成功,数据重复");
					return "redirect:"+Global.getAdminPath()+"/csly/touristInformation/?repage";
				}
			}
			touristInformationService.save(touristInformation);
			addMessage(redirectAttributes, "保存景区资讯排行成功");
		}catch (Exception e){
			addMessage(redirectAttributes, "保存景区资讯排行失败");
		}
		return "redirect:"+Global.getAdminPath()+"/csly/touristInformation/?repage";
	}
	
	@RequiresPermissions("csly:touristInformation:edit")
	@RequestMapping(value = "delete")
	public String delete(TouristInformation touristInformation, RedirectAttributes redirectAttributes) {
		touristInformationService.delete(touristInformation);
		addMessage(redirectAttributes, "删除景区资讯排行成功");
		return "redirect:"+Global.getAdminPath()+"/csly/touristInformation/?repage";
	}
	/**
	 * 导出数据
	 * @param  touristInformation
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("csly:touristInformation:view")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(TouristInformation touristInformation, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
			String wjm=new String(request.getParameter("name"));
		try {
            String fileName =wjm+"数据"+ DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<TouristInformation> page = touristInformationService.findPage(new Page<TouristInformation>(request, response, -1),  touristInformation);
    		new ExportExcel(wjm+"数据", TouristInformation.class).setDataList(page.getList()).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出"+wjm +"数据失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/csly/touristInformation/?repage";
    }
/**
	 * 导入用户数据
	 * @param file
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("csly:touristInformation:edit")
    @RequestMapping(value = "import", method= RequestMethod.POST)
    public String importFile(MultipartFile file,TouristInformation touristInformation, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<TouristInformation> list = ei.getDataList(TouristInformation.class);
			for (TouristInformation t : list){

			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条，导入信息如下：");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/csly/touristInformation/?repage";
    }
      /**
	 * 下载导入数据模板
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("csly:touristInformation:view")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(TouristInformation touristInformation,HttpServletRequest request,HttpServletResponse response, RedirectAttributes redirectAttributes) {
		String wjm=request.getParameter("name");
		try {
            String fileName = wjm+"数据导入模板.xlsx";
    		List<TouristInformation> list = Lists.newArrayList();
    		list.add(touristInformation);
    		new ExportExcel(wjm+"数据", TouristInformation.class, 2).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/csly/touristInformation/?repage";
    }

}