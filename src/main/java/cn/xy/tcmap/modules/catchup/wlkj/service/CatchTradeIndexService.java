/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.wlkj.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.service.CrudService;
import cn.xy.tcmap.modules.catchup.wlkj.entity.CatchTradeIndex;
import cn.xy.tcmap.modules.catchup.wlkj.dao.CatchTradeIndexDao;

/**
 * 行业指数Service
 * @author guoxunquan
 * @version 2018-09-20
 */
@Service
@Transactional(readOnly = true)
public class CatchTradeIndexService extends CrudService<CatchTradeIndexDao, CatchTradeIndex> {

	public CatchTradeIndex get(String id) {
		return super.get(id);
	}
	
	public List<CatchTradeIndex> findList(CatchTradeIndex catchTradeIndex) {
		return super.findList(catchTradeIndex);
	}
	
	public Page<CatchTradeIndex> findPage(Page<CatchTradeIndex> page, CatchTradeIndex catchTradeIndex) {
		return super.findPage(page, catchTradeIndex);
	}
	
	@Transactional(readOnly = false)
	public void save(CatchTradeIndex catchTradeIndex) {
		super.save(catchTradeIndex);
	}
	
	@Transactional(readOnly = false)
	public void delete(CatchTradeIndex catchTradeIndex) {
		super.delete(catchTradeIndex);
	}
	
}