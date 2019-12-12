/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.fzjc.dao;

import cn.xy.tcmap.common.persistence.CrudDao;
import cn.xy.tcmap.common.persistence.annotation.MyBatisDao;
import cn.xy.tcmap.modules.catchup.fzjc.entity.CatchResidentIncome;

/**
 * 居民收入DAO接口
 * @author FSH
 * @version 2018-10-18
 */
@MyBatisDao
public interface CatchResidentIncomeDao extends CrudDao<CatchResidentIncome> {
	
}