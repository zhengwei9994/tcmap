/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.sfmk.dao;

import cn.xy.tcmap.common.persistence.CrudDao;
import cn.xy.tcmap.common.persistence.annotation.MyBatisDao;
import cn.xy.tcmap.modules.catchup.sfmk.entity.AlgorithmicExample;

/**
 * 模型实例管理表DAO接口
 * @author wufan
 * @version 2019-08-22
 */
@MyBatisDao
public interface AlgorithmicExampleDao extends CrudDao<AlgorithmicExample> {
	
}