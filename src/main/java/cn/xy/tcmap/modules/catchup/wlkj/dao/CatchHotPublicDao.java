/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.wlkj.dao;

import cn.xy.tcmap.common.persistence.CrudDao;
import cn.xy.tcmap.common.persistence.annotation.MyBatisDao;
import cn.xy.tcmap.modules.catchup.wlkj.entity.CatchHotPublic;

import java.util.List;

/**
 * 热点舆情DAO接口
 * @author xuzhou
 * @version 2018-05-28
 */
@MyBatisDao
public interface CatchHotPublicDao extends CrudDao<CatchHotPublic> {
    public int insert(CatchHotPublic catchHotPublic);
    public int update(CatchHotPublic catchHotPublic);

    public List<CatchHotPublic> hotData();
	
}