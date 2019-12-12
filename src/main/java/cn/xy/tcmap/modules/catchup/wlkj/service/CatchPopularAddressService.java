/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.wlkj.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.service.CrudService;
import cn.xy.tcmap.modules.catchup.wlkj.entity.CatchPopularAddress;
import cn.xy.tcmap.modules.catchup.wlkj.dao.CatchPopularAddressDao;

/**
 * 热门公众号Service
 * @author guoxunquan
 * @version 2018-09-20
 */
@Service
@Transactional(readOnly = true)
public class CatchPopularAddressService extends CrudService<CatchPopularAddressDao, CatchPopularAddress> {

	public CatchPopularAddress get(String id) {
		return super.get(id);
	}
	
	public List<CatchPopularAddress> findList(CatchPopularAddress catchPopularAddress) {
		return super.findList(catchPopularAddress);
	}
	
	public Page<CatchPopularAddress> findPage(Page<CatchPopularAddress> page, CatchPopularAddress catchPopularAddress) {
		return super.findPage(page, catchPopularAddress);
	}
	
	@Transactional(readOnly = false)
	public void save(CatchPopularAddress catchPopularAddress) {
		super.save(catchPopularAddress);
	}
	
	@Transactional(readOnly = false)
	public void delete(CatchPopularAddress catchPopularAddress) {
		super.delete(catchPopularAddress);
	}
	
}