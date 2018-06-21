package com.cfl.util;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import static sun.net.www.protocol.http.HttpURLConnection.userAgent;

public class GetDB {
    public static List<HashMap<String,String>> getDatas(String url){
        Connection conn = Jsoup.connect(url);
        // 修改http包中的header,伪装成浏览器进行抓取
        conn.header("User-Agent", userAgent);
        Document doc = null;
        try {
            doc = conn.get();
            //解析源代码
            Document document = Jsoup.parse(doc.html());
            System.out.println(document);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void main(String arg[]){
        getDatas("https://instagram.com/p/BieUQrQlgKg/");
    }
}
