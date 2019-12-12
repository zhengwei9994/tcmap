/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csyx.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.service.CrudService;
import cn.xy.tcmap.modules.catchup.csyx.entity.LiveGovementSituation;
import cn.xy.tcmap.modules.catchup.csyx.dao.LiveGovementSituationDao;

/**
 * 网上办理情况Service
 * @author wufan
 * @version 2019-10-14
 */
@Service
@Transactional(readOnly = true)
public class LiveGovementSituationService extends CrudService<LiveGovementSituationDao, LiveGovementSituation> {

	public LiveGovementSituation get(String id) {
		return super.get(id);
	}
	
	public List<LiveGovementSituation> findList(LiveGovementSituation liveGovementSituation) {
		return super.findList(liveGovementSituation);
	}
	
	public Page<LiveGovementSituation> findPage(Page<LiveGovementSituation> page, LiveGovementSituation liveGovementSituation) {
		return super.findPage(page, liveGovementSituation);
	}
	
	@Transactional(readOnly = false)
	public void save(LiveGovementSituation liveGovementSituation) {
		super.save(liveGovementSituation);
	}
	
	@Transactional(readOnly = false)
	public void delete(LiveGovementSituation liveGovementSituation) {
		super.delete(liveGovementSituation);
	}
	
}