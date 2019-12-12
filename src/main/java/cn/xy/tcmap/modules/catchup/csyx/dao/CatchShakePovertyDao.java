/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csyx.dao;

import cn.xy.tcmap.common.persistence.CrudDao;
import cn.xy.tcmap.common.persistence.annotation.MyBatisDao;
import cn.xy.tcmap.modules.catchup.csyx.entity.CatchShakePoverty;

import java.util.HashMap;
import java.util.List;

/**
 * 精准务实抓脱贫DAO接口
 * @author xuzhou
 * @version 2018-05-24
 */
@MyBatisDao
public interface CatchShakePovertyDao extends CrudDao<CatchShakePoverty> {
	public List<HashMap> populationData(CatchShakePoverty catchShakePoverty);
	public List<CatchShakePoverty> histogramData(CatchShakePoverty catchShakePoverty);
}