/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csly.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.service.CrudService;
import cn.xy.tcmap.modules.catchup.csly.entity.TouristEmotionYear;
import cn.xy.tcmap.modules.catchup.csly.dao.TouristEmotionYearDao;

/**
 * 一年各来源情绪占比Service
 * @author wufan
 * @version 2019-08-16
 */
@Service
@Transactional(readOnly = true)
public class TouristEmotionYearService extends CrudService<TouristEmotionYearDao, TouristEmotionYear> {

	public TouristEmotionYear get(String id) {
		return super.get(id);
	}
	
	public List<TouristEmotionYear> findList(TouristEmotionYear touristEmotionYear) {
		return super.findList(touristEmotionYear);
	}
	
	public Page<TouristEmotionYear> findPage(Page<TouristEmotionYear> page, TouristEmotionYear touristEmotionYear) {
		return super.findPage(page, touristEmotionYear);
	}
	
	@Transactional(readOnly = false)
	public void save(TouristEmotionYear touristEmotionYear) {
		super.save(touristEmotionYear);
	}
	
	@Transactional(readOnly = false)
	public void delete(TouristEmotionYear touristEmotionYear) {
		super.delete(touristEmotionYear);
	}
	
}