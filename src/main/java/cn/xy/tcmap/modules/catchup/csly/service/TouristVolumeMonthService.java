/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csly.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.service.CrudService;
import cn.xy.tcmap.modules.catchup.csly.entity.TouristVolumeMonth;
import cn.xy.tcmap.modules.catchup.csly.dao.TouristVolumeMonthDao;

/**
 * 按月流客量Service
 * @author wufan
 * @version 2019-08-16
 */
@Service
@Transactional(readOnly = true)
public class TouristVolumeMonthService extends CrudService<TouristVolumeMonthDao, TouristVolumeMonth> {

	public TouristVolumeMonth get(String id) {
		return super.get(id);
	}
	
	public List<TouristVolumeMonth> findList(TouristVolumeMonth touristVolumeMonth) {
		return super.findList(touristVolumeMonth);
	}
	
	public Page<TouristVolumeMonth> findPage(Page<TouristVolumeMonth> page, TouristVolumeMonth touristVolumeMonth) {
		return super.findPage(page, touristVolumeMonth);
	}
	
	@Transactional(readOnly = false)
	public void save(TouristVolumeMonth touristVolumeMonth) {
		super.save(touristVolumeMonth);
	}
	
	@Transactional(readOnly = false)
	public void delete(TouristVolumeMonth touristVolumeMonth) {
		super.delete(touristVolumeMonth);
	}
	
}