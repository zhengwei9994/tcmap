/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csyx.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.service.CrudService;
import cn.xy.tcmap.modules.catchup.csyx.entity.BusinessBalance;
import cn.xy.tcmap.modules.catchup.csyx.dao.BusinessBalanceDao;

/**
 * 金融机构本外币存款余额Service
 * @author wufan
 * @version 2019-07-31
 */
@Service
@Transactional(readOnly = true)
public class BusinessBalanceService extends CrudService<BusinessBalanceDao, BusinessBalance> {

	public BusinessBalance get(String id) {
		return super.get(id);
	}
	
	public List<BusinessBalance> findList(BusinessBalance businessBalance) {
		return super.findList(businessBalance);
	}
	
	public Page<BusinessBalance> findPage(Page<BusinessBalance> page, BusinessBalance businessBalance) {
		return super.findPage(page, businessBalance);
	}
	
	@Transactional(readOnly = false)
	public void save(BusinessBalance businessBalance) {
		super.save(businessBalance);
	}
	
	@Transactional(readOnly = false)
	public void delete(BusinessBalance businessBalance) {
		super.delete(businessBalance);
	}
	
}