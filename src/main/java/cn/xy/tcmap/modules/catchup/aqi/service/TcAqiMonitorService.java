/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.aqi.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.service.CrudService;
import cn.xy.tcmap.modules.catchup.aqi.entity.TcAqiMonitor;
import cn.xy.tcmap.modules.catchup.aqi.dao.TcAqiMonitorDao;

/**
 * 气象数据Service
 * @author 王浩
 * @version 2019-12-03
 */
@Service
@Transactional(readOnly = true)
public class TcAqiMonitorService extends CrudService<TcAqiMonitorDao, TcAqiMonitor> {

	public TcAqiMonitor get(String id) {
		return super.get(id);
	}
	
	public List<TcAqiMonitor> findList(TcAqiMonitor tcAqiMonitor) {
		return super.findList(tcAqiMonitor);
	}
	
	public Page<TcAqiMonitor> findPage(Page<TcAqiMonitor> page, TcAqiMonitor tcAqiMonitor) {
		return super.findPage(page, tcAqiMonitor);
	}
	
	@Transactional(readOnly = false)
	public void save(TcAqiMonitor tcAqiMonitor) {
			super.save(tcAqiMonitor);
	}
	
	@Transactional(readOnly = false)
	public void delete(TcAqiMonitor tcAqiMonitor) {
		super.delete(tcAqiMonitor);
	}

}