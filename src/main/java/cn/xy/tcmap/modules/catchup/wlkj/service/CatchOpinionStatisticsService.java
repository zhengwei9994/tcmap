/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.wlkj.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.service.CrudService;
import cn.xy.tcmap.modules.catchup.wlkj.entity.CatchOpinionStatistics;
import cn.xy.tcmap.modules.catchup.wlkj.dao.CatchOpinionStatisticsDao;

/**
 * 舆情统计Service
 * @author xuzhou
 * @version 2018-05-28
 */
@Service
@Transactional(readOnly = true)
public class CatchOpinionStatisticsService extends CrudService<CatchOpinionStatisticsDao, CatchOpinionStatistics> {
	@Autowired
	private CatchOpinionStatisticsDao catchOpinionStatisticsDao;
	public CatchOpinionStatistics get(String id) {
		return super.get(id);
	}
	
	public List<CatchOpinionStatistics> findList(CatchOpinionStatistics catchOpinionStatistics) {
		return super.findList(catchOpinionStatistics);
	}
	public List<CatchOpinionStatistics> opinionStatistics(CatchOpinionStatistics catchOpinionStatistics){
		return catchOpinionStatisticsDao.opinionStatistics(catchOpinionStatistics);
	}
	public Page<CatchOpinionStatistics> findPage(Page<CatchOpinionStatistics> page, CatchOpinionStatistics catchOpinionStatistics) {
		return super.findPage(page, catchOpinionStatistics);
	}
	
	@Transactional(readOnly = false)
	public void save(CatchOpinionStatistics catchOpinionStatistics) {
		super.save(catchOpinionStatistics);
	}
	
	@Transactional(readOnly = false)
	public void delete(CatchOpinionStatistics catchOpinionStatistics) {
		super.delete(catchOpinionStatistics);
	}
	
}