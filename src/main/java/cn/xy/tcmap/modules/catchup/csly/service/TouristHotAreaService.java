/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csly.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.service.CrudService;
import cn.xy.tcmap.modules.catchup.csly.entity.TouristHotArea;
import cn.xy.tcmap.modules.catchup.csly.dao.TouristHotAreaDao;

/**
 * 地区游客密度Service
 * @author tuo
 * @version 2019-09-10
 */
@Service
@Transactional(readOnly = true)
public class TouristHotAreaService extends CrudService<TouristHotAreaDao, TouristHotArea> {

	public TouristHotArea get(String id) {
		return super.get(id);
	}
	
	public List<TouristHotArea> findList(TouristHotArea touristHotArea) {
		return super.findList(touristHotArea);
	}
	
	public Page<TouristHotArea> findPage(Page<TouristHotArea> page, TouristHotArea touristHotArea) {
		return super.findPage(page, touristHotArea);
	}
	
	@Transactional(readOnly = false)
	public void save(TouristHotArea touristHotArea) {
		super.save(touristHotArea);
	}
	
	@Transactional(readOnly = false)
	public void delete(TouristHotArea touristHotArea) {
		super.delete(touristHotArea);
	}
	
}