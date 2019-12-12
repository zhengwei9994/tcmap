/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csly.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.service.CrudService;
import cn.xy.tcmap.modules.catchup.csly.entity.TouristEmotionStack;
import cn.xy.tcmap.modules.catchup.csly.dao.TouristEmotionStackDao;

/**
 * 情绪堆叠图Service
 * @author wufan
 * @version 2019-08-16
 */
@Service
@Transactional(readOnly = true)
public class TouristEmotionStackService extends CrudService<TouristEmotionStackDao, TouristEmotionStack> {

	public TouristEmotionStack get(String id) {
		return super.get(id);
	}
	
	public List<TouristEmotionStack> findList(TouristEmotionStack touristEmotionStack) {
		return super.findList(touristEmotionStack);
	}
	
	public Page<TouristEmotionStack> findPage(Page<TouristEmotionStack> page, TouristEmotionStack touristEmotionStack) {
		return super.findPage(page, touristEmotionStack);
	}
	
	@Transactional(readOnly = false)
	public void save(TouristEmotionStack touristEmotionStack) {
		super.save(touristEmotionStack);
	}
	
	@Transactional(readOnly = false)
	public void delete(TouristEmotionStack touristEmotionStack) {
		super.delete(touristEmotionStack);
	}
	
}