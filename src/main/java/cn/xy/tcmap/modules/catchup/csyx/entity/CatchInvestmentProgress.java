package cn.xy.tcmap.modules.catchup.csyx.entity;

import cn.xy.tcmap.common.persistence.DataEntity;

/**
 * code is far away from bug with the animal protecting
 *
 * @Description : ${Desc}
 * ---------------------------------
 * @Author : dell
 * @Date : Create in 2018/9/19
 */
public class CatchInvestmentProgress extends DataEntity<CatchInvestmentProgress> {

    private String projectName;        //  项目名称
    private String unit;               //单位
    private double completedRatio;     //累计投资占比
    private double annualTask;      //投资金额

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public double getCompletedRatio() {
        return completedRatio;
    }

    public void setCompletedRatio(double completedRatio) {
        this.completedRatio = completedRatio;
    }

    public double getAnnualTask() {
        return annualTask;
    }

    public void setAnnualTask(double annualTask) {
        this.annualTask = annualTask;
    }
}
