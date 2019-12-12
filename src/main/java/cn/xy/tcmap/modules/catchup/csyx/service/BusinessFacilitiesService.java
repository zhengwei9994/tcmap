/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csyx.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.service.CrudService;
import cn.xy.tcmap.modules.catchup.csyx.entity.BusinessFacilities;
import cn.xy.tcmap.modules.catchup.csyx.dao.BusinessFacilitiesDao;

/**
 * 营商设施完成度Service
 * @author wufan
 * @version 2019-07-31
 */
@Service
@Transactional(readOnly = true)
public class BusinessFacilitiesService extends CrudService<BusinessFacilitiesDao, BusinessFacilities> {

	public BusinessFacilities get(String id) {
		return super.get(id);
	}
	
	public List<BusinessFacilities> findList(BusinessFacilities businessFacilities) {
		return super.findList(businessFacilities);
	}
	
	public Page<BusinessFacilities> findPage(Page<BusinessFacilities> page, BusinessFacilities businessFacilities) {
		return super.findPage(page, businessFacilities);
	}
	
	@Transactional(readOnly = false)
	public void save(BusinessFacilities businessFacilities) {
		super.save(businessFacilities);
	}
	
	@Transactional(readOnly = false)
	public void delete(BusinessFacilities businessFacilities) {
		super.delete(businessFacilities);
	}
	
}