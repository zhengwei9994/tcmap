/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csly.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.service.CrudService;
import cn.xy.tcmap.modules.catchup.csly.entity.TouristMultiscenicVolue;
import cn.xy.tcmap.modules.catchup.csly.dao.TouristMultiscenicVolueDao;

/**
 * 多景区品牌聆听气泡图Service
 * @author tuo
 * @version 2019-10-23
 */
@Service
@Transactional(readOnly = true)
public class TouristMultiscenicVolueService extends CrudService<TouristMultiscenicVolueDao, TouristMultiscenicVolue> {

	public TouristMultiscenicVolue get(String id) {
		return super.get(id);
	}
	
	public List<TouristMultiscenicVolue> findList(TouristMultiscenicVolue touristMultiscenicVolue) {
		return super.findList(touristMultiscenicVolue);
	}
	
	public Page<TouristMultiscenicVolue> findPage(Page<TouristMultiscenicVolue> page, TouristMultiscenicVolue touristMultiscenicVolue) {
		return super.findPage(page, touristMultiscenicVolue);
	}
	
	@Transactional(readOnly = false)
	public void save(TouristMultiscenicVolue touristMultiscenicVolue) {
		super.save(touristMultiscenicVolue);
	}
	
	@Transactional(readOnly = false)
	public void delete(TouristMultiscenicVolue touristMultiscenicVolue) {
		super.delete(touristMultiscenicVolue);
	}
	
}