/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.wlkj.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.service.CrudService;
import cn.xy.tcmap.modules.catchup.wlkj.entity.CatchHotSearch;
import cn.xy.tcmap.modules.catchup.wlkj.dao.CatchHotSearchDao;

/**
 * 热搜词汇Service
 * @author xuzhou
 * @version 2018-05-30
 */
@Service
@Transactional(readOnly = true)
public class CatchHotSearchService extends CrudService<CatchHotSearchDao, CatchHotSearch> {

	public CatchHotSearch get(String id) {
		return super.get(id);
	}
	
	public List<CatchHotSearch> findList(CatchHotSearch catchHotSearch) {
		return super.findList(catchHotSearch);
	}
	
	public Page<CatchHotSearch> findPage(Page<CatchHotSearch> page, CatchHotSearch catchHotSearch) {
		return super.findPage(page, catchHotSearch);
	}
	
	@Transactional(readOnly = false)
	public void save(CatchHotSearch catchHotSearch) {
		super.save(catchHotSearch);
	}
	
	@Transactional(readOnly = false)
	public void delete(CatchHotSearch catchHotSearch) {
		super.delete(catchHotSearch);
	}
	
}