/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csyx.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.service.CrudService;
import cn.xy.tcmap.modules.catchup.csyx.entity.BusinessMedical;
import cn.xy.tcmap.modules.catchup.csyx.dao.BusinessMedicalDao;

/**
 * 营商千人医疗Service
 * @author wufan
 * @version 2019-07-31
 */
@Service
@Transactional(readOnly = true)
public class BusinessMedicalService extends CrudService<BusinessMedicalDao, BusinessMedical> {

	public BusinessMedical get(String id) {
		return super.get(id);
	}
	
	public List<BusinessMedical> findList(BusinessMedical businessMedical) {
		return super.findList(businessMedical);
	}
	
	public Page<BusinessMedical> findPage(Page<BusinessMedical> page, BusinessMedical businessMedical) {
		return super.findPage(page, businessMedical);
	}
	
	@Transactional(readOnly = false)
	public void save(BusinessMedical businessMedical) {
		super.save(businessMedical);
	}
	
	@Transactional(readOnly = false)
	public void delete(BusinessMedical businessMedical) {
		super.delete(businessMedical);
	}
	
}