/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csyx.service;

import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.service.CrudService;
import cn.xy.tcmap.common.utils.HttpUtils;
import cn.xy.tcmap.common.utils.StringUtils;
import cn.xy.tcmap.modules.catchup.csyx.dao.CatchGovernmentDao;
import cn.xy.tcmap.modules.catchup.csyx.entity.CatchGovernment;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 社会治理Service
 *
 * @author xuzhou
 * @version 2018-05-24
 */
@Service
@Transactional(readOnly = true)
public class CatchGovernmentService extends CrudService<CatchGovernmentDao, CatchGovernment> {

    @Autowired
    private CatchGovernmentDao catchGovernmentDao;

    public CatchGovernment get(String id) {
        return super.get(id);
    }

    public List<CatchGovernment> findList(CatchGovernment catchGovernment) {
        return super.findList(catchGovernment);
    }

    public Page<CatchGovernment> findPage(Page<CatchGovernment> page, CatchGovernment catchGovernment) {
        return super.findPage(page, catchGovernment);
    }

    public List<CatchGovernment> governmentData(CatchGovernment catchGovernment) {
        return catchGovernmentDao.governmentData(catchGovernment);
    }

    @Transactional(readOnly = false)
    public void save(CatchGovernment catchGovernment) {
        super.save(catchGovernment);
    }

    @Transactional(readOnly = false)
    public void delete(CatchGovernment catchGovernment) {
        super.delete(catchGovernment);
    }

    @Transactional(readOnly = false)
    public void syndata(String url,String paramsAdd,String paramEdit) {
        /*String responseEditJsonStr= HttpUtils.post(url,paramEdit);
        //通过接口获取修改数据
        if(StringUtils.isNotBlank(responseEditJsonStr)) {
            //修改数据的处理
        }*/
        //通过接口获取新增数据
        String responseAddJsonStr = HttpUtils.post(url,paramsAdd);
        if(StringUtils.isNotBlank(responseAddJsonStr)){
            Object object = JSONObject.fromObject(responseAddJsonStr).get("data");
            if (object != null) {
                JSONArray jsonArray = JSONArray.fromObject(object);
                if (jsonArray.size() > 0) {
                    //添加获取的数据
                    List<CatchGovernment> list = new ArrayList<CatchGovernment>();
                    for (Object jsonObject : jsonArray) {
                        CatchGovernment catchGovernment = (CatchGovernment) JSONObject.toBean(JSONObject.fromObject(jsonObject), CatchGovernment.class);
                        JSONObject json = JSONObject.fromObject(jsonObject);
                        catchGovernment.setId(null);
                        catchGovernment.setGovernanceType(json.getString("governance_type"));
                        catchGovernment.setNumericalValue(json.getInt("numerical_value"));
                        list.add(catchGovernment);
                    }
                    //对数据进行同步
                    this.insertBatch(list);
                }
            }
        }
    }
}