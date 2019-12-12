/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.zhcs.dao;

import cn.xy.tcmap.common.persistence.CrudDao;
import cn.xy.tcmap.common.persistence.annotation.MyBatisDao;
import cn.xy.tcmap.modules.catchup.zhcs.entity.CatchTravelInfo;

import java.util.List;

/**
 * 旅游相关DAO接口
 * @author liuyang
 * @version 2018-05-29
 */
@MyBatisDao
public interface CatchTravelInfoDao extends CrudDao<CatchTravelInfo> {

    List<CatchTravelInfo> groupByindexType(CatchTravelInfo catchTravelInfo);


}