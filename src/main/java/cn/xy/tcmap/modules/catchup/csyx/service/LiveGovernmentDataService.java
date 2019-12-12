/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csyx.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.service.CrudService;
import cn.xy.tcmap.modules.catchup.csyx.entity.LiveGovernmentData;
import cn.xy.tcmap.modules.catchup.csyx.dao.LiveGovernmentDataDao;

/**
 * 政务服务Service
 * @author wufan
 * @version 2019-10-14
 */
@Service
@Transactional(readOnly = true)
public class LiveGovernmentDataService extends CrudService<LiveGovernmentDataDao, LiveGovernmentData> {

	public LiveGovernmentData get(String id) {
		return super.get(id);
	}
	
	public List<LiveGovernmentData> findList(LiveGovernmentData liveGovernmentData) {
		return super.findList(liveGovernmentData);
	}
	
	public Page<LiveGovernmentData> findPage(Page<LiveGovernmentData> page, LiveGovernmentData liveGovernmentData) {
		return super.findPage(page, liveGovernmentData);
	}
	
	@Transactional(readOnly = false)
	public void save(LiveGovernmentData liveGovernmentData) {
		super.save(liveGovernmentData);
	}
	
	@Transactional(readOnly = false)
	public void delete(LiveGovernmentData liveGovernmentData) {
		super.delete(liveGovernmentData);
	}
	
}