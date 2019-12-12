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
import cn.xy.tcmap.modules.catchup.wlkj.entity.CatchEarlyWarning;
import cn.xy.tcmap.modules.catchup.wlkj.dao.CatchEarlyWarningDao;

/**
 * 红色预警Service
 * @author xuzhou
 * @version 2018-05-28
 */
@Service
@Transactional(readOnly = true)
public class CatchEarlyWarningService extends CrudService<CatchEarlyWarningDao, CatchEarlyWarning> {
	@Autowired
	private CatchEarlyWarningDao catchEarlyWarningDao;
	public CatchEarlyWarning get(String id) {
		return super.get(id);
	}
	
	public List<CatchEarlyWarning> findList(CatchEarlyWarning catchEarlyWarning) {
		return super.findList(catchEarlyWarning);
	}
	public List<CatchEarlyWarning> earlyData(CatchEarlyWarning catchEarlyWarning){
		return catchEarlyWarningDao.earlyData(catchEarlyWarning);
	}
	public Page<CatchEarlyWarning> findPage(Page<CatchEarlyWarning> page, CatchEarlyWarning catchEarlyWarning) {
		return super.findPage(page, catchEarlyWarning);
	}
	
	@Transactional(readOnly = false)
	public void save(CatchEarlyWarning catchEarlyWarning) {
		super.save(catchEarlyWarning);
	}
	
	@Transactional(readOnly = false)
	public void delete(CatchEarlyWarning catchEarlyWarning) {
		super.delete(catchEarlyWarning);
	}
	
}