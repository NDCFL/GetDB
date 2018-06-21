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
            Elements elements = document.getElementsByClass("mlist ");
            for (Element elementes : elements) {
                Element img = elementes.getElementsByTag("div").get(1);//获取缩略图
                Element title = elementes.getElementsByClass("clearfix").get(0).getElementsByClass("indextitle").get(0);
//                System.out.println(img.getElementsByTag("div").get(1).getElementsByTag("img").attr("src")+"======"+img.getElementsByTag("div").get(2).getElementsByTag("a").attr("href"));
//                System.out.println(title.text());//打印标题
                try{
                    Element content = elementes.getElementsByTag("ul").get(0);
                    System.out.println(img.getElementsByTag("div").get(1).getElementsByTag("img").attr("src")+"======"+img.getElementsByTag("div").get(2).getElementsByTag("a").attr("href")+content.text());
                }catch (Exception e){
                    Element content = elementes.getElementsByTag("p").get(0);
                    System.out.println(img.getElementsByTag("div").get(1).getElementsByTag("img").attr("src")+"======"+img.getElementsByTag("div").get(2).getElementsByTag("a").attr("href")+content.text());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void open(){
        //第一个参数表示输入参数，用谷歌        第二个参数表示谷歌安装路径
        System.setProperty("webdriver.chrome.driver", "C:\\com\\chromedriver.exe");
        WebDriver webDriver=new ChromeDriver();
        //打开浏览器
        webDriver.get("https://www.dealmoon.co.uk/");
        System.out.println("打开页面");
        ((JavascriptExecutor) webDriver).executeScript("scrollTo(0,30000)");
        getDatas("https://m.dealmoon.co.uk/");
    }
    public static void getDatas(){
        //第一个参数表示输入参数，用谷歌        第二个参数表示谷歌安装路径
        System.setProperty("webdriver.chrome.driver", "C:\\com\\chromedriver.exe");
        WebDriver webDriver=new ChromeDriver();
        //打开浏览器
        ((JavascriptExecutor) webDriver).executeScript("scrollTo(0,3000)");
    }
    public static void main(String arg[]){
        for(int i=1;i<100;i++){
            getDatas("https://www.dealmoon.co.uk/?p="+i);
        }

    }
}
