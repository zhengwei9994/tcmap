/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csyx.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.service.CrudService;
import cn.xy.tcmap.modules.catchup.csyx.entity.DevelopmentEvolution;
import cn.xy.tcmap.modules.catchup.csyx.dao.DevelopmentEvolutionDao;

/**
 * 产业演进Service
 * @author wufan
 * @version 2019-07-31
 */
@Service
@Transactional(readOnly = true)
public class DevelopmentEvolutionService extends CrudService<DevelopmentEvolutionDao, DevelopmentEvolution> {

	public DevelopmentEvolution get(String id) {
		return super.get(id);
	}
	
	public List<DevelopmentEvolution> findList(DevelopmentEvolution developmentEvolution) {
		return super.findList(developmentEvolution);
	}
	
	public Page<DevelopmentEvolution> findPage(Page<DevelopmentEvolution> page, DevelopmentEvolution developmentEvolution) {
		return super.findPage(page, developmentEvolution);
	}
	
	@Transactional(readOnly = false)
	public void save(DevelopmentEvolution developmentEvolution) {
		super.save(developmentEvolution);
	}
	
	@Transactional(readOnly = false)
	public void delete(DevelopmentEvolution developmentEvolution) {
		super.delete(developmentEvolution);
	}
	
}