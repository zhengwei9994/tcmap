/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csly.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.service.CrudService;
import cn.xy.tcmap.modules.catchup.csly.entity.TouristInformationOnly;
import cn.xy.tcmap.modules.catchup.csly.dao.TouristInformationOnlyDao;

/**
 * 单一景区资讯Service
 * @author tuo
 * @version 2019-10-23
 */
@Service
@Transactional(readOnly = true)
public class TouristInformationOnlyService extends CrudService<TouristInformationOnlyDao, TouristInformationOnly> {

	public TouristInformationOnly get(String id) {
		return super.get(id);
	}
	
	public List<TouristInformationOnly> findList(TouristInformationOnly touristInformationOnly) {
		return super.findList(touristInformationOnly);
	}
	
	public Page<TouristInformationOnly> findPage(Page<TouristInformationOnly> page, TouristInformationOnly touristInformationOnly) {
		return super.findPage(page, touristInformationOnly);
	}
	
	@Transactional(readOnly = false)
	public void save(TouristInformationOnly touristInformationOnly) {
		super.save(touristInformationOnly);
	}
	
	@Transactional(readOnly = false)
	public void delete(TouristInformationOnly touristInformationOnly) {
		super.delete(touristInformationOnly);
	}
	
}