/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.wlkj.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.service.CrudService;
import cn.xy.tcmap.modules.catchup.wlkj.entity.CatchMediaResource;
import cn.xy.tcmap.modules.catchup.wlkj.dao.CatchMediaResourceDao;

/**
 * 媒体来源Service
 * @author guoxunquan
 * @version 2018-09-20
 */
@Service
@Transactional(readOnly = true)
public class CatchMediaResourceService extends CrudService<CatchMediaResourceDao, CatchMediaResource> {

	public CatchMediaResource get(String id) {
		return super.get(id);
	}
	
	public List<CatchMediaResource> findList(CatchMediaResource catchMediaResource) {
		return super.findList(catchMediaResource);
	}
	
	public Page<CatchMediaResource> findPage(Page<CatchMediaResource> page, CatchMediaResource catchMediaResource) {
		return super.findPage(page, catchMediaResource);
	}
	
	@Transactional(readOnly = false)
	public void save(CatchMediaResource catchMediaResource) {
		super.save(catchMediaResource);
	}
	
	@Transactional(readOnly = false)
	public void delete(CatchMediaResource catchMediaResource) {
		super.delete(catchMediaResource);
	}
	
}