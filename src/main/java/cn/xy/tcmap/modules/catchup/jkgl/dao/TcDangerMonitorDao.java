/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.jkgl.dao;

import cn.xy.tcmap.common.persistence.CrudDao;
import cn.xy.tcmap.common.persistence.annotation.MyBatisDao;
import cn.xy.tcmap.modules.catchup.jkgl.entity.TcDangerMonitor;

/**
 * 危险源监控DAO接口
 * @author wufan
 * @version 2019-12-05
 */
@MyBatisDao
public interface TcDangerMonitorDao extends CrudDao<TcDangerMonitor> {
	
}