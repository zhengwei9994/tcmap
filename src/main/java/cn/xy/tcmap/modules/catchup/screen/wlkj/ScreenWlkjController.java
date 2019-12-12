package cn.xy.tcmap.modules.catchup.screen.wlkj;

import cn.xy.tcmap.common.utils.DateUtils;
import cn.xy.tcmap.common.web.BaseController;
import cn.xy.tcmap.modules.catchup.wlkj.entity.*;
import cn.xy.tcmap.modules.catchup.wlkj.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.activiti.engine.impl.util.json.JSONArray;
import org.activiti.engine.impl.util.json.JSONObject;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
@Controller
@Api(tags = "f/csyx/screenwlkj", description = "网络空间晴朗一张图相关接口")
@RequestMapping(value = "${frontPath}/wlkj/screenwlkj")
public class ScreenWlkjController extends BaseController{
    @Autowired
    private CatchOpinionStatisticsService catchOpinionStatisticsService;
    @Autowired
    private CatchEarlyWarningService catchEarlyWarningService;
    @Autowired
    private CatchSpotOpinionService catchSpotOpinionService;
    @Autowired
    private CatchSourceOpinionService catchSourceOpinionService;
    @Autowired
    private CatchHotPublicService catchHotPublicService;
    @Autowired
    private CatchHotSearchService  catchHotSearchService;
    @Autowired
    private CatchGeographicTrackingService  catchGeographicTrackingService;
    @Autowired
    private CatchMediaHydService  catchMediaHydService;
    @Autowired
    private CatchMediaMtlxService  catchMediaMtlxService;
    @Autowired
    private CatchMediaResourceService  catchMediaResourceService;
    @Autowired
    private CatchMediaSpreadService  catchMediaSpreadService;
    @Autowired
    private CatchPopularAddressService  catchPopularAddressService;
    @Autowired
    private CatchSensitivityIndexService  catchSensitivityIndexService;
    @Autowired
    private CatchSentimentIndexService  catchSentimentIndexService;
    @Autowired
    private CatchTradeIndexService  catchTradeIndexService;
    @ResponseBody
    @ApiOperation(value = "舆情统计", httpMethod = "GET", notes = "")
    @RequestMapping(value = "opinionStatistics")
    public void opinionStatistics(CatchOpinionStatistics catchOpinionStatistics, HttpServletResponse response){
        JSONObject jsonObject1 = new JSONObject();
        JSONObject jsonObject2 = new JSONObject();
        JSONObject jsonObject3 = new JSONObject();
        JSONObject jsonObject4 = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        List<CatchOpinionStatistics> list = catchOpinionStatisticsService.opinionStatistics(catchOpinionStatistics);
        Double indexPositiveTotal = 0d; //正能量指数总数
        Double indexTheTotal = 0d; //中性总数
        Double indexNegativeTotal = 0d; //消极指数总数
        Double netizenTotal = 0d; //网民总数
        Double mediaTotal = 0d; //媒体总数

        if(list != null && list.size()>0){
            for(CatchOpinionStatistics catchOpinionStatistics1 : list){
                JSONArray jsonArray1 = new JSONArray();
                JSONArray jsonArray2 = new JSONArray();
                jsonArray1.put(catchOpinionStatistics1.getIndexPositive());//正
                jsonArray1.put(catchOpinionStatistics1.getIndexThe());//中
                jsonArray1.put(catchOpinionStatistics1.getIndexNegative());//负
                jsonArray2.put(catchOpinionStatistics1.getTotalNetizen());//网民
                jsonArray2.put(catchOpinionStatistics1.getTotalMedia());//媒体
                jsonObject1.put(catchOpinionStatistics1.getStatisticalType(),jsonArray1);
                jsonObject2.put(catchOpinionStatistics1.getStatisticalType(),jsonArray2);
                //统计所有各指标数量
                indexPositiveTotal += catchOpinionStatistics1.getIndexPositive();
                indexTheTotal += catchOpinionStatistics1.getIndexThe();
                indexNegativeTotal += catchOpinionStatistics1.getIndexNegative();
                netizenTotal +=  catchOpinionStatistics1.getTotalNetizen();
                mediaTotal += catchOpinionStatistics1.getTotalMedia();
            }
        }
        JSONArray json1 = new JSONArray();
        json1.put(indexPositiveTotal);
        json1.put(indexTheTotal);
        json1.put(indexNegativeTotal);
        JSONArray json2 = new JSONArray();
        json2.put(netizenTotal);
        json2.put(mediaTotal);

        jsonObject1.put("dataTotal",json1);
        jsonObject2.put("dataTotal",json2);

        jsonObject3.put("name","情感指数");
        jsonObject3.put("shuju",jsonObject1);
        jsonObject4.put("name","今日总量");
        jsonObject4.put("shuju",jsonObject2);
        jsonArray.put(jsonObject3);
        jsonArray.put(jsonObject4);
        response.setContentType("text/plain");
        response.setHeader("Cache-Control", "no-cache");
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        writer.print(jsonArray.toString());

    }
    //红色预警
    @ResponseBody
    @ApiOperation(value = "红色预警", httpMethod = "GET", notes = "")
    @RequestMapping(value = "earlyList")
    public void earlyList(CatchEarlyWarning catchEarlyWarning,HttpServletResponse response) throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        //catchEarlyWarning.setDate(date);
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray1 = new JSONArray();
        JSONArray jsonArray2 = new JSONArray();
        List<CatchEarlyWarning> list = catchEarlyWarningService.earlyData(catchEarlyWarning);
        if(list != null && list.size()>0){
            if(list.size()>6){
                list = list.subList(list.size()-6,list.size());
            }
            for(CatchEarlyWarning catchEarlyWarning1 :list){

                jsonArray1.put(catchEarlyWarning1.getDate());
                jsonArray2.put(catchEarlyWarning1.getNumber());
                jsonObject.put("date",jsonArray1);
                jsonObject.put("number",jsonArray2);
            }
        }
        response.setContentType("text/plain");
        response.setHeader("Cache-Control", "no-cache");
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        writer.print(jsonObject.toString());
    }
    //全国舆情热点
    @ResponseBody
    @ApiOperation(value = "全国舆情热点", httpMethod = "GET", notes = "")
    @RequestMapping(value = "spotList")
    public void spotList(HttpServletResponse response){
        JSONObject jsonObject =new JSONObject();
        List<CatchSpotOpinion> list = catchSpotOpinionService.spotData();
        if(list != null && list.size()>0){

            JSONArray jsonArray1 = new JSONArray();
            JSONArray jsonArray2 = new JSONArray();
            for(CatchSpotOpinion catchSpotOpinion : list){
                jsonArray1.put(catchSpotOpinion.getChinaType());
                jsonArray2.put(catchSpotOpinion.getNumber());
                jsonObject.put("type",jsonArray1);
                jsonObject.put("number",jsonArray2);
            }
        }
        response.setContentType("text/plain");
        response.setHeader("Cache-Control", "no-cache");
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        writer.print(jsonObject.toString());
    }
    //舆情来源
    @ResponseBody
    @ApiOperation(value = "舆情来源", httpMethod = "GET", notes = "")
    @RequestMapping(value = "surceList")
    public void surceList(HttpServletResponse response){
        JSONObject jsonObject = new JSONObject();
        List<CatchSourceOpinion> list = catchSourceOpinionService.sourceData();
        if(list != null && list.size()>0){
            JSONArray jsonArray1 = new JSONArray();
            JSONArray jsonArray2 = new JSONArray();
            for(CatchSourceOpinion catchSourceOpinion : list){
                jsonArray1.put(catchSourceOpinion.getSourceType());
                jsonArray2.put(catchSourceOpinion.getNumber());
                jsonObject.put("type",jsonArray1);
                jsonObject.put("number",jsonArray2);
            }
        }
        response.setContentType("text/plain");
        response.setHeader("Cache-Control", "no-cache");
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        writer.print(jsonObject.toString());
    }
    //铜川热点舆情
    @ResponseBody
    @ApiOperation(value = "铜川热点舆情", httpMethod = "GET", notes = "")
    @RequestMapping(value = "hotList")
    public void hotList(HttpServletResponse response){
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray2 = new JSONArray();
        List<CatchHotPublic> catchHotPublics = catchHotPublicService.hotData();
        for(CatchHotPublic catchHotPublic : catchHotPublics){
            jsonArray2.put(catchHotPublic.getPublicOpinion());
            jsonObject.put("hotOpinion",jsonArray2);
        }
        response.setContentType("text/plain");
        response.setHeader("Cache-Control", "no-cache");
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        writer.print(jsonObject.toString());
    }
    //铜川地理舆情追踪
    @ResponseBody
    @ApiOperation(value = "铜川地理舆情追踪", httpMethod = "GET", notes = "")
    @RequestMapping(value = "catchGeographicTrackingList")
    public void catchGeographicTrackingList(HttpServletResponse response){
        JSONArray jsonArray2 = new JSONArray();
        List<CatchGeographicTracking> catchGeographicTrackingList = catchGeographicTrackingService.findList(new CatchGeographicTracking());
        for(CatchGeographicTracking catchGeographicTracking : catchGeographicTrackingList){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name",catchGeographicTracking.getAreaName());
            jsonObject.put("value",catchGeographicTracking.getTrackNum());
            jsonArray2.put(jsonObject);
        }
        response.setContentType("text/plain");
        response.setHeader("Cache-Control", "no-cache");
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        writer.print(jsonArray2.toString());
    }
    //热搜词汇
    @ResponseBody
    @ApiOperation(value = "热搜词汇", httpMethod = "GET", notes = "")
    @RequestMapping(value = "cahchHotSearchList")
    public void cahchHotSearchList(HttpServletResponse response){
        JSONArray jsonArray2 = new JSONArray();
        List<CatchHotSearch> catchHotSearchList = catchHotSearchService.findList(new CatchHotSearch());
        if(catchHotSearchList !=null && catchHotSearchList.size() >0 ){
            for(CatchHotSearch catchHotSearch : catchHotSearchList){
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("name",catchHotSearch.getName());
                jsonObject.put("link",catchHotSearch.getLink());
                jsonArray2.put(jsonObject);
            }
        }
        response.setContentType("text/plain");
        response.setHeader("Cache-Control", "no-cache");
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        writer.print(jsonArray2.toString());
    }
    //媒体分布
    @ResponseBody
    @ApiOperation(value = "媒体分布", httpMethod = "GET", notes = "")
    @RequestMapping(value = "cahchMediaSpreadList")
    public void cahchMediaSpreadList(HttpServletResponse response){
        JSONArray jsonArray2 = new JSONArray();
        List<CatchMediaSpread> cahchMediaSpreadList = catchMediaSpreadService.findList(new CatchMediaSpread());
        if(cahchMediaSpreadList !=null && cahchMediaSpreadList.size() >0 ){
            for(CatchMediaSpread spread : cahchMediaSpreadList){
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("projectType",spread.getProjectType());
                jsonObject.put("nyear",spread.getNyear());
                jsonObject.put("totalInvestment", spread.getTotalInvestment());
                jsonArray2.put(jsonObject);
            }
        }
        response.setContentType("text/plain");
        response.setHeader("Cache-Control", "no-cache");
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        writer.print(jsonArray2.toString());
    }
  //媒体分布
    @ResponseBody
    @ApiOperation(value = "媒体类型", httpMethod = "GET", notes = "")
    @RequestMapping(value = "cahchMediaMtlxList")
    public void cahchMediaMtlxList(HttpServletResponse response){
    	int id = 1;//序号
        JSONArray jsonArray2 = new JSONArray();
        List<CatchMediaMtlx> catchMediaMtlxList = catchMediaMtlxService.findList(new CatchMediaMtlx());
        if(catchMediaMtlxList !=null && catchMediaMtlxList.size() >0 ){
            for(CatchMediaMtlx spread : catchMediaMtlxList){
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("name",spread.getName());
                jsonObject.put("id", id++);
                jsonObject.put("contribution",spread.getContribution());
                jsonObject.put("proportion", spread.getProportion());
                jsonArray2.put(jsonObject);
            }
        }
        response.setContentType("text/plain");
        response.setHeader("Cache-Control", "no-cache");
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        writer.print(jsonArray2.toString());
    }
  //媒体来源
    @ResponseBody
    @ApiOperation(value = "媒体来源", httpMethod = "GET", notes = "")
    @RequestMapping(value = "cahchMediaResourceList")
    public void cahchMediaResourceList(HttpServletResponse response){
    	int id = 1;//序号
        JSONArray jsonArray2 = new JSONArray();
        List<CatchMediaResource> cahchMediaResourceList = catchMediaResourceService.findList(new CatchMediaResource());
        if(cahchMediaResourceList !=null && cahchMediaResourceList.size() >0 ){
            for(CatchMediaResource spread : cahchMediaResourceList){
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("name",spread.getName());
                jsonObject.put("id", id++);
                jsonObject.put("contribution",spread.getContribution());
                jsonObject.put("proportion", spread.getProportion());
                jsonArray2.put(jsonObject);
            }
        }
        response.setContentType("text/plain");
        response.setHeader("Cache-Control", "no-cache");
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        writer.print(jsonArray2.toString());
    }
    //舆情指数
    @ResponseBody
    @ApiOperation(value = "舆情指数", httpMethod = "GET", notes = "")
    @RequestMapping(value = "catchSentimentIndexList")
    public void catchSentimentIndexList(HttpServletResponse response){
          JSONObject jsonObject = new JSONObject();
    	  JSONArray jsonArray1 = new JSONArray();
    	  JSONArray jsonArray2 = new JSONArray();
          JSONArray jsonArray3 = new JSONArray();
          JSONArray jsonArray4 = new JSONArray();
          CatchSentimentIndex da = new CatchSentimentIndex();
          da.getPage().setOrderBy("date");
        List<CatchSentimentIndex> CatchSentimentIndexList = catchSentimentIndexService.findList(da);
        if(CatchSentimentIndexList !=null && CatchSentimentIndexList.size() >0 ){
            for(CatchSentimentIndex spread : CatchSentimentIndexList){
                JSONArray jsonArray5 = new JSONArray();
                String formatDate = DateFormatUtils.format(spread.getDate(), "MM-dd HH:mm");
                jsonArray1.put(formatDate);
                jsonArray2.put(Integer.parseInt(spread.getSensitiveIndex()) + Integer.parseInt(spread.getNonSensitiveIndex())); //全部
                jsonArray3.put(spread.getSensitiveIndex());//敏感
                jsonArray4.put(spread.getNonSensitiveIndex());//非敏感
                jsonArray5.put(jsonArray2);
                jsonArray5.put(jsonArray3);
                jsonArray5.put(jsonArray4);
                jsonObject.put("date",jsonArray1);
                jsonObject.put("datas",jsonArray5);
            }
        }
        response.setContentType("text/plain");
        response.setHeader("Cache-Control", "no-cache");
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        writer.print(jsonObject.toString());
    }
    //媒体活跃度
    @ResponseBody
    @ApiOperation(value = "媒体活跃度", httpMethod = "GET", notes = "")
    @RequestMapping(value = "cahchMediaHydList")
    public void cahchMediaHydList(HttpServletResponse response){
        JSONArray jsonArray2 = new JSONArray();
        CatchMediaHyd catchMediaHyd =  new CatchMediaHyd();
        catchMediaHyd.getPage().setOrderBy("number");
        List<CatchMediaHyd> cahchMediaHydList = catchMediaHydService.findList(catchMediaHyd);
        if(cahchMediaHydList !=null && cahchMediaHydList.size() >0 ){
            for(CatchMediaHyd hyd : cahchMediaHydList){
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("legend", hyd.getLegend());
                jsonObject.put("number", hyd.getNumber());          
                jsonArray2.put(jsonObject);
            }
        }
        response.setContentType("text/plain");
        response.setHeader("Cache-Control", "no-cache");
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        writer.print(jsonArray2.toString());
    }
    //热门公众号
    @ResponseBody
    @ApiOperation(value = "热门公众号", httpMethod = "GET", notes = "")
    @RequestMapping(value = "cahchPopularAddressList")
    public void cahchPopularAddressList(HttpServletResponse response){
        JSONArray jsonArray2 = new JSONArray();
        List<CatchPopularAddress> list = catchPopularAddressService.findList(new CatchPopularAddress());
        if(list !=null && list.size() >0 ){
            for(CatchPopularAddress hyd : list){
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("name", hyd.getName());
                jsonObject.put("value", hyd.getHotValue());          
                jsonArray2.put(jsonObject);
            }
        }
        response.setContentType("text/plain");
        response.setHeader("Cache-Control", "no-cache");
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        writer.print(jsonArray2.toString());
    }
    //敏感指数
    @ResponseBody
    @ApiOperation(value = "敏感指数", httpMethod = "GET", notes = "")
    @RequestMapping(value = "cahchSensitivityIndexList")
    public void cahchSensitivityIndexList(HttpServletResponse response){
        JSONArray jsonArray2 = new JSONArray();
        List<CatchSensitivityIndex> list = catchSensitivityIndexService.findList(new CatchSensitivityIndex());
        if(list !=null && list.size() >0 ){
            for(CatchSensitivityIndex hyd : list){
                JSONObject jsonObject = new JSONObject();
                JSONObject jsonObject1 = new JSONObject();
                jsonObject.put("name","敏感指数");        //敏感指数
                jsonObject.put("num", hyd.getSensitiveIndex());        //敏感指数数量
                jsonObject1.put("name","非敏感指数");  //非敏感指数        
                jsonObject1.put("num", hyd.getNonSensitiveIndex());  //非敏感指数        
                jsonArray2.put(jsonObject);
                jsonArray2.put(jsonObject1);
            }
        }
        response.setContentType("text/plain");
        response.setHeader("Cache-Control", "no-cache");
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        writer.print(jsonArray2.toString());
    }
    //所属行业
    @ResponseBody
    @ApiOperation(value = "所属行业", httpMethod = "GET", notes = "")
    @RequestMapping(value = "catchTradeIndexList")
    public void catchTradeIndexList(HttpServletResponse response){
        JSONArray jsonArray2 = new JSONArray();
        List<CatchTradeIndex> list = catchTradeIndexService.findList(new CatchTradeIndex());
        if(list !=null && list.size() >0 ){
            for(CatchTradeIndex hyd : list){
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("name", hyd.getName());        //敏感指数
                jsonObject.put("value", hyd.getValue());  //非敏感指数        
                jsonArray2.put(jsonObject);
            }
        }
        response.setContentType("text/plain");
        response.setHeader("Cache-Control", "no-cache");
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        writer.print(jsonArray2.toString());
    }
}
