package com.github.SUT2014.bpdts.AllSuite;

import com.github.SUT2014.bpdts.properties.PropertiesLoad;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import com.github.SUT2014.bpdts.ApiTests.Instructions;
import com.github.SUT2014.bpdts.ApiTests.UserId;
import com.github.SUT2014.bpdts.ApiTests.Users;
import com.github.SUT2014.bpdts.ApiTests.CityUsers;

//main runner, runnable from CLI using mvn

@RunWith(Suite.class)
@Suite.SuiteClasses({
        Instructions.class,
        UserId.class,
        Users.class,
        CityUsers.class,
})


public class BpdtsAll {
    @BeforeClass
    //setup tests by loading properties file
    public static void setup(){
        PropertiesLoad.loadProp();
    }
}
