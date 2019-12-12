/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csyx.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.service.CrudService;
import cn.xy.tcmap.modules.catchup.csyx.entity.BusinessEducation;
import cn.xy.tcmap.modules.catchup.csyx.dao.BusinessEducationDao;

/**
 * 营商义务教育Service
 * @author wufan
 * @version 2019-07-31
 */
@Service
@Transactional(readOnly = true)
public class BusinessEducationService extends CrudService<BusinessEducationDao, BusinessEducation> {

	public BusinessEducation get(String id) {
		return super.get(id);
	}
	
	public List<BusinessEducation> findList(BusinessEducation businessEducation) {
		return super.findList(businessEducation);
	}
	
	public Page<BusinessEducation> findPage(Page<BusinessEducation> page, BusinessEducation businessEducation) {
		return super.findPage(page, businessEducation);
	}
	
	@Transactional(readOnly = false)
	public void save(BusinessEducation businessEducation) {
		super.save(businessEducation);
	}
	
	@Transactional(readOnly = false)
	public void delete(BusinessEducation businessEducation) {
		super.delete(businessEducation);
	}
	
}