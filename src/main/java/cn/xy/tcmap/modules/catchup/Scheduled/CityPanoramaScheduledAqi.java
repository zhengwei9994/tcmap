package cn.xy.tcmap.modules.catchup.Scheduled;

import cn.xy.tcmap.common.config.Global;
import cn.xy.tcmap.common.utils.DateUtils;
import cn.xy.tcmap.modules.catchup.csyx.entity.CatchAirQuality;
import cn.xy.tcmap.modules.catchup.csyx.entity.CatchEconomicGrowth;
import cn.xy.tcmap.modules.catchup.csyx.entity.CatchEconomicRate;
import cn.xy.tcmap.modules.catchup.csyx.entity.CatchGasResource;
import cn.xy.tcmap.modules.catchup.csyx.entity.CatchGovernment;
import cn.xy.tcmap.modules.catchup.csyx.entity.CatchIndexName;
import cn.xy.tcmap.modules.catchup.csyx.entity.CatchInvestmentProgress;
import cn.xy.tcmap.modules.catchup.csyx.entity.CatchKeyproject;
import cn.xy.tcmap.modules.catchup.csyx.entity.CatchKeyprojectClass;
import cn.xy.tcmap.modules.catchup.csyx.entity.CatchPlanInvestment;
import cn.xy.tcmap.modules.catchup.csyx.entity.CatchPollutionTreatment;
import cn.xy.tcmap.modules.catchup.csyx.entity.CatchProblemDisposal;
import cn.xy.tcmap.modules.catchup.csyx.entity.CatchProblemSolving;
import cn.xy.tcmap.modules.catchup.csyx.entity.CatchProjectConstruction;
import cn.xy.tcmap.modules.catchup.csyx.entity.CatchProvincesStatistical;
import cn.xy.tcmap.modules.catchup.csyx.entity.CatchSceniArea;
import cn.xy.tcmap.modules.catchup.csyx.entity.CatchServiceIndustry;
import cn.xy.tcmap.modules.catchup.csyx.entity.CatchShakePoverty;
import cn.xy.tcmap.modules.catchup.csyx.entity.CatchSpecialProject;
import cn.xy.tcmap.modules.catchup.csyx.entity.CatchSuperviseDynamic;
import cn.xy.tcmap.modules.catchup.csyx.entity.CatchSwageTreat;
import cn.xy.tcmap.modules.catchup.csyx.entity.CatchWasteWater;
import cn.xy.tcmap.modules.catchup.csyx.entity.CatchWaterTrade;
import cn.xy.tcmap.modules.catchup.csyx.entity.CathEconomic;
import cn.xy.tcmap.modules.catchup.csyx.service.*;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import javax.annotation.Resource;
import java.io.IOException;
import java.util.Date;


@Service
@Lazy(false)
@PropertySource("classpath:config.properties")
public class CityPanoramaScheduledAqi {
	Logger logger = Logger.getLogger(CityPanoramaScheduledAqi.class);
    private final static int FLAG_DEL = 1;
    private final static int FLAG_NO_DEL = 0;
    private final static int PAGE_NO = 1;
    private final static int PAGE_SIZE = 1000;
    @Resource
    private CatchEconomicRateService economicRateService;
    @Resource
    private CathEconomicService economicService;
    @Resource
    private CatchIndexNameService indexNameService;
    @Resource
    private CatchServiceIndustryService serviceIndustryService;
    @Resource
    private CatchSpecialProjectService specialProjectService;
    @Resource
    private CatchKeyprojectClassService keyprojectClassService;
    @Resource
    private CatchKeyprojectService keyprojectService;
    @Resource
    private CatchPlanInvestmentService planInvestmentService;
    @Resource
    private CatchGovernmentService governmentService;
    @Resource
    private CatchPollutionTreatmentService pollutionTreatmentService;
    @Resource
    private CatchShakePovertyService  shakePovertyService;
    @Resource
    private CatchProvincesStatisticalService  provincesStatisticalService;
    @Resource
    private CatchSceniAreaService sceniAreaService;
    @Resource
    private CatchAirQualityService  airQualityService;
    @Resource
    private CatchWasteWaterService  wasteWaterService;
    @Resource
    private CatchWaterTradeService  waterTradeService;
    @Resource
    private CatchGasResourceService gasResourceService;
    @Resource
    private CatchSwageTreatService  swageTreatService;
    @Resource
    private CatchProjectConstructionService  projectConstructionService;
    @Resource
    private CatchProblemDisposalService  problemDisposalService;
    @Resource
    private CatchInvestmentProgressService investmentProgressService;
    @Resource
    private CatchSuperviseDynamicService superviseDynamicService;
    @Resource
    private CatchProblemSolvingService problemSolvingService;
    @Resource
    private CatchEconomicGrowthService catchEconomicGrowthService;

