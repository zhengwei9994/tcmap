/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csly.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.service.CrudService;
import cn.xy.tcmap.modules.catchup.csly.entity.TouristWcSound;
import cn.xy.tcmap.modules.catchup.csly.dao.TouristWcSoundDao;

/**
 * 厕所声量Service
 * @author wufan
 * @version 2019-08-16
 */
@Service
@Transactional(readOnly = true)
public class TouristWcSoundService extends CrudService<TouristWcSoundDao, TouristWcSound> {

	public TouristWcSound get(String id) {
		return super.get(id);
	}
	
	public List<TouristWcSound> findList(TouristWcSound touristWcSound) {
		return super.findList(touristWcSound);
	}
	
	public Page<TouristWcSound> findPage(Page<TouristWcSound> page, TouristWcSound touristWcSound) {
		return super.findPage(page, touristWcSound);
	}
	
	@Transactional(readOnly = false)
	public void save(TouristWcSound touristWcSound) {
		super.save(touristWcSound);
	}
	
	@Transactional(readOnly = false)
	public void delete(TouristWcSound touristWcSound) {
		super.delete(touristWcSound);
	}
	
}