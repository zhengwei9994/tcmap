/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csyx.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.service.CrudService;
import cn.xy.tcmap.modules.catchup.csyx.entity.LivePovertyType;
import cn.xy.tcmap.modules.catchup.csyx.dao.LivePovertyTypeDao;

/**
 * 贫困类型Service
 * @author tuo
 * @version 2019-08-14
 */
@Service
@Transactional(readOnly = true)
public class LivePovertyTypeService extends CrudService<LivePovertyTypeDao, LivePovertyType> {

	public LivePovertyType get(String id) {
		return super.get(id);
	}
	
	public List<LivePovertyType> findList(LivePovertyType livePovertyType) {
		return super.findList(livePovertyType);
	}
	
	public Page<LivePovertyType> findPage(Page<LivePovertyType> page, LivePovertyType livePovertyType) {
		return super.findPage(page, livePovertyType);
	}
	
	@Transactional(readOnly = false)
	public void save(LivePovertyType livePovertyType) {
		super.save(livePovertyType);
	}
	
	@Transactional(readOnly = false)
	public void delete(LivePovertyType livePovertyType) {
		super.delete(livePovertyType);
	}
	
}