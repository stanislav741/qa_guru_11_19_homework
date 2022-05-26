package tests;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class DemoShopTest extends TestBase {

    @Test
    void loggedInWishlistCreateAndClear() {

        String authCookie =
                given()
                        .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                        .formParam("Email", Credentials.email)
                        .formParam("Password",Credentials.password)
                .when()
                        .post("http://demowebshop.tricentis.com/login")
                .then()
                        .statusCode(302)
                        .extract().cookie("NOPCOMMERCE.AUTH");

    }

}
