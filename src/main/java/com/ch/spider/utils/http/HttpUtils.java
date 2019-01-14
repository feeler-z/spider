package com.ch.spider.utils.http;

import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.httpclient.util.URIUtil;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.zip.GZIPInputStream;

/**
 * HTTP POST和GET处理工具类
 */
public class HttpUtils {

    /**
     * 向指定URL发送GET方法的请求
     * @param url 发送请求的URL
     * @param params 请求参数
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet(String url, HashMap<String,String> params) {
        String result = "";
        BufferedReader in = null;
        try {
            /**组装参数**/
            String param = parseParams(params);
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            /**打开和URL之间的连接**/
            URLConnection connection = realUrl.openConnection();
            /**设置通用的请求属性**/
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            /**建立实际的连接**/
            connection.connect();
            /**定义 BufferedReader输入流来读取URL的响应**/
            in = new BufferedReader(new InputStreamReader(connection.getInputStream(),"UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        } finally {/**使用finally块来关闭输入流**/
            try {
                if(in != null) { in.close(); }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 向指定 URL 发送POST方法的请求
     * @param url 发送请求的 URL
     * @param params 请求参数
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, HashMap<String,String> params) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            /**打开和URL之间的连接**/
            URLConnection conn = realUrl.openConnection();
            /**设置通用的请求属性**/
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            /**发送POST请求必须设置如下两行**/
            conn.setDoOutput(true);
            conn.setDoInput(true);
            /**获取URLConnection对象对应的输出流**/
            out = new PrintWriter(conn.getOutputStream());
            /**发送请求参数**/
            String param = parseParams(params);
            out.print(param);
            /**flush输出流的缓冲**/
            out.flush();
            /**定义BufferedReader输入流来读取URL的响应**/
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        } finally{ /**使用finally块来关闭输出流、输入流**/
            try{
                if(out!=null){   out.close();}
                if(in!=null){ in.close(); }
            }catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 将HashMap参数组装成字符串
     * @param map
     * @return
     */
    private static String parseParams(HashMap<String,String> map){
        StringBuffer sb = new StringBuffer();
        if(map != null){
            for (Entry<String, String> e : map.entrySet()) {
                sb.append(e.getKey());
                sb.append("=");
                sb.append(e.getValue());
                sb.append("&");
            }
            sb.substring(0, sb.length() - 1);
        }
        return sb.toString();
    }
    //没有考虑gzip压缩的情况
    public static String doGet ( String url, String queryString, String charset ,String strip,int iport)
    {
        StringBuffer response = new StringBuffer();
        try
        {
            HttpClient client = new HttpClient();
            client.getHttpConnectionManager().getParams().setConnectionTimeout(1000*60*10);//---
            if(strip!="")
            {
                // 设置HTTP代理IP和端口
                client.getHostConfiguration().setProxy(strip, iport);
                // 代理认证
                UsernamePasswordCredentials creds = new UsernamePasswordCredentials("username", "password");
                client.getState().setProxyCredentials(AuthScope.ANY, creds);
            }
            HttpMethod method = new GetMethod(url);
            method.getParams().setParameter(HttpMethodParams.SO_TIMEOUT,5000);//---
            method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,new DefaultHttpMethodRetryHandler());//---

            if ( queryString != null && !queryString.equals("") )
                //对get请求参数做了http请求默认编码，好像没有任何问题，汉字编码后，就成为%式样的字符串
                method.setQueryString(URIUtil.encodeQuery(queryString));
            client.executeMethod(method);
            if ( method.getStatusCode() == HttpStatus.SC_OK )
            {
                BufferedReader reader = new BufferedReader(new InputStreamReader(method.getResponseBodyAsStream(), charset));
                char[] c = new char[1024];
                int n=0;
                while((n=reader.read(c))!=-1){

                    String s = new String(c,0,n);
                    response.append(s);
                }
                reader.close();
            }
        }
        catch ( Exception ex )
        {
            System.out.print(ex.toString());
        }

        return response.toString();
    }
    public static String doGetGzip ( String url, String queryString, String charset ,String strip,int iport)
    {
        try
        {
            StringBuffer response = new StringBuffer();

            HttpClient client = new HttpClient();
            client.getHttpConnectionManager().getParams().setConnectionTimeout(1000*60*60*100);//---

            if(strip!="")
            {
                // 设置HTTP代理IP和端口
                client.getHostConfiguration().setProxy(strip, iport);
                // 代理认证
                UsernamePasswordCredentials creds = new UsernamePasswordCredentials("username", "password");
                client.getState().setProxyCredentials(AuthScope.ANY, creds);
            }
            HttpMethod method = new GetMethod(url);
            method.getParams().setParameter(HttpMethodParams.SO_TIMEOUT,5000);//---
            method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,new DefaultHttpMethodRetryHandler());//---
            try
            {
                if ( queryString != null && !queryString.equals("") )
                    //对get请求参数做了http请求默认编码，好像没有任何问题，汉字编码后，就成为%式样的字符串
                    method.setQueryString(URIUtil.encodeQuery(queryString));
                client.executeMethod(method);
                if ( method.getStatusCode() == HttpStatus.SC_OK )
                {
                    int iGizp=0;//1标示用了gzip
                    try
                    {
                        if(method.getResponseHeader("Content-Encoding").toString().toLowerCase().contains("gzip"))
                        {
                            iGizp=1;
                        }
                        else
                        {
                            iGizp=0;
                        }
                    }
                    catch(Exception ex)
                    {
                        String strex=ex.toString();
                        System.out.print(strex);
                        iGizp=0;
                    }
                    if(iGizp==1)
                    {

                        GZIPInputStream gzin;

                        InputStreamReader isr = new InputStreamReader(new GZIPInputStream(method.getResponseBodyAsStream()), "gbk");

                        BufferedReader br = new BufferedReader(isr);
                        String tempbf;

                        while ((tempbf = br.readLine()) != null) {
                            response.append(tempbf);
                        }
                        isr.close();


                    }
                    else
                    {
                        BufferedReader reader = new BufferedReader(new InputStreamReader(method.getResponseBodyAsStream(), charset));
                        char[] c = new char[1024];
                        int n=0;
                        while((n=reader.read(c))!=-1){

                            String s = new String(c,0,n);
                            response.append(s);
                        }
                        reader.close();
                    }
                }
            }
            catch ( Exception e )
            {
                System.out.print("Do Get error");
            }

            finally
            {
                method.releaseConnection();
            }
            return response.toString();
        }
        catch ( Exception e )
        {
            System.out.print("Do Get error");
            return "";
        }

    }


    public static int  downpic ( String strsavepath,String strurl, String queryString, String charset )
    {
        int isuccess=0;
        Properties prop   =   System.getProperties();
        try {
            URL url = new URL(strurl);
            HttpURLConnection connection;
            connection = (HttpURLConnection) url.openConnection();
            DataInputStream in = new DataInputStream(connection.getInputStream());
            DataOutputStream out = new DataOutputStream(new FileOutputStream(strsavepath));
            byte[] buffer = new byte[4096];
            int count = 0;
            while ((count = in.read(buffer)) > 0)/* 将输入流以字节的形式读取并写入buffer中 */
            {
                out.write(buffer, 0, count);
            }
            out.close();/* 后面三行为关闭输入输出流以及网络资源的固定格式 */
            in.close();
            connection.disconnect();
            isuccess=1;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            //e.printStackTrace();
            System.out.println("download pic error proxy or other wrong happen");
            System.out.println(e.toString());
            isuccess=0;
        }
        return isuccess;
    }
}//end