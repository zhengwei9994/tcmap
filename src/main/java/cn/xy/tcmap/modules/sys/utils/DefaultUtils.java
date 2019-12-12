package cn.xy.tcmap.modules.sys.utils;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 *
 * @author xuzhou
 * @version 2018-04-17
 *
 *
 */
public class DefaultUtils {

    public static String getNowYear(){
        Calendar date = Calendar.getInstance();
        String year = String.valueOf(date.get(Calendar.YEAR));
        return year;
    }

    public static String getNowMonths(){
        Calendar date = Calendar.getInstance();
        String months = String.valueOf(date.get(Calendar.MONTH)+1);
        return months;
    }
    public static String getNowRi(){
       return new SimpleDateFormat("yyyy-MM-dd").format(new Date()).toString();
    }
    /**
     17      * 获取过去第几天的日期(- 操作) 或者 未来 第几天的日期( + 操作)
     18      *
     19      * @param past
     20      * @return
     21      */
     public static String getPastDate(int past) {
                 Calendar calendar = Calendar.getInstance();
                 calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - past);
                 Date today = calendar.getTime();
                 SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                 String result = format.format(today);
                 //Log.e(null, result);
                 return result;
             }
    public static String getNowQuarter(){
        Calendar date = Calendar.getInstance();
        Integer months = date.get(Calendar.MONTH)+1;
        if(0<months&&months<4) {
            return "1";
        }else if(3<months&&months<7){
            return "2";
        }else if(6<months&&months<9){
            return "3";
        }else{
            return "4";
        }
    }

    public static String getNowQuarterName(){
        Calendar date = Calendar.getInstance();
        Integer months = date.get(Calendar.MONTH)+1;
        if(0<months&&months<4) {
            return "第一季度";
        }else if(3<months&&months<7){
            return "第二季度";
        }else if(6<months&&months<9){
            return "第三季度";
        }else{
            return "第四季度";
        }
    }

    @Test
    public static String test(long millisecond) {
        // = 1524031200000l;
        Date date = new Date(millisecond);
        SimpleDateFormat formats = new SimpleDateFormat("yyyy-MM-dd HH");
        return formats.format(date);
    }

}
