/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csyx.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.service.CrudService;
import cn.xy.tcmap.modules.catchup.csyx.entity.LiveHelpAudi;
import cn.xy.tcmap.modules.catchup.csyx.dao.LiveHelpAudiDao;

/**
 * 帮扶受众Service
 * @author tuo
 * @version 2019-08-14
 */
@Service
@Transactional(readOnly = true)
public class LiveHelpAudiService extends CrudService<LiveHelpAudiDao, LiveHelpAudi> {

	public LiveHelpAudi get(String id) {
		return super.get(id);
	}
	
	public List<LiveHelpAudi> findList(LiveHelpAudi liveHelpAudi) {
		return super.findList(liveHelpAudi);
	}
	
	public Page<LiveHelpAudi> findPage(Page<LiveHelpAudi> page, LiveHelpAudi liveHelpAudi) {
		return super.findPage(page, liveHelpAudi);
	}
	
	@Transactional(readOnly = false)
	public void save(LiveHelpAudi liveHelpAudi) {
		super.save(liveHelpAudi);
	}
	
	@Transactional(readOnly = false)
	public void delete(LiveHelpAudi liveHelpAudi) {
		super.delete(liveHelpAudi);
	}
	
}