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
                    System.out.println(content.text());
                }catch (Exception e){
                    Element content = elementes.getElementsByTag("p").get(0);
                    System.out.println(content.text());
                }
            }
            getDatas();
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
        webDriver.get("https://www.instagram.com/accounts/login/");
        System.out.println("打开页面");
        getDatas("https://m.dealmoon.co.uk/");
        ((JavascriptExecutor) webDriver).executeScript("window.scrollTo(0, document.body.scrollHeight)");

    }
    public static void getDatas(){
        //第一个参数表示输入参数，用谷歌        第二个参数表示谷歌安装路径
        System.setProperty("webdriver.chrome.driver", "C:\\com\\chromedriver.exe");
        WebDriver webDriver=new ChromeDriver();
        //打开浏览器
        ((JavascriptExecutor) webDriver).executeScript("window.scrollTo(0, document.body.scrollHeight)");

    }
    public static void main(String arg[]){
//        getData("http://www.qq.com","gbk");
        open();

    }
}
