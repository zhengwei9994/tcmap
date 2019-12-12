/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.fzjc.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.service.CrudService;
import cn.xy.tcmap.modules.catchup.fzjc.entity.CatchTalentAnalysis;
import cn.xy.tcmap.modules.catchup.fzjc.dao.CatchTalentAnalysisDao;

/**
 * 人才结构现状分析Service
 * @author wl
 * @version 2018-09-29
 */
@Service
@Transactional(readOnly = true)
public class CatchTalentAnalysisService extends CrudService<CatchTalentAnalysisDao, CatchTalentAnalysis> {

	public CatchTalentAnalysis get(String id) {
		return super.get(id);
	}
	
	public List<CatchTalentAnalysis> findList(CatchTalentAnalysis catchTalentAnalysis) {
		return super.findList(catchTalentAnalysis);
	}
	
	public Page<CatchTalentAnalysis> findPage(Page<CatchTalentAnalysis> page, CatchTalentAnalysis catchTalentAnalysis) {
		return super.findPage(page, catchTalentAnalysis);
	}
	
	@Transactional(readOnly = false)
	public void save(CatchTalentAnalysis catchTalentAnalysis) {
		super.save(catchTalentAnalysis);
	}
	
	@Transactional(readOnly = false)
	public void delete(CatchTalentAnalysis catchTalentAnalysis) {
		super.delete(catchTalentAnalysis);
	}
	
}