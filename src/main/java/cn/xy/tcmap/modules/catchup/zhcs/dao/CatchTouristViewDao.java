/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.zhcs.dao;

import cn.xy.tcmap.common.persistence.CrudDao;
import cn.xy.tcmap.common.persistence.annotation.MyBatisDao;
import cn.xy.tcmap.modules.catchup.zhcs.entity.CatchTouristView;

/**
 * 旅游资产DAO接口
 * @author zzc
 * @version 2018-11-07
 */
@MyBatisDao
public interface CatchTouristViewDao extends CrudDao<CatchTouristView> {
    //各级景点数量
    String totalData(String rank);
}