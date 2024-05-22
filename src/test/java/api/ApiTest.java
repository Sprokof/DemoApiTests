package api;

import api.helper.AssertionsHelper;
import java.util.HashMap;
import java.util.Map;

import io.qameta.allure.Epic;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@Epic("Authorization test case")
public class ApiTest {

    private final ApiCoreRequests coreRequests = new ApiCoreRequests();
    String cookie;
    String header;
    int userAuthId;

    @BeforeEach
    public void loginUser() {
        Map<String, String> params = new HashMap<>();
        params.put("email", "vinkotov@example.com");
        params.put("password", "1234");

        Response response = RestAssured.
                given()
                .body(params)
                .when()
                .post("https://playground.learnqa.ru/api/user/login")
                .then().statusCode(200)
                .extract().response();

        this.cookie = response.getCookie("auth_sid");
        this.header = response.header("x-csrf-token");
        this.userAuthId = response.jsonPath().getInt("user_id");
    }

    @Test
    @DisplayName("Positive authorization test")
    public void authUser() {
        Response response = coreRequests.makeGetAuthRequest(
                "https://playground.learnqa.ru/api/user/auth",
                cookie, header);

        AssertionsHelper.jsonHasValue(response, "user_id", 2);
    }

    @ParameterizedTest()
    @ValueSource(strings = {"cookie", "headers"})
    @DisplayName("Negative authorization test w/o cookie or header")
    public void negativeAuthTest(String condition) {
        Response response = null;
        if (condition.equals("cookie")) {
            response = coreRequests.makeGetAuthRequestWithCookie(
                    "https://playground.learnqa.ru/api/user/auth", this.cookie
            );
        } else if (condition.equals("headers")) {
            response = coreRequests.makeGetAuthRequestWithToken(
                    "https://playground.learnqa.ru/api/user/auth", this.header
            );
        } else {
            throw new IllegalArgumentException("Condition is unknown " + condition);
        }
        AssertionsHelper.jsonHasValue(response, "user_id", 0);
    }
}
