/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.wlkj.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.service.CrudService;
import cn.xy.tcmap.modules.catchup.wlkj.entity.CatchMediaSpread;
import cn.xy.tcmap.modules.catchup.wlkj.dao.CatchMediaSpreadDao;

/**
 * 媒体分布Service
 * @author guoxunquan
 * @version 2018-09-20
 */
@Service
@Transactional(readOnly = true)
public class CatchMediaSpreadService extends CrudService<CatchMediaSpreadDao, CatchMediaSpread> {

	public CatchMediaSpread get(String id) {
		return super.get(id);
	}
	
	public List<CatchMediaSpread> findList(CatchMediaSpread catchMediaSpread) {
		return super.findList(catchMediaSpread);
	}
	
	public Page<CatchMediaSpread> findPage(Page<CatchMediaSpread> page, CatchMediaSpread catchMediaSpread) {
		return super.findPage(page, catchMediaSpread);
	}
	
	@Transactional(readOnly = false)
	public void save(CatchMediaSpread catchMediaSpread) {
		super.save(catchMediaSpread);
	}
	
	@Transactional(readOnly = false)
	public void delete(CatchMediaSpread catchMediaSpread) {
		super.delete(catchMediaSpread);
	}
	
}