/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csly.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.service.CrudService;
import cn.xy.tcmap.modules.catchup.csly.entity.TouristSentimentTrend;
import cn.xy.tcmap.modules.catchup.csly.dao.TouristSentimentTrendDao;

/**
 * 舆情趋势Service
 * @author wufan
 * @version 2019-08-16
 */
@Service
@Transactional(readOnly = true)
public class TouristSentimentTrendService extends CrudService<TouristSentimentTrendDao, TouristSentimentTrend> {

	public TouristSentimentTrend get(String id) {
		return super.get(id);
	}
	
	public List<TouristSentimentTrend> findList(TouristSentimentTrend touristSentimentTrend) {
		return super.findList(touristSentimentTrend);
	}
	
	public Page<TouristSentimentTrend> findPage(Page<TouristSentimentTrend> page, TouristSentimentTrend touristSentimentTrend) {
		return super.findPage(page, touristSentimentTrend);
	}
	
	@Transactional(readOnly = false)
	public void save(TouristSentimentTrend touristSentimentTrend) {
		super.save(touristSentimentTrend);
	}
	
	@Transactional(readOnly = false)
	public void delete(TouristSentimentTrend touristSentimentTrend) {
		super.delete(touristSentimentTrend);
	}
	
}