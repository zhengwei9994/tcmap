/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.fzjc.dao;

import cn.xy.tcmap.common.persistence.CrudDao;
import cn.xy.tcmap.common.persistence.annotation.MyBatisDao;
import cn.xy.tcmap.modules.catchup.fzjc.entity.CatchElectronicEvidence;

/**
 * 电子证照使用率DAO接口
 * @author gxq
 * @version 2018-10-19
 */
@MyBatisDao
public interface CatchElectronicEvidenceDao extends CrudDao<CatchElectronicEvidence> {
	
}