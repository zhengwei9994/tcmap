/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csyx.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.service.CrudService;
import cn.xy.tcmap.modules.catchup.csyx.entity.BusinessEnvironmental;
import cn.xy.tcmap.modules.catchup.csyx.dao.BusinessEnvironmentalDao;

/**
 * 营商环境变量Service
 * @author wufan
 * @version 2019-07-31
 */
@Service
@Transactional(readOnly = true)
public class BusinessEnvironmentalService extends CrudService<BusinessEnvironmentalDao, BusinessEnvironmental> {

	public BusinessEnvironmental get(String id) {
		return super.get(id);
	}
	
	public List<BusinessEnvironmental> findList(BusinessEnvironmental businessEnvironmental) {
		return super.findList(businessEnvironmental);
	}
	
	public Page<BusinessEnvironmental> findPage(Page<BusinessEnvironmental> page, BusinessEnvironmental businessEnvironmental) {
		return super.findPage(page, businessEnvironmental);
	}
	
	@Transactional(readOnly = false)
	public void save(BusinessEnvironmental businessEnvironmental) {
		super.save(businessEnvironmental);
	}
	
	@Transactional(readOnly = false)
	public void delete(BusinessEnvironmental businessEnvironmental) {
		super.delete(businessEnvironmental);
	}
	
}