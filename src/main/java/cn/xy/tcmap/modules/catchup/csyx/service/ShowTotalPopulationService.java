/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csyx.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.service.CrudService;
import cn.xy.tcmap.modules.catchup.csyx.entity.ShowTotalPopulation;
import cn.xy.tcmap.modules.catchup.csyx.dao.ShowTotalPopulationDao;

/**
 * 人口总量Service
 * @author tuo
 * @version 2019-08-22
 */
@Service
@Transactional(readOnly = true)
public class ShowTotalPopulationService extends CrudService<ShowTotalPopulationDao, ShowTotalPopulation> {

	public ShowTotalPopulation get(String id) {
		return super.get(id);
	}
	
	public List<ShowTotalPopulation> findList(ShowTotalPopulation showTotalPopulation) {
		return super.findList(showTotalPopulation);
	}
	
	public Page<ShowTotalPopulation> findPage(Page<ShowTotalPopulation> page, ShowTotalPopulation showTotalPopulation) {
		return super.findPage(page, showTotalPopulation);
	}
	
	@Transactional(readOnly = false)
	public void save(ShowTotalPopulation showTotalPopulation) {
		super.save(showTotalPopulation);
	}
	
	@Transactional(readOnly = false)
	public void delete(ShowTotalPopulation showTotalPopulation) {
		super.delete(showTotalPopulation);
	}
	
}