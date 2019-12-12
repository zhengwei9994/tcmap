/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.safecity.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.service.CrudService;
import cn.xy.tcmap.modules.safecity.entity.SafetyProfile;
import cn.xy.tcmap.modules.safecity.dao.SafetyProfileDao;

/**
 * 安全概况Service
 * @author tuo
 * @version 2019-12-04
 */
@Service
@Transactional(readOnly = true)
public class SafetyProfileService extends CrudService<SafetyProfileDao, SafetyProfile> {

	public SafetyProfile get(String id) {
		return super.get(id);
	}
	
	public List<SafetyProfile> findList(SafetyProfile safetyProfile) {
		return super.findList(safetyProfile);
	}
	
	public Page<SafetyProfile> findPage(Page<SafetyProfile> page, SafetyProfile safetyProfile) {
		return super.findPage(page, safetyProfile);
	}
	
	@Transactional(readOnly = false)
	public void save(SafetyProfile safetyProfile) {
		super.save(safetyProfile);
	}
	
	@Transactional(readOnly = false)
	public void delete(SafetyProfile safetyProfile) {
		super.delete(safetyProfile);
	}
	
}