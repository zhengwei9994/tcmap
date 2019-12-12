/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csly.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.service.CrudService;
import cn.xy.tcmap.modules.catchup.csly.entity.TouristInformation;
import cn.xy.tcmap.modules.catchup.csly.dao.TouristInformationDao;

/**
 * 景区资讯排行Service
 * @author wufan
 * @version 2019-10-23
 */
@Service
@Transactional(readOnly = true)
public class TouristInformationService extends CrudService<TouristInformationDao, TouristInformation> {

	public TouristInformation get(String id) {
		return super.get(id);
	}
	
	public List<TouristInformation> findList(TouristInformation touristInformation) {
		return super.findList(touristInformation);
	}
	
	public Page<TouristInformation> findPage(Page<TouristInformation> page, TouristInformation touristInformation) {
		return super.findPage(page, touristInformation);
	}
	
	@Transactional(readOnly = false)
	public void save(TouristInformation touristInformation) {
		super.save(touristInformation);
	}
	
	@Transactional(readOnly = false)
	public void delete(TouristInformation touristInformation) {
		super.delete(touristInformation);
	}
	
}