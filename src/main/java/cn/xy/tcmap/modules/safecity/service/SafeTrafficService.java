/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.safecity.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.service.CrudService;
import cn.xy.tcmap.modules.safecity.entity.SafeTraffic;
import cn.xy.tcmap.modules.safecity.dao.SafeTrafficDao;

/**
 * 交通违法案件Service
 * @author tuo
 * @version 2019-12-05
 */
@Service
@Transactional(readOnly = true)
public class SafeTrafficService extends CrudService<SafeTrafficDao, SafeTraffic> {

	public SafeTraffic get(String id) {
		return super.get(id);
	}
	
	public List<SafeTraffic> findList(SafeTraffic safeTraffic) {
		return super.findList(safeTraffic);
	}
	
	public Page<SafeTraffic> findPage(Page<SafeTraffic> page, SafeTraffic safeTraffic) {
		return super.findPage(page, safeTraffic);
	}
	
	@Transactional(readOnly = false)
	public void save(SafeTraffic safeTraffic) {
		super.save(safeTraffic);
	}
	
	@Transactional(readOnly = false)
	public void delete(SafeTraffic safeTraffic) {
		super.delete(safeTraffic);
	}
	
}