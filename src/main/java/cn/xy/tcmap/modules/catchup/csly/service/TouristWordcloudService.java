/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csly.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.service.CrudService;
import cn.xy.tcmap.modules.catchup.csly.entity.TouristWordcloud;
import cn.xy.tcmap.modules.catchup.csly.dao.TouristWordcloudDao;

/**
 * 词云Service
 * @author wufan
 * @version 2019-08-16
 */
@Service
@Transactional(readOnly = true)
public class TouristWordcloudService extends CrudService<TouristWordcloudDao, TouristWordcloud> {

	public TouristWordcloud get(String id) {
		return super.get(id);
	}
	
	public List<TouristWordcloud> findList(TouristWordcloud touristWordcloud) {
		return super.findList(touristWordcloud);
	}
	
	public Page<TouristWordcloud> findPage(Page<TouristWordcloud> page, TouristWordcloud touristWordcloud) {
		return super.findPage(page, touristWordcloud);
	}
	
	@Transactional(readOnly = false)
	public void save(TouristWordcloud touristWordcloud) {
		super.save(touristWordcloud);
	}
	
	@Transactional(readOnly = false)
	public void delete(TouristWordcloud touristWordcloud) {
		super.delete(touristWordcloud);
	}
	
}