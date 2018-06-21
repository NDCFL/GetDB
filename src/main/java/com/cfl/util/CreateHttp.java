package com.cfl.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.NameValuePair;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class CreateHttp {

    public static final String GET_URL = "https://sp0.baidu.com/8aQDcjqpAAV3otqbppnN2DJv/api.php?query=192.168.1.6&co=&resource_id=6006&t=1527150278278&ie=utf8&oe=gbk&cb=op_aladdin_callback&format=json&tn=baidu&cb=jQuery110203653283747858922_1527150240348&_=1527150240353";
    // 妙兜测试接口
    public static final String POST_URL = "https://www.instagram.com/accounts/login/ajax/";

    /**
     * 接口调用 GET
     */
    public static void httpURLConectionGET() {
        try {
            URL url = new URL(GET_URL);    //把字符串转换为URL请求地址
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();// 打开连接
            //addRequestProperty添加相同的key不会覆盖，如果相同，内容会以{name1,name2}
            connection.addRequestProperty("from", "sfzh");  //来源哪个系统
            //setRequestProperty添加相同的key会覆盖value信息
            //setRequestProperty方法，如果key存在，则覆盖；不存在，直接添加。
            //addRequestProperty方法，不管key存在不存在，直接添加。
            connection.setRequestProperty("user", "user");  //访问申请用户
            InetAddress address = InetAddress.getLocalHost();
            String ip = address.getHostAddress();//获得本机IP
            connection.setRequestProperty("ip", ip);  //请求来源IP
            connection.setRequestProperty("encry", "00000");
            connection.connect();// 连接会话
            // 获取输入流
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "GBK"));
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {// 循环读取流
                sb.append(line);
            }
            br.close();// 关闭流
            connection.disconnect();// 断开连接
            System.out.println(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("失败!");
        }
    }

    /**
     * 接口调用  POST
     */
    public static void httpURLConnectionPOST() {
        try {
            //传递参数
            String Parma = "?username=clevercfl&password=cfl,1997";

            URL url = new URL(POST_URL);
            // 将url 以 open方法返回的urlConnection  连接强转为HttpURLConnection连接  (标识一个url所引用的远程对象连接)
            // 此时cnnection只是为一个连接对象,待连接中
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            // 设置连接输出流为true,默认false (post 请求是以流的方式隐式的传递参数)
            connection.setDoOutput(true);
            // 设置连接输入流为true
            connection.setDoInput(true);
            // 设置请求方式为post
            connection.setRequestMethod("POST");
            // post请求缓存设为false
            connection.setUseCaches(false);
            // 设置该HttpURLConnection实例是否自动执行重定向
            connection.setInstanceFollowRedirects(true);
            InetAddress address = InetAddress.getLocalHost();
            String ip = address.getHostAddress();//获得本机IP
            connection.setRequestProperty("ip","58.152.48.48:443");  //请求来源IP
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
            // 建立连接 (请求未开始,直到connection.getInputStream()方法调用时才发起,以上各个参数设置需在此方法之前进行)
            connection.connect();
            // 创建输入输出流,用于往连接里面输出携带的参数,(输出内容为?后面的内容)
            DataOutputStream dataout = new DataOutputStream(connection.getOutputStream());
            // 格式 parm = aaa=111&bbb=222&ccc=333&ddd=444
            String parm = "username=clevercfl&password=cfl,1997";
            System.out.println("传递参数：" + parm);
            // 将参数输出到连接
            dataout.writeBytes(parm);
            // 输出完成后刷新并关闭流
            dataout.flush();
            dataout.close(); // 重要且易忽略步骤 (关闭流,切记!)
            //System.out.println(connection.getResponseCode());
            // 连接发起请求,处理服务器响应  (从连接获取到输入流并包装为bufferedReader)
            BufferedReader bf = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            String line;
            StringBuilder sb = new StringBuilder(); // 用来存储响应数据

            // 循环读取流,若不到结尾处
            while ((line = bf.readLine()) != null) {
                //sb.append(bf.readLine());
                sb.append(line).append(System.getProperty("line.separator"));
            }
            bf.close();    // 重要且易忽略步骤 (关闭流,切记!)
            connection.disconnect(); // 销毁连接
            System.out.println(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        httpURLConectionGET();
//        httpURLConnectionPOST();
    }
    public static void main1(String args[]) throws Exception {
        // 创建HttpClientBuilder
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        // HttpClient
        CloseableHttpClient closeableHttpClient = httpClientBuilder.build();
        // 依次是目标请求地址，端口号,协议类型
        HttpHost target = new HttpHost("https://www.instagram.com/", 80,
                "http");
        // 依次是代理地址，代理端口号，协议类型
        HttpHost proxy = new HttpHost("yourproxy", 8080, "http");
        RequestConfig config = RequestConfig.custom().setProxy(proxy).build();

        // 请求地址
        HttpPost httpPost = new HttpPost("http://10.10.100.102:8080/mytest");
        httpPost.setConfig(config);
        // 创建参数队列
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        // 参数名为pid，值是2
        formparams.add(new BasicNameValuePair("username", "jiumu9711"));
        formparams.add(new BasicNameValuePair("password", "Gi&rAaoF"));
        UrlEncodedFormEntity entity;
        try {
            entity = new UrlEncodedFormEntity(formparams, "UTF-8");
            httpPost.setEntity(entity);
            CloseableHttpResponse response = closeableHttpClient.execute(
                    target, httpPost);
            // getEntity()
            HttpEntity httpEntity = response.getEntity();
            if (httpEntity != null) {
                // 打印响应内容
                System.out.println("response:"
                        + EntityUtils.toString(httpEntity, "UTF-8"));
            }
            // 释放资源
            closeableHttpClient.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}