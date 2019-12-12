/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.wlkj.dao;

import cn.xy.tcmap.common.persistence.CrudDao;
import cn.xy.tcmap.common.persistence.annotation.MyBatisDao;
import cn.xy.tcmap.modules.catchup.wlkj.entity.CatchOpinionStatistics;

import java.util.List;

/**
 * 舆情统计DAO接口
 * @author xuzhou
 * @version 2018-05-28
 */
@MyBatisDao
public interface CatchOpinionStatisticsDao extends CrudDao<CatchOpinionStatistics> {

    public List<CatchOpinionStatistics> opinionStatistics(CatchOpinionStatistics catchOpinionStatistics);
	
}