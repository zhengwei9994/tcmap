/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.sjgl.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.xy.tcmap.modules.catchup.sjgl.entity.TcEventManager;
import cn.xy.tcmap.modules.catchup.sjgl.service.TcEventManagerService;
import cn.xy.tcmap.modules.sys.utils.UserUtils;
import com.google.common.collect.Lists;
import net.sf.json.JSONArray;
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
import cn.xy.tcmap.modules.catchup.sjgl.entity.TcEventDetail;
import cn.xy.tcmap.modules.catchup.sjgl.service.TcEventDetailService;

/**
 * 事件数据详情Controller
 * @author wh
 * @version 2019-12-05
 */
@Controller
@RequestMapping(value = "${adminPath}/sjgl/tcEventDetail")
public class TcEventDetailController extends BaseController {

	@Autowired
	private TcEventDetailService tcEventDetailService;
	@Autowired
	private TcEventManagerService tcEventManagerService;
	@ModelAttribute
	public TcEventDetail get(@RequestParam(required=false) String id) {
		TcEventDetail entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tcEventDetailService.get(id);
		}
		if (entity == null){
			entity = new TcEventDetail();
		}
		return entity;
	}
	@RequiresPermissions("sjgl:tcEventDetail:view")
	@RequestMapping(value = {"list", ""})
	public String list(TcEventDetail tcEventDetail, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TcEventDetail> page = tcEventDetailService.findPage(new Page<TcEventDetail>(request, response), tcEventDetail);
		model.addAttribute("page", page);
		return "catchup/sjgl/tcEventDetailList";
	}

    /**
     *
     * @param tcEventDetail
     * @param type 事件类型
     * @param request
     * @param response
     * @param model
     * @return   事件详情页面展示 当前所有此类型事件
     */
	@RequiresPermissions("sjgl:tcEventDetail:view")
	@RequestMapping(value = {"eventManager"})
	public String eventManager(TcEventDetail tcEventDetail, @RequestParam(value ="type" ) String type , HttpServletRequest request, HttpServletResponse response, Model model) {
		if(type!=null && type !=""){
			tcEventDetail.setType(type);
			Page<TcEventDetail> page = tcEventDetailService.findPage(new Page<TcEventDetail>(request, response), tcEventDetail);
			model.addAttribute("page", page);
		}
		return "catchup/sjgl/tcEventDetailList";
	}

	@RequiresPermissions("sjgl:tcEventDetail:view")
	@RequestMapping(value = "form")
	public String form(TcEventDetail tcEventDetail, Model model) {
		model.addAttribute("tcEventDetail", tcEventDetail);
		return "catchup/sjgl/tcEventDetailForm";
	}

	@RequiresPermissions("sjgl:tcEventDetail:edit")
	@RequestMapping(value = "save")
	public String save(TcEventDetail tcEventDetail, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tcEventDetail)){
			return form(tcEventDetail, model);
		}
		try{
			if(tcEventDetail.getEventContent()!=null){
			    tcEventDetail.setEventContent(StringEscapeUtils.unescapeHtml4(tcEventDetail.getEventContent()));
			}
			tcEventDetail.setCreateTime(new Date());
			tcEventDetail.setCreateUser(UserUtils.getUser().getName());
			tcEventDetailService.save(tcEventDetail);
			addMessage(redirectAttributes, "保存事件详情成功");
		} catch (Exception e) {
			addMessage(redirectAttributes, "保存事件详情失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/sjgl/tcEventDetail/?repage";
	}
	
	@RequiresPermissions("sjgl:tcEventDetail:edit")
	@RequestMapping(value = "delete")
	public String delete(TcEventDetail tcEventDetail, RedirectAttributes redirectAttributes) {
		tcEventDetailService.delete(tcEventDetail);
		List<TcEventDetail> eventDetailList=tcEventDetailService.findList(tcEventDetail);
		if(eventDetailList.size()==0){//执行删除时，查询事件详情是否删除彻底，若删除干净。则调事件管理模块方法，关联删除操作
			TcEventManager tcEventManager=new TcEventManager();
			tcEventManager.setEventType(tcEventDetail.getType());
                  List<TcEventManager> eventManagers=tcEventManagerService.findList(tcEventManager);//根据事件类型查出 事件管理模块数据
                  if(eventManagers.size()>0){//存在，则删除数据
                  	int a =  tcEventManagerService.deleteByType(tcEventManager);
                  }
		}
		addMessage(redirectAttributes, "删除事件详情成功");
		return "redirect:"+Global.getAdminPath()+"/sjgl/tcEventDetail/?repage";
	}

	/**
	 * 导出数据
	 * @param  tcEventDetail
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("sjgl:tcEventDetail:view")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(TcEventDetail tcEventDetail, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
			String wjm=new String(request.getParameter("name"));
		try {
            String fileName =wjm+"数据"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
           // Page<TcEventDetail> page = tcEventDetailService.findPage(new Page<TcEventDetail>(request, response, -1),  tcEventDetail);
           //导出全部数据
           List<TcEventDetail>  tcEventDetailList= tcEventDetailService.findList(new TcEventDetail());
    		new ExportExcel(wjm+"数据", TcEventDetail.class).setDataList(tcEventDetailList).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出"+wjm +"数据失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/sjgl/tcEventDetail/?repage";
    }
/**
	 * 导入用户数据
	 * @param file
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("sjgl:tcEventDetail:edit")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file,TcEventDetail tcEventDetail, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<TcEventDetail> list = ei.getDataList(TcEventDetail.class);
			for (TcEventDetail t : list){

			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条，导入信息如下：");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/sjgl/tcEventDetail/?repage";
    }
      /**
	 * 下载导入数据模板
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("sjgl:tcEventDetail:view")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(TcEventDetail tcEventDetail,HttpServletRequest request,HttpServletResponse response, RedirectAttributes redirectAttributes) {
		String wjm=request.getParameter("name");
		try {
            String fileName = wjm+"数据导入模板.xlsx";
    		List<TcEventDetail> list = Lists.newArrayList();
    		list.add(tcEventDetail);
    		new ExportExcel(wjm+"数据", TcEventDetail.class, 2).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/sjgl/tcEventDetail/?repage";
    }

}