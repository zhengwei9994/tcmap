/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.sfqgl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.service.CrudService;
import cn.xy.tcmap.modules.catchup.sfqgl.entity.TcDemonstrationArea;
import cn.xy.tcmap.modules.catchup.sfqgl.dao.TcDemonstrationAreaDao;

/**
 * 示范区管理Service
 * @author wufan
 * @version 2019-12-04
 */
@Service
@Transactional(readOnly = true)
public class TcDemonstrationAreaService extends CrudService<TcDemonstrationAreaDao, TcDemonstrationArea> {

	@Autowired
 	private TcDemonstrationAreaDao tcDemonstrationAreaDao;

	public TcDemonstrationArea get(String id) {
		return super.get(id);
	}
	
	public List<TcDemonstrationArea> findList(TcDemonstrationArea tcDemonstrationArea) {
		return super.findList(tcDemonstrationArea);
	}

	public boolean isRepeat(TcDemonstrationArea tcDemonstrationArea){
		if(tcDemonstrationAreaDao.isRepeat(tcDemonstrationArea)>0){
			return true;
		}
		return false;
	}

	public Page<TcDemonstrationArea> findPage(Page<TcDemonstrationArea> page, TcDemonstrationArea tcDemonstrationArea) {
		return super.findPage(page, tcDemonstrationArea);
	}
	
	@Transactional(readOnly = false)
	public void save(TcDemonstrationArea tcDemonstrationArea) {
		super.save(tcDemonstrationArea);
	}
	
	@Transactional(readOnly = false)
	public void delete(TcDemonstrationArea tcDemonstrationArea) {
		super.delete(tcDemonstrationArea);
	}
	
}