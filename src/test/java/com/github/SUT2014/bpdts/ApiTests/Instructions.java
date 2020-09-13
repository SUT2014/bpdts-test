package com.github.SUT2014.bpdts.ApiTests;

import com.github.SUT2014.bpdts.Utils.Restils;
import com.github.SUT2014.bpdts.properties.PropertiesLoad;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import io.restassured.response.Response;
import org.junit.runners.MethodSorters;

//Instructions API tests

//setup sequence of test execution, Name ascending
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Instructions {

    private Response resp = null;
    //private io.restassured.path.json.JsonPath JsonPath = null;

    //setup before
    @Before
    public void setup(){
        Restils.setBaseURI(PropertiesLoad.getBaseUrl());
        Restils.setBasePath("/instructions");
        resp = Restils.getRESTResponse();
        //JsonPath = Restils.getJsonPath(resp);
        //System.out.println("Response Body is =>  " + resp.asString());
    }

    //check header in the response structure
    @Test
    public void T001CheckResponseHeader(){
        Restils.checkResponseCode(resp, "200");
        Restils.checkResponseContentType(resp, "application/json" );
        Restils.checkConnection(resp, "keep-alive" );
        Restils.checkContentLength(resp, "281" );
        Restils.checkResponseServer(resp, "gunicorn/19.9.0");
        Restils.checkResponseVia(resp, "1.1 vegur");
    }
    //negative tests
    @Test
    public void T010CheckInvalidUrl(){
        Restils.setBasePath("/instructions1");
        resp = Restils.getRESTResponse();
        Restils.checkResponseCode(resp, "404");
        Restils.checkResponseContentType(resp, "text/html" );
        Restils.checkContentLength(resp, "232" );
        //System.out.println("Response Body is =>  " + resp.asString());
    }

    //Cleanup
    @After
    public void tearDown(){
        Restils.resetBaseURI();
        Restils.resetBasePath();
    }
}
