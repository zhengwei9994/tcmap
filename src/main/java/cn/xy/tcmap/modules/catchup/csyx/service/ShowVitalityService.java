/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csyx.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.service.CrudService;
import cn.xy.tcmap.modules.catchup.csyx.entity.ShowVitality;
import cn.xy.tcmap.modules.catchup.csyx.dao.ShowVitalityDao;

/**
 * 经济活力指数Service
 * @author wufan
 * @version 2019-07-31
 */
@Service
@Transactional(readOnly = true)
public class ShowVitalityService extends CrudService<ShowVitalityDao, ShowVitality> {

	public ShowVitality get(String id) {
		return super.get(id);
	}
	
	public List<ShowVitality> findList(ShowVitality showVitality) {
		return super.findList(showVitality);
	}
	
	public Page<ShowVitality> findPage(Page<ShowVitality> page, ShowVitality showVitality) {
		return super.findPage(page, showVitality);
	}
	
	@Transactional(readOnly = false)
	public void save(ShowVitality showVitality) {
		super.save(showVitality);
	}
	
	@Transactional(readOnly = false)
	public void delete(ShowVitality showVitality) {
		super.delete(showVitality);
	}
	
}