/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csyx.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.service.CrudService;
import cn.xy.tcmap.modules.catchup.csyx.entity.DevelopmentIndustry;
import cn.xy.tcmap.modules.catchup.csyx.dao.DevelopmentIndustryDao;

/**
 * 产业发展Service
 * @author wufan
 * @version 2019-07-31
 */
@Service
@Transactional(readOnly = true)
public class DevelopmentIndustryService extends CrudService<DevelopmentIndustryDao, DevelopmentIndustry> {

	public DevelopmentIndustry get(String id) {
		return super.get(id);
	}
	
	public List<DevelopmentIndustry> findList(DevelopmentIndustry developmentIndustry) {
		return super.findList(developmentIndustry);
	}
	
	public Page<DevelopmentIndustry> findPage(Page<DevelopmentIndustry> page, DevelopmentIndustry developmentIndustry) {
		return super.findPage(page, developmentIndustry);
	}
	
	@Transactional(readOnly = false)
	public void save(DevelopmentIndustry developmentIndustry) {
		super.save(developmentIndustry);
	}
	
	@Transactional(readOnly = false)
	public void delete(DevelopmentIndustry developmentIndustry) {
		super.delete(developmentIndustry);
	}
	
}