/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csyx.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.service.CrudService;
import cn.xy.tcmap.modules.catchup.csyx.entity.ShowVitalityArea;
import cn.xy.tcmap.modules.catchup.csyx.dao.ShowVitalityAreaDao;

/**
 * 经济活力地图Service
 * @author wufan
 * @version 2019-07-31
 */
@Service
@Transactional(readOnly = true)
public class ShowVitalityAreaService extends CrudService<ShowVitalityAreaDao, ShowVitalityArea> {

	public ShowVitalityArea get(String id) {
		return super.get(id);
	}
	
	public List<ShowVitalityArea> findList(ShowVitalityArea showVitalityArea) {
		return super.findList(showVitalityArea);
	}
	
	public Page<ShowVitalityArea> findPage(Page<ShowVitalityArea> page, ShowVitalityArea showVitalityArea) {
		return super.findPage(page, showVitalityArea);
	}
	
	@Transactional(readOnly = false)
	public void save(ShowVitalityArea showVitalityArea) {
		super.save(showVitalityArea);
	}
	
	@Transactional(readOnly = false)
	public void delete(ShowVitalityArea showVitalityArea) {
		super.delete(showVitalityArea);
	}
	
}