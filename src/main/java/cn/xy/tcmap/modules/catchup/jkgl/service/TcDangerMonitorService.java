/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.jkgl.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.service.CrudService;
import cn.xy.tcmap.modules.catchup.jkgl.entity.TcDangerMonitor;
import cn.xy.tcmap.modules.catchup.jkgl.dao.TcDangerMonitorDao;

/**
 * 危险源监控Service
 * @author wufan
 * @version 2019-12-05
 */
@Service
@Transactional(readOnly = true)
public class TcDangerMonitorService extends CrudService<TcDangerMonitorDao, TcDangerMonitor> {

	public TcDangerMonitor get(String id) {
		return super.get(id);
	}
	
	public List<TcDangerMonitor> findList(TcDangerMonitor tcDangerMonitor) {
		return super.findList(tcDangerMonitor);
	}
	
	public Page<TcDangerMonitor> findPage(Page<TcDangerMonitor> page, TcDangerMonitor tcDangerMonitor) {
		return super.findPage(page, tcDangerMonitor);
	}
	
	@Transactional(readOnly = false)
	public void save(TcDangerMonitor tcDangerMonitor) {
		super.save(tcDangerMonitor);
	}
	
	@Transactional(readOnly = false)
	public void delete(TcDangerMonitor tcDangerMonitor) {
		super.delete(tcDangerMonitor);
	}
	
}