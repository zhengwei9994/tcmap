/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csly.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.service.CrudService;
import cn.xy.tcmap.modules.catchup.csly.entity.TouristEmotionsNegative;
import cn.xy.tcmap.modules.catchup.csly.dao.TouristEmotionsNegativeDao;

/**
 * 负面情绪Service
 * @author wufan
 * @version 2019-08-16
 */
@Service
@Transactional(readOnly = true)
public class TouristEmotionsNegativeService extends CrudService<TouristEmotionsNegativeDao, TouristEmotionsNegative> {

	public TouristEmotionsNegative get(String id) {
		return super.get(id);
	}
	
	public List<TouristEmotionsNegative> findList(TouristEmotionsNegative touristEmotionsNegative) {
		return super.findList(touristEmotionsNegative);
	}
	
	public Page<TouristEmotionsNegative> findPage(Page<TouristEmotionsNegative> page, TouristEmotionsNegative touristEmotionsNegative) {
		return super.findPage(page, touristEmotionsNegative);
	}
	
	@Transactional(readOnly = false)
	public void save(TouristEmotionsNegative touristEmotionsNegative) {
		super.save(touristEmotionsNegative);
	}
	
	@Transactional(readOnly = false)
	public void delete(TouristEmotionsNegative touristEmotionsNegative) {
		super.delete(touristEmotionsNegative);
	}
	
}