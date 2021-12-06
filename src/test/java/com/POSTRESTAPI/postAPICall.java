package com.POSTRESTAPI;

import com.base.Base;
import com.client.RestClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pojodata.Users;
import com.util.Util;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.fasterxml.jackson.core.*;

import java.io.IOException;
import java.util.HashMap;

public class postAPICall extends Base {

    String url;
    String pathUrl;
    String uri;
    RestClient restClient;
    HashMap<String, String> headerMap;
    CloseableHttpResponse closeableHttpResponse;

    @BeforeMethod
    public void setUp() {
        new Base();
        url = prop.getProperty("URL");
        pathUrl = prop.getProperty("createUserURL");

        uri = url + pathUrl;

    }

    @Test
    public void postCallWithHeader() throws IOException {
        restClient = new RestClient();

        //Creating Header
        headerMap = new HashMap<String, String>();
        headerMap.put("Content-Type", "application/json");

        //Creating POJO object
        Users usersRequestObject = new Users("Nishant", "Tester"); //Expected User, Input

        //Marshalling, Jackson api
        ObjectMapper objectMapper = new ObjectMapper();
        String usersJsonString = objectMapper.writeValueAsString(usersRequestObject);
        System.out.println("RequestJSONString ---> "+usersJsonString);

        //Triggering POST Call and saving response
        closeableHttpResponse = restClient.post(uri, headerMap, usersJsonString);

        //Validating response
        int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
        Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_201, "Response code not 201");

        //Storing response in String
        String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");

        //Converting response String to JSON
        JSONObject jsonObject = new JSONObject(responseString);
        System.out.println("Response payload ---> "+jsonObject);

        //Fetching name field value from response JSON
        String name = Util.getValueByJPath(jsonObject, "/name");
        System.out.println("Value of name field ---> "+name);

        //Un-Marshalling, converting response string to Java Object
        Users userResponseObject = objectMapper.readValue(responseString, Users.class);
        System.out.println("Response Users name: "+userResponseObject.getName());
        System.out.println("Response Users job: "+userResponseObject.getJob());
        System.out.println("Response Users id: "+userResponseObject.getId());

        //Asserting name
        Assert.assertEquals(usersRequestObject.getName(), userResponseObject.getName(), "Name does not match");

        //Assert job like above

    }



}
