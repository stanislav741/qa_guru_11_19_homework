package tests;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.restassured.RestAssured.given;
import static listeners.CustomAllureListener.withCustomTemplates;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

public class DemoShopTest extends TestBase {

    @Test
    void loggedInWishlistCreateAndClear() {

        //Extracting an auth cookie for the test run
        String authCookie =
                given()
                        .filter(withCustomTemplates())
                        .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                        .formParam("Email", Credentials.email)
                        .formParam("Password", Credentials.password)
                .when()
                        .post("http://demowebshop.tricentis.com/login")
                .then()
                        .statusCode(302)
                        .extract().cookie("NOPCOMMERCE.AUTH");

        //Applying the auth cookie to the WebDriver
        open("/Themes/DefaultClean/Content/images/logo.png");
        getWebDriver().manage().addCookie(new Cookie("NOPCOMMERCE.AUTH", authCookie));
        open("");

        //Adding 5 products to the wishlist and verifying if the action is completed
        for(int i=0; i<5; i++){
            given()
                    .filter(withCustomTemplates())
                    .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                    .cookie("NOPCOMMERCE.AUTH", authCookie)
            .when()
                    .post("/addproducttocart/details/43/2")
            .then()
                    .statusCode(200)
                    .body("success", is(true))
                    .body("updatetopwishlistsectionhtml", not(is("()")));
        }

        //Opening the filled wishlist page and clearing all the items
        $("#topcartlink").sibling(0).click();

        $(byText("The wishlist is empty!")).shouldBe(hidden);

        $(".remove-from-cart").click();
        $(byName("updatecart")).click();

        $(byText("The wishlist is empty!")).shouldBe(visible);

    }

}
