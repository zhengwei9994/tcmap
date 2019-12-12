/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.safecity.dao;

import cn.xy.tcmap.common.persistence.CrudDao;
import cn.xy.tcmap.common.persistence.annotation.MyBatisDao;
import cn.xy.tcmap.modules.safecity.entity.SafeTraffic;

/**
 * 交通违法案件DAO接口
 * @author tuo
 * @version 2019-12-05
 */
@MyBatisDao
public interface SafeTrafficDao extends CrudDao<SafeTraffic> {
	
}