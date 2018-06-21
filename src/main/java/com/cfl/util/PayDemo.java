package com.cfl.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;

public class PayDemo {
    /**
     * 接口调用  POST
     */
    public static void httpURLConnectionPOST() {
        try {
            //传递参数
            String Parma = "?data={\"tid\":\"161035135135\",\"app\":\"3015404354\",\"url_r\":\"http://www.vip533.vip\", \"url_h\" :\"http://www.vip533.vip\",\"acid\":510777}&sign=MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAJa5bQL4FZGcVzyy6I5t5ZjjvHx7ZmU/IWWZ10y9EuY5wmBBCz959jZCne+d7xXArVQmiVs9EeU7vXfk2u5HCSrJYKWoZnKCqKA9DENr0gugmkrycslCU8MVmaLhLTJs/0oDE5l3Nyf/aoM6xvpvbP4XPQD9uykdMLaThdy1mMsZAgMBAAECgYBo8/dfoOZL/YSVhLMBMq80tifyum7Dw6D6wZQ1IFd/ruqV4dT2P3PdtqeJSf1ItWE8fpUYzj6e0+mMCfUVBKvxaKWknecQWHTw7mZVnhTwtNLjKMtSoCNCSYslLpxXAwojRzAS3iRpz5/JvHn9lVU2k2BUqzXo3rL/RsLFtnXoAQJBAMi4dDTM1lCweJLiZrngvHy5WVjnbTItXxh59XUgntlBbunzHh5DyQFESfVJW11DSMgYP9YbiNZzNO9SoFiLNQECQQDAPA2NRZ6wSDKvl53JBgV9yFEvjStRS+DFzzOonUxjNJWAhJCP74dWdb1W/p3kHpFdQ4d71WOneDWl06HTSp4ZAkEAlwf8ILK03Jg80cFbrKfHDE5nRXc1+hibwo4aPRzjifQrYgwWcI1u1O4ufEvLXJzBSha9Cf1D9KFRhnb0wktVAQJBAJnpexA5RazuoWZlhjeFUrA6yoXACYa1semu1DWUe6ySVYDoorYbmchnzIjyo4gZ0VlS0A8d8wQLAykaIN8DR0ECQGCYwEEQXDSpegmFIAI+ldBaqrdh1YaT4OR/u03xmXDcSyaAhyG1d6qxmt6HbfIioCpVlTAI8sFelsHY++ZR0p4=&sign_type=RSA";

            URL url = new URL("https://web.iapppay.com/pay/gateway");
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
            connection.setRequestProperty("ip",ip);  //请求来源IP
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
            // 建立连接 (请求未开始,直到connection.getInputStream()方法调用时才发起,以上各个参数设置需在此方法之前进行)
            connection.connect();
            // 创建输入输出流,用于往连接里面输出携带的参数,(输出内容为?后面的内容)
            DataOutputStream dataout = new DataOutputStream(connection.getOutputStream());
            // 格式 parm = aaa=111&bbb=222&ccc=333&ddd=444
            // 将参数输出到连接
            dataout.writeBytes(Parma);
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
    public static void main(String arg[]){
        httpURLConnectionPOST();

    }
}
