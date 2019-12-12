package cn.xy.tcmap.modules.catchup.csyx.dao;

import cn.xy.tcmap.common.persistence.CrudDao;
import cn.xy.tcmap.common.persistence.annotation.MyBatisDao;
import cn.xy.tcmap.modules.catchup.csyx.entity.CatchKeyproject;

import java.util.List;

@MyBatisDao
public interface CatchKeyprojectDao extends CrudDao<CatchKeyproject> {


    public List<CatchKeyproject> keyprojectTypeData(CatchKeyproject catchKeyproject);

}
