/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.zhcs.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.service.CrudService;
import cn.xy.tcmap.modules.catchup.zhcs.entity.CatchAreaPosition;
import cn.xy.tcmap.modules.catchup.zhcs.dao.CatchAreaPositionDao;

/**
 * 铜川区域管理Service
 * @author gxq
 * @version 2018-11-08
 */
@Service
@Transactional(readOnly = true)
public class CatchAreaPositionService extends CrudService<CatchAreaPositionDao, CatchAreaPosition> {

	public CatchAreaPosition get(String id) {
		return super.get(id);
	}
	
	public List<CatchAreaPosition> findList(CatchAreaPosition catchAreaPosition) {
		return super.findList(catchAreaPosition);
	}
	
	public Page<CatchAreaPosition> findPage(Page<CatchAreaPosition> page, CatchAreaPosition catchAreaPosition) {
		return super.findPage(page, catchAreaPosition);
	}
	
	@Transactional(readOnly = false)
	public void save(CatchAreaPosition catchAreaPosition) {
		super.save(catchAreaPosition);
	}
	
	@Transactional(readOnly = false)
	public void delete(CatchAreaPosition catchAreaPosition) {
		super.delete(catchAreaPosition);
	}
	
}