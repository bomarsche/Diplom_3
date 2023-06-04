package StellarBurgersAPI;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class BaseRestClient {

    //E2E
    public static final String BASE_URL = "https://stellarburgers.nomoreparties.site/";
    public static final String REG_URL = "https://stellarburgers.nomoreparties.site/register";
    public static final String LOGIN_URL = "https://stellarburgers.nomoreparties.site/login";


    //API
    public static final String API_PATH = "https://stellarburgers.nomoreparties.site/api/";
    public static final String USER_CREATE = "auth/register";
    public static final String USER_PATH = "auth/user";



    public RequestSpecification getReqSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(API_PATH)
                .setContentType(ContentType.JSON)
                .build();
    }

    public RequestSpecification getReqSpecAuth(String accessToken) {
        return new RequestSpecBuilder()
                .setBaseUri(API_PATH)
                .setContentType(ContentType.JSON)
                .addHeader("authorization", accessToken)
                .build();
    }
}
