/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csyx.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.service.CrudService;
import cn.xy.tcmap.modules.catchup.csyx.entity.ShowCapitalattractItem;
import cn.xy.tcmap.modules.catchup.csyx.dao.ShowCapitalattractItemDao;

/**
 * 经济财政支出Service
 * @author wufan
 * @version 2019-07-31
 */
@Service
@Transactional(readOnly = true)
public class ShowCapitalattractItemService extends CrudService<ShowCapitalattractItemDao, ShowCapitalattractItem> {

	public ShowCapitalattractItem get(String id) {
		return super.get(id);
	}
	
	public List<ShowCapitalattractItem> findList(ShowCapitalattractItem showCapitalattractItem) {
		return super.findList(showCapitalattractItem);
	}
	
	public Page<ShowCapitalattractItem> findPage(Page<ShowCapitalattractItem> page, ShowCapitalattractItem showCapitalattractItem) {
		return super.findPage(page, showCapitalattractItem);
	}
	
	@Transactional(readOnly = false)
	public void save(ShowCapitalattractItem showCapitalattractItem) {
		super.save(showCapitalattractItem);
	}
	
	@Transactional(readOnly = false)
	public void delete(ShowCapitalattractItem showCapitalattractItem) {
		super.delete(showCapitalattractItem);
	}
	
}