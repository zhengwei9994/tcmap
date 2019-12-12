/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.sjgl.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.service.CrudService;
import cn.xy.tcmap.modules.catchup.sjgl.entity.TcManagerInfo;
import cn.xy.tcmap.modules.catchup.sjgl.dao.TcManagerInfoDao;

/**
 * 责任人管理Service
 * @author wh
 * @version 2019-12-05
 */
@Service
@Transactional(readOnly = true)
public class TcManagerInfoService extends CrudService<TcManagerInfoDao, TcManagerInfo> {

	public TcManagerInfo get(String id) {
		return super.get(id);
	}
	
	public List<TcManagerInfo> findList(TcManagerInfo tcManagerInfo) {
		return super.findList(tcManagerInfo);
	}
	
	public Page<TcManagerInfo> findPage(Page<TcManagerInfo> page, TcManagerInfo tcManagerInfo) {
		return super.findPage(page, tcManagerInfo);
	}
	
	@Transactional(readOnly = false)
	public void save(TcManagerInfo tcManagerInfo) {
		super.save(tcManagerInfo);
	}
	
	@Transactional(readOnly = false)
	public void delete(TcManagerInfo tcManagerInfo) {
		super.delete(tcManagerInfo);
	}
	
}