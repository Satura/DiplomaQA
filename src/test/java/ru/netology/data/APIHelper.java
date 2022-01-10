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

    public static String fillPaymentFormWithDebitCardData(DataHelper.CardInfo cardValidInformationModel) {
        return given()
                .spec(requestSpec)
                .body(cardValidInformationModel)

                .when()
                .post("/api/v1/pay")

                .then()
                .statusCode(200)
                .extract().response().asString();
    }

    public static String fillPaymentFormWithCreditCardData(DataHelper.CardInfo cardValidInformationModel) {
        return given()
                .spec(requestSpec)
                .body(cardValidInformationModel)

                .when()
                .post("/api/v1/credit")

                .then()
                .statusCode(200)
                .extract().response().asString();
    }

    public static String fillFormWithWrongCreditCard(DataHelper.CardInfo cardValidInformationModel) {
        return given()
                .spec(requestSpec)
                .body(cardValidInformationModel)

                .when()
                .post("/api/v1/credit")

                .then()
                .statusCode(400)
                .extract().response().asString();
    }

    public static String fillFormWithWrongDebitCard(DataHelper.CardInfo cardValidInformationModel) {
        return given()
                .spec(requestSpec)
                .body(cardValidInformationModel)

                .when()
                .post("/api/v1/credit")

                .then()
                .statusCode(400)
                .extract().response().asString();
    }
}
