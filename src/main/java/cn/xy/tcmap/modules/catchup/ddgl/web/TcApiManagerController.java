/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.ddgl.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

import cn.xy.tcmap.modules.catchup.ddgl.entity.TcSyserviceMananger;
import cn.xy.tcmap.modules.catchup.ddgl.service.TcSyserviceManangerService;
import cn.xy.tcmap.modules.sys.entity.Area;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import cn.xy.tcmap.common.utils.excel.ImportExcel;
import cn.xy.tcmap.common.utils.DateUtils;
import cn.xy.tcmap.common.utils.excel.ExportExcel;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.xy.tcmap.common.config.Global;
import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.web.BaseController;
import cn.xy.tcmap.common.utils.StringUtils;
import cn.xy.tcmap.modules.catchup.ddgl.entity.TcApiManager;
import cn.xy.tcmap.modules.catchup.ddgl.service.TcApiManagerService;

/**
 * 接口调度管理Controller
 * @author wh
 * @version 2019-12-06
 */
@Controller
@RequestMapping(value = "${adminPath}/ddgl/tcApiManager")
public class TcApiManagerController extends BaseController {

	@Autowired
	private TcApiManagerService tcApiManagerService;
	@Autowired
    private TcSyserviceManangerService tcSyserviceManangerService;
	@ModelAttribute
	public TcApiManager get(@RequestParam(required=false) String id) {
		TcApiManager entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tcApiManagerService.get(id);
		}
		if (entity == null){
			entity = new TcApiManager();
		}
		return entity;
	}
	
	@RequiresPermissions("ddgl:tcApiManager:view")
	@RequestMapping(value = {"list", ""})
	public String list(TcApiManager tcApiManager, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TcApiManager> page = tcApiManagerService.findPage(new Page<TcApiManager>(request, response), tcApiManager); 
		model.addAttribute("page", page);
		return "catchup/ddgl/tcApiManagerList";
	}

	@RequiresPermissions("ddgl:tcApiManager:view")
	@RequestMapping(value = "form")
	public String form(TcApiManager tcApiManager, Model model) {
		model.addAttribute("tcApiManager", tcApiManager);
		return "catchup/ddgl/tcApiManagerForm";
	}

	@RequiresPermissions("ddgl:tcApiManager:edit")
	@RequestMapping(value = "save")
	public String save(TcApiManager tcApiManager, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tcApiManager)){
			return form(tcApiManager, model);
		}
		try{
			tcApiManagerService.save(tcApiManager);
			addMessage(redirectAttributes, "保存接口成功");
		} catch (Exception e) {
			addMessage(redirectAttributes, "保存接口失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/ddgl/tcApiManager/?repage";
	}
	
	@RequiresPermissions("ddgl:tcApiManager:edit")
	@RequestMapping(value = "delete")
	public String delete(TcApiManager tcApiManager, RedirectAttributes redirectAttributes) {
		tcApiManagerService.delete(tcApiManager);
		addMessage(redirectAttributes, "删除接口成功");
		return "redirect:"+Global.getAdminPath()+"/ddgl/tcApiManager/?repage";
	}
	/**
	 * 导出数据
	 * @param  tcApiManager
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("ddgl:tcApiManager:view")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(TcApiManager tcApiManager, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
			String wjm=new String(request.getParameter("name"));
		try {
            String fileName =wjm+"数据"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
           // Page<TcApiManager> page = tcApiManagerService.findPage(new Page<TcApiManager>(request, response, -1),  tcApiManager);
           //导出全部数据
           List<TcApiManager>  tcApiManagerList= tcApiManagerService.findList(new TcApiManager());
    		new ExportExcel(wjm+"数据", TcApiManager.class).setDataList(tcApiManagerList).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出"+wjm +"数据失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/ddgl/tcApiManager/?repage";
    }

/**
	 * 导入用户数据
	 * @param file
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("ddgl:tcApiManager:edit")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file,TcApiManager tcApiManager, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<TcApiManager> list = ei.getDataList(TcApiManager.class);
			for (TcApiManager t : list){

			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条，导入信息如下：");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/ddgl/tcApiManager/?repage";
    }
      /**
	 * 下载导入数据模板
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("ddgl:tcApiManager:view")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(TcApiManager tcApiManager,HttpServletRequest request,HttpServletResponse response, RedirectAttributes redirectAttributes) {
		String wjm=request.getParameter("name");
		try {
            String fileName = wjm+"数据导入模板.xlsx";
    		List<TcApiManager> list = Lists.newArrayList();
    		list.add(tcApiManager);
    		new ExportExcel(wjm+"数据", TcApiManager.class, 2).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/ddgl/tcApiManager/?repage";
    }
    @RequiresPermissions("ddgl:tcApiManager:view")
    @ResponseBody
    @RequestMapping(value = "treeData")
    public List<Map<String, Object>> treeData(@RequestParam(required=false) String extId, HttpServletResponse response) {
        List<Map<String, Object>> mapList = Lists.newArrayList();
        List<TcSyserviceMananger> list = tcSyserviceManangerService.findList(new TcSyserviceMananger());
        for (int i=0; i<list.size(); i++){
            TcSyserviceMananger e = list.get(i);
            if (StringUtils.isBlank(extId) || (extId!=null && !extId.equals(e.getId()) && e.getId().indexOf(","+extId+",")==-1)){
                Map<String, Object> map = Maps.newHashMap();
                map.put("id", e.getId());
                map.put("pId", e.getId());
                map.put("name", e.getSysName());
                mapList.add(map);
            }
        }
        return mapList;
    }

}