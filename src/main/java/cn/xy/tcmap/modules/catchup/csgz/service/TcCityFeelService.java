/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csgz.service;

import java.util.List;

import cn.xy.tcmap.modules.sys.service.AreaService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.service.CrudService;
import cn.xy.tcmap.modules.catchup.csgz.entity.TcCityFeel;
import cn.xy.tcmap.modules.catchup.csgz.dao.TcCityFeelDao;

/**
 * 城市感知数据管理Service
 * @author wh
 * @version 2019-12-04
 */
@Service
@Transactional(readOnly = true)
public class TcCityFeelService extends CrudService<TcCityFeelDao, TcCityFeel> {

	public TcCityFeel get(String id) {
		return super.get(id);
	}
	
	public List<TcCityFeel> findList(TcCityFeel tcCityFeel) {
		return super.findList(tcCityFeel);
	}
	
	public Page<TcCityFeel> findPage(Page<TcCityFeel> page, TcCityFeel tcCityFeel) {
		return super.findPage(page, tcCityFeel);
	}
	
	@Transactional(readOnly = false)
	public void save(TcCityFeel tcCityFeel) {
		super.save(tcCityFeel);
	}
	
	@Transactional(readOnly = false)
	public void delete(TcCityFeel tcCityFeel) {
		super.delete(tcCityFeel);
	}

}