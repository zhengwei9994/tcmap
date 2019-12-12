/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csyx.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.service.CrudService;
import cn.xy.tcmap.modules.catchup.csyx.entity.IndustrialDevelopment;
import cn.xy.tcmap.modules.catchup.csyx.dao.IndustrialDevelopmentDao;

/**
 * 产业发展Service
 * @author tuo
 * @version 2019-09-27
 */
@Service
@Transactional(readOnly = true)
public class IndustrialDevelopmentService extends CrudService<IndustrialDevelopmentDao, IndustrialDevelopment> {

	public IndustrialDevelopment get(String id) {
		return super.get(id);
	}
	
	public List<IndustrialDevelopment> findList(IndustrialDevelopment industrialDevelopment) {
		return super.findList(industrialDevelopment);
	}
	
	public Page<IndustrialDevelopment> findPage(Page<IndustrialDevelopment> page, IndustrialDevelopment industrialDevelopment) {
		return super.findPage(page, industrialDevelopment);
	}
	
	@Transactional(readOnly = false)
	public void save(IndustrialDevelopment industrialDevelopment) {
		super.save(industrialDevelopment);
	}
	
	@Transactional(readOnly = false)
	public void delete(IndustrialDevelopment industrialDevelopment) {
		super.delete(industrialDevelopment);
	}
	
}