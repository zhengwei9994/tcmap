package cn.xy.tcmap.modules.catchup.csyx.entity;

import cn.xy.tcmap.common.persistence.DataEntity;

/**
 * @Author guoxunquan
 * @Title CatchSwageTreat
 * @Description TODO
 * @Package cn.xy.tcmap.modules.catchup.csyx.entity
 * @Date 2018-09-19 13:53
 **/
public class CatchSwageTreat extends DataEntity<CatchSwageTreat> {
    private String nyear;//年份
    private String month;//月份
    private String rank;//等级
    private Double swageValue; //占比

    public String getNyear() {
        return nyear;
    }

    public void setNyear(String nyear) {
        this.nyear = nyear;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public Double getSwageValue() {
        return swageValue;
    }

    public void setSwageValue(Double swageValue) {
        this.swageValue = swageValue;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }
}
