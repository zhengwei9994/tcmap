/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csyx.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import cn.xy.tcmap.common.beanvalidator.BeanValidators;
import cn.xy.tcmap.common.utils.DateUtils;
import cn.xy.tcmap.common.utils.excel.ExportExcel;
import cn.xy.tcmap.common.utils.excel.ImportExcel;
import com.google.common.collect.Lists;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.xy.tcmap.common.config.Global;
import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.web.BaseController;
import cn.xy.tcmap.common.utils.StringUtils;
import cn.xy.tcmap.modules.catchup.csyx.entity.BusinessBalance;
import cn.xy.tcmap.modules.catchup.csyx.service.BusinessBalanceService;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 金融机构本外币存款余额Controller
 * @author wufan
 * @version 2019-07-31
 */
@Controller
@RequestMapping(value = "${adminPath}/csyx/businessBalance")
public class BusinessBalanceController extends BaseController {

	@Autowired
	private BusinessBalanceService businessBalanceService;
	
	@ModelAttribute
	public BusinessBalance get(@RequestParam(required=false) String id) {
		BusinessBalance entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = businessBalanceService.get(id);
		}
		if (entity == null){
			entity = new BusinessBalance();
		}
		return entity;
	}

	@RequiresPermissions("csyx:businessBalance:view")
	@RequestMapping(value = {"list", ""})
	public String list(BusinessBalance businessBalance, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<BusinessBalance> page = businessBalanceService.findPage(new Page<BusinessBalance>(request, response), businessBalance); 
		model.addAttribute("page", page);
		return "catchup/csyx/businessBalanceList";
	}

	@RequiresPermissions("csyx:businessBalance:view")
	@RequestMapping(value = "form")
	public String form(BusinessBalance businessBalance, Model model) {
		model.addAttribute("businessBalance", businessBalance);
		return "catchup/csyx/businessBalanceForm";
	}

	@RequiresPermissions("csyx:businessBalance:edit")
	@RequestMapping(value = "save")
	public String save(BusinessBalance businessBalance, Model model, RedirectAttributes redirectAttributes) {

		try{
			if (!beanValidator(model, businessBalance)){
				return form(businessBalance, model);
			}
			BusinessBalance condition = new BusinessBalance();
			condition.setMonth(businessBalance.getMonth());
			condition.setYear(businessBalance.getYear());
			List<BusinessBalance> list = businessBalanceService.findList(condition);

			if( list != null && businessBalance.getIsNewRecord()  && list.size()>0 ){
				throw new Exception("数据重复");
			}
			businessBalanceService.save(businessBalance);
			addMessage(redirectAttributes, "保存金融机构本外币存款余额成功");
		}catch (Exception e){
			addMessage(redirectAttributes, "保存金融机构本外币存款余额失败，失败原因:"+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/csyx/businessBalance/?repage";
	}
	
	@RequiresPermissions("csyx:businessBalance:edit")
	@RequestMapping(value = "delete")
	public String delete(BusinessBalance businessBalance, RedirectAttributes redirectAttributes) {
		businessBalanceService.delete(businessBalance);
		addMessage(redirectAttributes, "删除金融机构本外币存款余额成功");
		return "redirect:"+Global.getAdminPath()+"/csyx/businessBalance/?repage";
	}


	/**
	 * 导出金融机构本外币存款余额信息数据
	 * @param businessBalance
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("csyx:businessBalance:view")
	@RequestMapping(value = "export", method= RequestMethod.POST)
	public String exportFile(BusinessBalance businessBalance, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
			String fileName = "金融机构本外币存款余额"+ DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
			Page<BusinessBalance> page = businessBalanceService.findPage(new Page<BusinessBalance>(request, response, -1), businessBalance);
			new ExportExcel("金融机构本外币存款余额", BusinessBalance.class).setDataList(page.getList()).write(response, fileName).dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出金融机构本外币存款余额！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/csyx/businessBalance/?repage";
	}


	/**
	 * 导入金融机构本外币存款余额数据
	 * @param file
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("csyx:businessBalance:edit")
	@RequestMapping(value = "import", method=RequestMethod.POST)
	public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/csyx/businessBalance/?repage";
		}
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<BusinessBalance> list = ei.getDataList(BusinessBalance.class);
			for (BusinessBalance businessBalance : list){
				try{

					Pattern balancePattern = Pattern.compile("-?[0-9]+\\.?[0-9]*");
					if(!balancePattern.matcher(businessBalance.getBalance()).matches()){
						throw new Exception("余额字段是非数字");
					}
					Pattern yearPattern = Pattern.compile("^\\d{4}$");
					if(!yearPattern.matcher(businessBalance.getYear()).matches()){
						throw new Exception("年份字段格式不对");
					}
					Pattern monthPattern = Pattern.compile("^\\d{2}$");

					if(businessBalance.getMonth().length()==1){
						 businessBalance.setMonth("0"+businessBalance.getMonth());
					}
					if(!monthPattern.matcher(businessBalance.getMonth()).matches()){
						throw new Exception("月份字段格式不对");
					}
					businessBalanceService.save(businessBalance);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureMsg.append("<br/>余额 "+businessBalance.getBalance()+" 导入失败：");
					List<String> messageList = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");  //告诉你出错原因
					for (String message : messageList){
						failureMsg.append(message+"; ");
						failureNum++;
					}
				}catch (Exception ex) {
					failureMsg.append("<br/>余额 "+businessBalance.getBalance()+" 导入失败："+ex.getMessage());
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条信息，导入信息如下：");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条信息"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入金融机构本外币存款余额失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/csyx/businessBalance/list?repage";
	}

	/**
	 * 下载金融机构本外币存款余额数据模板
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("csyx:businessBalance:view")
	@RequestMapping(value = "import/template")
	public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
			String fileName = "金融机构本外币存款余额.xlsx";
			List<BusinessBalance> list = Lists.newArrayList();
			//示例
			BusinessBalance businessBalance = new BusinessBalance();
			list.add(businessBalance);
			new ExportExcel("金融机构本外币存款余额", BusinessBalance.class, 2).setDataList(list).write(response, fileName).dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/csyx/businessBalance/list?repage";
	}


}