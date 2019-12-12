/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.wlkj.dao;

import cn.xy.tcmap.common.persistence.CrudDao;
import cn.xy.tcmap.common.persistence.annotation.MyBatisDao;
import cn.xy.tcmap.modules.catchup.wlkj.entity.CatchHotSearch;

/**
 * 热搜词汇DAO接口
 * @author xuzhou
 * @version 2018-05-30
 */
@MyBatisDao
public interface CatchHotSearchDao extends CrudDao<CatchHotSearch> {
	
}