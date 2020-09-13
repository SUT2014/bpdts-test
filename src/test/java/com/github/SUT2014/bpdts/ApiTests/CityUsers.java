package com.github.SUT2014.bpdts.ApiTests;

import com.github.SUT2014.bpdts.Utils.Restils;
import com.github.SUT2014.bpdts.properties.PropertiesLoad;
import io.restassured.response.Response;
import org.junit.*;
import org.junit.runners.MethodSorters;
import static org.junit.Assert.assertEquals;

//CityUsers API tests
//setup sequence of test execution, Name ascending
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CityUsers {
    private Response resp = null;
    private io.restassured.path.json.JsonPath JsonPath = null;

    //setup before
    @Before
    public void setup(){
        Restils.setBaseURI(PropertiesLoad.getBaseUrl());
        Restils.setBasePath("/city/London/users");
        resp = Restils.getRESTResponse();
        JsonPath = Restils.getJsonPath(resp);
        //System.out.println("Response Body is =>  " + resp.asString());
    }

    //check header in the response structure
    @Test
    public void T001CheckResponseHeader(){
        Restils.checkResponseCode(resp, "200");
        Restils.checkResponseContentType(resp, "application/json" );
        Restils.checkConnection(resp, "keep-alive" );
        Restils.checkContentLength(resp, "1065" );
        Restils.checkResponseServer(resp, "gunicorn/19.9.0");
        Restils.checkResponseVia(resp, "1.1 vegur");
    }

    //check id of the first returned user from the list
    @Test
    public void T002CheckId(){
        assertEquals("Response ID expected : 135" , "135", Integer.toString(JsonPath.get("id[0]")));
    }

    //Check Number of users returned
    @Test
    public void T003CheckNoOfUsers(){
        Assert.assertEquals("Expected 1000 Users", "6", Integer.toString(Restils.getJSonIds(JsonPath).size()));
    }

    //negative tests
    //invalid URL
    @Test
    public void T010CheckInvalidUrl(){
        Restils.setBasePath("/city/London/user");
        resp = Restils.getRESTResponse();
        Restils.checkResponseCode(resp, "404");
        Restils.checkResponseContentType(resp, "text/html" );
        Restils.checkContentLength(resp, "232" );
        //System.out.println("Response Body is =>  " + resp.asString());
    }

    //invalid city id
    @Test
    public void TO11CheckInvalidCity(){
        Restils.setBasePath("/city/Timbuktu/users");
        resp = Restils.getRESTResponse();
        //System.out.println("Response Body is =>  " + resp.asString());
        Restils.checkResponseCode(resp, "200");
        Restils.checkResponseContentType(resp, "application/json" );
        Restils.checkContentLength(resp, "3" );
    }

    //invalid city id 2
    @Test
    public void TO12CheckInvalidCity(){
        Restils.setBasePath("/city/ABC/users");
        resp = Restils.getRESTResponse();
        //System.out.println("Response Body is =>  " + resp.asString());
        Restils.checkResponseCode(resp, "200");
        Restils.checkResponseContentType(resp, "application/json" );
        Restils.checkContentLength(resp, "3" );
    }

    //Cleanup
    @After
    public void tearDown(){
        Restils.resetBaseURI();
        Restils.resetBasePath();
    }
}
