/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.fzjc.web;

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
import cn.xy.tcmap.modules.catchup.fzjc.entity.CatchVideoResource;
import cn.xy.tcmap.modules.catchup.fzjc.service.CatchVideoResourceService;

import java.util.List;

/**
 * 公共安全视频资源覆盖情况Controller
 * @author liuyang
 * @version 2018-05-29
 */
@Controller
@RequestMapping(value = "${adminPath}/fzjc/catchVideoResource")
public class CatchVideoResourceController extends BaseController {

	@Autowired
	private CatchVideoResourceService catchVideoResourceService;
	
	@ModelAttribute
	public CatchVideoResource get(@RequestParam(required=false) String id) {
		CatchVideoResource entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = catchVideoResourceService.get(id);
		}
		if (entity == null){
			entity = new CatchVideoResource();
		}
		return entity;
	}
	
	@RequiresPermissions("fzjc:catchVideoResource:view")
	@RequestMapping(value = {"list", ""})
	public String list(CatchVideoResource catchVideoResource, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CatchVideoResource> page = catchVideoResourceService.findPage(new Page<CatchVideoResource>(request, response), catchVideoResource); 
		model.addAttribute("page", page);
		return "catchup/fzjc/catchVideoResourceList";
	}

	@RequiresPermissions("fzjc:catchVideoResource:view")
	@RequestMapping(value = "form")
	public String form(CatchVideoResource catchVideoResource, Model model) {
		model.addAttribute("catchVideoResource", catchVideoResource);
		return "catchup/fzjc/catchVideoResourceForm";
	}

	@RequiresPermissions("fzjc:catchVideoResource:edit")
	@RequestMapping(value = "save")
	public String save(CatchVideoResource catchVideoResource, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, catchVideoResource)){
			return form(catchVideoResource, model);
		}
		try {
			if(catchVideoResource.getIsNewRecord()){
                CatchVideoResource catchVideoResource1 = new CatchVideoResource();
                catchVideoResource1.setNyear(catchVideoResource.getNyear());
                List<CatchVideoResource> list = catchVideoResourceService.findList(catchVideoResource1);
                if(list!=null&&list.size()>0){
                    throw new Exception("数据重复");
                }
            }
			catchVideoResourceService.save(catchVideoResource);
			addMessage(redirectAttributes, "保存公共安全视频资源覆盖情况成功");
		}catch (Exception e){
            addMessage(redirectAttributes, "保存公共安全视频资源覆盖情况失败，原因："+e.getMessage());
		}

		return "redirect:"+Global.getAdminPath()+"/fzjc/catchVideoResource/?repage";
	}
	
	@RequiresPermissions("fzjc:catchVideoResource:edit")
	@RequestMapping(value = "delete")
	public String delete(CatchVideoResource catchVideoResource, RedirectAttributes redirectAttributes) {
		catchVideoResourceService.delete(catchVideoResource);
		addMessage(redirectAttributes, "删除公共安全视频资源覆盖情况成功");
		return "redirect:"+Global.getAdminPath()+"/fzjc/catchVideoResource/?repage";
	}

}