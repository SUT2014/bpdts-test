package com.github.SUT2014.bpdts.ApiTests;

import com.github.SUT2014.bpdts.Utils.Restils;
import com.github.SUT2014.bpdts.properties.PropertiesLoad;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.*;
import org.junit.runners.MethodSorters;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.junit.Assert.assertEquals;

//User ID API tests
//setup sequence of test execution, Name ascending
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserId {

    private Response resp = null;
    private JsonPath JsonPath = null;

    //setup before
    @Before
    public void setup(){
        Restils.setBaseURI(PropertiesLoad.getBaseUrl());
        Restils.setBasePath("/user/1");
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
        Restils.checkContentLength(resp, "192" );
        Restils.checkResponseServer(resp, "gunicorn/19.9.0");
        Restils.checkResponseVia(resp, "1.1 vegur");
    }

    @Test
    public void T002CheckId(){
        assertEquals("Response ID expected : 1" , "1", Integer.toString(JsonPath.get("id")));
    }

    @Test
    public void T003ValidatewithSchema(){
        resp.then().assertThat().body(matchesJsonSchemaInClasspath("bpdts-schema-user.json"));
    }

    //negative tests
    @Test
    public void T010CheckInvalidUrl(){
        Restils.setBasePath("/users/1");
        resp = Restils.getRESTResponse();
        Restils.checkResponseCode(resp, "404");
        Restils.checkResponseContentType(resp, "text/html" );
        Restils.checkContentLength(resp, "232" );
        //System.out.println("Response Body is =>  " + resp.asString());
    }

    @Test
    public void TO11CheckInvalidUserId(){
        Restils.setBasePath("/user/A");
        resp = Restils.getRESTResponse();
        //System.out.println("Response Body is =>  " + resp.asString());
        Restils.checkResponseCode(resp, "404");
        Restils.checkResponseContentType(resp, "application/json" );
        Restils.checkContentLength(resp, "120" );
    }

    @Test
    public void T012CheckMaxUsersPlus(){
        Restils.setBasePath("/user/"+ (Integer.parseInt(PropertiesLoad.getNumOfUsers())+1));
        resp = Restils.getRESTResponse();
        //System.out.println("Response Body is =>  " + resp.asString());
        Restils.checkResponseCode(resp, "404");
        Restils.checkResponseContentType(resp, "application/json" );
        Restils.checkContentLength(resp, "126" );
    }

    //Cleanup
    @After
    public void tearDown(){
        Restils.resetBaseURI();
        Restils.resetBasePath();
    }
}
