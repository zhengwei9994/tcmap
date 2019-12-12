/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.safecity.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.service.CrudService;
import cn.xy.tcmap.modules.safecity.entity.SafeCity;
import cn.xy.tcmap.modules.safecity.dao.SafeCityDao;

/**
 * 平安城市Service
 * @author tuo
 * @version 2019-12-04
 */
@Service
@Transactional(readOnly = true)
public class SafeCityService extends CrudService<SafeCityDao, SafeCity> {

	public SafeCity get(String id) {
		return super.get(id);
	}
	
	public List<SafeCity> findList(SafeCity safeCity) {
		return super.findList(safeCity);
	}

	
	public Page<SafeCity> findPage(Page<SafeCity> page, SafeCity safeCity) {
		return super.findPage(page, safeCity);
	}
	
	@Transactional(readOnly = false)
	public void save(SafeCity safeCity) {
		super.save(safeCity);
	}
	
	@Transactional(readOnly = false)
	public void delete(SafeCity safeCity) {
		super.delete(safeCity);
	}
	
}