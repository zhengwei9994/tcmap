/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csyx.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.service.CrudService;
import cn.xy.tcmap.modules.catchup.csyx.entity.DevelopmentSituation;
import cn.xy.tcmap.modules.catchup.csyx.dao.DevelopmentSituationDao;

/**
 * 产业现状Service
 * @author wufan
 * @version 2019-07-31
 */
@Service
@Transactional(readOnly = true)
public class DevelopmentSituationService extends CrudService<DevelopmentSituationDao, DevelopmentSituation> {

	public DevelopmentSituation get(String id) {
		return super.get(id);
	}
	
	public List<DevelopmentSituation> findList(DevelopmentSituation developmentSituation) {
		return super.findList(developmentSituation);
	}
	
	public Page<DevelopmentSituation> findPage(Page<DevelopmentSituation> page, DevelopmentSituation developmentSituation) {
		return super.findPage(page, developmentSituation);
	}
	
	@Transactional(readOnly = false)
	public void save(DevelopmentSituation developmentSituation) {
		super.save(developmentSituation);
	}
	
	@Transactional(readOnly = false)
	public void delete(DevelopmentSituation developmentSituation) {
		super.delete(developmentSituation);
	}
	
}