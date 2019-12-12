/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csyx.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.service.CrudService;
import cn.xy.tcmap.modules.catchup.csyx.entity.ShowCapitalattract;
import cn.xy.tcmap.modules.catchup.csyx.dao.ShowCapitalattractDao;

/**
 * 经济资本吸引力Service
 * @author wufan
 * @version 2019-07-31
 */
@Service
@Transactional(readOnly = true)
public class ShowCapitalattractService extends CrudService<ShowCapitalattractDao, ShowCapitalattract> {

	public ShowCapitalattract get(String id) {
		return super.get(id);
	}
	
	public List<ShowCapitalattract> findList(ShowCapitalattract showCapitalattract) {
		return super.findList(showCapitalattract);
	}
	
	public Page<ShowCapitalattract> findPage(Page<ShowCapitalattract> page, ShowCapitalattract showCapitalattract) {
		return super.findPage(page, showCapitalattract);
	}
	
	@Transactional(readOnly = false)
	public void save(ShowCapitalattract showCapitalattract) {
		super.save(showCapitalattract);
	}
	
	@Transactional(readOnly = false)
	public void delete(ShowCapitalattract showCapitalattract) {
		super.delete(showCapitalattract);
	}
	
}