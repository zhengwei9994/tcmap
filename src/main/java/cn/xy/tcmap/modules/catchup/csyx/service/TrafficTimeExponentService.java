/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csyx.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.service.CrudService;
import cn.xy.tcmap.modules.catchup.csyx.entity.TrafficTimeExponent;
import cn.xy.tcmap.modules.catchup.csyx.dao.TrafficTimeExponentDao;

/**
 * 交通时间指数Service
 * @author wufan
 * @version 2019-10-18
 */
@Service
@Transactional(readOnly = true)
public class TrafficTimeExponentService extends CrudService<TrafficTimeExponentDao, TrafficTimeExponent> {

	public TrafficTimeExponent get(String id) {
		return super.get(id);
	}
	
	public List<TrafficTimeExponent> findList(TrafficTimeExponent trafficTimeExponent) {
		return super.findList(trafficTimeExponent);
	}
	
	public Page<TrafficTimeExponent> findPage(Page<TrafficTimeExponent> page, TrafficTimeExponent trafficTimeExponent) {
		return super.findPage(page, trafficTimeExponent);
	}
	
	@Transactional(readOnly = false)
	public void save(TrafficTimeExponent trafficTimeExponent) {
		super.save(trafficTimeExponent);
	}
	
	@Transactional(readOnly = false)
	public void delete(TrafficTimeExponent trafficTimeExponent) {
		super.delete(trafficTimeExponent);
	}
	
}