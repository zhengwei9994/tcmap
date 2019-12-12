/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csyx.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.service.CrudService;
import cn.xy.tcmap.modules.catchup.csyx.entity.BusinessHighschool;
import cn.xy.tcmap.modules.catchup.csyx.dao.BusinessHighschoolDao;

/**
 * 营商高等院校数量Service
 * @author wufan
 * @version 2019-07-31
 */
@Service
@Transactional(readOnly = true)
public class BusinessHighschoolService extends CrudService<BusinessHighschoolDao, BusinessHighschool> {

	public BusinessHighschool get(String id) {
		return super.get(id);
	}
	
	public List<BusinessHighschool> findList(BusinessHighschool businessHighschool) {
		return super.findList(businessHighschool);
	}
	
	public Page<BusinessHighschool> findPage(Page<BusinessHighschool> page, BusinessHighschool businessHighschool) {
		return super.findPage(page, businessHighschool);
	}
	
	@Transactional(readOnly = false)
	public void save(BusinessHighschool businessHighschool) {
		super.save(businessHighschool);
	}
	
	@Transactional(readOnly = false)
	public void delete(BusinessHighschool businessHighschool) {
		super.delete(businessHighschool);
	}
	
}