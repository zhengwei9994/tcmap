/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csyx.dao;

import cn.xy.tcmap.common.persistence.CrudDao;
import cn.xy.tcmap.common.persistence.annotation.MyBatisDao;
import cn.xy.tcmap.modules.catchup.csyx.entity.CatchAqiparam;

import java.util.List;

/**
 * 空气质量DAO接口
 * @author xuzhou
 * @version 2018-05-24
 */
@MyBatisDao
public interface CatchAqiparamDao extends CrudDao<CatchAqiparam> {
    public int insert(CatchAqiparam catchAqiparam);
    public int update(CatchAqiparam catchAqiparam);
    public List<CatchAqiparam> aqiparamData();
}