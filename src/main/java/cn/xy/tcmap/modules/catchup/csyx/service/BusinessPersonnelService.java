/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csyx.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.service.CrudService;
import cn.xy.tcmap.modules.catchup.csyx.entity.BusinessPersonnel;
import cn.xy.tcmap.modules.catchup.csyx.dao.BusinessPersonnelDao;

/**
 * 营商人才结构Service
 * @author wufan
 * @version 2019-07-31
 */
@Service
@Transactional(readOnly = true)
public class BusinessPersonnelService extends CrudService<BusinessPersonnelDao, BusinessPersonnel> {

	public BusinessPersonnel get(String id) {
		return super.get(id);
	}
	
	public List<BusinessPersonnel> findList(BusinessPersonnel businessPersonnel) {
		return super.findList(businessPersonnel);
	}
	
	public Page<BusinessPersonnel> findPage(Page<BusinessPersonnel> page, BusinessPersonnel businessPersonnel) {
		return super.findPage(page, businessPersonnel);
	}
	
	@Transactional(readOnly = false)
	public void save(BusinessPersonnel businessPersonnel) {
		super.save(businessPersonnel);
	}
	
	@Transactional(readOnly = false)
	public void delete(BusinessPersonnel businessPersonnel) {
		super.delete(businessPersonnel);
	}
	
}