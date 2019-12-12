/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.zhcs.dao;

import cn.xy.tcmap.common.persistence.CrudDao;
import cn.xy.tcmap.common.persistence.annotation.MyBatisDao;
import cn.xy.tcmap.modules.catchup.zhcs.entity.CatchPatentCount;

/**
 * 专利数量统计DAO接口
 * @author liuyang
 * @version 2018-06-05
 */
@MyBatisDao
public interface CatchPatentCountDao extends CrudDao<CatchPatentCount> {
	
}