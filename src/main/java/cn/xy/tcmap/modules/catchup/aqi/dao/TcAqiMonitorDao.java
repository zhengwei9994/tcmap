/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.aqi.dao;

import cn.xy.tcmap.common.persistence.CrudDao;
import cn.xy.tcmap.common.persistence.annotation.MyBatisDao;
import cn.xy.tcmap.modules.catchup.aqi.entity.TcAqiMonitor;

/**
 * 气象数据DAO接口
 * @author 王浩
 * @version 2019-12-03
 */
@MyBatisDao
public interface TcAqiMonitorDao extends CrudDao<TcAqiMonitor> {
	
}