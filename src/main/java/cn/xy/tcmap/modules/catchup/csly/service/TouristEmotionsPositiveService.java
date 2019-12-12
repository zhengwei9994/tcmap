/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csly.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.service.CrudService;
import cn.xy.tcmap.modules.catchup.csly.entity.TouristEmotionsPositive;
import cn.xy.tcmap.modules.catchup.csly.dao.TouristEmotionsPositiveDao;

/**
 * 正面情绪Service
 * @author wufan
 * @version 2019-08-16
 */
@Service
@Transactional(readOnly = true)
public class TouristEmotionsPositiveService extends CrudService<TouristEmotionsPositiveDao, TouristEmotionsPositive> {

	public TouristEmotionsPositive get(String id) {
		return super.get(id);
	}
	
	public List<TouristEmotionsPositive> findList(TouristEmotionsPositive touristEmotionsPositive) {
		return super.findList(touristEmotionsPositive);
	}
	
	public Page<TouristEmotionsPositive> findPage(Page<TouristEmotionsPositive> page, TouristEmotionsPositive touristEmotionsPositive) {
		return super.findPage(page, touristEmotionsPositive);
	}
	
	@Transactional(readOnly = false)
	public void save(TouristEmotionsPositive touristEmotionsPositive) {
		super.save(touristEmotionsPositive);
	}
	
	@Transactional(readOnly = false)
	public void delete(TouristEmotionsPositive touristEmotionsPositive) {
		super.delete(touristEmotionsPositive);
	}
	
}