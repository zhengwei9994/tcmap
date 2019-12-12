/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.wlkj.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.service.CrudService;
import cn.xy.tcmap.modules.catchup.wlkj.entity.CatchSpotOpinion;
import cn.xy.tcmap.modules.catchup.wlkj.dao.CatchSpotOpinionDao;

/**
 * 全国舆情热点Service
 * @author xuzhou
 * @version 2018-05-28
 */
@Service
@Transactional(readOnly = true)
public class CatchSpotOpinionService extends CrudService<CatchSpotOpinionDao, CatchSpotOpinion> {
	@Autowired
	private CatchSpotOpinionDao catchSpotOpinionDao;
	public CatchSpotOpinion get(String id) {
		return super.get(id);
	}
	
	public List<CatchSpotOpinion> findList(CatchSpotOpinion catchSpotOpinion) {
		return super.findList(catchSpotOpinion);
	}
	
	public Page<CatchSpotOpinion> findPage(Page<CatchSpotOpinion> page, CatchSpotOpinion catchSpotOpinion) {
		return super.findPage(page, catchSpotOpinion);
	}
	public List<CatchSpotOpinion> spotData(){
		return catchSpotOpinionDao.spotData();
	}
	@Transactional(readOnly = false)
	public void save(CatchSpotOpinion catchSpotOpinion) {
		super.save(catchSpotOpinion);
	}
	
	@Transactional(readOnly = false)
	public void delete(CatchSpotOpinion catchSpotOpinion) {
		super.delete(catchSpotOpinion);
	}
	
}