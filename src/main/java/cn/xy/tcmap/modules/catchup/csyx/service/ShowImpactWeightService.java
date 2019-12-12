/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csyx.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.service.CrudService;
import cn.xy.tcmap.modules.catchup.csyx.entity.ShowImpactWeight;
import cn.xy.tcmap.modules.catchup.csyx.dao.ShowImpactWeightDao;

/**
 * 影响权重Service
 * @author tuo
 * @version 2019-08-22
 */
@Service
@Transactional(readOnly = true)
public class ShowImpactWeightService extends CrudService<ShowImpactWeightDao, ShowImpactWeight> {

	public ShowImpactWeight get(String id) {
		return super.get(id);
	}
	
	public List<ShowImpactWeight> findList(ShowImpactWeight showImpactWeight) {
		return super.findList(showImpactWeight);
	}
	
	public Page<ShowImpactWeight> findPage(Page<ShowImpactWeight> page, ShowImpactWeight showImpactWeight) {
		return super.findPage(page, showImpactWeight);
	}
	
	@Transactional(readOnly = false)
	public void save(ShowImpactWeight showImpactWeight) {
		super.save(showImpactWeight);
	}
	
	@Transactional(readOnly = false)
	public void delete(ShowImpactWeight showImpactWeight) {
		super.delete(showImpactWeight);
	}
	
}