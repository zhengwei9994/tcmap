/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csly.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.service.CrudService;
import cn.xy.tcmap.modules.catchup.csly.entity.TouristSatisfaction;
import cn.xy.tcmap.modules.catchup.csly.dao.TouristSatisfactionDao;

/**
 * 满意度雷达图Service
 * @author wufan
 * @version 2019-08-16
 */
@Service
@Transactional(readOnly = true)
public class TouristSatisfactionService extends CrudService<TouristSatisfactionDao, TouristSatisfaction> {

	public TouristSatisfaction get(String id) {
		return super.get(id);
	}
	
	public List<TouristSatisfaction> findList(TouristSatisfaction touristSatisfaction) {
		return super.findList(touristSatisfaction);
	}
	
	public Page<TouristSatisfaction> findPage(Page<TouristSatisfaction> page, TouristSatisfaction touristSatisfaction) {
		return super.findPage(page, touristSatisfaction);
	}
	
	@Transactional(readOnly = false)
	public void save(TouristSatisfaction touristSatisfaction) {
		super.save(touristSatisfaction);
	}
	
	@Transactional(readOnly = false)
	public void delete(TouristSatisfaction touristSatisfaction) {
		super.delete(touristSatisfaction);
	}
	
}