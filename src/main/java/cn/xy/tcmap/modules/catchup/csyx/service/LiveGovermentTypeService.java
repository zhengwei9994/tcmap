/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csyx.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.service.CrudService;
import cn.xy.tcmap.modules.catchup.csyx.entity.LiveGovermentType;
import cn.xy.tcmap.modules.catchup.csyx.dao.LiveGovermentTypeDao;

/**
 * 办理类型Service
 * @author wufan
 * @version 2019-10-14
 */
@Service
@Transactional(readOnly = true)
public class LiveGovermentTypeService extends CrudService<LiveGovermentTypeDao, LiveGovermentType> {

	public LiveGovermentType get(String id) {
		return super.get(id);
	}
	
	public List<LiveGovermentType> findList(LiveGovermentType liveGovermentType) {
		return super.findList(liveGovermentType);
	}
	
	public Page<LiveGovermentType> findPage(Page<LiveGovermentType> page, LiveGovermentType liveGovermentType) {
		return super.findPage(page, liveGovermentType);
	}
	
	@Transactional(readOnly = false)
	public void save(LiveGovermentType liveGovermentType) {
		super.save(liveGovermentType);
	}
	
	@Transactional(readOnly = false)
	public void delete(LiveGovermentType liveGovermentType) {
		super.delete(liveGovermentType);
	}
	
}