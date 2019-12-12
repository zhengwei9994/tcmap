/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.fzjc.dao;

import cn.xy.tcmap.common.persistence.CrudDao;
import cn.xy.tcmap.common.persistence.annotation.MyBatisDao;
import cn.xy.tcmap.modules.catchup.fzjc.entity.CatchPractitioners;

/**
 * 劳动就业占比DAO接口
 * @author gxq
 * @version 2018-10-18
 */
@MyBatisDao
public interface CatchPractitionersDao extends CrudDao<CatchPractitioners> {
	
}