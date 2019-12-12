/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csly.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.service.CrudService;
import cn.xy.tcmap.modules.catchup.csly.entity.TouristVolumeTime;
import cn.xy.tcmap.modules.catchup.csly.dao.TouristVolumeTimeDao;

/**
 * 当日游客量Service
 * @author wufan
 * @version 2019-08-16
 */
@Service
@Transactional(readOnly = true)
public class TouristVolumeTimeService extends CrudService<TouristVolumeTimeDao, TouristVolumeTime> {

	public TouristVolumeTime get(String id) {
		return super.get(id);
	}
	
	public List<TouristVolumeTime> findList(TouristVolumeTime touristVolumeTime) {
		return super.findList(touristVolumeTime);
	}
	
	public Page<TouristVolumeTime> findPage(Page<TouristVolumeTime> page, TouristVolumeTime touristVolumeTime) {
		return super.findPage(page, touristVolumeTime);
	}
	
	@Transactional(readOnly = false)
	public void save(TouristVolumeTime touristVolumeTime) {
		super.save(touristVolumeTime);
	}
	
	@Transactional(readOnly = false)
	public void delete(TouristVolumeTime touristVolumeTime) {
		super.delete(touristVolumeTime);
	}
	
}