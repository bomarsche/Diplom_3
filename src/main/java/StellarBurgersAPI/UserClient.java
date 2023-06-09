package StellarBurgersAPI;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class UserClient extends BaseRestClient {

    @Step("Создание пользователя")
    public ValidatableResponse createUser(User user) {
        return given()
                .spec(getReqSpec())
                .body(user)
                .post(BaseRestClient.USER_CREATE)
                .then()
                .log().body();
    }


    @Step("Удаление пользователя")
    public ValidatableResponse deleteUser(String accessToken) {
        return given()
                .spec(getReqSpecAuth(accessToken))
                .delete(BaseRestClient.USER_PATH)
                .then()
                .log().body();
    }

}
