/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csly.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.service.CrudService;
import cn.xy.tcmap.modules.catchup.csly.entity.TouristWcNegative;
import cn.xy.tcmap.modules.catchup.csly.dao.TouristWcNegativeDao;

/**
 * 厕所负面词云Service
 * @author wufan
 * @version 2019-08-16
 */
@Service
@Transactional(readOnly = true)
public class TouristWcNegativeService extends CrudService<TouristWcNegativeDao, TouristWcNegative> {

	public TouristWcNegative get(String id) {
		return super.get(id);
	}
	
	public List<TouristWcNegative> findList(TouristWcNegative touristWcNegative) {
		return super.findList(touristWcNegative);
	}
	
	public Page<TouristWcNegative> findPage(Page<TouristWcNegative> page, TouristWcNegative touristWcNegative) {
		return super.findPage(page, touristWcNegative);
	}
	
	@Transactional(readOnly = false)
	public void save(TouristWcNegative touristWcNegative) {
		super.save(touristWcNegative);
	}
	
	@Transactional(readOnly = false)
	public void delete(TouristWcNegative touristWcNegative) {
		super.delete(touristWcNegative);
	}
	
}