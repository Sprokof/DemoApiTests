package api;

import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ApiCoreRequests {

    @Step("make get auth with cookie and token")
    public Response makeGetAuthRequest(String url, String cookie, String token) {
        return given()
                .filter(new AllureRestAssured())
                .header("x-csrf-token", token)
                .cookie("auth_sid", cookie)
                .get(url)
                .thenReturn();
    }

    @Step("make get auth without token")
    public Response makeGetAuthRequestWithCookie(String url, String cookie) {
        return given()
                .filter(new AllureRestAssured())
                .cookie("auth_sid", cookie)
                .get(url)
                .thenReturn();
    }

    @Step("make get auth without cookie")
    public Response makeGetAuthRequestWithToken(String url, String token) {
        return given()
                .filter(new AllureRestAssured())
                .header("x-csrf-token", token)
                .get(url)
                .thenReturn();
    }


}
