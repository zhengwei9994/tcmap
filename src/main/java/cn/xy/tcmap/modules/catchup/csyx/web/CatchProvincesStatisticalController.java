/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csyx.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import cn.xy.tcmap.modules.catchup.csyx.entity.CatchProvincesStatistical;
import cn.xy.tcmap.modules.catchup.csyx.service.CatchProvincesStatisticalService;

import java.util.List;

/**
 * 游客分析Controller
 * @author xuzhou
 * @version 2018-05-24
 */
@Controller
@RequestMapping(value = "${adminPath}/csyx/catchProvincesStatistical")
public class CatchProvincesStatisticalController extends BaseController {

	@Autowired
	private CatchProvincesStatisticalService catchProvincesStatisticalService;
	
	@ModelAttribute
	public CatchProvincesStatistical get(@RequestParam(required=false) String id) {
		CatchProvincesStatistical entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = catchProvincesStatisticalService.get(id);
		}
		if (entity == null){
			entity = new CatchProvincesStatistical();
		}
		return entity;
	}
	
	@RequiresPermissions("csyx:catchProvincesStatistical:view")
	@RequestMapping(value = {"list", ""})
	public String list(CatchProvincesStatistical catchProvincesStatistical, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CatchProvincesStatistical> page = catchProvincesStatisticalService.findPage(new Page<CatchProvincesStatistical>(request, response), catchProvincesStatistical); 
		model.addAttribute("page", page);
		return "catchup/csyx/catchProvincesStatisticalList";
	}

	@RequiresPermissions("csyx:catchProvincesStatistical:view")
	@RequestMapping(value = "form")
	public String form(CatchProvincesStatistical catchProvincesStatistical, Model model) {
		model.addAttribute("catchProvincesStatistical", catchProvincesStatistical);
		return "catchup/csyx/catchProvincesStatisticalForm";
	}

	@RequiresPermissions("csyx:catchProvincesStatistical:edit")
	@RequestMapping(value = "save")
	public String save(CatchProvincesStatistical catchProvincesStatistical, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, catchProvincesStatistical)){
			return form(catchProvincesStatistical, model);
		}
		try {
			if(catchProvincesStatistical.getIsNewRecord()){
                CatchProvincesStatistical catchProvincesStatistical1 = new CatchProvincesStatistical();
                catchProvincesStatistical1.setNyear(catchProvincesStatistical.getNyear());
                List<CatchProvincesStatistical> list = catchProvincesStatisticalService.findList(catchProvincesStatistical1);
                if(list!=null&&list.size()>0){
                    throw new Exception("数据重复");
                }
            }
			Integer sum = catchProvincesStatistical.getForeigns()+catchProvincesStatistical.getOtherProvinces()+catchProvincesStatistical.getThisProvinces();
			catchProvincesStatistical.setTouristsSum(sum);
			catchProvincesStatisticalService.save(catchProvincesStatistical);
			addMessage(redirectAttributes, "保存游客分析成功");
		}catch (Exception e){
            addMessage(redirectAttributes, "保存游客分析失败，原因："+e.getMessage());
		}

		return "redirect:"+Global.getAdminPath()+"/csyx/catchProvincesStatistical/?repage";
	}
	
	@RequiresPermissions("csyx:catchProvincesStatistical:edit")
	@RequestMapping(value = "delete")
	public String delete(CatchProvincesStatistical catchProvincesStatistical, RedirectAttributes redirectAttributes) {
		catchProvincesStatisticalService.delete(catchProvincesStatistical);
		addMessage(redirectAttributes, "删除游客分析成功");
		return "redirect:"+Global.getAdminPath()+"/csyx/catchProvincesStatistical/?repage";
	}

}