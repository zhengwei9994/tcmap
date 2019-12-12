package cn.xy.tcmap.modules.catchup.csyx.dao;

import cn.xy.tcmap.common.persistence.CrudDao;
import cn.xy.tcmap.common.persistence.annotation.MyBatisDao;
import cn.xy.tcmap.modules.catchup.csyx.entity.CatchEconomicRate;

import java.util.List;

/**
 * @Author guoxunquan
 * @Title CatchEconomicRateDao
 * @Description TODO
 * @Package cn.xy.tcmap.modules.catchup.csyx.dao
 * @Date 2018-09-18 13:57
 **/
@MyBatisDao
public interface CatchEconomicRateDao extends CrudDao<CatchEconomicRate> {
     List<CatchEconomicRate> catchEconomicRateList(CatchEconomicRate catchEconomicRate);
}
