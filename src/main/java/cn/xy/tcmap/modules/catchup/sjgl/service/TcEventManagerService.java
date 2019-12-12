/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.sjgl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.service.CrudService;
import cn.xy.tcmap.modules.catchup.sjgl.entity.TcEventManager;
import cn.xy.tcmap.modules.catchup.sjgl.dao.TcEventManagerDao;

/**
 * 事件数据管理Service
 * @author wh
 * @version 2019-12-05
 */
@Service
@Transactional(readOnly = true)
public class TcEventManagerService extends CrudService<TcEventManagerDao, TcEventManager> {

	@Autowired
	private TcEventManagerDao tcEventManagerDao;
	@Transactional(readOnly = false)
	public int update(TcEventManager tcEventManager){
		return tcEventManagerDao.update(tcEventManager);
	}
	public TcEventManager get(String id) {
		return super.get(id);
	}

	public List<TcEventManager> findManagerType(){
	return 	tcEventManagerDao.findEventManager();
	}
	public List<TcEventManager> findList(TcEventManager tcEventManager) {
		return super.findList(tcEventManager);
	}
	@Transactional(readOnly = false)
	public int deleteByType(TcEventManager tcEventManager){
            return tcEventManagerDao.deleteByType(tcEventManager);
	}
	public Page<TcEventManager> findPage(Page<TcEventManager> page, TcEventManager tcEventManager) {
		return super.findPage(page, tcEventManager);
	}
	
	@Transactional(readOnly = false)
	public void save(TcEventManager tcEventManager) {
		super.save(tcEventManager);
	}
	
	@Transactional(readOnly = false)
	public void delete(TcEventManager tcEventManager) {
		super.delete(tcEventManager);
	}
	
}