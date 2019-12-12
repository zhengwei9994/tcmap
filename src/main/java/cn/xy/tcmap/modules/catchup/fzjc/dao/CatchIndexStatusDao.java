/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.fzjc.dao;

import cn.xy.tcmap.common.persistence.CrudDao;
import cn.xy.tcmap.common.persistence.annotation.MyBatisDao;
import cn.xy.tcmap.modules.catchup.fzjc.entity.CatchIndexStatus;

import java.util.List;

/**
 * 指标数据运行状态DAO接口
 * @author liuyang
 * @version 2018-05-29
 */
@MyBatisDao
public interface CatchIndexStatusDao extends CrudDao<CatchIndexStatus> {

    List<CatchIndexStatus> groupByindexType(CatchIndexStatus catchIndexStatus);


}