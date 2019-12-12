/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.wlkj.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.service.CrudService;
import cn.xy.tcmap.modules.catchup.wlkj.entity.CatchMediaHyd;
import cn.xy.tcmap.modules.catchup.wlkj.dao.CatchMediaHydDao;

/**
 * 媒体活跃度Service
 * @author guoxunquan
 * @version 2018-09-20
 */
@Service
@Transactional(readOnly = true)
public class CatchMediaHydService extends CrudService<CatchMediaHydDao, CatchMediaHyd> {

	public CatchMediaHyd get(String id) {
		return super.get(id);
	}
	
	public List<CatchMediaHyd> findList(CatchMediaHyd catchMediaHyd) {
		return super.findList(catchMediaHyd);
	}
	
	public Page<CatchMediaHyd> findPage(Page<CatchMediaHyd> page, CatchMediaHyd catchMediaHyd) {
		return super.findPage(page, catchMediaHyd);
	}
	
	@Transactional(readOnly = false)
	public void save(CatchMediaHyd catchMediaHyd) {
		super.save(catchMediaHyd);
	}
	
	@Transactional(readOnly = false)
	public void delete(CatchMediaHyd catchMediaHyd) {
		super.delete(catchMediaHyd);
	}
	
}