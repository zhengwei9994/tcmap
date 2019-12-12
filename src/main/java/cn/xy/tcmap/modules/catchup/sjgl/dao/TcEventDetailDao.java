/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.sjgl.dao;

import cn.xy.tcmap.common.persistence.CrudDao;
import cn.xy.tcmap.common.persistence.annotation.MyBatisDao;
import cn.xy.tcmap.modules.catchup.sjgl.entity.TcEventDetail;

import java.util.List;

/**
 * 事件数据详情DAO接口
 * @author wh
 * @version 2019-12-05
 */
@MyBatisDao
public interface TcEventDetailDao extends CrudDao<TcEventDetail> {

    List<TcEventDetail> findEventType();
}