package com.java.commons.util;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * HttpClient工具类
 */
public class HttpClientUtils {
    public static final String URL="http://localhost:8080";
    private static final String REQUEST_METHOD_GET = "GET";
    private static final String REQUEST_METHOD_POST = "POST";
    private static final String REQUEST_HEADER_CONNECTION="keep-alive";
    private static final String REQUEST_HEADER_USER_AGENT="Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36";
    private static CloseableHttpClient httpClient;
    public static final String CHARSET = "UTF-8";

    /**
     *  Get请求
     * @param url  路径
     * @return  字符串
     */
    public static String doGet(String url){
        String result = HttpClientUtils.getResult("GET", url, null);
        return result;
    }


    public static String sendGet(String url, Map<String, Object> params) {
        if(!StringUtils.hasText(url)){
            return "";
        }
        try {
            if (params != null && !params.isEmpty()) {
                List<NameValuePair> pairs = new ArrayList<NameValuePair>(params.size());
                for (String key : params.keySet()) {
                    pairs.add(new BasicNameValuePair(key, params.get(key).toString()));
                }
                url += "?" + EntityUtils.toString(new UrlEncodedFormEntity(pairs, CHARSET));
            }
            HttpGet httpGet = new HttpGet(url);
            CloseableHttpResponse response = httpClient.execute(httpGet);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != 200) {
                httpGet.abort();
                throw new RuntimeException("HttpClient,error status code :" + statusCode);
            }
            HttpEntity entity = response.getEntity();
            String result = null;
            if (entity != null) {
                result = EntityUtils.toString(entity, "utf-8");
            }
            EntityUtils.consume(entity);
            response.close();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * GET请求
     * @param url  API
     * @param cookie  cookie
     * @return json字符串
     */
    public static String doGet(String url,String cookie){
        String result = HttpClientUtils.getResult("GET", url, cookie);
        return result;
    }

    /**
     * POST请求
     * @param url
     * @param params  请求参数
     * @return  json字符串
     */
    public static String doPost(String url,BasicNameValuePair...params){
        String result = HttpClientUtils.getResult("POST", url, null,params);
        return result;
    }

    /**
     * POST请求
     * @param url
     * @param cookie
     * @param params 请求参数
     * @return  json字符串
     */
    public static String doPost(String url,String cookie,BasicNameValuePair...params){
        String result = HttpClientUtils.getResult("POST", url, cookie,params);
        return result;
    }


    /**
     * 使用HttpClient通过API请求来获得json字符串
     * @param requestMethod 请求方式
     * @param url  请求路径
     * @param cookie  cookie
     * @param params  请求参数
     * @return  json字符串
     */
    private static String getResult(String requestMethod, String url, String cookie, BasicNameValuePair...params){
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse httpResponse = null;
        String result = null;
        try {
            //GET
            if(requestMethod.equals(HttpClientUtils.REQUEST_METHOD_GET)){
                HttpGet httpGet = new HttpGet(url);
                httpGet.setHeader("Connection",HttpClientUtils.REQUEST_HEADER_CONNECTION);
                httpGet.setHeader("Cookie",cookie);
                httpGet.setHeader("User-Agent",HttpClientUtils.REQUEST_HEADER_USER_AGENT);
                httpResponse = httpClient.execute(httpGet);
            }

            //POST
            else if(requestMethod.equals(HttpClientUtils.REQUEST_METHOD_POST)){
                HttpPost httpPost = new HttpPost(url);
                httpPost.setHeader("Connection",HttpClientUtils.REQUEST_HEADER_CONNECTION);
                httpPost.setHeader("Cookie",cookie);
                httpPost.setHeader("User-Agent",HttpClientUtils.REQUEST_HEADER_USER_AGENT);
                httpPost.setEntity(new UrlEncodedFormEntity(Arrays.asList(params)));
                httpResponse = httpClient.execute(httpPost);
            }
            //----

            HttpEntity entity = httpResponse.getEntity();
            result = EntityUtils.toString(entity);
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if(httpClient!=null){
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


        return result;
    }


}
