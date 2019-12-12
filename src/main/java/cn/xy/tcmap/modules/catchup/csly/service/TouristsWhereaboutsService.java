/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csly.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.service.CrudService;
import cn.xy.tcmap.modules.catchup.csly.entity.TouristsWhereabouts;
import cn.xy.tcmap.modules.catchup.csly.dao.TouristsWhereaboutsDao;

/**
 * 游客去向地Service
 * @author wufan
 * @version 2019-08-16
 */
@Service
@Transactional(readOnly = true)
public class TouristsWhereaboutsService extends CrudService<TouristsWhereaboutsDao, TouristsWhereabouts> {

	public TouristsWhereabouts get(String id) {
		return super.get(id);
	}
	
	public List<TouristsWhereabouts> findList(TouristsWhereabouts touristsWhereabouts) {
		return super.findList(touristsWhereabouts);
	}
	
	public Page<TouristsWhereabouts> findPage(Page<TouristsWhereabouts> page, TouristsWhereabouts touristsWhereabouts) {
		return super.findPage(page, touristsWhereabouts);
	}
	
	@Transactional(readOnly = false)
	public void save(TouristsWhereabouts touristsWhereabouts) {
		super.save(touristsWhereabouts);
	}
	
	@Transactional(readOnly = false)
	public void delete(TouristsWhereabouts touristsWhereabouts) {
		super.delete(touristsWhereabouts);
	}
	
}