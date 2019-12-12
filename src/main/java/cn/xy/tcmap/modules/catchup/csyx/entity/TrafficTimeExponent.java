/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csyx.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import cn.xy.tcmap.common.persistence.DataEntity;

/**
 * 交通时间指数Entity
 * @author wufan
 * @version 2019-10-18
 */
public class TrafficTimeExponent extends DataEntity<TrafficTimeExponent> {

    private static final long serialVersionUID = 1L;
    private String value;		// 指数
    private String city;		// 城市
    private Date time;		// 时间

    public TrafficTimeExponent() {
        super();
    }

    public TrafficTimeExponent(String id){
        super(id);
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Length(min=0, max=255, message="城市长度必须介于 0 和 255 之间")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

}