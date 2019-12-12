/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csly.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.service.CrudService;
import cn.xy.tcmap.modules.catchup.csly.entity.TouristSentimentSource;
import cn.xy.tcmap.modules.catchup.csly.dao.TouristSentimentSourceDao;

/**
 * 舆情数据来源Service
 * @author wufan
 * @version 2019-08-16
 */
@Service
@Transactional(readOnly = true)
public class TouristSentimentSourceService extends CrudService<TouristSentimentSourceDao, TouristSentimentSource> {

	public TouristSentimentSource get(String id) {
		return super.get(id);
	}
	
	public List<TouristSentimentSource> findList(TouristSentimentSource touristSentimentSource) {
		return super.findList(touristSentimentSource);
	}
	
	public Page<TouristSentimentSource> findPage(Page<TouristSentimentSource> page, TouristSentimentSource touristSentimentSource) {
		return super.findPage(page, touristSentimentSource);
	}
	
	@Transactional(readOnly = false)
	public void save(TouristSentimentSource touristSentimentSource) {
		super.save(touristSentimentSource);
	}
	
	@Transactional(readOnly = false)
	public void delete(TouristSentimentSource touristSentimentSource) {
		super.delete(touristSentimentSource);
	}
	
}