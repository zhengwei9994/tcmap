package cn.xy.tcmap.modules.catchup.csyx.entity;

import cn.xy.tcmap.common.persistence.DataEntity;

/**
 * @Author guoxunquan
 * @Title CatchEconomicRate
 * @Description TODO
 * @Date 2018-09-18 13:53
 * @Package cn.xy.tcmap.modules.catchup.csyx.entity
 **/
public class CatchEconomicRate extends DataEntity<CatchEconomicRate> {
    private static final long serialVersionUID = 1L;
    private String nyear;		// 年份
    private String indexName;		// 指标项
    private String areaName;		// 区县
    private String indicatorsUnit;		// 单位
    private Double indicators;    //指标数量

    public String getNyear() {
        return nyear;
    }

    public void setNyear(String nyear) {
        this.nyear = nyear;
    }

    public String getIndexName() {
        return indexName;
    }

    public void setIndexName(String indexName) {
        this.indexName = indexName;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getIndicatorsUnit() {
        return indicatorsUnit;
    }

    public void setIndicatorsUnit(String indicatorsUnit) {
        this.indicatorsUnit = indicatorsUnit;
    }

    public Double getIndicators() {
        return indicators;
    }

    public void setIndicators(Double indicators) {
        this.indicators = indicators;
    }
}
