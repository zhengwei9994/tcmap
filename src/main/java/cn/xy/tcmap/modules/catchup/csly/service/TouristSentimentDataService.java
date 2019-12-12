/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csly.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.service.CrudService;
import cn.xy.tcmap.modules.catchup.csly.entity.TouristSentimentData;
import cn.xy.tcmap.modules.catchup.csly.dao.TouristSentimentDataDao;

/**
 * 舆情数据Service
 * @author wufan
 * @version 2019-08-16
 */
@Service
@Transactional(readOnly = true)
public class TouristSentimentDataService extends CrudService<TouristSentimentDataDao, TouristSentimentData> {

	public TouristSentimentData get(String id) {
		return super.get(id);
	}
	
	public List<TouristSentimentData> findList(TouristSentimentData touristSentimentData) {
		return super.findList(touristSentimentData);
	}
	
	public Page<TouristSentimentData> findPage(Page<TouristSentimentData> page, TouristSentimentData touristSentimentData) {
		return super.findPage(page, touristSentimentData);
	}
	
	@Transactional(readOnly = false)
	public void save(TouristSentimentData touristSentimentData) {
		super.save(touristSentimentData);
	}
	
	@Transactional(readOnly = false)
	public void delete(TouristSentimentData touristSentimentData) {
		super.delete(touristSentimentData);
	}
	
}