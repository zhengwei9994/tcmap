/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.wlkj.dao;

import cn.xy.tcmap.common.persistence.CrudDao;
import cn.xy.tcmap.common.persistence.annotation.MyBatisDao;
import cn.xy.tcmap.modules.catchup.wlkj.entity.CatchEarlyWarning;

import java.util.List;

/**
 * 红色预警DAO接口
 * @author xuzhou
 * @version 2018-05-28
 */
@MyBatisDao
public interface CatchEarlyWarningDao extends CrudDao<CatchEarlyWarning> {
    public List<CatchEarlyWarning> earlyData(CatchEarlyWarning catchEarlyWarning);
	
}