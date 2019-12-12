/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csyx.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.service.CrudService;
import cn.xy.tcmap.modules.catchup.csyx.entity.ShowEconomicIncome;
import cn.xy.tcmap.modules.catchup.csyx.dao.ShowEconomicIncomeDao;

/**
 * 经济财政收入Service
 * @author wufan
 * @version 2019-07-31
 */
@Service
@Transactional(readOnly = true)
public class ShowEconomicIncomeService extends CrudService<ShowEconomicIncomeDao, ShowEconomicIncome> {

	public ShowEconomicIncome get(String id) {
		return super.get(id);
	}
	
	public List<ShowEconomicIncome> findList(ShowEconomicIncome showEconomicIncome) {
		return super.findList(showEconomicIncome);
	}
	
	public Page<ShowEconomicIncome> findPage(Page<ShowEconomicIncome> page, ShowEconomicIncome showEconomicIncome) {
		return super.findPage(page, showEconomicIncome);
	}
	
	@Transactional(readOnly = false)
	public void save(ShowEconomicIncome showEconomicIncome) {
		super.save(showEconomicIncome);
	}
	
	@Transactional(readOnly = false)
	public void delete(ShowEconomicIncome showEconomicIncome) {
		super.delete(showEconomicIncome);
	}
	
}