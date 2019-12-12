/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csly.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.service.CrudService;
import cn.xy.tcmap.modules.catchup.csly.entity.TouristReputation;
import cn.xy.tcmap.modules.catchup.csly.dao.TouristReputationDao;

/**
 * 美誉度趋势Service
 * @author tuo
 * @version 2019-10-23
 */
@Service
@Transactional(readOnly = true)
public class TouristReputationService extends CrudService<TouristReputationDao, TouristReputation> {

	public TouristReputation get(String id) {
		return super.get(id);
	}
	
	public List<TouristReputation> findList(TouristReputation touristReputation) {
		return super.findList(touristReputation);
	}
	
	public Page<TouristReputation> findPage(Page<TouristReputation> page, TouristReputation touristReputation) {
		return super.findPage(page, touristReputation);
	}
	
	@Transactional(readOnly = false)
	public void save(TouristReputation touristReputation) {
		super.save(touristReputation);
	}
	
	@Transactional(readOnly = false)
	public void delete(TouristReputation touristReputation) {
		super.delete(touristReputation);
	}
	
}