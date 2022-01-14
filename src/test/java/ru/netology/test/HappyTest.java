package ru.netology.test;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.val;
import org.junit.jupiter.api.*;
import ru.netology.data.ConnectionHelper;
import ru.netology.pages.CreditpayPage;
import ru.netology.pages.MainPage;
import ru.netology.pages.PaymentPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static ru.netology.data.ConnectionHelper.*;
import static ru.netology.data.DataHelper.getValidApprovedCard;
import static ru.netology.data.DataHelper.getValidDeclinedCard;

public class HappyTest {
    MainPage mainPage;
    PaymentPage paymentPage;
    CreditpayPage creditpayPage;

    @BeforeAll
    static void setUp() {
        Configuration.screenshots = false;
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(false));
    }

    @AfterAll
    static void tearDown() {
        ConnectionHelper.cleanDb();
        SelenideLogger.removeListener("AllureSelenide");
    }

    @BeforeEach
    void setUpUrl() {
        mainPage = open(System.getProperty("sut.url"), MainPage.class);
    }

    @AfterEach
    void cleanDb() {
        ConnectionHelper.cleanDb();
    }

    @Nested
    class HappyPath1OfDebitCardTests {

        @BeforeEach
        void setUpAllDebitCardTests() {
            paymentPage = mainPage.payWithDebitCard();
        }

        @Test
        void shouldDoPaymentWhenValidApprovedCard() {
            val info = getValidApprovedCard();
            paymentPage.fillForm(info);
            paymentPage.waitIfSuccessMessage();
            val expectedStatus = "APPROVED";
            val actualStatus = getStatusForPaymentWithDebitCard();
            assertEquals(expectedStatus, actualStatus);
            val expectedId = getPaymentId();
            assertNotNull(expectedId);
            val actualId = getOrderPaymentId();
            assertNotNull(actualId);
            assertEquals(expectedId, actualId);
        }

        @Test
        void shouldNotDoPaymentWhenValidDeclinedCard() {
            val info = getValidDeclinedCard();
            paymentPage.fillForm(info);
            paymentPage.waitIfFailMessage();
            val expectedStatus = "DECLINED";
            val actualStatus = getStatusForPaymentWithDebitCard();
            assertEquals(expectedStatus, actualStatus);
            assertEquals(0, getOrderCount());
        }
    }

    @Nested
    class HappyPath2OfCreditCardTests {

        @BeforeEach
        void setUpAllCreditCardTests() {
            creditpayPage = mainPage.payWithCreditCard();
        }

        @Test
        void shouldDoPaymentWhenValidApprovedCard() {
            val info = getValidApprovedCard();
            creditpayPage.fillForm(info);
            creditpayPage.waitIfSuccessMessage();
            val expectedStatus = "APPROVED";
            val actualStatus = getStatusForPaymentWithCreditCard();
            assertEquals(expectedStatus, actualStatus);
            val expectedId = getCreditId();
            assertNotNull(expectedId);
            val actualId = getOrderCreditId();
            assertNotNull(actualId);
            assertEquals(expectedId, actualId);
        }

        @Test
        void shouldNotDoPaymentWhenValidDeclinedCard() {
            val info = getValidDeclinedCard();
            creditpayPage.fillForm(info);
            creditpayPage.waitIfFailMessage();
            val expectedStatus = "DECLINED";
            val actualStatus = getStatusForPaymentWithCreditCard();
            assertEquals(expectedStatus, actualStatus);
            assertEquals(0, getOrderCount());
        }
    }

}
