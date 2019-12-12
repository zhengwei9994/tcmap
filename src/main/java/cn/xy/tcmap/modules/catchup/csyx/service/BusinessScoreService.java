/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csyx.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.service.CrudService;
import cn.xy.tcmap.modules.catchup.csyx.entity.BusinessScore;
import cn.xy.tcmap.modules.catchup.csyx.dao.BusinessScoreDao;

/**
 * 营商环境评分Service
 * @author wufan
 * @version 2019-08-01
 */
@Service
@Transactional(readOnly = true)
public class BusinessScoreService extends CrudService<BusinessScoreDao, BusinessScore> {

	public BusinessScore get(String id) {
		return super.get(id);
	}
	
	public List<BusinessScore> findList(BusinessScore businessScore) {
		return super.findList(businessScore);
	}
	
	public Page<BusinessScore> findPage(Page<BusinessScore> page, BusinessScore businessScore) {
		return super.findPage(page, businessScore);
	}
	
	@Transactional(readOnly = false)
	public void save(BusinessScore businessScore) {
		super.save(businessScore);
	}
	
	@Transactional(readOnly = false)
	public void delete(BusinessScore businessScore) {
		super.delete(businessScore);
	}
	
}