/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.sfmk.dao;

import cn.xy.tcmap.common.persistence.CrudDao;
import cn.xy.tcmap.common.persistence.annotation.MyBatisDao;
import cn.xy.tcmap.modules.catchup.sfmk.entity.AlgorithmicResult;

/**
 * 算法结果表DAO接口
 * @author tuo
 * @version 2019-09-03
 */
@MyBatisDao
public interface AlgorithmicResultDao extends CrudDao<AlgorithmicResult> {
	
}