/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csyx.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.service.CrudService;
import cn.xy.tcmap.modules.catchup.csyx.entity.ShowQualitylifeConsumption;
import cn.xy.tcmap.modules.catchup.csyx.dao.ShowQualitylifeConsumptionDao;

/**
 * 经济消费状况Service
 * @author wufan
 * @version 2019-07-31
 */
@Service
@Transactional(readOnly = true)
public class ShowQualitylifeConsumptionService extends CrudService<ShowQualitylifeConsumptionDao, ShowQualitylifeConsumption> {

	public ShowQualitylifeConsumption get(String id) {
		return super.get(id);
	}
	
	public List<ShowQualitylifeConsumption> findList(ShowQualitylifeConsumption showQualitylifeConsumption) {
		return super.findList(showQualitylifeConsumption);
	}
	
	public Page<ShowQualitylifeConsumption> findPage(Page<ShowQualitylifeConsumption> page, ShowQualitylifeConsumption showQualitylifeConsumption) {
		return super.findPage(page, showQualitylifeConsumption);
	}
	
	@Transactional(readOnly = false)
	public void save(ShowQualitylifeConsumption showQualitylifeConsumption) {
		super.save(showQualitylifeConsumption);
	}
	
	@Transactional(readOnly = false)
	public void delete(ShowQualitylifeConsumption showQualitylifeConsumption) {
		super.delete(showQualitylifeConsumption);
	}
	
}