/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.sjgl.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

import cn.xy.tcmap.modules.catchup.sjgl.entity.TcEventDetail;
import cn.xy.tcmap.modules.catchup.sjgl.service.TcEventDetailService;
import com.google.common.collect.Lists;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
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
import cn.xy.tcmap.modules.catchup.sjgl.entity.TcEventManager;
import cn.xy.tcmap.modules.catchup.sjgl.service.TcEventManagerService;

/**
 * 事件数据管理Controller
 * @author wh
 * @version 2019-12-05
 */
@Controller
@RequestMapping(value = "${adminPath}/sjgl/tcEventManager")
public class TcEventManagerController extends BaseController {

	@Autowired
	private TcEventManagerService tcEventManagerService;
	@Autowired
	private TcEventDetailService tcEventDetailService;
	@ModelAttribute
	public TcEventManager get(@RequestParam(required=false) String id) {
		TcEventManager entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tcEventManagerService.get(id);
		}
		if (entity == null){
			entity = new TcEventManager();
		}
		return entity;
	}
	
	@RequiresPermissions("sjgl:tcEventManager:view")
	@RequestMapping(value = {"list", ""})
	public String list(TcEventManager tcEventManager, HttpServletRequest request, HttpServletResponse response, Model model) {
		//初始化时计算一次事件数据，若有更新则存入本地
		JSONArray array=tcEventDetailService.sumEvent();//从事件详情表中获取数据
		if(array!=null && array.size()>0){
		for(int i=0;i<array.size();i++) {
		//拼接数据，保存本地表
				JSONObject jsonObject = (JSONObject) array.get(i);
				TcEventManager manager = new TcEventManager();
				Double eventSum=(Double)jsonObject.get("eventSum");
				Double sum=(Double) jsonObject.get("sum");
				Double rate=eventSum/sum*100;
				String type=jsonObject.get("type").toString();
				manager.setEventNumber(eventSum.toString());
				manager.setEventType(type);
				manager.setEventRate(rate.toString().substring(0,2)+"%");
				manager.setEventPersonal(jsonObject.get("personal").toString());
				List<TcEventManager> eventManagers=tcEventManagerService.findList(manager);
				if( eventManagers.size() ==0 ){//未检测到数据，则新增
				    tcEventManagerService.save(manager);
				}else {//如果存在，则只修正数据
					TcEventManager eventManager=eventManagers.get(0);
					eventManager.setEventNumber(jsonObject.get("eventSum").toString());
					eventManager.setEventRate(rate.toString().substring(0,2)+"%");
					int update=tcEventManagerService.update(eventManager);
					System.out.println(update);
				}
		}
	}
		Page<TcEventManager> page = tcEventManagerService.findPage(new Page<TcEventManager>(request, response), tcEventManager);
		model.addAttribute("page", page);
		return "catchup/sjgl/tcEventManagerList";
	}

	@RequiresPermissions("sjgl:tcEventManager:view")
	@RequestMapping(value = "form")
	public String form(TcEventManager tcEventManager, Model model) {
		model.addAttribute("tcEventManager", tcEventManager);
		return "catchup/sjgl/tcEventManagerForm";
	}

	@RequiresPermissions("sjgl:tcEventManager:edit")
	@RequestMapping(value = "save")
	public String save(TcEventManager tcEventManager, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tcEventManager)){
			return form(tcEventManager, model);
		}
		try{
			tcEventManagerService.save(tcEventManager);
			addMessage(redirectAttributes, "保存事件数据成功");
		} catch (Exception e) {
			addMessage(redirectAttributes, "保存事件数据失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/sjgl/tcEventManager/?repage";
	}
	
	@RequiresPermissions("sjgl:tcEventManager:edit")
	@RequestMapping(value = "delete")
	public String delete(TcEventManager tcEventManager, RedirectAttributes redirectAttributes, HttpServletRequest request, HttpServletResponse response, Model model) {
		tcEventManagerService.delete(tcEventManager);
		addMessage(redirectAttributes, "删除事件数据成功");
		Page<TcEventManager> page = tcEventManagerService.findPage(new Page<TcEventManager>(request, response), tcEventManager);
		model.addAttribute("page", page);
		return "catchup/sjgl/tcEventManagerList";
	}


	/**
	 * 导出数据
	 * @param  tcEventManager
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("sjgl:tcEventManager:view")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(TcEventManager tcEventManager, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
			String wjm=new String(request.getParameter("name"));
		try {
            String fileName =wjm+"数据"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
           // Page<TcEventManager> page = tcEventManagerService.findPage(new Page<TcEventManager>(request, response, -1),  tcEventManager);
           //导出全部数据
           List<TcEventManager>  tcEventManagerList= tcEventManagerService.findList(new TcEventManager());
    		new ExportExcel(wjm+"数据", TcEventManager.class).setDataList(tcEventManagerList).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出"+wjm +"数据失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/sjgl/tcEventManager/?repage";
    }
/**
	 * 导入用户数据
	 * @param file
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("sjgl:tcEventManager:edit")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file,TcEventManager tcEventManager, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<TcEventManager> list = ei.getDataList(TcEventManager.class);
			for (TcEventManager t : list){

			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条，导入信息如下：");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/sjgl/tcEventManager/?repage";
    }
      /**
	 * 下载导入数据模板
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("sjgl:tcEventManager:view")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(TcEventManager tcEventManager,HttpServletRequest request,HttpServletResponse response, RedirectAttributes redirectAttributes) {
		String wjm=request.getParameter("name");
		try {
            String fileName = wjm+"数据导入模板.xlsx";
    		List<TcEventManager> list = Lists.newArrayList();
    		list.add(tcEventManager);
    		new ExportExcel(wjm+"数据", TcEventManager.class, 2).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/sjgl/tcEventManager/?repage";
    }

}