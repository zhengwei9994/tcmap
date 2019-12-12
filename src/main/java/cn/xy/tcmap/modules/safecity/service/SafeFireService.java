/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.safecity.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.service.CrudService;
import cn.xy.tcmap.modules.safecity.entity.SafeFire;
import cn.xy.tcmap.modules.safecity.dao.SafeFireDao;

/**
 * 消防安全Service
 * @author tuo
 * @version 2019-12-05
 */
@Service
@Transactional(readOnly = true)
public class SafeFireService extends CrudService<SafeFireDao, SafeFire> {

	public SafeFire get(String id) {
		return super.get(id);
	}
	
	public List<SafeFire> findList(SafeFire safeFire) {
		return super.findList(safeFire);
	}
	
	public Page<SafeFire> findPage(Page<SafeFire> page, SafeFire safeFire) {
		return super.findPage(page, safeFire);
	}
	
	@Transactional(readOnly = false)
	public void save(SafeFire safeFire) {
		super.save(safeFire);
	}
	
	@Transactional(readOnly = false)
	public void delete(SafeFire safeFire) {
		super.delete(safeFire);
	}
	
}