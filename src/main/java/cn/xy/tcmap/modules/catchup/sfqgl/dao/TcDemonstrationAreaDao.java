/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.sfqgl.dao;

import cn.xy.tcmap.common.persistence.CrudDao;
import cn.xy.tcmap.common.persistence.annotation.MyBatisDao;
import cn.xy.tcmap.modules.catchup.sfqgl.entity.TcDemonstrationArea;

/**
 * 示范区管理DAO接口
 * @author wufan
 * @version 2019-12-04
 */
@MyBatisDao
public interface TcDemonstrationAreaDao extends CrudDao<TcDemonstrationArea> {

    public int isRepeat(TcDemonstrationArea tcDemonstrationArea);
	
}