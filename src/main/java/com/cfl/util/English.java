package com.cfl.util;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import static sun.net.www.protocol.http.HttpURLConnection.userAgent;

public class English  {
    public static List<HashMap<String,String>> getDatas(String url){
        Connection conn = Jsoup.connect(url);
        // 修改http包中的header,伪装成浏览器进行抓取
        conn.header("User-Agent", userAgent);
        Document doc = null;
        try {
            doc = conn.get();
            //解析源代码
            Document document = Jsoup.parse(doc.html());
            Element element = document.getElementById("center-5");
            System.out.println(element);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void main(String arg[]){
//        getData("http://www.qq.com","gbk");
        getDatas("https://www.audible.com/");

    }
}
