/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.zhcs.dao;

import cn.xy.tcmap.common.persistence.CrudDao;
import cn.xy.tcmap.common.persistence.annotation.MyBatisDao;
import cn.xy.tcmap.modules.catchup.zhcs.entity.CatchStayTime;

/**
 * 停留时长分布DAO接口
 * @author wl
 * @version 2018-09-29
 */
@MyBatisDao
public interface CatchStayTimeDao extends CrudDao<CatchStayTime> {
	
}