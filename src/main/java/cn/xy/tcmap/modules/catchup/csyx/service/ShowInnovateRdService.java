/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csyx.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.service.CrudService;
import cn.xy.tcmap.modules.catchup.csyx.entity.ShowInnovateRd;
import cn.xy.tcmap.modules.catchup.csyx.dao.ShowInnovateRdDao;

/**
 * 经济rd变化Service
 * @author wufan
 * @version 2019-07-31
 */
@Service
@Transactional(readOnly = true)
public class ShowInnovateRdService extends CrudService<ShowInnovateRdDao, ShowInnovateRd> {

	public ShowInnovateRd get(String id) {
		return super.get(id);
	}
	
	public List<ShowInnovateRd> findList(ShowInnovateRd showInnovateRd) {
		return super.findList(showInnovateRd);
	}
	
	public Page<ShowInnovateRd> findPage(Page<ShowInnovateRd> page, ShowInnovateRd showInnovateRd) {
		return super.findPage(page, showInnovateRd);
	}
	
	@Transactional(readOnly = false)
	public void save(ShowInnovateRd showInnovateRd) {
		super.save(showInnovateRd);
	}
	
	@Transactional(readOnly = false)
	public void delete(ShowInnovateRd showInnovateRd) {
		super.delete(showInnovateRd);
	}
	
}