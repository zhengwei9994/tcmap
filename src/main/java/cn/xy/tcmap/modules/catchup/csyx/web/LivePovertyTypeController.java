/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csyx.web;

import cn.xy.tcmap.common.config.Global;
import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.utils.DateUtils;
import cn.xy.tcmap.common.utils.StringUtils;
import cn.xy.tcmap.common.utils.excel.ExportExcel;
import cn.xy.tcmap.common.web.BaseController;
import cn.xy.tcmap.modules.catchup.csyx.entity.LivePovertyType;
import cn.xy.tcmap.modules.catchup.csyx.service.LivePovertyTypeService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 贫困类型Controller
 * @author tuo
 * @version 2019-08-14
 */
@Controller
@RequestMapping(value = "${adminPath}/csyx/livePovertyType")
public class LivePovertyTypeController extends BaseController {

	@Autowired
	private LivePovertyTypeService livePovertyTypeService;
	
	@ModelAttribute
	public LivePovertyType get(@RequestParam(required=false) String id) {
		LivePovertyType entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = livePovertyTypeService.get(id);
		}
		if (entity == null){
			entity = new LivePovertyType();
		}
		return entity;
	}
	
	@RequiresPermissions("csyx:livePovertyType:view")
	@RequestMapping(value = {"list", ""})
	public String list(LivePovertyType livePovertyType, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<LivePovertyType> page = livePovertyTypeService.findPage(new Page<LivePovertyType>(request, response), livePovertyType); 
		model.addAttribute("page", page);
		return "catchup/csyx/livePovertyTypeList";
	}

	@RequiresPermissions("csyx:livePovertyType:view")
	@RequestMapping(value = "form")
	public String form(LivePovertyType livePovertyType, Model model) {
		model.addAttribute("livePovertyType", livePovertyType);
		return "catchup/csyx/livePovertyTypeForm";
	}

	@RequiresPermissions("csyx:livePovertyType:edit")
	@RequestMapping(value = "save")
	public String save(LivePovertyType livePovertyType, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, livePovertyType)){
			return form(livePovertyType, model);
		}
		try{
            if (livePovertyType.getIsNewRecord()){
                LivePovertyType livePovertyType1 = new LivePovertyType();
                livePovertyType1.setArea(livePovertyType.getArea());
                livePovertyType1.setType(livePovertyType.getType());
                List<LivePovertyType> list = livePovertyTypeService.findList(livePovertyType1);
                if(list!=null && list.size()>0){
                    throw new Exception("数据重复");
                }

            }
            livePovertyTypeService.save(livePovertyType);
            addMessage(redirectAttributes, "保存贫困类型成功");
        }catch (Exception e){
            addMessage(redirectAttributes, "保存贫困类型失败，原因："+e.getMessage());
        }

		return "redirect:"+Global.getAdminPath()+"/csyx/livePovertyType/?repage";
	}
	
	@RequiresPermissions("csyx:livePovertyType:edit")
	@RequestMapping(value = "delete")
	public String delete(LivePovertyType livePovertyType, RedirectAttributes redirectAttributes) {
		livePovertyTypeService.delete(livePovertyType);
		addMessage(redirectAttributes, "删除贫困类型成功");
		return "redirect:"+Global.getAdminPath()+"/csyx/livePovertyType/?repage";
	}

	@RequiresPermissions("csyx:livePovertyType:edit")
	@RequestMapping(value = "export", method= RequestMethod.POST)
	public String exportFile(LivePovertyType livePovertyType, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		String wjm=new String(request.getParameter("name"));
		try {
			String fileName =wjm+"数据"+ DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
			// Page<SafeCity> page = safeCityService.findPage(new Page<SafeCity>(request, response, -1),  safeCity);
			//导出全部数据
			List<LivePovertyType>  livePovertyTypeList= livePovertyTypeService.findList(livePovertyType);
			new ExportExcel(wjm+"数据", LivePovertyType.class).setDataList(livePovertyTypeList).write(response, fileName).dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出"+wjm +"数据失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/csyx/livePovertyType/?repage";
	}
}