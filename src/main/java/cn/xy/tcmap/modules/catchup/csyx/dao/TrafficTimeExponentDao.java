/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csyx.dao;

import cn.xy.tcmap.common.persistence.CrudDao;
import cn.xy.tcmap.common.persistence.annotation.MyBatisDao;
import cn.xy.tcmap.modules.catchup.csyx.entity.TrafficTimeExponent;

/**
 * 交通时间指数DAO接口
 * @author wufan
 * @version 2019-10-18
 */
@MyBatisDao
public interface TrafficTimeExponentDao extends CrudDao<TrafficTimeExponent> {
	
}