/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.fzjc.dao;

import cn.xy.tcmap.common.persistence.CrudDao;
import cn.xy.tcmap.common.persistence.annotation.MyBatisDao;
import cn.xy.tcmap.modules.catchup.fzjc.entity.CatchElectronicCategory;

/**
 * 电子证照类别管理DAO接口
 * @author gxq
 * @version 2018-10-19
 */
@MyBatisDao
public interface CatchElectronicCategoryDao extends CrudDao<CatchElectronicCategory> {
	
}