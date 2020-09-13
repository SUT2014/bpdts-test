package com.github.SUT2014.bpdts.Utils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.util.ArrayList;

import static org.junit.Assert.*;

//All the REST Assured Utils

public class Restils {

    //set BaseURI
    public static void setBaseURI (String baseURI){
        RestAssured.baseURI = baseURI;
    }

    //reset BaseURI
    public static void resetBaseURI (){
        RestAssured.baseURI = null;
    }

    //set BasePath
    public static void setBasePath (String basePath){
        RestAssured.basePath = basePath;
    }

    //reset BasePath
    public static void resetBasePath (){
        RestAssured.basePath = null;
    }

    //set content type
    public static void setContentType (ContentType Type) {
        RestAssured.given().contentType(Type);
    }

    //get content type
    public static String getContentType (Response resp) {
        return(resp.header("Content-Type"));
    }

    //get connection from header
    public static String getConnection (Response resp) {
        return(resp.header("connection"));
    }

    //get content length from header
    public static String getContentLength (Response resp) {
        return(resp.header("content-length"));
    }

    //get server from header
    public static String getServer (Response resp) {
        return(resp.header("server"));
    }

    //get via from header
    public static String getVia (Response resp) {
        return(resp.header("via"));
    }

    // get response
    public static Response getRESTResponse(){
        return RestAssured.given().get();
    }

    //get the respective json path
    public static JsonPath getJsonPath (Response resp) {
        String json = resp.asString();
        return new JsonPath(json);
    }

    //get id list
    public static ArrayList getJSonIds (JsonPath jsonPath){
        return(jsonPath.get("id"));
    }

    //check response code
    public static void checkResponseCode(Response resp, String code){
        assertEquals("Response Code expected : " + code, code, Integer.toString(resp.getStatusCode()));
    }

    //check response header content type
    public static void checkResponseContentType(Response resp, String type){
        assertEquals("Content Type expected : " + type, type, getContentType(resp));
    }

    //check response header connection
    public static void checkConnection(Response resp, String type){
        assertEquals("Connection expected : " + type, type, getConnection(resp));
    }

    //check response header content length
    public static void checkContentLength(Response resp, String length){
        assertEquals("Connection expected : " + length, length, getContentLength(resp));
    }

    //check response header server
    public static void checkResponseServer(Response resp, String server){
        assertEquals("Server in Header expected : " + server, server, getServer(resp));
    }

    //check response header Via
    public static void checkResponseVia(Response resp, String via){
        assertEquals("Server in Header expected : " + via, via, getVia(resp));
    }
}
