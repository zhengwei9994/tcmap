/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csyx.dao;

import cn.xy.tcmap.common.persistence.CrudDao;
import cn.xy.tcmap.common.persistence.annotation.MyBatisDao;
import cn.xy.tcmap.modules.catchup.csyx.entity.BusinessBalance;

/**
 * 金融机构本外币存款余额DAO接口
 * @author wufan
 * @version 2019-07-31
 */
@MyBatisDao
public interface BusinessBalanceDao extends CrudDao<BusinessBalance> {
	
}