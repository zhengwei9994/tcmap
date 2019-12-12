/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.fzjc.dao;

import cn.xy.tcmap.common.persistence.CrudDao;
import cn.xy.tcmap.common.persistence.annotation.MyBatisDao;
import cn.xy.tcmap.modules.catchup.fzjc.entity.CatchDataStatus;

/**
 * 指标数据运行状态DAO接口
 * @author gxq
 * @version 2018-10-22
 */
@MyBatisDao
public interface CatchDataStatusDao extends CrudDao<CatchDataStatus> {
	
}