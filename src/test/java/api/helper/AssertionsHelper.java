package api.helper;

import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;

import static org.hamcrest.Matchers.hasKey;

public class AssertionsHelper {

    public static void jsonHasValue(Response response, String name, int exceptedValue) {
        response.then().assertThat().body("$", hasKey(name));

        int value = response.jsonPath().getInt(name);
        Assertions.assertEquals(exceptedValue, value, "user_id must be equals to " + exceptedValue);
    }
}
