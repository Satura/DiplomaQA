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

import java.time.LocalDate;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static ru.netology.data.DataHelper.*;


public class FieldsTest {

    MainPage mainPage;
    PaymentPage paymentPage;

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
        paymentPage = mainPage.payWithDebitCard();
    }

    /* Проверки поля "Номер карты" */

    @Test
    void shouldNotDoPaymentWhenEmptyCard() {
        val info = getEmptyNumberCard();
        paymentPage.fillForm(info);
        paymentPage.waitIfShouldFillFieldMessage();
    }

    @Test
    void shouldNotDoPaymentWhenShortCard() {
        val info = getShortNumberCard();
        paymentPage.fillForm(info);
        paymentPage.waitIfWrongFormatMessage();
    }

    @Test
    void shouldNotDoPaymentWhenAnotherBankCard() {
        val info = getStrangerBankNumberCard();
        paymentPage.fillForm(info);
        paymentPage.waitIfFailMessage();
    }

    @Test
    void shouldNotDoPaymentWhenSymbolNumber() {
        val info = getSymbolNumberCard();
        paymentPage.fillForm(info);
        assertTrue(paymentPage.getNumberField().isEmpty());
    }

    @Test
    void shouldNotDoPaymentWhenLetterNumber() {
        val info = getLetterNumberCard();
        paymentPage.fillForm(info);
        assertTrue(paymentPage.getNumberField().isEmpty());
    }

    /* Проверки поля "Месяц" */

    @Test
    void shouldNotPayWhenEmptyMonth() {
        val info = getEmptyMonthCard();
        paymentPage.fillForm(info);
        paymentPage.waitIfShouldFillFieldMessage();
    }

    @Test
    void shouldNotPayWhenZeroMonth() {
        val info = getZeroMonthCard();
        paymentPage.fillForm(info);
        paymentPage.waitIfWrongFormatMessage();
    }

    @Test
    void shouldNotPayWhenWrongMonth() {
        val info = getWrongMonthCard();
        paymentPage.fillForm(info);
        paymentPage.waitIfWrongTermMessage();
    }

    @Test
    void shouldNotPayWhenSymbolMonth() {
        val info = getSymbolMonthCard();
        paymentPage.fillForm(info);
        assertTrue(paymentPage.getMonthField().isEmpty());
    }

    @Test
    void shouldNotPayWhenLetterMonth() {
        val info = getLetterMonthCard();
        paymentPage.fillForm(info);
        assertTrue(paymentPage.getMonthField().isEmpty());
    }

    /* Проверки поля "Год" */

    @Test
    void shouldNotPayWhenEmptyYear() {
        val info = getEmptyYearCard();
        paymentPage.fillForm(info);
        paymentPage.waitIfShouldFillFieldMessage();
    }


    @Test
    void shouldNotPayWhenPastYear() {
        val info = getPastYearCard();
        paymentPage.fillForm(info);
        paymentPage.waitIfCardExpiredMessage();
    }

    @Test
    void shouldNotPayWhenMuchFutureYear() {
        val info = getMuchFutureYearCard();
        paymentPage.fillForm(info);
        paymentPage.waitIfWrongTermMessage();
    }

    @Test
    void shouldNotPayWhenShortYear() {
        val info = getShortYearCard();
        paymentPage.fillForm(info);
        paymentPage.waitIfWrongFormatMessage();
    }

    @Test
    void shouldNotPayWhenSymbolYear() {
        val info = getSymbolYearCard();
        paymentPage.fillForm(info);
        assertTrue(paymentPage.getYearField().isEmpty());
    }

    @Test
    void shouldNotPayWhenLetterYear() {
        val info = getLetterYearCard();
        paymentPage.fillForm(info);
        assertTrue(paymentPage.getYearField().isEmpty());
    }

    @Test
    void shouldNotPayWhenPastMonthPresentYear() {
        val info = getPastMonthPresentYearCard();
        paymentPage.fillForm(info);
        LocalDate today = LocalDate.now();
        if (today.getMonth().getValue() == 1) paymentPage.waitIfCardExpiredMessage();
        else paymentPage.waitIfWrongTermMessage();
    }

    /* Проверки поля "Владелец" */

    @Test
    void shouldNotPayWhenEmptyOwner() {
        val info = getEmptyOwnerCard();
        paymentPage.fillForm(info);
        paymentPage.waitIfShouldFillFieldMessage();
    }

    @Test
    void shouldNotPayWhenOneWordOwner() {
        val info = getOneWordOwnerCard();
        paymentPage.fillForm(info);
        paymentPage.waitIfWrongFormatMessage();
    }

    @Test
    void shouldNotPayWhenThreeWordOwner() {
        val info = getThreeWordOwnerCard();
        paymentPage.fillForm(info);
        paymentPage.waitIfWrongFormatMessage();
    }

    @Test
    void shouldNotPayWhenSymbolOwner() {
        val info = getSymbolNameOwnerCard();
        paymentPage.fillForm(info);
        paymentPage.waitIfWrongFormatMessage();
    }

    @Test
    void shouldNotPayWhenRussianOwner() {
        val info = getRussianNameOwnerCard();
        paymentPage.fillForm(info);
        paymentPage.waitIfWrongFormatMessage();
    }

    @Test
    void shouldNotPayWhenNumbersOwner() {
        val info = getNumberNameOwnerCard();
        paymentPage.fillForm(info);
        paymentPage.waitIfWrongFormatMessage();
    }

    /* Проверки поля "CVV" */

    @Test
    void shouldNotPayWhenEmptyCVV() {
        val info = getEmptyCVVCard();
        paymentPage.fillForm(info);
        assertEquals("Поле обязательно для заполнения", paymentPage.getCVVSubLine().trim());
        paymentPage.waitIfWrongFormatMessage();

    }

    @Test
    void shouldNotPayWhenShortCVV() {
        val info = getShortCVVCard();
        paymentPage.fillForm(info);
        paymentPage.waitIfWrongFormatMessage();
    }

    @Test
    void shouldCutLongCVV() {
        val info = getLongCVVCard();
        paymentPage.fillForm(info);
        String expected = getLongCVVCard().getCvv().substring(4);
        assertEquals(expected, paymentPage.getCVVField());
    }

    @Test
    void shouldNotPayWhenSymbolCVV() {
        val info = getSymbolCVVCard();
        paymentPage.fillForm(info);
        assertTrue(paymentPage.getCVVField().isEmpty());
    }

    @Test
    void shouldNotPayWhenLetterCVV() {
        val info = getLetterCVVCard();
        paymentPage.fillForm(info);
        assertTrue(paymentPage.getCVVField().isEmpty());
    }
}