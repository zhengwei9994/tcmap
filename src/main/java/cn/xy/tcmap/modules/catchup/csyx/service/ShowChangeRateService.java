/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csyx.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.service.CrudService;
import cn.xy.tcmap.modules.catchup.csyx.entity.ShowChangeRate;
import cn.xy.tcmap.modules.catchup.csyx.dao.ShowChangeRateDao;

/**
 * 变化率Service
 * @author 变化率
 * @version 2019-08-22
 */
@Service
@Transactional(readOnly = true)
public class ShowChangeRateService extends CrudService<ShowChangeRateDao, ShowChangeRate> {

	@Autowired
	private ShowChangeRateDao showChangeRateDao;
	public ShowChangeRate CheckByYear(ShowChangeRate year){
		return showChangeRateDao.findByYear(year);
	}
	public ShowChangeRate get(String id) {
		return super.get(id);
	}
	
	public List<ShowChangeRate> findList(ShowChangeRate showChangeRate) {
		return super.findList(showChangeRate);
	}
	
	public Page<ShowChangeRate> findPage(Page<ShowChangeRate> page, ShowChangeRate showChangeRate) {
		return super.findPage(page, showChangeRate);
	}
	
	@Transactional(readOnly = false)
	public void save(ShowChangeRate showChangeRate) {
		super.save(showChangeRate);
	}
	
	@Transactional(readOnly = false)
	public void delete(ShowChangeRate showChangeRate) {
		super.delete(showChangeRate);
	}
	
}