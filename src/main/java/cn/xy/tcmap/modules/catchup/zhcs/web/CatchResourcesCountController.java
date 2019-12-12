/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.zhcs.web;

import cn.xy.tcmap.common.config.Global;
import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.utils.HttpUtils;
import cn.xy.tcmap.common.utils.StringUtils;
import cn.xy.tcmap.common.web.BaseController;
import cn.xy.tcmap.modules.catchup.zhcs.entity.CatchResourcesCount;
import cn.xy.tcmap.modules.catchup.zhcs.service.CatchResourcesCountService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 人才类型统计Controller
 * @author liuyang
 * @version 2018-06-05
 */
@Controller
@RequestMapping(value = "${adminPath}/zhcs/catchResourcesCount")
public class CatchResourcesCountController extends BaseController {

	@Autowired
	private CatchResourcesCountService catchResourcesCountService;
	
	@ModelAttribute
	public CatchResourcesCount get(@RequestParam(required=false) String id) {
		CatchResourcesCount entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = catchResourcesCountService.get(id);
		}
		if (entity == null){
			entity = new CatchResourcesCount();
		}
		return entity;
	}
	
	@RequiresPermissions("zhcs:catchResourcesCount:view")
	@RequestMapping(value = {"list", ""})
	public String list(CatchResourcesCount catchResourcesCount, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CatchResourcesCount> page = catchResourcesCountService.findPage(new Page<CatchResourcesCount>(request, response), catchResourcesCount); 
		model.addAttribute("page", page);
		return "catchup/zhcs/catchResourcesCountList";
	}

	@RequiresPermissions("zhcs:catchResourcesCount:view")
	@RequestMapping(value = "form")
	public String form(CatchResourcesCount catchResourcesCount, Model model) {
		model.addAttribute("catchResourcesCount", catchResourcesCount);
		return "catchup/zhcs/catchResourcesCountForm";
	}

	@RequiresPermissions("zhcs:catchResourcesCount:edit")
	@RequestMapping(value = "save")
	public String save(CatchResourcesCount catchResourcesCount, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, catchResourcesCount)){
			return form(catchResourcesCount, model);
		}
		List<CatchResourcesCount> list = catchResourcesCountService.findList(catchResourcesCount);
		if(list !=null && list.size() >0){
			addMessage(redirectAttributes, "人才类型已存在，请勿重复添加");
		}else {
			catchResourcesCountService.save(catchResourcesCount);
			addMessage(redirectAttributes, "保存人才类型统计成功");
		}
		return "redirect:"+Global.getAdminPath()+"/zhcs/catchResourcesCount/?repage";
	}
	
	@RequiresPermissions("zhcs:catchResourcesCount:edit")
	@RequestMapping(value = "delete")
	public String delete(CatchResourcesCount catchResourcesCount, RedirectAttributes redirectAttributes) {
		catchResourcesCountService.delete(catchResourcesCount);
		addMessage(redirectAttributes, "删除人才类型统计成功");
		return "redirect:"+Global.getAdminPath()+"/zhcs/catchResourcesCount/?repage";
	}

	@RequiresPermissions("zhcs:catchResourcesCount:edit")
	@RequestMapping(value = "synData")
	public String synDatas(RedirectAttributes redirectAttributes) {
		catchResourcesCountService.syndata("","");
		//通过接口获取数据
		String str = HttpUtils.sendGet("http://192.168.114.12:8080/restful/mid/cdf/cdf/data/service/QgaTWd","nyear=2018");
		if(StringUtils.isNotBlank(str)){
			Object object = JSONObject.fromObject(str).get("datasets");
			if(object != null  && !"".equals(object) ){
				JSONArray jsonArray = JSONArray.fromObject(object);
				for (Object jsonObject : jsonArray) {
					CatchResourcesCount	catchResourcesCount  = (CatchResourcesCount)JSONObject.toBean(JSONObject.fromObject(jsonObject),CatchResourcesCount.class);
					System.out.println(catchResourcesCount.getNyear()+"--------------"+catchResourcesCount.getDbName());
					//对数据进行同步
					//catchResourcesCountService.save(catchResourcesCount);
				}
			}

		}
		return "redirect:"+Global.getAdminPath()+"/zhcs/catchResourcesCount/?repage";
	}

}