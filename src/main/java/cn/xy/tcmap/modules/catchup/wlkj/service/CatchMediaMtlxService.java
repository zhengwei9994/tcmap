/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.wlkj.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.service.CrudService;
import cn.xy.tcmap.modules.catchup.wlkj.entity.CatchMediaMtlx;
import cn.xy.tcmap.modules.catchup.wlkj.dao.CatchMediaMtlxDao;

/**
 * 媒体类型Service
 * @author guoxunquan
 * @version 2018-09-20
 */
@Service
@Transactional(readOnly = true)
public class CatchMediaMtlxService extends CrudService<CatchMediaMtlxDao, CatchMediaMtlx> {

	public CatchMediaMtlx get(String id) {
		return super.get(id);
	}
	
	public List<CatchMediaMtlx> findList(CatchMediaMtlx catchMediaMtlx) {
		return super.findList(catchMediaMtlx);
	}
	
	public Page<CatchMediaMtlx> findPage(Page<CatchMediaMtlx> page, CatchMediaMtlx catchMediaMtlx) {
		return super.findPage(page, catchMediaMtlx);
	}
	
	@Transactional(readOnly = false)
	public void save(CatchMediaMtlx catchMediaMtlx) {
		super.save(catchMediaMtlx);
	}
	
	@Transactional(readOnly = false)
	public void delete(CatchMediaMtlx catchMediaMtlx) {
		super.delete(catchMediaMtlx);
	}
	
}