/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csly.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.service.CrudService;
import cn.xy.tcmap.modules.catchup.csly.entity.TouristEmotionMonth;
import cn.xy.tcmap.modules.catchup.csly.dao.TouristEmotionMonthDao;

/**
 * 一月各来源情绪占比Service
 * @author wufan
 * @version 2019-08-16
 */
@Service
@Transactional(readOnly = true)
public class TouristEmotionMonthService extends CrudService<TouristEmotionMonthDao, TouristEmotionMonth> {

	public TouristEmotionMonth get(String id) {
		return super.get(id);
	}
	
	public List<TouristEmotionMonth> findList(TouristEmotionMonth touristEmotionMonth) {
		return super.findList(touristEmotionMonth);
	}
	
	public Page<TouristEmotionMonth> findPage(Page<TouristEmotionMonth> page, TouristEmotionMonth touristEmotionMonth) {
		return super.findPage(page, touristEmotionMonth);
	}
	
	@Transactional(readOnly = false)
	public void save(TouristEmotionMonth touristEmotionMonth) {
		super.save(touristEmotionMonth);
	}
	
	@Transactional(readOnly = false)
	public void delete(TouristEmotionMonth touristEmotionMonth) {
		super.delete(touristEmotionMonth);
	}
	
}