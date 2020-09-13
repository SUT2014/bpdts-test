package com.github.SUT2014.bpdts.ApiTests;

import com.github.SUT2014.bpdts.Utils.Restils;
import com.github.SUT2014.bpdts.properties.PropertiesLoad;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.*;
import org.junit.runners.MethodSorters;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

//Users API tests

//setup sequence of test execution, Name ascending
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Users {

    private Response resp = null;
    private JsonPath JsonPath = null;

    //setup before
    @Before
    public void setup(){
        Restils.setBaseURI(PropertiesLoad.getBaseUrl());
        Restils.setBasePath("/users");
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
        Restils.checkContentLength(resp, "175719" );
        Restils.checkResponseServer(resp, "gunicorn/19.9.0");
        Restils.checkResponseVia(resp, "1.1 vegur");
    }

    @Test
    public void T002ValidateWithSchema(){
        resp.then().assertThat().body(matchesJsonSchemaInClasspath("bpdts-schema-users.json"));
    }

    @Test
    public void T003CheckNoOfUsers(){
        Assert.assertEquals("Expected 1000 Users", PropertiesLoad.getNumOfUsers(), Integer.toString(Restils.getJSonIds(JsonPath).size()));
    }

    //negative tests
    @Test
    public void TN01CheckInvalidUrl(){
        Restils.setBasePath("/users1");
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
