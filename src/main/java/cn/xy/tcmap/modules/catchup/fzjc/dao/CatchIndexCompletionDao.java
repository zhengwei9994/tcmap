/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.fzjc.dao;

import cn.xy.tcmap.common.persistence.CrudDao;
import cn.xy.tcmap.common.persistence.annotation.MyBatisDao;
import cn.xy.tcmap.modules.catchup.fzjc.entity.CatchIndexCompletion;

import java.util.List;

/**
 * 运行指标完成率DAO接口
 * @author liuyang
 * @version 2018-05-29
 */
@MyBatisDao
public interface CatchIndexCompletionDao extends CrudDao<CatchIndexCompletion> {

    List<CatchIndexCompletion> groupByindexType(CatchIndexCompletion catchIndexCompletion);


}