/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csyx.dao;

import cn.xy.tcmap.common.persistence.CrudDao;
import cn.xy.tcmap.common.persistence.annotation.MyBatisDao;
import cn.xy.tcmap.modules.catchup.csyx.entity.CatchProvincesStatistical;

import java.util.List;

/**
 * 游客分析DAO接口
 * @author xuzhou
 * @version 2018-05-24
 */
@MyBatisDao
public interface CatchProvincesStatisticalDao extends CrudDao<CatchProvincesStatistical> {
    public CatchProvincesStatistical provincesData(CatchProvincesStatistical catchProvincesStatistical);
	
}