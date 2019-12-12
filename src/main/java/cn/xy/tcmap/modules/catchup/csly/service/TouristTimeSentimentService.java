/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csly.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.service.CrudService;
import cn.xy.tcmap.modules.catchup.csly.entity.TouristTimeSentiment;
import cn.xy.tcmap.modules.catchup.csly.dao.TouristTimeSentimentDao;

/**
 * 实时预警舆情Service
 * @author wufan
 * @version 2019-08-19
 */
@Service
@Transactional(readOnly = true)
public class TouristTimeSentimentService extends CrudService<TouristTimeSentimentDao, TouristTimeSentiment> {

	public TouristTimeSentiment get(String id) {
		return super.get(id);
	}
	
	public List<TouristTimeSentiment> findList(TouristTimeSentiment touristTimeSentiment) {
		return super.findList(touristTimeSentiment);
	}
	
	public Page<TouristTimeSentiment> findPage(Page<TouristTimeSentiment> page, TouristTimeSentiment touristTimeSentiment) {
		return super.findPage(page, touristTimeSentiment);
	}
	
	@Transactional(readOnly = false)
	public void save(TouristTimeSentiment touristTimeSentiment) {
		super.save(touristTimeSentiment);
	}
	
	@Transactional(readOnly = false)
	public void delete(TouristTimeSentiment touristTimeSentiment) {
		super.delete(touristTimeSentiment);
	}
	
}