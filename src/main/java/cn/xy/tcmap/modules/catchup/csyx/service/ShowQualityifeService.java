/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csyx.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.service.CrudService;
import cn.xy.tcmap.modules.catchup.csyx.entity.ShowQualityife;
import cn.xy.tcmap.modules.catchup.csyx.dao.ShowQualityifeDao;

/**
 * 经济生活质量Service
 * @author wufan
 * @version 2019-07-31
 */
@Service
@Transactional(readOnly = true)
public class ShowQualityifeService extends CrudService<ShowQualityifeDao, ShowQualityife> {

	public ShowQualityife get(String id) {
		return super.get(id);
	}
	
	public List<ShowQualityife> findList(ShowQualityife showQualityife) {
		return super.findList(showQualityife);
	}
	
	public Page<ShowQualityife> findPage(Page<ShowQualityife> page, ShowQualityife showQualityife) {
		return super.findPage(page, showQualityife);
	}
	
	@Transactional(readOnly = false)
	public void save(ShowQualityife showQualityife) {
		super.save(showQualityife);
	}
	
	@Transactional(readOnly = false)
	public void delete(ShowQualityife showQualityife) {
		super.delete(showQualityife);
	}
	
}