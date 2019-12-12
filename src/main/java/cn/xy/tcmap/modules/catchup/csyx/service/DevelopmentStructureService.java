/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csyx.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.service.CrudService;
import cn.xy.tcmap.modules.catchup.csyx.entity.DevelopmentStructure;
import cn.xy.tcmap.modules.catchup.csyx.dao.DevelopmentStructureDao;

/**
 * 产业结构Service
 * @author wufan
 * @version 2019-07-31
 */
@Service
@Transactional(readOnly = true)
public class DevelopmentStructureService extends CrudService<DevelopmentStructureDao, DevelopmentStructure> {

	public DevelopmentStructure get(String id) {
		return super.get(id);
	}
	
	public List<DevelopmentStructure> findList(DevelopmentStructure developmentStructure) {
		return super.findList(developmentStructure);
	}
	
	public Page<DevelopmentStructure> findPage(Page<DevelopmentStructure> page, DevelopmentStructure developmentStructure) {
		return super.findPage(page, developmentStructure);
	}
	
	@Transactional(readOnly = false)
	public void save(DevelopmentStructure developmentStructure) {
		super.save(developmentStructure);
	}
	
	@Transactional(readOnly = false)
	public void delete(DevelopmentStructure developmentStructure) {
		super.delete(developmentStructure);
	}
	
}