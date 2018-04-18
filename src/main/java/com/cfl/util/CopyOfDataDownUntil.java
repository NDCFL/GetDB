package com.cfl.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class CopyOfDataDownUntil {
    public static String getData(String url,String encoding){
        StringBuffer buff = new StringBuffer();
        InputStreamReader isr = null;
        try {
            //建立网络连接
            URL urls = new URL(url);
            //打开网络连接
            URLConnection uc = urls.openConnection();
            isr = new InputStreamReader(uc.getInputStream(),encoding);
            BufferedReader reader = new BufferedReader(isr);
            String line = "";
            //循环
            while ((line=reader.readLine())!=null){
                //保存
                buff.append(line+"\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(isr!=null){
                try {
                    isr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return buff.toString();
    }

    public static List<HashMap<String,String>> getList(String url){
        String html = getData(url,"utf-8");
        //解析源代码
        Document document = Jsoup.parse(html);
        Element element = document.getElementById("hotel_item");
        //获取酒店列表
        // 进行迭代；读取根节点下的所有节点
        return  null;
    }
    public static void main(String arg[]){
        getData("http://www.qq.com","gbk");
        System.out.println("");
    }
}
