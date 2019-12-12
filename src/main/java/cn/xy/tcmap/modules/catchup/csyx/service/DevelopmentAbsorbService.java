/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csyx.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.service.CrudService;
import cn.xy.tcmap.modules.catchup.csyx.entity.DevelopmentAbsorb;
import cn.xy.tcmap.modules.catchup.csyx.dao.DevelopmentAbsorbDao;

/**
 * 产业发展潜力内容Service
 * @author wufan
 * @version 2019-07-31
 */
@Service
@Transactional(readOnly = true)
public class DevelopmentAbsorbService extends CrudService<DevelopmentAbsorbDao, DevelopmentAbsorb> {

	public DevelopmentAbsorb get(String id) {
		return super.get(id);
	}
	
	public List<DevelopmentAbsorb> findList(DevelopmentAbsorb developmentAbsorb) {
		return super.findList(developmentAbsorb);
	}
	
	public Page<DevelopmentAbsorb> findPage(Page<DevelopmentAbsorb> page, DevelopmentAbsorb developmentAbsorb) {
		return super.findPage(page, developmentAbsorb);
	}
	
	@Transactional(readOnly = false)
	public void save(DevelopmentAbsorb developmentAbsorb) {
		super.save(developmentAbsorb);
	}
	
	@Transactional(readOnly = false)
	public void delete(DevelopmentAbsorb developmentAbsorb) {
		super.delete(developmentAbsorb);
	}
	
}