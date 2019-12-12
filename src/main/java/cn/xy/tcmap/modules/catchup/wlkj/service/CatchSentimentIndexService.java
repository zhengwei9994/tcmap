/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.wlkj.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.service.CrudService;
import cn.xy.tcmap.modules.catchup.wlkj.entity.CatchSentimentIndex;
import cn.xy.tcmap.modules.catchup.wlkj.dao.CatchSentimentIndexDao;

/**
 * 舆情指数Service
 * @author guoxunquan
 * @version 2018-09-20
 */
@Service
@Transactional(readOnly = true)
public class CatchSentimentIndexService extends CrudService<CatchSentimentIndexDao, CatchSentimentIndex> {

	public CatchSentimentIndex get(String id) {
		return super.get(id);
	}
	
	public List<CatchSentimentIndex> findList(CatchSentimentIndex catchSentimentIndex) {
		return super.findList(catchSentimentIndex);
	}
	
	public Page<CatchSentimentIndex> findPage(Page<CatchSentimentIndex> page, CatchSentimentIndex catchSentimentIndex) {
		return super.findPage(page, catchSentimentIndex);
	}
	
	@Transactional(readOnly = false)
	public void save(CatchSentimentIndex catchSentimentIndex) {
		super.save(catchSentimentIndex);
	}
	
	@Transactional(readOnly = false)
	public void delete(CatchSentimentIndex catchSentimentIndex) {
		super.delete(catchSentimentIndex);
	}
	
}