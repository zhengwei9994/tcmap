/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.fzjc.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.service.CrudService;
import cn.xy.tcmap.modules.catchup.fzjc.entity.CatchSalaryStaff;
import cn.xy.tcmap.modules.catchup.fzjc.dao.CatchSalaryStaffDao;

/**
 * 薪资及人员分析Service
 * @author liuyang
 * @version 2018-05-29
 */
@Service
@Transactional(readOnly = true)
public class CatchSalaryStaffService extends CrudService<CatchSalaryStaffDao, CatchSalaryStaff> {

	public CatchSalaryStaff get(String id) {
		return super.get(id);
	}
	
	public List<CatchSalaryStaff> findList(CatchSalaryStaff catchSalaryStaff) {
		return super.findList(catchSalaryStaff);
	}
	
	public Page<CatchSalaryStaff> findPage(Page<CatchSalaryStaff> page, CatchSalaryStaff catchSalaryStaff) {
		return super.findPage(page, catchSalaryStaff);
	}
	
	@Transactional(readOnly = false)
	public void save(CatchSalaryStaff catchSalaryStaff) {
		super.save(catchSalaryStaff);
	}
	
	@Transactional(readOnly = false)
	public void delete(CatchSalaryStaff catchSalaryStaff) {
		super.delete(catchSalaryStaff);
	}
	
}