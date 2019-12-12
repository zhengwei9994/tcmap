/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.wlkj.web;

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
import cn.xy.tcmap.modules.catchup.wlkj.entity.CatchPopularAddress;
import cn.xy.tcmap.modules.catchup.wlkj.service.CatchPopularAddressService;

/**
 * 热门公众号Controller
 * @author guoxunquan
 * @version 2018-09-20
 */
@Controller
@RequestMapping(value = "${adminPath}/wlkj/catchPopularAddress")
public class CatchPopularAddressController extends BaseController {

	@Autowired
	private CatchPopularAddressService catchPopularAddressService;
	
	@ModelAttribute
	public CatchPopularAddress get(@RequestParam(required=false) String id) {
		CatchPopularAddress entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = catchPopularAddressService.get(id);
		}
		if (entity == null){
			entity = new CatchPopularAddress();
		}
		return entity;
	}
	
	@RequiresPermissions("wlkj:catchPopularAddress:view")
	@RequestMapping(value = {"list", ""})
	public String list(CatchPopularAddress catchPopularAddress, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CatchPopularAddress> page = catchPopularAddressService.findPage(new Page<CatchPopularAddress>(request, response), catchPopularAddress); 
		model.addAttribute("page", page);
		return "catchup/wlkj/catchPopularAddressList";
	}

	@RequiresPermissions("wlkj:catchPopularAddress:view")
	@RequestMapping(value = "form")
	public String form(CatchPopularAddress catchPopularAddress, Model model) {
		model.addAttribute("catchPopularAddress", catchPopularAddress);
		return "catchup/wlkj/catchPopularAddressForm";
	}

	@RequiresPermissions("wlkj:catchPopularAddress:edit")
	@RequestMapping(value = "save")
	public String save(CatchPopularAddress catchPopularAddress, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, catchPopularAddress)){
			return form(catchPopularAddress, model);
		}
		catchPopularAddressService.save(catchPopularAddress);
		addMessage(redirectAttributes, "保存热门公众号成功");
		return "redirect:"+Global.getAdminPath()+"/wlkj/catchPopularAddress/?repage";
	}
	
	@RequiresPermissions("wlkj:catchPopularAddress:edit")
	@RequestMapping(value = "delete")
	public String delete(CatchPopularAddress catchPopularAddress, RedirectAttributes redirectAttributes) {
		catchPopularAddressService.delete(catchPopularAddress);
		addMessage(redirectAttributes, "删除热门公众号成功");
		return "redirect:"+Global.getAdminPath()+"/wlkj/catchPopularAddress/?repage";
	}

}