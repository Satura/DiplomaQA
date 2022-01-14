package ru.netology.data;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class APIHelper {
    public static RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri(System.getProperty("sut.url"))
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    public static String fillForm(DataHelper.CardInfo info, String path, int code) {
        return given()
                .spec(requestSpec)
                .body(info)

                .when()
                .post(path)

                .then()
                .statusCode(code)
                .extract().response().asString();
    }
}
