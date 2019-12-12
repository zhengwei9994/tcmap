/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csyx.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.service.CrudService;
import cn.xy.tcmap.modules.catchup.csyx.entity.LivePovertyReason;
import cn.xy.tcmap.modules.catchup.csyx.dao.LivePovertyReasonDao;

/**
 * 致贫原因Service
 * @author tuo
 * @version 2019-08-14
 */
@Service
@Transactional(readOnly = true)
public class LivePovertyReasonService extends CrudService<LivePovertyReasonDao, LivePovertyReason> {

	public LivePovertyReason get(String id) {
		return super.get(id);
	}
	
	public List<LivePovertyReason> findList(LivePovertyReason livePovertyReason) {
		return super.findList(livePovertyReason);
	}
	
	public Page<LivePovertyReason> findPage(Page<LivePovertyReason> page, LivePovertyReason livePovertyReason) {
		return super.findPage(page, livePovertyReason);
	}
	
	@Transactional(readOnly = false)
	public void save(LivePovertyReason livePovertyReason) {
		super.save(livePovertyReason);
	}
	
	@Transactional(readOnly = false)
	public void delete(LivePovertyReason livePovertyReason) {
		super.delete(livePovertyReason);
	}
	
}