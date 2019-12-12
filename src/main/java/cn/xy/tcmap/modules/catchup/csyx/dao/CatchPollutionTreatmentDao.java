/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csyx.dao;

import cn.xy.tcmap.common.persistence.CrudDao;
import cn.xy.tcmap.common.persistence.annotation.MyBatisDao;
import cn.xy.tcmap.modules.catchup.csyx.entity.CatchPollutionTreatment;

import java.util.List;

/**
 * 重拳治污染DAO接口
 * @author xuzhou
 * @version 2018-05-24
 */
@MyBatisDao
public interface CatchPollutionTreatmentDao extends CrudDao<CatchPollutionTreatment> {
    public List<CatchPollutionTreatment> pollutionTodayData(CatchPollutionTreatment catchPollutionTreatment);
	
}