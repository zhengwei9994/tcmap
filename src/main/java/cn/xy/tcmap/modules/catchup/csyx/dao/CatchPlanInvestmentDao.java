package cn.xy.tcmap.modules.catchup.csyx.dao;

import cn.xy.tcmap.common.persistence.CrudDao;
import cn.xy.tcmap.common.persistence.annotation.MyBatisDao;
import cn.xy.tcmap.modules.catchup.csyx.entity.CatchPlanInvestment;

import java.util.HashMap;
import java.util.List;

/**
 * 年计划总投资DAO接口
 * @author xuzhou
 * @version 2018-05-24
 */
@MyBatisDao
public interface CatchPlanInvestmentDao  extends CrudDao<CatchPlanInvestment>{


   public List<HashMap> yearPlanData(CatchPlanInvestment catchPlanInvestment);
}
