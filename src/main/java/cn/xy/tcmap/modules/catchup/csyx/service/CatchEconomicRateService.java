package cn.xy.tcmap.modules.catchup.csyx.service;

import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.service.CrudService;
import cn.xy.tcmap.common.utils.HttpUtils;
import cn.xy.tcmap.common.utils.StringUtils;
import cn.xy.tcmap.modules.catchup.csyx.dao.CatchEconomicRateDao;
import cn.xy.tcmap.modules.catchup.csyx.entity.CatchEconomicRate;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author guoxunquan
 * @Title CatchEconomicRateService
 * @Description TODO
 * @Package cn.xy.tcmap.modules.catchup.csyx.service
 * @Date 2018-09-18 13:52
 **/
@Service
@Transactional(readOnly = true)
public class CatchEconomicRateService extends CrudService<CatchEconomicRateDao, CatchEconomicRate> {
    @Autowired
    private CatchEconomicRateDao catchEconomicRateDao;
    public CatchEconomicRate get(String id) {
        return super.get(id);
    }

    public List<CatchEconomicRate> findList(CatchEconomicRate CatchEconomicRate) {
        return super.findList(CatchEconomicRate);
    }

    public Page<CatchEconomicRate> findPage(Page<CatchEconomicRate> page, CatchEconomicRate CatchEconomicRate) {
        return super.findPage(page, CatchEconomicRate);
    }

    public List<CatchEconomicRate> economicRateList(CatchEconomicRate CatchEconomicRate){
        return catchEconomicRateDao.catchEconomicRateList(CatchEconomicRate);
    }

    @Transactional(readOnly = false)
    public void save(CatchEconomicRate CatchEconomicRate) {
        super.save(CatchEconomicRate);
    }

    @Transactional(readOnly = false)
    public void delete(CatchEconomicRate CatchEconomicRate) {
        super.delete(CatchEconomicRate);
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
            if(object != null){
                JSONArray jsonArray = JSONArray.fromObject(object);
                if(jsonArray.size() > 0 ){
                    //添加获取的数据
                    List<CatchEconomicRate> list = new ArrayList<CatchEconomicRate>();
                    for (Object jsonObject : jsonArray) {
                        CatchEconomicRate catchEconomicRate  = (CatchEconomicRate)JSONObject.toBean(JSONObject.fromObject(jsonObject),CatchEconomicRate.class);
                        JSONObject json = JSONObject.fromObject(jsonObject);
                        catchEconomicRate.setId(null);
                        catchEconomicRate.setIndexName(json.getString("index_name"));
                        catchEconomicRate.setAreaName(json.getString("area_name"));
                        catchEconomicRate.setIndicatorsUnit(json.getString("indicators_unit"));
                        list.add(catchEconomicRate);
                    }
                    //对数据进行同步
                    this.insertBatch(list);
                }
            }
        }
    }
}
