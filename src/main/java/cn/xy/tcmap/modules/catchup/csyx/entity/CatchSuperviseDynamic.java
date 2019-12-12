package cn.xy.tcmap.modules.catchup.csyx.entity;

import cn.xy.tcmap.common.persistence.DataEntity;
import cn.xy.tcmap.modules.sys.entity.User;
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
public class CatchSuperviseDynamic extends DataEntity<CatchSuperviseDynamic> {
    private String projectName;//项目名称
    private String content;        // 项目内容
    private Date createTime;       //项目开始事件
    private String hasRead;      //是否已读
    private String hasReadLabel;      //是否已读标签
    private User user;          //用户
    private CatchProjectConstruction catchProjectConstruction; //项目

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getHasRead() {
        return hasRead;
    }

    public void setHasRead(String hasRead) {
        this.hasRead = hasRead;
    }

    public String getHasReadLabel() {
        return hasReadLabel;
    }

    public void setHasReadLabel(String hasReadLabel) {
        this.hasReadLabel = hasReadLabel;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public CatchProjectConstruction getCatchProjectConstruction() {
        return catchProjectConstruction;
    }

    public void setCatchProjectConstruction(CatchProjectConstruction catchProjectConstruction) {
        this.catchProjectConstruction = catchProjectConstruction;
    }

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
    
}
