/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csgz.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import cn.xy.tcmap.modules.sys.service.AreaService;
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
import cn.xy.tcmap.modules.catchup.csgz.entity.TcCityFeel;
import cn.xy.tcmap.modules.catchup.csgz.service.TcCityFeelService;

/**
 * 城市感知数据管理Controller
 * @author wh
 * @version 2019-12-04
 */
@Controller
@RequestMapping(value = "${adminPath}/csgz/tcCityFeel")
public class TcCityFeelController extends BaseController {

	@Autowired
	private TcCityFeelService tcCityFeelService;
	@Autowired
	private AreaService areaService;
	@ModelAttribute
	public TcCityFeel get(@RequestParam(required=false) String id) {
		TcCityFeel entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tcCityFeelService.get(id);
		}
		if (entity == null){
			entity = new TcCityFeel();
		}
		return entity;
	}
	
	@RequiresPermissions("csgz:tcCityFeel:view")
	@RequestMapping(value = {"list", ""})
	public String list(TcCityFeel tcCityFeel, HttpServletRequest request, HttpServletResponse response, Model model) {
		if (tcCityFeel.getFocusArea()!=null && tcCityFeel.getFocusArea().length()>0) {
			tcCityFeel.setFocusArea((areaService.get(tcCityFeel.getFocusArea())).getName());
		}
		Page<TcCityFeel> page = tcCityFeelService.findPage(new Page<TcCityFeel>(request, response), tcCityFeel);
		model.addAttribute("page", page);
		return "catchup/csgz/tcCityFeelList";
	}

	@RequiresPermissions("csgz:tcCityFeel:view")
	@RequestMapping(value = "form")
	public String form(TcCityFeel tcCityFeel, Model model) {
		model.addAttribute("tcCityFeel", tcCityFeel);
		return "catchup/csgz/tcCityFeelForm";
	}

	@RequiresPermissions("csgz:tcCityFeel:edit")
	@RequestMapping(value = "save")
	public String save(TcCityFeel tcCityFeel, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tcCityFeel)){
			return form(tcCityFeel, model);
		}
		try{
			tcCityFeel.setFocusArea(areaService.get(tcCityFeel.getFocusArea()).getName());
			tcCityFeelService.save(tcCityFeel);
			addMessage(redirectAttributes, "保存区域感知数据成功");
		} catch (Exception e) {
			addMessage(redirectAttributes, "保存区域感知数据失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/csgz/tcCityFeel/?repage";
	}
	
	@RequiresPermissions("csgz:tcCityFeel:edit")
	@RequestMapping(value = "delete")
	public String delete(TcCityFeel tcCityFeel, RedirectAttributes redirectAttributes) {
		tcCityFeelService.delete(tcCityFeel);
		addMessage(redirectAttributes, "删除区域感知数据成功");
		return "redirect:"+Global.getAdminPath()+"/csgz/tcCityFeel/?repage";
	}
	/**
	 * 导出数据
	 * @param  tcCityFeel
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("csgz:tcCityFeel:view")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(TcCityFeel tcCityFeel, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
			String wjm=new String(request.getParameter("name"));
		try {
            String fileName =wjm+"数据"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
           // Page<TcCityFeel> page = tcCityFeelService.findPage(new Page<TcCityFeel>(request, response, -1),  tcCityFeel);
           //导出全部数据
           List<TcCityFeel>  tcCityFeelList= tcCityFeelService.findList(new TcCityFeel());
    		new ExportExcel(wjm+"数据", TcCityFeel.class).setDataList(tcCityFeelList).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出"+wjm +"数据失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/csgz/tcCityFeel/?repage";
    }
/**
	 * 导入城市感知数据
	 * @param file
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("csgz:tcCityFeel:edit")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file,TcCityFeel tcCityFeel, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<TcCityFeel> list = ei.getDataList(TcCityFeel.class);
			for (TcCityFeel t : list){

			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条，导入信息如下：");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/csgz/tcCityFeel/?repage";
    }
      /**
	 * 下载导入数据模板
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("csgz:tcCityFeel:view")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(TcCityFeel tcCityFeel,HttpServletRequest request,HttpServletResponse response, RedirectAttributes redirectAttributes) {
		String wjm=request.getParameter("name");
		try {
            String fileName = wjm+"数据导入模板.xlsx";
    		List<TcCityFeel> list = Lists.newArrayList();
    		list.add(tcCityFeel);
    		new ExportExcel(wjm+"数据", TcCityFeel.class, 2).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/csgz/tcCityFeel/?repage";
    }


}