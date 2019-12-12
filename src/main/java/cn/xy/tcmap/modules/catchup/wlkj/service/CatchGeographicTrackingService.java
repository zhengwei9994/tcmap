package cn.xy.tcmap.modules.catchup.wlkj.service;

import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.service.CrudService;
import cn.xy.tcmap.modules.catchup.wlkj.dao.CatchGeographicTrackingDao;
import cn.xy.tcmap.modules.catchup.wlkj.entity.CatchEarlyWarning;
import cn.xy.tcmap.modules.catchup.wlkj.entity.CatchGeographicTracking;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author guoxunquan
 * @Title CatchGeographicTrackingService
 * @Description TODO
 * @Package cn.xy.tcmap.modules.catchup.wlkj.service
 * @Date 2018-09-17 14:28
 **/
@Service
@Transactional(readOnly = true)
public class CatchGeographicTrackingService extends CrudService<CatchGeographicTrackingDao,CatchGeographicTracking> {
    public CatchGeographicTracking get(String id) {
        return super.get(id);
    }

    public List<CatchGeographicTracking> findList(CatchGeographicTracking catchGeographicTracking) {
        return super.findList(catchGeographicTracking);
    }

    public Page<CatchGeographicTracking> findPage(Page<CatchGeographicTracking> page, CatchGeographicTracking catchGeographicTracking) {
        return super.findPage(page, catchGeographicTracking);
    }

    @Transactional(readOnly = false)
    public void save(CatchGeographicTracking catchGeographicTracking) {
        super.save(catchGeographicTracking);
    }

    @Transactional(readOnly = false)
    public void delete(CatchGeographicTracking catchGeographicTracking) {
        super.delete(catchGeographicTracking);
    }
}

