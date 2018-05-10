package com.cfl.util;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import static sun.net.www.protocol.http.HttpURLConnection.userAgent;

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
        Element element = document.getElementById("hotel_list");
        Elements elementsUl = element.getElementsByTag("ul");
        for (Element elementes : elementsUl) {
            Element img = elementes.getElementsByTag("li").get(0);
            Element name = elementes.getElementsByTag("li").get(1);
            Elements element1 = img.getElementsByTag("img");
            Element element2 = name.getElementsByTag("h2").get(0);
            System.out.println("图片地址:http:"+element1.attr("src")+";酒店名称："+element2.text()+"访问地址:http://hotels.ctrip.com/"+element2.getElementsByTag("a").attr("href"));
        }
        //获取酒店列表
        // 进行迭代；读取根节点下的所有节点
        return  null;
    }
    public static List<HashMap<String,String>> getDatas(String url){
        Connection conn = Jsoup.connect(url);
        // 修改http包中的header,伪装成浏览器进行抓取
        conn.header("User-Agent", userAgent);
        Document doc = null;
        try {
            doc = conn.get();
            //解析源代码
            Document document = Jsoup.parse(doc.html());
            Element element = document.getElementById("hotel_list");
            Elements elementsUl = element.getElementsByTag("ul");
            for (Element elementes : elementsUl) {
                Element img = elementes.getElementsByTag("li").get(0);
                Element name = elementes.getElementsByTag("li").get(1);
                Elements element1 = img.getElementsByTag("img");
                Element element2 = name.getElementsByTag("h2").get(0);
                System.out.println("图片地址:http:"+element1.attr("src")+";酒店名称："+element2.text()+"访问地址:http://hotels.ctrip.com/"+element2.getElementsByTag("a").attr("href"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void main1(String arg[]){
//        getData("http://www.qq.com","gbk");
        getDatas("https://www.audible.com/");
    }
    public static void main(String arg[]){
       try{
           //第一个参数表示输入参数，用谷歌        第二个参数表示谷歌安装路径
           System.setProperty("webdriver.chrome.driver", "C:\\com\\chromedriver.exe");
           //获取谷歌驱动
           WebDriver webDriver=new ChromeDriver();
           //打开浏览器
           webDriver.get("https://www.instagram.com/accounts/login/");
           System.out.println("打开页面");
           //暂停
           Thread.sleep(1000);
           //屏幕最大化
           webDriver.manage().window().maximize();

           //使用Xpath获取
           webDriver.findElement(By.id("f26b8ac696c4998")).sendKeys("jiumu9711");
           webDriver.findElement(By.id("f2208c59425f134")).sendKeys("Gi&rAaoF");

           Thread.sleep(3000);
           webDriver.findElement(By.className("_qv64e       _gexxb _4tgw8    _jfvwv    ")).click();
           ((JavascriptExecutor) webDriver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
       }catch (Exception e){

       }


    }
}
