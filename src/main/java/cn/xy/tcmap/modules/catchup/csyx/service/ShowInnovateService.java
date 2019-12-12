/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csyx.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.service.CrudService;
import cn.xy.tcmap.modules.catchup.csyx.entity.ShowInnovate;
import cn.xy.tcmap.modules.catchup.csyx.dao.ShowInnovateDao;

/**
 * 经济创新能力Service
 * @author wufan
 * @version 2019-07-31
 */
@Service
@Transactional(readOnly = true)
public class ShowInnovateService extends CrudService<ShowInnovateDao, ShowInnovate> {

	public ShowInnovate get(String id) {
		return super.get(id);
	}
	
	public List<ShowInnovate> findList(ShowInnovate showInnovate) {
		return super.findList(showInnovate);
	}
	
	public Page<ShowInnovate> findPage(Page<ShowInnovate> page, ShowInnovate showInnovate) {
		return super.findPage(page, showInnovate);
	}
	
	@Transactional(readOnly = false)
	public void save(ShowInnovate showInnovate) {
		super.save(showInnovate);
	}
	
	@Transactional(readOnly = false)
	public void delete(ShowInnovate showInnovate) {
		super.delete(showInnovate);
	}
	
}