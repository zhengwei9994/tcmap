package cn.xy.tcmap.modules.catchup.Scheduled;

import cn.xy.tcmap.common.config.Global;
import cn.xy.tcmap.common.utils.DateUtils;
import cn.xy.tcmap.modules.catchup.aqi.entity.TcAqiMonitor;
import cn.xy.tcmap.modules.catchup.aqi.service.TcAqiMonitorService;
import cn.xy.tcmap.modules.catchup.aqi.web.TcAqiMonitorController;
import cn.xy.tcmap.modules.catchup.csyx.entity.CatchAqiparam;
import cn.xy.tcmap.modules.catchup.csyx.service.CatchAqiparamService;
import cn.xy.tcmap.modules.catchup.fzjc.entity.CacheEnterpriseBasicData;
import cn.xy.tcmap.modules.catchup.fzjc.entity.CatchApprovalMatters;
import cn.xy.tcmap.modules.catchup.fzjc.entity.CatchDataStatus;
import cn.xy.tcmap.modules.catchup.fzjc.entity.CatchElectronicCategory;
import cn.xy.tcmap.modules.catchup.fzjc.entity.CatchElectronicEvidence;
import cn.xy.tcmap.modules.catchup.fzjc.entity.CatchEnergyConsumption;
import cn.xy.tcmap.modules.catchup.fzjc.entity.CatchGrowthSituation;
import cn.xy.tcmap.modules.catchup.fzjc.entity.CatchHumanResources;
import cn.xy.tcmap.modules.catchup.fzjc.entity.CatchIndexCompletion;
import cn.xy.tcmap.modules.catchup.fzjc.entity.CatchIndexStatus;
import cn.xy.tcmap.modules.catchup.fzjc.entity.CatchLaborEmployment;
import cn.xy.tcmap.modules.catchup.fzjc.entity.CatchPractitioners;
import cn.xy.tcmap.modules.catchup.fzjc.entity.CatchResidentIncome;
import cn.xy.tcmap.modules.catchup.fzjc.entity.CatchSalaryStaff;
import cn.xy.tcmap.modules.catchup.fzjc.entity.CatchSalaryStructure;
import cn.xy.tcmap.modules.catchup.fzjc.entity.CatchServiceIndicators;
import cn.xy.tcmap.modules.catchup.fzjc.entity.CatchVideoResource;
import cn.xy.tcmap.modules.catchup.fzjc.service.CacheEnterpriseBasicDataService;
import cn.xy.tcmap.modules.catchup.fzjc.service.CatchApprovalMattersService;
import cn.xy.tcmap.modules.catchup.fzjc.service.CatchDataStatusService;
import cn.xy.tcmap.modules.catchup.fzjc.service.CatchElectronicCategoryService;
import cn.xy.tcmap.modules.catchup.fzjc.service.CatchElectronicEvidenceService;
import cn.xy.tcmap.modules.catchup.fzjc.service.CatchEnergyConsumptionService;
import cn.xy.tcmap.modules.catchup.fzjc.service.CatchGrowthSituationService;
import cn.xy.tcmap.modules.catchup.fzjc.service.CatchHumanResourcesService;
import cn.xy.tcmap.modules.catchup.fzjc.service.CatchIndexCompletionService;
import cn.xy.tcmap.modules.catchup.fzjc.service.CatchIndexStatusService;
import cn.xy.tcmap.modules.catchup.fzjc.service.CatchLaborEmploymentService;
import cn.xy.tcmap.modules.catchup.fzjc.service.CatchPractitionersService;
import cn.xy.tcmap.modules.catchup.fzjc.service.CatchResidentIncomeService;
import cn.xy.tcmap.modules.catchup.fzjc.service.CatchSalaryStaffService;
import cn.xy.tcmap.modules.catchup.fzjc.service.CatchSalaryStructureService;
import cn.xy.tcmap.modules.catchup.fzjc.service.CatchServiceIndicatorsService;
import cn.xy.tcmap.modules.catchup.fzjc.service.CatchVideoResourceService;
import cn.xy.tcmap.modules.catchup.wlkj.entity.CatchHotPublic;
import cn.xy.tcmap.modules.catchup.wlkj.entity.CatchHotSearch;
import cn.xy.tcmap.modules.catchup.wlkj.service.CatchHotPublicService;
import cn.xy.tcmap.modules.catchup.wlkj.service.CatchHotSearchService;
import cn.xy.tcmap.modules.catchup.zhcs.entity.CatchAreaRanking;
import cn.xy.tcmap.modules.catchup.zhcs.entity.CatchAssetDetails;
import cn.xy.tcmap.modules.catchup.zhcs.entity.CatchEducationAssets;
import cn.xy.tcmap.modules.catchup.zhcs.entity.CatchHingeStatistics;
import cn.xy.tcmap.modules.catchup.zhcs.entity.CatchHotelResInfo;
import cn.xy.tcmap.modules.catchup.zhcs.entity.CatchMedicalAssets;
import cn.xy.tcmap.modules.catchup.zhcs.entity.CatchPatentCount;
import cn.xy.tcmap.modules.catchup.zhcs.entity.CatchResourcesCount;
import cn.xy.tcmap.modules.catchup.zhcs.entity.CatchRoadCount;
import cn.xy.tcmap.modules.catchup.zhcs.entity.CatchScenicSpot;
import cn.xy.tcmap.modules.catchup.zhcs.entity.CatchStayTime;
import cn.xy.tcmap.modules.catchup.zhcs.entity.CatchTourCountAnalysis;
import cn.xy.tcmap.modules.catchup.zhcs.entity.CatchTouristView;
import cn.xy.tcmap.modules.catchup.zhcs.entity.CatchTravelInfo;
import cn.xy.tcmap.modules.catchup.zhcs.service.*;
import cn.xy.tcmap.modules.sys.utils.HtmlFileParser;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@Lazy(false)
@PropertySource("classpath:config.properties")
@Component
public class ScheduledAqi {
	Logger logger = Logger.getLogger(ScheduledAqi.class);
    private final int pageSize = 9999;
    private final int FLAG_NO_DEL = 0;//没有删除
    private final int FLAG_DEL = 1;   //删除标记
    @Autowired
    private CatchAqiparamService catchAqiparamService;
    @Autowired
    private CatchHotPublicService catchHotPublicService;
    @Autowired
    private CatchHotSearchService catchHotSearchService;
    @Autowired
    private CatchResourcesCountService catchResourcesCountService;
    @Autowired
    private CatchMedicalAssetsService catchMedicalAssetsService;
    @Autowired
    private CatchAssetDetailsService  catchAssetDetailsService;
    @Autowired
    private CatchEducationAssetsService catchEducationAssetsService;
    @Autowired
    private CatchHingeStatisticsService  catchHingeStatisticsService;
    @Autowired
    private CatchPatentCountService  catchPatentCountService;
    @Autowired
    private CatchRoadCountService  catchRoadCountService;
    @Autowired
    private CatchScenicSpotService  catchScenicSpotService;
    @Autowired
    private CatchTourCountAnalysisService catchTourCountAnalysisService;
    @Autowired
    private CatchTravelInfoService catchTravelInfoService;
    @Autowired
    private CatchStayTimeService catchStayTimeService;
    @Autowired
    private CatchAreaRankingService catchAreaRankingService;
    @Autowired
    private CatchHotelResInfoService catchHotelResInfoService;
    @Autowired
    private CatchIndexCompletionService  catchIndexCompletionService;
    @Autowired
    private CatchApprovalMattersService catchApprovalMattersService;
    @Autowired
    private CatchVideoResourceService catchVideoResourceService;
    @Autowired
    private CatchEnergyConsumptionService catchEnergyConsumptionService;
    @Autowired
    private CatchHumanResourcesService catchHumanResourcesService;
    @Autowired
    private CatchIndexStatusService catchIndexStatusService;
    @Autowired
    private CatchGrowthSituationService catchGrowthSituationService;
    @Autowired
    private CatchElectronicEvidenceService catchElectronicEvidenceService;
    @Autowired
    private CatchElectronicCategoryService catchElectronicCategoryService;
    @Autowired
    private CatchDataStatusService catchDataStatusService;
    @Autowired
    private CatchServiceIndicatorsService catchServiceIndicatorsService;
    @Autowired
    private CacheEnterpriseBasicDataService cacheEnterpriseBasicDataService;
    @Autowired
    private CatchSalaryStructureService catchSalaryStructureService;
    @Autowired
    private CatchResidentIncomeService catchResidentIncomeService;
    @Autowired
    private CatchLaborEmploymentService catchLaborEmploymentService;
    @Autowired
    private CatchPractitionersService catchPractitionersService;
    @Autowired
    private CatchTouristViewService catchTouristViewService;
    @Autowired
    private CatchSalaryStaffService catchSalaryStaffService;
    @Autowired
    private TcAqiMonitorService tcAqiMonitorService;
    //人力资源
    private final  String catchResourcesCountURL = Global.getPathConfig("catch_resources_count");
    //旅游景点
    private final  String catchScenicSpotURL = Global.getPathConfig("catch_scenic_spot");
    //资产详情
    private final  String catchAssetDetailsURL =  Global.getPathConfig("catch_asset_details");
    //教育资产
    private final  String catchEducationAssetsURL =  Global.getPathConfig("catch_education_assets");
    //交通枢纽统计
    private final  String catchHingeStatisticsURL = Global.getPathConfig("catch_hinge_statistics");
    //医疗资源统计
    private final  String catchMedicalAssetsURL =  Global.getPathConfig("catch_medical_assets");
    //专利数量统计
    private final  String catchPatentCountURL =  Global.getPathConfig("catch_patent_count");
    //公路统计
    private final  String catchRoadCountURL = Global.getPathConfig("catch_road_count");
    //景区地点排名
    private final String catchAreaRankingURL = Global.getPathConfig("catch_area_ranking");
    //酒店资源信息
    private final String catchHotelResInfoURL = Global.getPathConfig("catch_hotel_res_info");
    //停留时长分布
    private final String catchStayTimeURL = Global.getPathConfig("catch_stay_time");
    //旅游趋势分析
    private final String catchTourCountAnalysisURL = Global.getPathConfig("catch_tour_count_analysis");
    //指标数据运行状态
    private final String catchTravelInfoURL = Global.getPathConfig("catch_travel_info");

