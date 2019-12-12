package cn.xy.tcmap.modules.catchup.csyx.entity;

import cn.xy.tcmap.common.persistence.DataEntity;

/**
 * @Author guoxunquan
 * @Title CatchAirQuality
 * @Description TODO
 * @Package cn.xy.tcmap.modules.catchup.csyx.entity
 * @Date 2018-09-19 10:48
 **/
public class CatchAirQuality extends DataEntity<CatchAirQuality> {
    private String date;//日期
    private int number;//质量值

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
