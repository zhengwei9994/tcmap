/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csyx.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.service.CrudService;
import cn.xy.tcmap.modules.catchup.csyx.entity.ShowEconomicGrowth;
import cn.xy.tcmap.modules.catchup.csyx.dao.ShowEconomicGrowthDao;

/**
 * 经济成长性Service
 * @author wufan
 * @version 2019-07-31
 */
@Service
@Transactional(readOnly = true)
public class ShowEconomicGrowthService extends CrudService<ShowEconomicGrowthDao, ShowEconomicGrowth> {

	public ShowEconomicGrowth get(String id) {
		return super.get(id);
	}
	
	public List<ShowEconomicGrowth> findList(ShowEconomicGrowth showEconomicGrowth) {
		return super.findList(showEconomicGrowth);
	}
	
	public Page<ShowEconomicGrowth> findPage(Page<ShowEconomicGrowth> page, ShowEconomicGrowth showEconomicGrowth) {
		return super.findPage(page, showEconomicGrowth);
	}
	
	@Transactional(readOnly = false)
	public void save(ShowEconomicGrowth showEconomicGrowth) {
		super.save(showEconomicGrowth);
	}
	
	@Transactional(readOnly = false)
	public void delete(ShowEconomicGrowth showEconomicGrowth) {
		super.delete(showEconomicGrowth);
	}
	
}