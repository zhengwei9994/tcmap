/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csly.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.service.CrudService;
import cn.xy.tcmap.modules.catchup.csly.entity.TouristsSources;
import cn.xy.tcmap.modules.catchup.csly.dao.TouristsSourcesDao;

/**
 * 游客来源地Service
 * @author wufan
 * @version 2019-08-16
 */
@Service
@Transactional(readOnly = true)
public class TouristsSourcesService extends CrudService<TouristsSourcesDao, TouristsSources> {

	public TouristsSources get(String id) {
		return super.get(id);
	}
	
	public List<TouristsSources> findList(TouristsSources touristsSources) {
		return super.findList(touristsSources);
	}
	
	public Page<TouristsSources> findPage(Page<TouristsSources> page, TouristsSources touristsSources) {
		return super.findPage(page, touristsSources);
	}
	
	@Transactional(readOnly = false)
	public void save(TouristsSources touristsSources) {
		super.save(touristsSources);
	}
	
	@Transactional(readOnly = false)
	public void delete(TouristsSources touristsSources) {
		super.delete(touristsSources);
	}
	
}