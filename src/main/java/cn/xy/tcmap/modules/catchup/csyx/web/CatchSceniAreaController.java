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
import cn.xy.tcmap.modules.catchup.csyx.entity.CatchSceniArea;
import cn.xy.tcmap.modules.catchup.csyx.service.CatchSceniAreaService;

import java.util.List;

/**
 * 景区旅游数据分析Controller
 * @author xuzhou
 * @version 2018-05-24
 */
@Controller
@RequestMapping(value = "${adminPath}/csyx/catchSceniArea")
public class CatchSceniAreaController extends BaseController {

	@Autowired
	private CatchSceniAreaService catchSceniAreaService;
	
	@ModelAttribute
	public CatchSceniArea get(@RequestParam(required=false) String id) {
		CatchSceniArea entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = catchSceniAreaService.get(id);
		}
		if (entity == null){
			entity = new CatchSceniArea();
		}
		return entity;
	}
	
	@RequiresPermissions("csyx:catchSceniArea:view")
	@RequestMapping(value = {"list", ""})
	public String list(CatchSceniArea catchSceniArea, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CatchSceniArea> page = catchSceniAreaService.findPage(new Page<CatchSceniArea>(request, response), catchSceniArea); 
		model.addAttribute("page", page);
		return "catchup/csyx/catchSceniAreaList";
	}

	@RequiresPermissions("csyx:catchSceniArea:view")
	@RequestMapping(value = "form")
	public String form(CatchSceniArea catchSceniArea, Model model) {
		model.addAttribute("catchSceniArea", catchSceniArea);
		return "catchup/csyx/catchSceniAreaForm";
	}

	@RequiresPermissions("csyx:catchSceniArea:edit")
	@RequestMapping(value = "save")
	public String save(CatchSceniArea catchSceniArea, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, catchSceniArea)){
			return form(catchSceniArea, model);
		}
		try {
			if(catchSceniArea.getIsNewRecord()){
                CatchSceniArea catchSceniArea1 = new CatchSceniArea();
                catchSceniArea1.setNyear(catchSceniArea.getNyear());
                catchSceniArea1.setScenicArea(catchSceniArea.getScenicArea());
                List<CatchSceniArea> list = catchSceniAreaService.findList(catchSceniArea1);
                if(list!=null&&list.size()>0){
                    throw new Exception("数据重复");
                }
            }
			catchSceniAreaService.save(catchSceniArea);
			addMessage(redirectAttributes, "保存景区旅游数据分析成功");
		}catch (Exception e){
            addMessage(redirectAttributes, "保存景区旅游数据分析失败，原因："+e.getMessage());
		}

		return "redirect:"+Global.getAdminPath()+"/csyx/catchSceniArea/?repage";
	}
	
	@RequiresPermissions("csyx:catchSceniArea:edit")
	@RequestMapping(value = "delete")
	public String delete(CatchSceniArea catchSceniArea, RedirectAttributes redirectAttributes) {
		catchSceniAreaService.delete(catchSceniArea);
		addMessage(redirectAttributes, "删除景区旅游数据分析成功");
		return "redirect:"+Global.getAdminPath()+"/csyx/catchSceniArea/?repage";
	}

}