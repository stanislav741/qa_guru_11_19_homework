package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;


public class TestBase {

    @BeforeAll
    public static void beforeAll(){
        RestAssured.baseURI = "http://demowebshop.tricentis.com";
        Configuration.baseUrl = "http://demowebshop.tricentis.com";
//      System.setProperty("org.aspectj.weaver.Dump.exception", "false");
    }

}
