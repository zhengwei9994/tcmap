/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csyx.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.service.CrudService;
import cn.xy.tcmap.modules.catchup.csyx.entity.LiveMultipleCityManage;
import cn.xy.tcmap.modules.catchup.csyx.dao.LiveMultipleCityManageDao;

/**
 * 城市综合治理Service
 * @author tuo
 * @version 2019-08-14
 */
@Service
@Transactional(readOnly = true)
public class LiveMultipleCityManageService extends CrudService<LiveMultipleCityManageDao, LiveMultipleCityManage> {

	public LiveMultipleCityManage get(String id) {
		return super.get(id);
	}
	
	public List<LiveMultipleCityManage> findList(LiveMultipleCityManage liveMultipleCityManage) {
		return super.findList(liveMultipleCityManage);
	}
	
	public Page<LiveMultipleCityManage> findPage(Page<LiveMultipleCityManage> page, LiveMultipleCityManage liveMultipleCityManage) {
		return super.findPage(page, liveMultipleCityManage);
	}
	
	@Transactional(readOnly = false)
	public void save(LiveMultipleCityManage liveMultipleCityManage) {
		super.save(liveMultipleCityManage);
	}
	
	@Transactional(readOnly = false)
	public void delete(LiveMultipleCityManage liveMultipleCityManage) {
		super.delete(liveMultipleCityManage);
	}
	
}