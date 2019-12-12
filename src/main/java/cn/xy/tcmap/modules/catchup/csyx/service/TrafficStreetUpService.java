/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csyx.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.service.CrudService;
import cn.xy.tcmap.modules.catchup.csyx.entity.TrafficStreetUp;
import cn.xy.tcmap.modules.catchup.csyx.dao.TrafficStreetUpDao;

/**
 * 重点道路上报事件Service
 * @author wufan
 * @version 2019-10-18
 */
@Service
@Transactional(readOnly = true)
public class TrafficStreetUpService extends CrudService<TrafficStreetUpDao, TrafficStreetUp> {

	public TrafficStreetUp get(String id) {
		return super.get(id);
	}
	
	public List<TrafficStreetUp> findList(TrafficStreetUp trafficStreetUp) {
		return super.findList(trafficStreetUp);
	}
	
	public Page<TrafficStreetUp> findPage(Page<TrafficStreetUp> page, TrafficStreetUp trafficStreetUp) {
		return super.findPage(page, trafficStreetUp);
	}
	
	@Transactional(readOnly = false)
	public void save(TrafficStreetUp trafficStreetUp) {
		super.save(trafficStreetUp);
	}
	
	@Transactional(readOnly = false)
	public void delete(TrafficStreetUp trafficStreetUp) {
		super.delete(trafficStreetUp);
	}
	
}