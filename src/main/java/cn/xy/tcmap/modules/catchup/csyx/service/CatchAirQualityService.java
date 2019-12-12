package cn.xy.tcmap.modules.catchup.csyx.service;

import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.service.CrudService;
import cn.xy.tcmap.common.utils.HttpUtils;
import cn.xy.tcmap.common.utils.StringUtils;
import cn.xy.tcmap.modules.catchup.csyx.dao.CatchAirQualityDao;
import cn.xy.tcmap.modules.catchup.csyx.entity.CatchAirQuality;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author guoxunquan
 * @Title CatchAirQualityService
 * @Description TODO
 * @Package cn.xy.tcmap.modules.catchup.csyx.service
 * @Date 2018-09-19 10:47
 **/
@Service
@Transactional(readOnly = true)
public class CatchAirQualityService extends CrudService<CatchAirQualityDao, CatchAirQuality> {

    public CatchAirQuality get(String id) {
        return super.get(id);
    }

    public List<CatchAirQuality> findList(CatchAirQuality CatchAirQuality) {
        return super.findList(CatchAirQuality);
    }

    public Page<CatchAirQuality> findPage(Page<CatchAirQuality> page, CatchAirQuality CatchAirQuality) {
        return super.findPage(page, CatchAirQuality);
    }


    @Transactional(readOnly = false)
    public void save(CatchAirQuality CatchAirQuality) {
        super.save(CatchAirQuality);
    }

    @Transactional(readOnly = false)
    public void delete(CatchAirQuality CatchAirQuality) {
        super.delete(CatchAirQuality);
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
                    List<CatchAirQuality> list = new ArrayList<CatchAirQuality>();
                    for (Object jsonObject : jsonArray) {
                        CatchAirQuality catchAirQuality  = (CatchAirQuality)JSONObject.toBean(JSONObject.fromObject(jsonObject),CatchAirQuality.class);
                        catchAirQuality.setId(null);
                        list.add(catchAirQuality);
                    }
                    //对数据进行同步
                    this.insertBatch(list);
                }
            }
        }
    }
}
