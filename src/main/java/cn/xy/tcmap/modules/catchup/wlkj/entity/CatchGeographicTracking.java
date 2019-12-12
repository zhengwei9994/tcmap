package cn.xy.tcmap.modules.catchup.wlkj.entity;

import cn.xy.tcmap.common.persistence.DataEntity;

/**
 * @Author guoxunquan
 * @Title CatchGeographicTracking
 * @Description TODO
 * @Package cn.xy.tcmap.modules.catchup.wlkj.entity
 * @Date 2018-09-17 14:29
 **/
public class CatchGeographicTracking extends DataEntity<CatchGeographicTracking> {
      private static final long serialVersionUID = 1L;
      private String areaName;//区域名称
      private String trackNum;//数量

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getTrackNum() {
        return trackNum;
    }

    public void setTrackNum(String trackNum) {
        this.trackNum = trackNum;
    }
}