    //1.经济指标统计
    private final  String cathEconomicURL = Global.getPathConfig("cath_economic");
    //2.	经济指标率
    private final  String catchconomicRate = Global.getPathConfig("catch_economic_rate");
    //3.	经济指标名称
    private final  String catchIndexNameURL =  Global.getPathConfig("catch_index_name");
    //4.	服务业稳中向好
    private final  String catchServiceIndustryURL =  Global.getPathConfig("catch_service_industry");
    //5.	特色项目
    private final  String catchSpecialProjectURL =  Global.getPathConfig("catch_special_project");
   //6.	工程创新
    private final  String catchKeyprojectURL =  Global.getPathConfig("catch_keyproject");
    //7.	年度投资总计划
    private final  String catchPlanInvestmentURL =  Global.getPathConfig("catch_plan_investment");
    //8.	社会治理
    private final  String catchGovernmentURL =  Global.getPathConfig("catch_government");
    //9.	重拳治污染
    private final  String catchPollutionTreatmentURL =  Global.getPathConfig("catch_pollution_treatment");
    //10.	精准务实抓脱贫
    private final  String catchShakePovertyURL =  Global.getPathConfig("catch_shake_poverty");
    //11.	游客分析
    private final  String catchProvincesStatisticalURL =  Global.getPathConfig("catch_provinces_statistical");
    //12.	景区旅游数据分析
    private final  String catchSceniAreaURL =  Global.getPathConfig("catch_sceni_area");
    //13.	空气质量趋势
    private final  String catchAirQualityURL =  Global.getPathConfig("catch_air_quality");
    //14.	污水排放量趋势
    private final  String catchWasteWaterURL =  Global.getPathConfig("catch_waste_water");
    //15.	污水处置能力
    private final  String catchWaterTradeURL =  Global.getPathConfig("catch_water_trade");
    //16.	废气排放指标来源
    private final  String catchGasResourceURL =  Global.getPathConfig("catch_gas_resource");
    //17.	水质监测数据
    private final  String catchSwageTreatURL =  Global.getPathConfig("catch_swage_treat");
    //18.	重点项目建设
    private final  String catchProjectConstructionURL =  Global.getPathConfig("catch_project_construction");
    //19.	工程问题管理
    private final  String catchProblemDisposalURL =  Global.getPathConfig("catch_problem_disposal");
    //20.	项目投资进度
    private final  String catchInvestmentProgressURL =  Global.getPathConfig("catch_investment_progress");
    //21.	抓包动态
    private final  String catchSuperviseDynamicsURL =  Global.getPathConfig("catch_supervise_dynamics");
    //22.	工程问题比例
    private final  String catchProblemSolvingURL =  Global.getPathConfig("catch_problem_solving");
    //23.	社会治理
    private final  String catchKeyprojecClasstURL =  Global.getPathConfig("catch_keyprojectclass");
    //24    区域经济增长
    private final  String catchEconomicGrowthURL = Global.getPathConfig("catch_economic_growth");

