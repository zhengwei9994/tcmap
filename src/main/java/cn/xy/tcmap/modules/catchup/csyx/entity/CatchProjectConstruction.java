package cn.xy.tcmap.modules.catchup.csyx.entity;

import cn.xy.tcmap.common.persistence.DataEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * code is far away from bug with the animal protecting
 * ┏┓　　　┏┓
 * ┏┛┻━━━┛┻┓
 * ┃　　　　　　　┃
 * ┃　　　━　　　┃
 * ┃　┳┛　┗┳　┃
 * ┃　　　　　　　┃
 * ┃　　　┻　　　┃
 * ┃　　　　　　　┃
 * ┗━┓　　　┏━┛
 * 　　┃　　　┃神兽保佑
 * 　　┃　　　┃代码无BUG！
 * 　　┃　　　┗━━━┓
 * 　　┃　　　　　　　┣┓
 * 　　┃　　　　　　　┏┛
 * 　　┗┓┓┏━┳┓┏┛
 * 　　　┃┫┫　┃┫┫
 * 　　　┗┻┛　┗┻┛
 *
 * @Description : ${Desc}
 * ---------------------------------
 * @Author : dell
 * @Date : Create in 2018/9/19
 */
public class CatchProjectConstruction extends DataEntity<CatchProjectConstruction> {

    private String projectName;        // 项目名称
    private String leadership;        //负责人
    private Date startTime;       //项目开始时间
    private Double annualTask;        // 项目金额
    private Double plannedInvestment;        // 投资金额
    private Double progress;        // 进度
    private String projectIntro;        // 描述
    private String hostUnit;        //项目单位
    private String imagePath;      //图片路径
    private String leaderImagePath;//负责人头像路径

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getLeadership() {
        return leadership;
    }

    public void setLeadership(String leadership) {
        this.leadership = leadership;
    }
    @JsonFormat(pattern="yyyy/MM/dd")
    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Double getAnnualTask() {
        return annualTask;
    }

    public void setAnnualTask(Double annualTask) {
        this.annualTask = annualTask;
    }

    public Double getPlannedInvestment() {
        return plannedInvestment;
    }

    public void setPlannedInvestment(Double plannedInvestment) {
        this.plannedInvestment = plannedInvestment;
    }

    public Double getProgress() {
        return progress;
    }

    public void setProgress(Double progress) {
        this.progress = progress;
    }

    public String getProjectIntro() {
        return projectIntro;
    }

    public void setProjectIntro(String projectIntro) {
        this.projectIntro = projectIntro;
    }

    public String getHostUnit() {
        return hostUnit;
    }

    public void setHostUnit(String hostUnit) {
        this.hostUnit = hostUnit;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getLeaderImagePath() {
        return leaderImagePath;
    }

    public void setLeaderImagePath(String leaderImagePath) {
        this.leaderImagePath = leaderImagePath;
    }
}
