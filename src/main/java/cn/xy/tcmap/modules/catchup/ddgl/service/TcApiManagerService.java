/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.ddgl.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.service.CrudService;
import cn.xy.tcmap.modules.catchup.ddgl.entity.TcApiManager;
import cn.xy.tcmap.modules.catchup.ddgl.dao.TcApiManagerDao;

/**
 * 接口调度管理Service
 * @author wh
 * @version 2019-12-06
 */
@Service
@Transactional(readOnly = true)
public class TcApiManagerService extends CrudService<TcApiManagerDao, TcApiManager> {

	public TcApiManager get(String id) {
		return super.get(id);
	}
	
	public List<TcApiManager> findList(TcApiManager tcApiManager) {
		return super.findList(tcApiManager);
	}
	
	public Page<TcApiManager> findPage(Page<TcApiManager> page, TcApiManager tcApiManager) {
		return super.findPage(page, tcApiManager);
	}
	
	@Transactional(readOnly = false)
	public void save(TcApiManager tcApiManager) {
		super.save(tcApiManager);
	}
	
	@Transactional(readOnly = false)
	public void delete(TcApiManager tcApiManager) {
		super.delete(tcApiManager);
	}
	
}