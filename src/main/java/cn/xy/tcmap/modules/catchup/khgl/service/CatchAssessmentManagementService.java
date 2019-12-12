/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.khgl.service;

import java.util.List;

import cn.xy.tcmap.common.utils.CacheUtils;
import cn.xy.tcmap.common.utils.EhCacheUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.service.CrudService;
import cn.xy.tcmap.modules.catchup.khgl.entity.CatchAssessmentManagement;
import cn.xy.tcmap.modules.catchup.khgl.dao.CatchAssessmentManagementDao;

/**
 * 考核管理Service
 *
 * @author wufan
 * @version 2019-12-03
 */
@Service
@Transactional(readOnly = true)
public class CatchAssessmentManagementService extends CrudService<CatchAssessmentManagementDao, CatchAssessmentManagement> {

    public CatchAssessmentManagement get(String id) {
        return super.get(id);
    }

    public List<CatchAssessmentManagement> findList(CatchAssessmentManagement catchAssessmentManagement) {
        return super.findList(catchAssessmentManagement);
    }

    public List<CatchAssessmentManagement> findAllList(){
		List<CatchAssessmentManagement> catchAssessmentManagementList = (List<CatchAssessmentManagement>) EhCacheUtils.get("CatchAssessmentManagementList");
		if (catchAssessmentManagementList != null && catchAssessmentManagementList.size() > 0) {
			return catchAssessmentManagementList;
		} else {
			catchAssessmentManagementList = super.findList(new CatchAssessmentManagement());
			EhCacheUtils.put("CatchAssessmentManagementList", catchAssessmentManagementList);
			return catchAssessmentManagementList;
		}
	}

    public Page<CatchAssessmentManagement> findPage(Page<CatchAssessmentManagement> page, CatchAssessmentManagement catchAssessmentManagement) {
        return super.findPage(page, catchAssessmentManagement);
    }

    @Transactional(readOnly = false)
    public void save(CatchAssessmentManagement catchAssessmentManagement) {
        super.save(catchAssessmentManagement);
        EhCacheUtils.remove("CatchAssessmentManagementList");
    }

    @Transactional(readOnly = false)
    public void delete(CatchAssessmentManagement catchAssessmentManagement) {
        super.delete(catchAssessmentManagement);
		EhCacheUtils.remove("CatchAssessmentManagementList");
    }

}