    @Scheduled(cron="0 10  * * * ?")
    public void ranking() {
        CatchAqiparam catchAqiparam = new CatchAqiparam();
        List<CatchAqiparam> aqiParams = new ArrayList<CatchAqiparam>();
        try {
            aqiParams = HtmlFileParser.aqiTable();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("获取陕西省城市空气AQI数据，出现异常！！");
        }
        for (CatchAqiparam aqiParam : aqiParams) {
            catchAqiparam.setAreaName(aqiParam.getAreaName());
            List<CatchAqiparam> list = catchAqiparamService.findList(catchAqiparam);
            if (list != null && list.size() == 1) {
                CatchAqiparam catchAqiparam1 = list.get(0);
                catchAqiparam1.setAqi(aqiParam.getAqi());
                catchAqiparam1.setAqilevel(aqiParam.getAqilevel());
                catchAqiparam1.setRank(aqiParam.getRank());
                catchAqiparam1.preUpdate();
                catchAqiparamService.update(catchAqiparam1);
            } else {
                CatchAqiparam catchAqiparam1 = new CatchAqiparam();
                catchAqiparam1.setAreaName(aqiParam.getAreaName());
                catchAqiparam1.setAqi(aqiParam.getAqi());
                catchAqiparam1.setAqilevel(aqiParam.getAqilevel());
                catchAqiparam1.setRank(aqiParam.getRank());
                catchAqiparam1.preInsert();
                catchAqiparamService.insert(catchAqiparam1);
            }
        }
        System.out.println("定时任务执行结束");
    }
  @Scheduled(cron="0 20 * * * ?")
    public void hotPublicSave(){
        CatchHotPublic catchHotPublic = new CatchHotPublic();
        List<CatchHotPublic> list = new ArrayList<CatchHotPublic>();
        try {
            list = HtmlFileParser.hotPublic();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("获取铜川市舆情热点，出现异常！！");
        }
        for (CatchHotPublic catchHotPublic1 : list) {
            catchHotPublic.setSort(catchHotPublic1.getSort());
            List<CatchHotPublic> list1 = catchHotPublicService.findList(catchHotPublic);
            if(list1 != null && list1.size()==1){
                CatchHotPublic   catchHotPublic2 = list1.get(0);
                catchHotPublic2.setPublicOpinion(catchHotPublic1.getPublicOpinion());
                catchHotPublic2.setSort(catchHotPublic1.getSort());
                catchHotPublic2.preUpdate();
                catchHotPublicService.update(catchHotPublic2);
            }else {
                CatchHotPublic catchHotPublic2 = new CatchHotPublic();
                catchHotPublic2.setPublicOpinion(catchHotPublic1.getPublicOpinion());
                catchHotPublic2.setSort(catchHotPublic1.getSort());
                catchHotPublic2.preInsert();
                catchHotPublicService.insert(catchHotPublic2);
            }
        }
        System.out.println("定时任务执行结束");
    }
    //百度热搜索词汇
    @Scheduled(cron="0 30 * * * ?")
    public void hotSearchSave(){
        CatchHotSearch catchHotSearch = new CatchHotSearch();
        List<CatchHotSearch> list = new ArrayList<CatchHotSearch>();
        try {
            list =    HtmlFileParser.hotSearch();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("获取百度热搜索词，出现异常！！");
        }
        for(CatchHotSearch catchHotSearch1 : list){
            catchHotSearch.setSort(catchHotSearch1.getSort());
            List<CatchHotSearch> catchHotPublics = catchHotSearchService.findList(catchHotSearch);
            if(catchHotPublics != null && catchHotPublics.size()>0){
                CatchHotSearch catchHotSearch2 = catchHotPublics.get(0);
                catchHotSearch2.setName(catchHotSearch1.getName());
                catchHotSearch2.setLink(catchHotSearch1.getLink());
                catchHotSearch2.setSort(catchHotSearch1.getSort());
                catchHotSearchService.save(catchHotSearch2);
            }else {
                CatchHotSearch catchHotSearch2 = new CatchHotSearch();
                catchHotSearch2.setName(catchHotSearch1.getName());
                catchHotSearch2.setLink(catchHotSearch1.getLink());
                catchHotSearch2.setSort(catchHotSearch1.getSort());
                catchHotSearchService.save(catchHotSearch2);
            }
        }
    }

//    @Scheduled(cron="0 58 23 ? * *")   //每天晚上23：58分执行
    @Scheduled(cron="${jobs.schedule.fzjc}") 
    public void catchFZJCTask() throws JsonParseException, JsonMappingException, IOException{
        logger.debug(DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss") + ",FZJC开始执行。。。。");
        JSONObject mapParam = new JSONObject();
        JSONObject delParams = new JSONObject();
        final String dateTime = DateUtils.getDate();//获取当前时间
        mapParam.put("dateTime", dateTime); 
        mapParam.put("flag", FLAG_NO_DEL); //删除标记   0：没有删除
        mapParam.put("pagesize", pageSize);
        delParams.put("dateTime", dateTime); 
        delParams.put("flag", FLAG_DEL); //删除标记  1:表示删除
        delParams.put("pagesize", pageSize);
        String mapParamStr = mapParam.toString();
        String delParamStr = delParams.toString();
        //1.运行指标完成率  
        catchIndexCompletionService.syncDataTask(Global.getPathConfig("catch_index_completion"), mapParamStr, delParamStr,new CatchIndexCompletion());
        //2.政务服务
        catchApprovalMattersService.syncDataTask(Global.getPathConfig("catch_approval_matters"),mapParamStr, delParamStr,new CatchApprovalMatters());
        //3.公共安全视频资源覆盖情况
        catchVideoResourceService.syncDataTask(Global.getPathConfig("catch_video_resource"),mapParamStr, delParamStr,new CatchVideoResource());
        //4.万元GDP能耗降低率 
        catchEnergyConsumptionService.syncDataTask(Global.getPathConfig("catch_energy_consumption"),mapParamStr, delParamStr,new CatchEnergyConsumption());
        //5.人力资源统计   
        catchHumanResourcesService.syncDataTask(Global.getPathConfig("catch_human_resources"),mapParamStr, delParamStr,new CatchHumanResources());
        //6.	指标数据运行状态
        catchIndexStatusService.syncDataTask(Global.getPathConfig("catch_index_status"),mapParamStr, delParamStr,new CatchIndexStatus());
        //7.	区县经济增长情况  
        catchGrowthSituationService.syncDataTask(Global.getPathConfig("catch_growth_situation"),mapParamStr, delParamStr,new CatchGrowthSituation());
        //8.	电子证照使用率
        catchElectronicEvidenceService.syncDataTask(Global.getPathConfig("catch_electronic_evidence"),mapParamStr, delParamStr,new CatchElectronicEvidence());
        //9.	证照类别管理
        catchElectronicCategoryService.syncDataTask(Global.getPathConfig("catch_electronic_category"),mapParamStr, delParamStr,new CatchElectronicCategory());
        //10.	社保在线办理统计  
        catchDataStatusService.syncDataTask(Global.getPathConfig("catch_data_status"),mapParamStr, delParamStr,new CatchDataStatus());
        //11.	区县综合政府服务指标 
        catchServiceIndicatorsService.syncDataTask(Global.getPathConfig("catch_service_indicators"),mapParamStr, delParamStr,new CatchServiceIndicators());
        //12.	企业结构基础数据分析 
        cacheEnterpriseBasicDataService.syncDataTask(Global.getPathConfig("cache_enterprise_basic_data"),mapParamStr, delParamStr,new CacheEnterpriseBasicData());
        //13.	人才结构现状分析    
        catchSalaryStructureService.syncDataTask(Global.getPathConfig("catch_salary_structure"),mapParamStr, delParamStr,new CatchSalaryStructure()); 
        //14.	居民收入
        catchResidentIncomeService.syncDataTask(Global.getPathConfig("catch_resident_income"),mapParamStr, delParamStr,new CatchResidentIncome()); 
        //15.	劳动就业检索     
        catchLaborEmploymentService.syncDataTask(Global.getPathConfig("catch_labor_employment"),mapParamStr, delParamStr,new CatchLaborEmployment());
        //16.	劳动就业占比
        catchPractitionersService.syncDataTask(Global.getPathConfig("catch_practitioners"),mapParamStr, delParamStr,new CatchPractitioners());  
        //17. 薪资及人员分析
        catchSalaryStaffService.syncDataTask(Global.getPathConfig("catch_salary_staff"), mapParamStr, delParamStr, new CatchSalaryStaff());
        logger.debug("FZJC执行结束。。。");
    }

    /**气象数据本地化,plain A
     * @return 气象数据json
     */
    public void saveAqiA(JSONObject object){
        TcAqiMonitor tcAqiMonitor=new TcAqiMonitor();
        JSONObject cityinfo=JSONObject.fromObject(object.get("cityInfo"));
        JSONObject aqiObject=JSONObject.fromObject(object.get("data"));
        JSONArray arraya=aqiObject.getJSONArray("forecast");
        String date=getToday();
        //遍历数组，取当日数据
        for(int i=0;i<arraya.size();i++){
            JSONObject currentDay=JSONObject.fromObject(arraya.get(i));
            String ymd=currentDay.get("ymd").toString();
            if(currentDay!=null && ymd.equals(date)){
                try {
                    tcAqiMonitor.setAqi(currentDay.get("aqi").toString());
                    tcAqiMonitor.setCity(cityinfo.getString("city"));
                    tcAqiMonitor.setCreateTime(new Date());
                    tcAqiMonitor.setFl(currentDay.get("fl").toString());
                    tcAqiMonitor.setFx(currentDay.get("fx").toString());
                    tcAqiMonitor.setHighTemprature(currentDay.get("high").toString());
                    tcAqiMonitor.setLowTemprature(currentDay.get("low").toString());
                    tcAqiMonitor.setNotice(currentDay.get("notice").toString());
                    tcAqiMonitor.setPm10(aqiObject.get("pm10").toString());
                    tcAqiMonitor.setPm25(aqiObject.get("pm25").toString());
                    tcAqiMonitor.setSunRise(currentDay.get("sunrise").toString());
                    tcAqiMonitor.setSunSet(currentDay.get("sunset").toString());
                    tcAqiMonitor.setUpdateTime(cityinfo.get("updateTime").toString());
                    tcAqiMonitor.setWeather(currentDay.get("type").toString());
                    tcAqiMonitor.setWeek(currentDay.get("week").toString());
                    tcAqiMonitor.setYmd(currentDay.get("ymd").toString());
                    tcAqiMonitorService.save(tcAqiMonitor);
                    System.out.println("========================================定时任务执行,开始采集气象数据=======================================");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
    }

    /**
     * 气象数据请求 plain B
     */
    public void saveAqiB(JSONObject object){
        TcAqiMonitor tcAqiMonitor=new TcAqiMonitor();

        String date=object.get("date").toString();
        if(date.equals(getToday())){
            tcAqiMonitor.setAqi(object.get("air").toString());
            tcAqiMonitor.setCity(object.get("city").toString()+"市");
            tcAqiMonitor.setNotice(object.get("air_tips").toString());
            tcAqiMonitor.setCreateTime(new Date());
            tcAqiMonitor.setFl(object.get("win_speed").toString());
            tcAqiMonitor.setFx(object.get("win").toString());
            tcAqiMonitor.setHighTemprature(object.get("tem1").toString());
            tcAqiMonitor.setLowTemprature(object.get("tem2").toString());
            tcAqiMonitor.setWeather(object.get("wea").toString());
            tcAqiMonitor.setUpdateTime(object.get("update_time").toString());
            tcAqiMonitor.setWeek(object.get("week").toString());
            tcAqiMonitor.setYmd(object.get("date").toString());
            tcAqiMonitorService.save(tcAqiMonitor);
        }
    }

    /**
     *执行方法
     */
    @Scheduled(cron = "${tc.schedule.aqi}")
    public void task(){
        String pathA="http://t.weather.sojson.com/api/weather/city/101111001";
        String pathB="https://www.tianqiapi.com/api/?version=v6&city=%E9%93%9C%E5%B7%9D&appid=77838766&appsecret=ebnXD4Ad";
        try {
            if(getUrlconnect(pathB)!=null){
                JSONObject jsonObjectB=JSONObject.fromObject(getUrlconnect(pathB));
                saveAqiB(jsonObjectB);
                return;
            }else if (getUrlconnect(pathA)!=null){
                JSONObject jsonObjectA=JSONObject.fromObject(getUrlconnect(pathA));
                saveAqiA(jsonObjectA);
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**  接口请求方法
     * @param
     * @return
     */
    public  String getUrlconnect(String path){
        BufferedReader in = null;
        StringBuffer result;
        try {
            URL url=new URL(path);
            HttpURLConnection connection=(HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Charset","utf-8");
            connection.connect();
            result= new StringBuffer();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            reader.close();
            connection.disconnect();
            System.out.println(result.toString());
            return result.toString();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if(in !=null){
                    in.close();
                }
            }catch (Exception e1){
                e1.printStackTrace();
            }
        }
        return null;
    }

    public String getToday() {
        Date currentDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.add(Calendar.DAY_OF_MONTH, 0);
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
        String starDate = sdf.format(calendar.getTime());

        return  starDate;
    }
    //@Scheduled(cron="0 50 23 ? * *")   //每天晚上23：50分执行
    @Scheduled(cron="${jobs.schedule.zhcs}") 
    public void catchTaskForZHCS() throws JsonParseException, JsonMappingException, IOException{
    	logger.debug(DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss") + ",ZHCS开始执行。。。。");
        JSONObject mapParam = new JSONObject();
        JSONObject delParams = new JSONObject();
        final String dateTime = DateUtils.getDate();//获取当前时间
        mapParam.put("dateTime", dateTime); 
        mapParam.put("flag", FLAG_NO_DEL); //删除标记   0：没有删除
        mapParam.put("pagesize", pageSize);
        delParams.put("dateTime", dateTime);
        delParams.put("flag", FLAG_DEL); //删除标记1
        delParams.put("pagesize", pageSize);
        String mapParamStr = mapParam.toString();
        String delParamStr = delParams.toString();
        //人力资源
        catchResourcesCountService.syncDataTask(catchResourcesCountURL,mapParamStr,delParamStr,new CatchResourcesCount());
        //医疗资源统计
        catchMedicalAssetsService.syncDataTask(catchMedicalAssetsURL,mapParamStr,delParamStr,new CatchMedicalAssets());
        //旅游景点
        catchScenicSpotService.syncDataTask(catchScenicSpotURL,mapParamStr,delParamStr,new CatchScenicSpot());
        //资产详情
        catchAssetDetailsService.syncDataTask(catchAssetDetailsURL,mapParamStr,delParamStr,new CatchAssetDetails());
        //教育资产
        catchEducationAssetsService.syncDataTask(catchEducationAssetsURL,mapParamStr,delParamStr,new CatchEducationAssets());
        //交通枢纽统计
        catchHingeStatisticsService.syncDataTask(catchHingeStatisticsURL,mapParamStr,delParamStr,new CatchHingeStatistics());
        //医疗资源统计
        catchMedicalAssetsService.syncDataTask(catchMedicalAssetsURL,mapParamStr,delParamStr,new CatchMedicalAssets());
        //专利数量统计
        catchPatentCountService.syncDataTask(catchPatentCountURL,mapParamStr,delParamStr,new CatchPatentCount());
        //公路统计
        catchRoadCountService.syncDataTask(catchRoadCountURL,mapParamStr,delParamStr,new CatchRoadCount());
        //地区景点排名
        catchAreaRankingService.syncDataTask(catchAreaRankingURL,mapParamStr,delParamStr,new CatchAreaRanking());
        //酒店资源信息
        catchHotelResInfoService.syncDataTask(catchHotelResInfoURL,mapParamStr,delParamStr,new CatchHotelResInfo());
        //停留时长分布
        catchStayTimeService.syncDataTask(catchStayTimeURL,mapParamStr,delParamStr,new CatchStayTime());
        //旅游趋势分析
        catchTourCountAnalysisService.syncDataTask(catchTourCountAnalysisURL,mapParamStr,delParamStr,new CatchTourCountAnalysis());
        //指标数据运行状态
        catchTravelInfoService.syncDataTask(catchTravelInfoURL,mapParamStr,delParamStr,new CatchTravelInfo());
        //铜川星级景区 
        catchTouristViewService.syncDataTask(Global.getPathConfig("catch_tourist_view"), mapParamStr, delParamStr, new CatchTouristView());
        logger.info("ZHCS执行结束。。。");
    }


}
