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
import cn.xy.tcmap.modules.catchup.wlkj.entity.CatchHotPublic;
import cn.xy.tcmap.modules.catchup.wlkj.dao.CatchHotPublicDao;

/**
 * 热点舆情Service
 * @author xuzhou
 * @version 2018-05-28
 */
@Service
@Transactional(readOnly = true)
public class CatchHotPublicService extends CrudService<CatchHotPublicDao, CatchHotPublic> {
	@Autowired
	private CatchHotPublicDao catchHotPublicDao;
	public CatchHotPublic get(String id) {
		return super.get(id);
	}
	
	public List<CatchHotPublic> findList(CatchHotPublic catchHotPublic) {
		return super.findList(catchHotPublic);
	}
	
	public Page<CatchHotPublic> findPage(Page<CatchHotPublic> page, CatchHotPublic catchHotPublic) {
		return super.findPage(page, catchHotPublic);
	}
	@Transactional(readOnly = false)
	public void insert(CatchHotPublic catchHotPublic){
		catchHotPublicDao.insert(catchHotPublic);
	}
	@Transactional(readOnly = false)
	public void update(CatchHotPublic catchHotPublic){
		catchHotPublicDao.update(catchHotPublic);
	}
	public List<CatchHotPublic> hotData(){
		return  catchHotPublicDao.hotData();
	}
	@Transactional(readOnly = false)
	public void save(CatchHotPublic catchHotPublic) {
		super.save(catchHotPublic);
	}
	
	@Transactional(readOnly = false)
	public void delete(CatchHotPublic catchHotPublic) {
		super.delete(catchHotPublic);
	}
	
}