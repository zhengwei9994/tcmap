/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.zhcs.dao;

import cn.xy.tcmap.common.persistence.CrudDao;
import cn.xy.tcmap.common.persistence.annotation.MyBatisDao;
import cn.xy.tcmap.modules.catchup.zhcs.entity.CatchScenicSpot;

import java.util.HashMap;
import java.util.List;

/**
 * 旅游资产DAO接口
 * @author liuyang
 * @version 2018-06-11
 */
@MyBatisDao
public interface CatchScenicSpotDao extends CrudDao<CatchScenicSpot> {
    //各级景点数量
    List<HashMap> totalData(CatchScenicSpot catchScenicSpot);
}