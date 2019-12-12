/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.fzjc.web;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.formula.functions.T;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import cn.xy.tcmap.common.config.Global;
import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.web.BaseController;
import cn.xy.tcmap.common.utils.DateUtils;
import cn.xy.tcmap.common.utils.StringUtils;
import cn.xy.tcmap.modules.catchup.fzjc.entity.CatchIndexCompletion;
import cn.xy.tcmap.modules.catchup.fzjc.service.CatchIndexCompletionService;
import net.sf.json.JSONObject;

/**
 * 运行指标完成率Controller
 * @author liuyang
 * @version 2018-05-29
 */
@Controller
@RequestMapping(value = "${adminPath}/fzjc/catchIndexCompletion")
public class CatchIndexCompletionController extends BaseController {

	@Autowired
	private CatchIndexCompletionService catchIndexCompletionService;
	
	@ModelAttribute
	public CatchIndexCompletion get(@RequestParam(required=false) String id) {
		CatchIndexCompletion entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = catchIndexCompletionService.get(id);
		}
		if (entity == null){
			entity = new CatchIndexCompletion();
		}
		return entity;
	}
	
	@RequiresPermissions("fzjc:catchIndexCompletion:view")
	@RequestMapping(value = {"list", ""})
	public String list(CatchIndexCompletion catchIndexCompletion, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CatchIndexCompletion> page = catchIndexCompletionService.findPage(new Page<CatchIndexCompletion>(request, response), catchIndexCompletion); 
		model.addAttribute("page", page);
		return "catchup/fzjc/catchIndexCompletionList";
	}

	@RequiresPermissions("fzjc:catchIndexCompletion:view")
	@RequestMapping(value = "form")
	public String form(CatchIndexCompletion catchIndexCompletion, Model model) {
		model.addAttribute("catchIndexCompletion", catchIndexCompletion);
		return "catchup/fzjc/catchIndexCompletionForm";
	}

	@RequiresPermissions("fzjc:catchIndexCompletion:edit")
	@RequestMapping(value = "save")
	public String save(CatchIndexCompletion catchIndexCompletion, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, catchIndexCompletion)){
			return form(catchIndexCompletion, model);
		}
		catchIndexCompletionService.save(catchIndexCompletion);
		addMessage(redirectAttributes, "保存运行指标完成率成功");
		return "redirect:"+Global.getAdminPath()+"/fzjc/catchIndexCompletion/?repage";
	}
	
	@RequiresPermissions("fzjc:catchIndexCompletion:edit")
	@RequestMapping(value = "delete")
	public String delete(CatchIndexCompletion catchIndexCompletion, RedirectAttributes redirectAttributes) {
		catchIndexCompletionService.delete(catchIndexCompletion);
		addMessage(redirectAttributes, "删除运行指标完成率成功");
		return "redirect:"+Global.getAdminPath()+"/fzjc/catchIndexCompletion/?repage";
	}
	@RequiresPermissions("fzjc:catchIndexCompletion:edit")
	@RequestMapping(value = "test")
	public void test(CatchIndexCompletion catchIndexCompletion) throws JsonParseException, JsonMappingException, IOException, BeansException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		String a = Global.getPathConfig("catch_index_completion");
		String an = DateUtils.nyear;
		 JSONObject addOrEditParams = new JSONObject();
		addOrEditParams.put("nyear", "2018");
		addOrEditParams.put("dateTime", "2018-10-25");
		String url = "http://192.168.114.12:8080/restful/mid/cdf/cdf/webservice/queryService/zxxtyxzb";
		catchIndexCompletionService.syncDataTask(url, addOrEditParams.toString(), null, catchIndexCompletion);
		//catchIndexCompletionService.syndata("http://192.168.114.12:8080/restful/mid/cdf/cdf/webservice/queryService/zxxtyxzb",map.toString(),new CatchIndexCompletion());
	}
}