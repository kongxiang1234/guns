package cn.stylefeng.guns.modular;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {
    public static void main(String[] args) throws  Exception{

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr1 = "2018-12-27 17:07:07";
        String dateStr2 = "2018-12-31 00:00:00";

        // 获取日期
        long time1 = sdf.parse(dateStr1).getTime();
        long time2 = new Date().getTime();

        System.out.println((time2-time1)/(24*60*60*1000));
    }
}
