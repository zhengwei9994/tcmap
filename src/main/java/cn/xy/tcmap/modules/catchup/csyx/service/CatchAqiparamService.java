/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csyx.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.service.CrudService;
import cn.xy.tcmap.modules.catchup.csyx.entity.CatchAqiparam;
import cn.xy.tcmap.modules.catchup.csyx.dao.CatchAqiparamDao;

/**
 * 空气质量Service
 * @author xuzhou
 * @version 2018-05-24
 */
@Service
@Transactional(readOnly = true)
public class CatchAqiparamService extends CrudService<CatchAqiparamDao, CatchAqiparam> {
	@Autowired
	private CatchAqiparamDao catchAqiparamDao;
	public CatchAqiparam get(String id) {
		return super.get(id);
	}
	
	public List<CatchAqiparam> findList(CatchAqiparam catchAqiparam) {
		return super.findList(catchAqiparam);
	}
	
	public Page<CatchAqiparam> findPage(Page<CatchAqiparam> page, CatchAqiparam catchAqiparam) {
		return super.findPage(page, catchAqiparam);
	}
	public List<CatchAqiparam> aqiparamData(){
		return catchAqiparamDao.aqiparamData();
	}
	@Transactional(readOnly = false)
	public void update(CatchAqiparam catchAqiparam){
		catchAqiparamDao.update(catchAqiparam);
	}
	@Transactional(readOnly = false)
	public void insert(CatchAqiparam catchAqiparam){
		catchAqiparamDao.insert(catchAqiparam);
	}
	@Transactional(readOnly = false)
	public void save(CatchAqiparam catchAqiparam) {
		super.save(catchAqiparam);
	}
	
	@Transactional(readOnly = false)
	public void delete(CatchAqiparam catchAqiparam) {
		super.delete(catchAqiparam);
	}
	
}