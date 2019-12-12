/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csyx.dao;

import cn.xy.tcmap.common.persistence.CrudDao;
import cn.xy.tcmap.common.persistence.annotation.MyBatisDao;
import cn.xy.tcmap.modules.catchup.csyx.entity.ShowChangeRate;

/**
 * 变化率DAO接口
 * @author 变化率
 * @version 2019-08-22
 */
@MyBatisDao
public interface ShowChangeRateDao extends CrudDao<ShowChangeRate> {
    ShowChangeRate  findByYear(ShowChangeRate year);
}