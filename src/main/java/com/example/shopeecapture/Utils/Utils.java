package com.example.shopeecapture.Utils;

import com.alibaba.fastjson.JSONObject;
import com.example.shopeecapture.config.MyX509TrustManager;
import com.example.shopeecapture.config.NullHostNameVerifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.List;

public class Utils {

    static final Logger logger = LoggerFactory.getLogger(Utils.class);

    public static JSONObject callShoppe(String requestUrl) throws InterruptedException {
        JSONObject jsonObject = null;
        logger.debug("call shopee start-------");
        logger.debug(requestUrl);
        int callCount = 0;
        int initCallCount = 5;
        while (callCount<initCallCount){
            try{
                HttpsURLConnection.setDefaultHostnameVerifier(new NullHostNameVerifier());
                TrustManager[] tm = {new MyX509TrustManager()};
                SSLContext sslContext = SSLContext.getInstance("TLS");
                sslContext.init(null, tm, new java.security.SecureRandom());
                SSLSocketFactory ssf = sslContext.getSocketFactory();
                URL url = new URL(requestUrl);
                HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
                con.setConnectTimeout(600000);
                con.setReadTimeout(600000);
                con.setSSLSocketFactory(ssf);
                con.setRequestMethod("GET");
                con.setDoInput(true);
                con.setDoOutput(true);

                InputStreamReader in = new InputStreamReader(con.getInputStream());
                BufferedReader bfreader = new BufferedReader(in);
                StringBuilder result = new StringBuilder();
                String line;
                while ((line = bfreader.readLine()) != null) {
                    result.append(line);
                }
                jsonObject = JSONObject.parseObject(result.toString());
                callCount = initCallCount;
                logger.debug("call "+(callCount+1)+" time and finished");
            }catch (Exception e){
                callCount = callCount + 1;
                logger.debug("call "+callCount+" time");
                logger.error(e.getMessage());
                Thread.sleep(1000);
            }
            logger.debug("call shopee end-------");
        }
        return jsonObject;
    }

    public static String getPercent(int x, int y) {
        double d1 = x * 1.0;
        double d2 = y * 1.0;
        // 设置保留几位小数， “.”后面几个零就保留几位小数，这里设置保留四位小数
        DecimalFormat decimalFormat = new DecimalFormat("##.00%");
        return decimalFormat.format(d1 / d2);
    }

    public static double doubleValueCheck(Object value){
        if (value==null){
            return 0;
        }else{
            double valueDouble = Double.parseDouble(value.toString());
            if (valueDouble>0){
                valueDouble = valueDouble / 100000;
            }else{
                valueDouble = 0;
            }
            return valueDouble;
        }
    }

    public static int intValueCheck(Object value){
        if (value==null){
            return 0;
        }else{
            int valueInt = Integer.parseInt(value.toString());
            if (valueInt<=0){
                valueInt = 0;
            }
            return valueInt;
        }
    }

    public static double median(List<Integer> total) {
        double j;
        //集合排序
        Collections.sort(total);
        int size = total.size();
        if(size % 2 == 1){
            j = total.get((size-1)/2);
        }else {
            //加0.0是为了把int转成double类型，否则除以2会算错
            j = (total.get(size/2-1) + total.get(size/2) + 0.0)/2;
        }
        return j;
    }

    public static String formatTime(Long ms) {
        Integer ss = 1000;
        Integer mi = ss * 60;
        Integer hh = mi * 60;
        Integer dd = hh * 24;

        Long day = ms / dd;
        Long hour = (ms - day * dd) / hh;
        Long minute = (ms - day * dd - hour * hh) / mi;
        Long second = (ms - day * dd - hour * hh - minute * mi) / ss;
        Long milliSecond = ms - day * dd - hour * hh - minute * mi - second * ss;

        StringBuffer sb = new StringBuffer();
        if(day > 0) {
            sb.append(day+" Day ");
        }
        if(hour > 0) {
            sb.append(hour+" Hours ");
        }
        if(minute > 0) {
            sb.append(minute+" Min ");
        }
        if(second > 0) {
            sb.append(second+" Sec ");
        }
        if(milliSecond > 0) {
            sb.append(milliSecond+" ms ");
        }
        return sb.toString();
    }

}