    /**
     * 城市全景数据定时更新
     * @throws IOException 
     * @throws JsonMappingException 
     * @throws JsonParseException 
     */   
    @Scheduled(cron="${jobs.schedule.csyx}")  //每天晚上23：58分执行
    public void cityPanoramaTask() throws JsonParseException, JsonMappingException, IOException{
		logger.debug(DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss") + ",CSYX开始执行。。。。");
		JSONObject mapParam = new JSONObject();
		JSONObject delParams = new JSONObject();
		final String dateTime = DateUtils.getDate();// 获取当前时间
		mapParam.put("dateTime", dateTime);
		mapParam.put("flag", FLAG_NO_DEL); // 删除标记 0：没有删除
		mapParam.put("pagesize", PAGE_SIZE);
		delParams.put("dateTime", dateTime);
		delParams.put("flag", FLAG_DEL); // 删除标记1
		delParams.put("pagesize", PAGE_SIZE);
		String mapParamStr = mapParam.toString();
		String delParamStr = delParams.toString();
        //执行同步
        economicService.syncDataTask(cathEconomicURL,mapParamStr,delParamStr,new CathEconomic());
        economicRateService.syncDataTask(catchconomicRate,mapParamStr,delParamStr,new CatchEconomicRate());
        indexNameService.syncDataTask(catchIndexNameURL,mapParamStr,delParamStr,new CatchIndexName());
        serviceIndustryService.syncDataTask(catchServiceIndustryURL,mapParamStr,delParamStr,new CatchServiceIndustry());
        specialProjectService.syncDataTask(catchSpecialProjectURL,mapParamStr,delParamStr,new CatchSpecialProject());
        keyprojectService.syncDataTask(catchKeyprojecClasstURL,mapParamStr,delParamStr,new CatchKeyproject());
        keyprojectClassService.syncDataTask(catchKeyprojectURL,mapParamStr,delParamStr,new CatchKeyprojectClass());
        planInvestmentService.syncDataTask(catchPlanInvestmentURL,mapParamStr,delParamStr,new CatchPlanInvestment());
        governmentService.syncDataTask(catchGovernmentURL,mapParamStr,delParamStr,new CatchGovernment());
        pollutionTreatmentService.syncDataTask(catchPollutionTreatmentURL,mapParamStr,delParamStr,new CatchPollutionTreatment());
        shakePovertyService.syncDataTask(catchShakePovertyURL,mapParamStr,delParamStr,new CatchShakePoverty());
        provincesStatisticalService.syncDataTask(catchProvincesStatisticalURL,mapParamStr,delParamStr,new CatchProvincesStatistical());
        sceniAreaService.syncDataTask(catchSceniAreaURL,mapParamStr,delParamStr,new CatchSceniArea());
        airQualityService.syncDataTask(catchAirQualityURL,mapParamStr,delParamStr,new CatchAirQuality());
        wasteWaterService.syncDataTask(catchWasteWaterURL,mapParamStr,delParamStr,new CatchWasteWater());
        waterTradeService.syncDataTask(catchWaterTradeURL,mapParamStr,delParamStr,new CatchWaterTrade());
        gasResourceService.syncDataTask(catchGasResourceURL,mapParamStr,delParamStr,new CatchGasResource());
        swageTreatService.syncDataTask(catchSwageTreatURL,mapParamStr,delParamStr,new CatchSwageTreat());
        projectConstructionService.syncDataTask(catchProjectConstructionURL,mapParamStr,delParamStr,new CatchProjectConstruction());
        problemDisposalService.syncDataTask(catchProblemDisposalURL,mapParamStr,delParamStr,new CatchProblemDisposal());
        investmentProgressService.syncDataTask(catchInvestmentProgressURL,mapParamStr,delParamStr,new CatchInvestmentProgress());
        superviseDynamicService.syncDataTask(catchSuperviseDynamicsURL,mapParamStr,delParamStr,new CatchSuperviseDynamic());
        problemSolvingService.syncDataTask(catchProblemSolvingURL,mapParamStr,delParamStr,new CatchProblemSolving());
        catchEconomicGrowthService.syncDataTask(catchEconomicGrowthURL, mapParamStr, delParamStr, new CatchEconomicGrowth());
        logger.info("执行结束");
    }
}
