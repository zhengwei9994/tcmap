/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.ddgl.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.service.CrudService;
import cn.xy.tcmap.modules.catchup.ddgl.entity.TcSyserviceMananger;
import cn.xy.tcmap.modules.catchup.ddgl.dao.TcSyserviceManangerDao;

/**
 * 服务系统管理Service
 * @author wh
 * @version 2019-12-06
 */
@Service
@Transactional(readOnly = true)
public class TcSyserviceManangerService extends CrudService<TcSyserviceManangerDao, TcSyserviceMananger> {

	public TcSyserviceMananger get(String id) {
		return super.get(id);
	}
	
	public List<TcSyserviceMananger> findList(TcSyserviceMananger tcSyserviceMananger) {
		return super.findList(tcSyserviceMananger);
	}
	
	public Page<TcSyserviceMananger> findPage(Page<TcSyserviceMananger> page, TcSyserviceMananger tcSyserviceMananger) {
		return super.findPage(page, tcSyserviceMananger);
	}
	
	@Transactional(readOnly = false)
	public void save(TcSyserviceMananger tcSyserviceMananger) {
		super.save(tcSyserviceMananger);
	}
	
	@Transactional(readOnly = false)
	public void delete(TcSyserviceMananger tcSyserviceMananger) {
		super.delete(tcSyserviceMananger);
	}
	
}