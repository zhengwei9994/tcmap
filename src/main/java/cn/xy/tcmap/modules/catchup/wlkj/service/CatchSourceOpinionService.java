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
import cn.xy.tcmap.modules.catchup.wlkj.entity.CatchSourceOpinion;
import cn.xy.tcmap.modules.catchup.wlkj.dao.CatchSourceOpinionDao;

/**
 * 舆情来源Service
 * @author xuzhou
 * @version 2018-05-28
 */
@Service
@Transactional(readOnly = true)
public class CatchSourceOpinionService extends CrudService<CatchSourceOpinionDao, CatchSourceOpinion> {
	@Autowired
	private CatchSourceOpinionDao catchSourceOpinionDao;
	public CatchSourceOpinion get(String id) {
		return super.get(id);
	}
	
	public List<CatchSourceOpinion> findList(CatchSourceOpinion catchSourceOpinion) {
		return super.findList(catchSourceOpinion);
	}
	
	public Page<CatchSourceOpinion> findPage(Page<CatchSourceOpinion> page, CatchSourceOpinion catchSourceOpinion) {
		return super.findPage(page, catchSourceOpinion);
	}
	public List<CatchSourceOpinion> sourceData(){
		return catchSourceOpinionDao.sourceData();
	}
	@Transactional(readOnly = false)
	public void save(CatchSourceOpinion catchSourceOpinion) {
		super.save(catchSourceOpinion);
	}
	
	@Transactional(readOnly = false)
	public void delete(CatchSourceOpinion catchSourceOpinion) {
		super.delete(catchSourceOpinion);
	}
	
}