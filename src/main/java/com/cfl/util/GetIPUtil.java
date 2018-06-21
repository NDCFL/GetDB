package com.cfl.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GetIPUtil {
    public static String getMyIP(){
        String url="http://ip.chinaz.com/getip.aspx";
        InputStream is = null;
        try {
            is = new URL(url).openStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));

            StringBuilder sb = new StringBuilder();
            int cp;
            while ((cp = rd.read()) != -1) {
                sb.append((char) cp);
            }
            String jsonText =  sb.toString();;
//            jsonText=jsonText.replaceAll("'", "");
//            jsonText=jsonText.substring(1,jsonText.length()-1);
//            jsonText=jsonText.replaceAll(",", "");
            return jsonText;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args){
        System.out.println(getMyIP());
    }
}
