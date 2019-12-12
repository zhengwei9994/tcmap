package cn.xy.tcmap.modules.catchup.csyx.dao;

import cn.xy.tcmap.common.persistence.CrudDao;
import cn.xy.tcmap.common.persistence.annotation.MyBatisDao;
import cn.xy.tcmap.modules.catchup.csyx.entity.CatchKeyproject;
import cn.xy.tcmap.modules.catchup.csyx.entity.CatchKeyprojectClass;

import java.util.HashMap;
import java.util.List;

@MyBatisDao
public interface CatchKeyprojectClassDao extends CrudDao<CatchKeyprojectClass> {
    public List<HashMap> totalData(CatchKeyproject catchKeyproject);
    public List<HashMap> keyprojectNum(CatchKeyproject catchKeyproject);
    public List<HashMap> keyprojectProgress(CatchKeyproject catchKeyproject);
}
