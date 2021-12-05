package com.GETRESTAPI;

import com.base.Base;
import com.client.RestClient;
import com.util.Util;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;

public class getAPICall extends Base {

    String url;
    String pathUrl;
    String uri;
    RestClient restClient;
    CloseableHttpResponse closeableHttpResponse;

    @BeforeMethod
    public void setUp() {
        new Base();
        url = prop.getProperty("URL");
        pathUrl = prop.getProperty("serviceURL");

        //Create URI
        uri = url+pathUrl;
    }

    @Test(priority = 0)
    public void getCallWithoutHeader() throws IOException {

        restClient = new RestClient();
        closeableHttpResponse = restClient.get(uri);

        //Reading & asserting status code
        int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
        System.out.println(" Get call Response Status Code ---> "+statusCode);
        Assert.assertEquals(RESPONSE_STATUS_CODE_200, statusCode, "Status not 200, Assertion failed");

        //Storing response payload to string
        String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(),"UTF-8");

        //Converting String to JSON Object
        JSONObject responseJson = new JSONObject(responseString);
        System.out.println(" Get call json response ---> "+responseJson);

        //Fetching value of field total from response
        String totalValue = Util.getValueByJPath(responseJson, "/total");
        System.out.println(" Value of total json object is  ---> "+totalValue);

        //Asserting value of total JSON Object
        Assert.assertEquals(totalValue, "12", " Value of total field not equal to 12");

        //Fetching value of JSON Array
        String lastnameValue = Util.getValueByJPath(responseJson, "/data[0]/last_name");
        System.out.println(" Value of last_name data JSON Array --->"+lastnameValue);

        //Asserting value of last_name
        Assert.assertEquals(lastnameValue, "Lawson", "last_name not equal to Lawson");

    }

    @Test(priority = 1)
    public void getCallWithHeader() throws IOException {

        restClient = new RestClient();

        //Header for GET Call
        HashMap<String, String> headerHashMap = new HashMap<String, String>();
        headerHashMap.put("Content-Type", "application/json");

        //Calling GET Method with uri and header
        closeableHttpResponse = restClient.get(uri, headerHashMap);

        //Reading & asserting status code
        int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
        System.out.println(" Get call Response Status Code ---> "+statusCode);
        Assert.assertEquals(RESPONSE_STATUS_CODE_200, statusCode, "Status not 200, Assertion failed");

        //Storing response payload to string
        String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(),"UTF-8");

        //Converting String to JSON Object
        JSONObject responseJson = new JSONObject(responseString);
        System.out.println(" Get call json response ---> "+responseJson);

        //Fetching value of field total from response
        String totalValue = Util.getValueByJPath(responseJson, "/total");
        System.out.println(" Value of total json object is  ---> "+totalValue);

        //Asserting value of total JSON Object
        Assert.assertEquals(totalValue, "12", " Value of total field not equal to 12");

        //Fetching value of JSON Array
        String lastnameValue = Util.getValueByJPath(responseJson, "/data[0]/last_name");
        System.out.println(" Value of last_name data JSON Array --->"+lastnameValue);

        //Asserting value of last_name
        Assert.assertEquals(lastnameValue, "Lawson", "last_name not equal to Lawson");

    }


}
