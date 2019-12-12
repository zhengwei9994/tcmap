/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.zhcs.dao;

import cn.xy.tcmap.common.persistence.CrudDao;
import cn.xy.tcmap.common.persistence.annotation.MyBatisDao;
import cn.xy.tcmap.modules.catchup.zhcs.entity.CatchTourCountAnalysis;

/**
 * 旅游趋势分析DAO接口
 * @author wl
 * @version 2018-09-28
 */
@MyBatisDao
public interface CatchTourCountAnalysisDao extends CrudDao<CatchTourCountAnalysis> {
	
}