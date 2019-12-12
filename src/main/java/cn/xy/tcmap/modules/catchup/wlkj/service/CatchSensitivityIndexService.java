/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.wlkj.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.service.CrudService;
import cn.xy.tcmap.modules.catchup.wlkj.entity.CatchSensitivityIndex;
import cn.xy.tcmap.modules.catchup.wlkj.dao.CatchSensitivityIndexDao;

/**
 * 敏感指数Service
 * @author guoxunquan
 * @version 2018-09-20
 */
@Service
@Transactional(readOnly = true)
public class CatchSensitivityIndexService extends CrudService<CatchSensitivityIndexDao, CatchSensitivityIndex> {

	public CatchSensitivityIndex get(String id) {
		return super.get(id);
	}
	
	public List<CatchSensitivityIndex> findList(CatchSensitivityIndex catchSensitivityIndex) {
		return super.findList(catchSensitivityIndex);
	}
	
	public Page<CatchSensitivityIndex> findPage(Page<CatchSensitivityIndex> page, CatchSensitivityIndex catchSensitivityIndex) {
		return super.findPage(page, catchSensitivityIndex);
	}
	
	@Transactional(readOnly = false)
	public void save(CatchSensitivityIndex catchSensitivityIndex) {
		super.save(catchSensitivityIndex);
	}
	
	@Transactional(readOnly = false)
	public void delete(CatchSensitivityIndex catchSensitivityIndex) {
		super.delete(catchSensitivityIndex);
	}
	
}