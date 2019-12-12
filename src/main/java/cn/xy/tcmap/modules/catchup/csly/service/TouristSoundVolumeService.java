/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csly.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.service.CrudService;
import cn.xy.tcmap.modules.catchup.csly.entity.TouristSoundVolume;
import cn.xy.tcmap.modules.catchup.csly.dao.TouristSoundVolumeDao;

/**
 * 游客声量Service
 * @author wufan
 * @version 2019-08-16
 */
@Service
@Transactional(readOnly = true)
public class TouristSoundVolumeService extends CrudService<TouristSoundVolumeDao, TouristSoundVolume> {

	public TouristSoundVolume get(String id) {
		return super.get(id);
	}
	
	public List<TouristSoundVolume> findList(TouristSoundVolume touristSoundVolume) {
		return super.findList(touristSoundVolume);
	}
	
	public Page<TouristSoundVolume> findPage(Page<TouristSoundVolume> page, TouristSoundVolume touristSoundVolume) {
		return super.findPage(page, touristSoundVolume);
	}
	
	@Transactional(readOnly = false)
	public void save(TouristSoundVolume touristSoundVolume) {
		super.save(touristSoundVolume);
	}
	
	@Transactional(readOnly = false)
	public void delete(TouristSoundVolume touristSoundVolume) {
		super.delete(touristSoundVolume);
	}
	
}