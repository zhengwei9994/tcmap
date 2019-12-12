/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csyx.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.service.CrudService;
import cn.xy.tcmap.modules.catchup.csyx.entity.BusinessVenue;
import cn.xy.tcmap.modules.catchup.csyx.dao.BusinessVenueDao;

/**
 * 营商文化场馆Service
 * @author wufan
 * @version 2019-07-31
 */
@Service
@Transactional(readOnly = true)
public class BusinessVenueService extends CrudService<BusinessVenueDao, BusinessVenue> {

	public BusinessVenue get(String id) {
		return super.get(id);
	}
	
	public List<BusinessVenue> findList(BusinessVenue businessVenue) {
		return super.findList(businessVenue);
	}
	
	public Page<BusinessVenue> findPage(Page<BusinessVenue> page, BusinessVenue businessVenue) {
		return super.findPage(page, businessVenue);
	}
	
	@Transactional(readOnly = false)
	public void save(BusinessVenue businessVenue) {
		super.save(businessVenue);
	}
	
	@Transactional(readOnly = false)
	public void delete(BusinessVenue businessVenue) {
		super.delete(businessVenue);
	}
	
}