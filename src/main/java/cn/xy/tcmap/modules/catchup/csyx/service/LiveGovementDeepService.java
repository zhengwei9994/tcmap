/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csyx.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.service.CrudService;
import cn.xy.tcmap.modules.catchup.csyx.entity.LiveGovementDeep;
import cn.xy.tcmap.modules.catchup.csyx.dao.LiveGovementDeepDao;

/**
 * 办理深度Service
 * @author wufan
 * @version 2019-10-14
 */
@Service
@Transactional(readOnly = true)
public class LiveGovementDeepService extends CrudService<LiveGovementDeepDao, LiveGovementDeep> {

	public LiveGovementDeep get(String id) {
		return super.get(id);
	}
	
	public List<LiveGovementDeep> findList(LiveGovementDeep liveGovementDeep) {
		return super.findList(liveGovementDeep);
	}
	
	public Page<LiveGovementDeep> findPage(Page<LiveGovementDeep> page, LiveGovementDeep liveGovementDeep) {
		return super.findPage(page, liveGovementDeep);
	}
	
	@Transactional(readOnly = false)
	public void save(LiveGovementDeep liveGovementDeep) {
		super.save(liveGovementDeep);
	}
	
	@Transactional(readOnly = false)
	public void delete(LiveGovementDeep liveGovementDeep) {
		super.delete(liveGovementDeep);
	}
	
}