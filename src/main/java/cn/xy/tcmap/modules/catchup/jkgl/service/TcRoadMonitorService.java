/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.jkgl.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.service.CrudService;
import cn.xy.tcmap.modules.catchup.jkgl.entity.TcRoadMonitor;
import cn.xy.tcmap.modules.catchup.jkgl.dao.TcRoadMonitorDao;

/**
 * 路况监控Service
 * @author wufan
 * @version 2019-12-05
 */
@Service
@Transactional(readOnly = true)
public class TcRoadMonitorService extends CrudService<TcRoadMonitorDao, TcRoadMonitor> {

	public TcRoadMonitor get(String id) {
		return super.get(id);
	}
	
	public List<TcRoadMonitor> findList(TcRoadMonitor tcRoadMonitor) {
		return super.findList(tcRoadMonitor);
	}
	
	public Page<TcRoadMonitor> findPage(Page<TcRoadMonitor> page, TcRoadMonitor tcRoadMonitor) {
		return super.findPage(page, tcRoadMonitor);
	}
	
	@Transactional(readOnly = false)
	public void save(TcRoadMonitor tcRoadMonitor) {
		super.save(tcRoadMonitor);
	}
	
	@Transactional(readOnly = false)
	public void delete(TcRoadMonitor tcRoadMonitor) {
		super.delete(tcRoadMonitor);
	}
	
}