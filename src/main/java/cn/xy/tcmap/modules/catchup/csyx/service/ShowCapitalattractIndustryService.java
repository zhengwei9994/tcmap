/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csyx.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.service.CrudService;
import cn.xy.tcmap.modules.catchup.csyx.entity.ShowCapitalattractIndustry;
import cn.xy.tcmap.modules.catchup.csyx.dao.ShowCapitalattractIndustryDao;

/**
 * 经济三产占比Service
 * @author wufan
 * @version 2019-07-31
 */
@Service
@Transactional(readOnly = true)
public class ShowCapitalattractIndustryService extends CrudService<ShowCapitalattractIndustryDao, ShowCapitalattractIndustry> {

	public ShowCapitalattractIndustry get(String id) {
		return super.get(id);
	}
	
	public List<ShowCapitalattractIndustry> findList(ShowCapitalattractIndustry showCapitalattractIndustry) {
		return super.findList(showCapitalattractIndustry);
	}
	
	public Page<ShowCapitalattractIndustry> findPage(Page<ShowCapitalattractIndustry> page, ShowCapitalattractIndustry showCapitalattractIndustry) {
		return super.findPage(page, showCapitalattractIndustry);
	}
	
	@Transactional(readOnly = false)
	public void save(ShowCapitalattractIndustry showCapitalattractIndustry) {
		super.save(showCapitalattractIndustry);
	}
	
	@Transactional(readOnly = false)
	public void delete(ShowCapitalattractIndustry showCapitalattractIndustry) {
		super.delete(showCapitalattractIndustry);
	}
	
}