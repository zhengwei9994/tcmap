/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csyx.web;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import cn.xy.tcmap.common.config.Global;
import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.utils.StringUtils;
import cn.xy.tcmap.common.web.BaseController;
import cn.xy.tcmap.modules.catchup.csyx.entity.CatchKeyprojectClass;
import cn.xy.tcmap.modules.catchup.csyx.service.CatchKeyprojectClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;

/**
 * 月进度Controller
 * @author xuzhou
 * @version 2018-05-02
 */
@Controller
@RequestMapping(value = "${adminPath}/csyx/catchKeyprojectClass")
public class CatchKeyprojectClassController extends BaseController {

	@Autowired
	private CatchKeyprojectClassService catchKeyprojectClassService;
	
	@ModelAttribute
	public CatchKeyprojectClass get(@RequestParam(required=false) String id) {
		CatchKeyprojectClass entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = catchKeyprojectClassService.get(id);
		}
		if (entity == null){
			entity = new CatchKeyprojectClass();
		}
		return entity;
	}

	@RequestMapping(value = {"list", ""})
	public String list(CatchKeyprojectClass catchKeyprojectClass, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CatchKeyprojectClass> page = catchKeyprojectClassService.findPage(new Page<CatchKeyprojectClass>(request, response), catchKeyprojectClass);
		model.addAttribute("page", page);
		return "catchup/csyx/catchKeyprojectClassList";
	}


	@RequestMapping(value = "form")
	public String form(CatchKeyprojectClass catchKeyprojectClass, Model model) {
		model.addAttribute("catchKeyprojectClass", catchKeyprojectClass);
		return "catchup/csyx/catchKeyprojectClassForm";
	}

	@RequestMapping(value = "save")
	public String save(CatchKeyprojectClass catchKeyprojectClass, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, catchKeyprojectClass)){
			return form(catchKeyprojectClass, model);
		}
        catchKeyprojectClass.setMonth(catchKeyprojectClass.getMonth().replaceFirst("^0*", "")); 
	    String str = catchKeyprojectClass.getAmountCompleted();
		String str1 = catchKeyprojectClass.getKeyprojectId().getTotalInvestment();
	    double zhanbi = Double.parseDouble(str) / Double.parseDouble(str1)*100;
		catchKeyprojectClass.setCompletionRatio(zhanbi+"");
		if(catchKeyprojectClass.getId() == null || "".equals(catchKeyprojectClass.getId())){
			List<CatchKeyprojectClass> catchFineAirList = catchKeyprojectClassService.findList(catchKeyprojectClass);
			if(catchFineAirList != null && catchFineAirList.size()>0){
				addMessage(redirectAttributes, "保存月进度失败，该月份下已有数据，请重新选择！");
				return "redirect:"+Global.getAdminPath()+"/csyx/catchKeyprojectClass?keyprojectId.id="+catchKeyprojectClass.getKeyprojectId().getId()+"&keyprojectId.totalInvestment="+catchKeyprojectClass.getKeyprojectId().getTotalInvestment();
			}
		}else{
			CatchKeyprojectClass catchKeyprojectClass1 = catchKeyprojectClassService.get(catchKeyprojectClass);
			if(!catchKeyprojectClass.getMonth().equals(catchKeyprojectClass1.getMonth())){
				List<CatchKeyprojectClass> list = catchKeyprojectClassService.findList(catchKeyprojectClass);
				if(list != null && list.size()>0){
					addMessage(redirectAttributes, "保存月进度失败，该月份下已有数据，请重新选择！");
					return "redirect:"+Global.getAdminPath()+"/csyx/catchKeyprojectClass?keyprojectId.id="+catchKeyprojectClass.getKeyprojectId().getId()+"&keyprojectId.totalInvestment="+catchKeyprojectClass.getKeyprojectId().getTotalInvestment();
				}
			}
		}
		catchKeyprojectClassService.save(catchKeyprojectClass);
		addMessage(redirectAttributes, "保存月进度成功");
		return "redirect:"+Global.getAdminPath()+"/csyx/catchKeyprojectClass?keyprojectId.id="+catchKeyprojectClass.getKeyprojectId().getId()+"&keyprojectId.totalInvestment="+catchKeyprojectClass.getKeyprojectId().getTotalInvestment();
	}
	

	@RequestMapping(value = "delete")
	public String delete(CatchKeyprojectClass catchKeyprojectClass, RedirectAttributes redirectAttributes) {
		catchKeyprojectClassService.delete(catchKeyprojectClass);
		addMessage(redirectAttributes, "删除月进度成功");
		return "redirect:"+Global.getAdminPath()+"/csyx/catchKeyprojectClass?keyprojectId.id="+catchKeyprojectClass.getKeyprojectId().getId()+"&keyprojectId.totalInvestment="+catchKeyprojectClass.getKeyprojectId().getTotalInvestment();
	}

}