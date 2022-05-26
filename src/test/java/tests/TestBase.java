package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;


public class TestBase {

    @BeforeAll
    public static void beforeAll(){
        RestAssured.baseURI = "http://demowebshop.tricentis.com";

        Configuration.baseUrl = "http://demowebshop.tricentis.com";
        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";
    }

}
