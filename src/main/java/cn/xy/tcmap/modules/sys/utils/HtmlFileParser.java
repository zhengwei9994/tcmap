package cn.xy.tcmap.modules.sys.utils;

import cn.xy.tcmap.modules.catchup.csyx.entity.CatchAqiparam;
import cn.xy.tcmap.modules.catchup.wlkj.entity.CatchHotPublic;
import cn.xy.tcmap.modules.catchup.wlkj.entity.CatchHotSearch;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HtmlFileParser {

    /*
   * 获取更新时间
   */public static String weatherDate( ) throws Exception {

        Document doc = Jsoup.connect("http://113.140.66.226:8024/sxAQIWeb/PageDistrict.aspx?cityCode=NjEwNDAw").timeout(3000000).get();
        Element liws1= doc.getElementById("ctl00_ContentPlaceHolder1_labTime");
        return liws1.toString();
    }
    //获取陕西省空气质量数据
    public static List<CatchAqiparam>  aqiTable() {
        List<CatchAqiparam> aqiParams = new ArrayList<CatchAqiparam>();
        String http[] = {"NjEwMTAw","NjEwMzAw","NjEwNDAw","NjEwMjAw","NjEwNTAw","NjEwNjAw","NjEwODAw","NjEwNzAw","NjEwOTAw","NjExMDAw","NjE1MDAw","NjEyMDAw","NjEzMDAw"};
        String area[] = {"西安市","宝鸡市","咸阳市","铜川市","渭南市","延安市","榆林市","汉中市","安康市","商洛市","杨凌示范区","西咸新区","韩城市"};
        try {

            for(int i=0;i<http.length;i++){
                CatchAqiparam aqiParam = new CatchAqiparam();
                Document doc = Jsoup.connect("http://113.140.66.226:8024/sxAQIWeb/PageCity.aspx?cityCode="+http[i]).timeout(3000000).get();
                Element a = doc.getElementById("ctl00_ContentPlaceHolder1_labAQI");//aq值
                String value =  a.toString();
                if(!value.equals("<span id=\"ctl00_ContentPlaceHolder1_labAQI\" class=\"zk_c\">Na</span>")){
                    int strvalue = value.indexOf("</");
                    aqiParam.setAreaName(area[i]);
                    String strValue = value.substring(57,strvalue);
                    aqiParam.setAqi(strValue);
                }
                String b = doc.getElementById("ctl00_ContentPlaceHolder1_labOrder").toString();//全县排名
                if(!b.equals("<span id=\"ctl00_ContentPlaceHolder1_labOrder\">Na</span>")){
                    int str = b.indexOf("/");
                    String sort = b.toString().substring(46,str);
                    int sorts=Integer.parseInt(sort);
                    aqiParam.setRank(sorts);
                }
                Element d = doc.getElementById("ctl00_ContentPlaceHolder1_labLevelName");//污染等级名称
                String name = d.toString();
                if(!name.equals("<span id=\"ctl00_ContentPlaceHolder1_labLevelName\">Na</span>")){
                    int strname = name.indexOf("</");
                    String nameValue = name.substring(50,strname);
                    aqiParam.setAqilevel(nameValue);
                }
                if(!value.equals("<span id=\"ctl00_ContentPlaceHolder1_labAQI\" class=\"zk_c\">Na</span>")){
                    aqiParams.add(aqiParam);
                }

            }

           /* sortIntMethod(aqiParams);*/

        }catch (Exception e){
            e.printStackTrace();
        }

        return aqiParams;
    }

    public static List<CatchHotPublic>  hotPublic(){

        List<CatchHotPublic> list = new ArrayList<CatchHotPublic>();
        try {
            Document doc = Jsoup.connect("http://bbs.hsw.cn/thread-htm-fid-328-search-digest.html#tabA").timeout(3000000).get();
            Elements ment = doc.select("td a b font");
            for(int i=0; i<8;i++){
                CatchHotPublic catchHotPublic = new CatchHotPublic();
                String men =   ment.get(i).toString();
                int a = men.indexOf(">");
                int b = men.indexOf("</");
                String publicOpinnion = men.substring(a+1,b);
                catchHotPublic.setPublicOpinion(publicOpinnion);
                catchHotPublic.setSort((i+1)+"");
                list.add(catchHotPublic);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
    //热搜词汇
    public static List<CatchHotSearch>  hotSearch(){
        List<CatchHotSearch> list = new ArrayList<CatchHotSearch>();

        try {
            Document doc = Jsoup.connect("http://top.baidu.com/buzz?b=1&fr=topindex").timeout(3000000).get();
            Elements as  = doc.getElementsByClass("list-title");
            for(int i=0; i<as.size(); i++){
                CatchHotSearch catchHotSearch = new CatchHotSearch();
                String href =  as.get(i).attr("href");
             String men =   as.get(i).toString();
                int a = men.indexOf(">");
                int b = men.indexOf("</");
                catchHotSearch.setName(men.substring(a+1,b));
                catchHotSearch.setLink(href);
                catchHotSearch.setSort((i+1)+"");
                list.add(catchHotSearch);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}
