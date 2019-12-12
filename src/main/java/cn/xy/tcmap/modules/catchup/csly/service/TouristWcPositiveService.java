/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csly.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.service.CrudService;
import cn.xy.tcmap.modules.catchup.csly.entity.TouristWcPositive;
import cn.xy.tcmap.modules.catchup.csly.dao.TouristWcPositiveDao;

/**
 * 厕所正面词云Service
 * @author wufan
 * @version 2019-08-16
 */
@Service
@Transactional(readOnly = true)
public class TouristWcPositiveService extends CrudService<TouristWcPositiveDao, TouristWcPositive> {

	public TouristWcPositive get(String id) {
		return super.get(id);
	}
	
	public List<TouristWcPositive> findList(TouristWcPositive touristWcPositive) {
		return super.findList(touristWcPositive);
	}
	
	public Page<TouristWcPositive> findPage(Page<TouristWcPositive> page, TouristWcPositive touristWcPositive) {
		return super.findPage(page, touristWcPositive);
	}
	
	@Transactional(readOnly = false)
	public void save(TouristWcPositive touristWcPositive) {
		super.save(touristWcPositive);
	}
	
	@Transactional(readOnly = false)
	public void delete(TouristWcPositive touristWcPositive) {
		super.delete(touristWcPositive);
	}
	
}