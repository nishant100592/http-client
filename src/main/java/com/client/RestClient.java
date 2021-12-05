package com.client;

import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;


public class RestClient {


    //1. GET Method without header
    public CloseableHttpResponse get(String url) throws IOException {

        // Creating http client connection
        CloseableHttpClient httpClient = HttpClients.createDefault();

        // Creating the request
        HttpGet httpGet = new HttpGet(url);

        // Firing the request and saving it in the response
        CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpGet);

        return closeableHttpResponse;

    }


    //2. GET Method with Header
    public CloseableHttpResponse get(String url, HashMap<String, String> headerMap) throws IOException {

        // Creating http client connection
        CloseableHttpClient httpClient = HttpClients.createDefault();

        // Creating the request
        HttpGet httpGet = new HttpGet(url);

        for(Map.Entry<String, String> mapEntry: headerMap.entrySet()) {
            httpGet.addHeader(mapEntry.getKey(), mapEntry.getValue());
        }

        // Firing the request and saving it in the response
        CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpGet);

        return closeableHttpResponse;

    }

    //3. POST Call with Header
    public CloseableHttpResponse post(String uri, HashMap<String, String> headerMap, String requestPayloadString) throws IOException {

        //Creating HTTP Client
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();


        //Creating request
        HttpPost httpPost = new HttpPost(uri);


        //Creating Header
        for(Map.Entry<String, String> mapEntry: headerMap.entrySet()) {
            httpPost.addHeader(mapEntry.getKey(), mapEntry.getValue());
        }

        //Adding request payload
        httpPost.setEntity(new StringEntity(requestPayloadString));

        //Triggering API
        CloseableHttpResponse closeableHttpResponse = closeableHttpClient.execute(httpPost);

        return closeableHttpResponse;
    }


}
