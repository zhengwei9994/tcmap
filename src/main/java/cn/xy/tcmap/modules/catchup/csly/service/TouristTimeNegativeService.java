/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csly.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.service.CrudService;
import cn.xy.tcmap.modules.catchup.csly.entity.TouristTimeNegative;
import cn.xy.tcmap.modules.catchup.csly.dao.TouristTimeNegativeDao;

/**
 * 实时负面评价Service
 * @author wufan
 * @version 2019-08-19
 */
@Service
@Transactional(readOnly = true)
public class TouristTimeNegativeService extends CrudService<TouristTimeNegativeDao, TouristTimeNegative> {

	public TouristTimeNegative get(String id) {
		return super.get(id);
	}
	
	public List<TouristTimeNegative> findList(TouristTimeNegative touristTimeNegative) {
		return super.findList(touristTimeNegative);
	}
	
	public Page<TouristTimeNegative> findPage(Page<TouristTimeNegative> page, TouristTimeNegative touristTimeNegative) {
		return super.findPage(page, touristTimeNegative);
	}
	
	@Transactional(readOnly = false)
	public void save(TouristTimeNegative touristTimeNegative) {
		super.save(touristTimeNegative);
	}
	
	@Transactional(readOnly = false)
	public void delete(TouristTimeNegative touristTimeNegative) {
		super.delete(touristTimeNegative);
	}
	
}