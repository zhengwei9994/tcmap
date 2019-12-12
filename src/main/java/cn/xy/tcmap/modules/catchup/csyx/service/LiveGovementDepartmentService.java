/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csyx.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.service.CrudService;
import cn.xy.tcmap.modules.catchup.csyx.entity.LiveGovementDepartment;
import cn.xy.tcmap.modules.catchup.csyx.dao.LiveGovementDepartmentDao;

/**
 * 行业主管部门事项分布Service
 * @author wufan
 * @version 2019-10-14
 */
@Service
@Transactional(readOnly = true)
public class LiveGovementDepartmentService extends CrudService<LiveGovementDepartmentDao, LiveGovementDepartment> {

	public LiveGovementDepartment get(String id) {
		return super.get(id);
	}
	
	public List<LiveGovementDepartment> findList(LiveGovementDepartment liveGovementDepartment) {
		return super.findList(liveGovementDepartment);
	}
	
	public Page<LiveGovementDepartment> findPage(Page<LiveGovementDepartment> page, LiveGovementDepartment liveGovementDepartment) {
		return super.findPage(page, liveGovementDepartment);
	}
	
	@Transactional(readOnly = false)
	public void save(LiveGovementDepartment liveGovementDepartment) {
		super.save(liveGovementDepartment);
	}
	
	@Transactional(readOnly = false)
	public void delete(LiveGovementDepartment liveGovementDepartment) {
		super.delete(liveGovementDepartment);
	}
	
}