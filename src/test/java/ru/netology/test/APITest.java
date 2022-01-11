package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.val;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.netology.data.ConnectionHelper;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static ru.netology.data.ConnectionHelper.getCreditId;
import static ru.netology.data.ConnectionHelper.getPaymentId;
import static ru.netology.data.DataHelper.*;
import static ru.netology.data.APIHelper.*;

public class APITest {

	String pathToPay = "/api/v1/pay";
	String pathToCredit = "/api/v1/credit";
	int successCode = 200;
	int errorCode = 400;	
    
	@BeforeAll
    static void setUp(){
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(false));
    }

    @AfterAll
    static void tearDown() {
        SelenideLogger.removeListener("AllureSelenide");
    }
    
    @AfterEach
    void clean(){
        ConnectionHelper.cleanDb();
    }

	@Test
    void shouldGiveResponseForViladApprovedDebitCard() {
        val validApprovedCard = getValidApprovedCard();
        val response = fillForm(validApprovedCard, pathToPay, successCode);
        assertTrue(response.contains("APPROVED"));
        
        val actualId = getPaymentId();
        assertNotNull(actualId);
    }
	
	@Test
    void shouldGiveResponseForViladApprovedCreditCard() {
        val validApprovedCard = getValidApprovedCard();
        val response = fillForm(validApprovedCard, pathToCredit, successCode);
        assertTrue(response.contains("APPROVED"));

        val actualId = getCreditId();
        assertNotNull(actualId);
    }
	
	@Test
    void shouldGiveResponseForViladDeclinedDebitCard() {
        val validDeclinedCard = getValidDeclinedCard();
        val response = fillForm(validDeclinedCard, pathToPay, successCode);
        assertTrue(response.contains("DECLINED"));

        val actualId = getPaymentId();
        assertNotNull(actualId);
    }
	
	@Test
    void shouldGiveResponseForViladDeclinedCreditCard() {
        val validDeclinedCard = getValidDeclinedCard();
        val response = fillForm(validDeclinedCard, pathToCredit, successCode);
        assertTrue(response.contains("DECLINED"));

        val actualId = getCreditId();
        assertNotNull(actualId);
    }
	
	@Test
    void shouldGiveResponseForInviladDebitCard() {
        val invalidCard = getEmptyNumberCard();
        val response = fillForm(invalidCard, pathToPay, errorCode);
        assertTrue(response.contains("Bad Request"));
    }
	
	@Test
    void shouldGiveResponseForInviladCreditCard() {
        val invalidCard = getMuchFutureYearCard();
        val response = fillForm(invalidCard, pathToCredit, errorCode);
        assertTrue(response.contains("Bad Request"));
    }
}
