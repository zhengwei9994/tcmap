package cn.xy.tcmap.modules.catchup.csyx.service;

import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.service.CrudService;
import cn.xy.tcmap.common.utils.HttpUtils;
import cn.xy.tcmap.common.utils.StringUtils;
import cn.xy.tcmap.modules.catchup.csyx.dao.CatchSwageTreatDao;
import cn.xy.tcmap.modules.catchup.csyx.entity.CatchSwageTreat;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author guoxunquan
 * @Title CatchSwageTreatService
 * @Description TODO
 * @Package cn.xy.tcmap.modules.catchup.csyx.service
 * @Date 2018-09-19 13:58
 **/
@Service
@Transactional(readOnly = true)
public class CatchSwageTreatService extends CrudService<CatchSwageTreatDao, CatchSwageTreat> {
    public CatchSwageTreat get(String id) {
        return super.get(id);
    }

    public List<CatchSwageTreat> findList(CatchSwageTreat CatchSwageTreat) {
        return super.findList(CatchSwageTreat);
    }

    public Page<CatchSwageTreat> findPage(Page<CatchSwageTreat> page, CatchSwageTreat CatchSwageTreat) {
        return super.findPage(page, CatchSwageTreat);
    }

    @Transactional(readOnly = false)
    public void save(CatchSwageTreat CatchSwageTreat) {
        super.save(CatchSwageTreat);
    }

    @Transactional(readOnly = false)
    public void delete(CatchSwageTreat CatchSwageTreat) {
        super.delete(CatchSwageTreat);
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
                    List<CatchSwageTreat> list = new ArrayList<CatchSwageTreat>();
                    for (Object jsonObject : jsonArray) {
                        CatchSwageTreat catchSwageTreat  = (CatchSwageTreat)JSONObject.toBean(JSONObject.fromObject(jsonObject),CatchSwageTreat.class);
                        JSONObject json = JSONObject.fromObject(jsonObject);
                        catchSwageTreat.setId(null);
                        catchSwageTreat.setSwageValue(json.getDouble("swage_value"));
                        list.add(catchSwageTreat);
                    }
                    //对数据进行同步
                    this.insertBatch(list);
                }
            }
        }
    }
}
