/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csly.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.service.CrudService;
import cn.xy.tcmap.modules.catchup.csly.entity.TouristNetData;
import cn.xy.tcmap.modules.catchup.csly.dao.TouristNetDataDao;

/**
 * 互联网数据流Service
 * @author wufan
 * @version 2019-08-16
 */
@Service
@Transactional(readOnly = true)
public class TouristNetDataService extends CrudService<TouristNetDataDao, TouristNetData> {

	public TouristNetData get(String id) {
		return super.get(id);
	}
	
	public List<TouristNetData> findList(TouristNetData touristNetData) {
		return super.findList(touristNetData);
	}
	
	public Page<TouristNetData> findPage(Page<TouristNetData> page, TouristNetData touristNetData) {
		return super.findPage(page, touristNetData);
	}
	
	@Transactional(readOnly = false)
	public void save(TouristNetData touristNetData) {
		super.save(touristNetData);
	}
	
	@Transactional(readOnly = false)
	public void delete(TouristNetData touristNetData) {
		super.delete(touristNetData);
	}
	
}