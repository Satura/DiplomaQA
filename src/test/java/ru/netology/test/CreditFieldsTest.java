package ru.netology.test;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.val;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.ConnectionHelper;
import ru.netology.pages.CreditpayPage;
import ru.netology.pages.MainPage;

import java.time.LocalDate;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static ru.netology.data.DataHelper.*;

public class CreditFieldsTest {

    MainPage mainPage;
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
        creditpayPage = mainPage.payWithCreditCard();
    }

    /* Проверки поля "Номер карты" */

    @Test
    void shouldNotDoPaymentWhenEmptyCard() {
        val info = getEmptyNumberCard();
        creditpayPage.fillForm(info);
        creditpayPage.waitIfShouldFillFieldMessage();
    }

    @Test
    void shouldNotDoPaymentWhenShortCard() {
        val info = getShortNumberCard();
        creditpayPage.fillForm(info);
        creditpayPage.waitIfWrongFormatMessage();
    }

    @Test
    void shouldNotDoPaymentWhenAnotherBankCard() {
        val info = getStrangerBankNumberCard();
        creditpayPage.fillForm(info);
        creditpayPage.waitIfFailMessage();
    }

    @Test
    void shouldNotDoPaymentWhenSymbolNumber() {
        val info = getSymbolNumberCard();
        creditpayPage.fillForm(info);
        assertTrue(creditpayPage.getNumberField().isEmpty());
    }

    @Test
    void shouldNotDoPaymentWhenLetterNumber() {
        val info = getLetterNumberCard();
        creditpayPage.fillForm(info);
        assertTrue(creditpayPage.getNumberField().isEmpty());
    }

    /* Проверки поля "Месяц" */

    @Test
    void shouldNotPayWhenEmptyMonth() {
        val info = getEmptyMonthCard();
        creditpayPage.fillForm(info);
        creditpayPage.waitIfShouldFillFieldMessage();
    }

    @Test
    void shouldNotPayWhenZeroMonth() {
        val info = getZeroMonthCard();
        creditpayPage.fillForm(info);
        creditpayPage.waitIfWrongFormatMessage();
    }

    @Test
    void shouldNotPayWhenWrongMonth() {
        val info = getWrongMonthCard();
        creditpayPage.fillForm(info);
        creditpayPage.waitIfWrongTermMessage();
    }

    @Test
    void shouldNotPayWhenSymbolMonth() {
        val info = getSymbolMonthCard();
        creditpayPage.fillForm(info);
        assertTrue(creditpayPage.getMonthField().isEmpty());
    }

    @Test
    void shouldNotPayWhenLetterMonth() {
        val info = getLetterMonthCard();
        creditpayPage.fillForm(info);
        assertTrue(creditpayPage.getMonthField().isEmpty());
    }

    /* Проверки поля "Год" */

    @Test
    void shouldNotPayWhenEmptyYear() {
        val info = getEmptyYearCard();
        creditpayPage.fillForm(info);
        creditpayPage.waitIfShouldFillFieldMessage();
    }


    @Test
    void shouldNotPayWhenPastYear() {
        val info = getPastYearCard();
        creditpayPage.fillForm(info);
        creditpayPage.waitIfCardExpiredMessage();
    }

    @Test
    void shouldNotPayWhenMuchFutureYear() {
        val info = getMuchFutureYearCard();
        creditpayPage.fillForm(info);
        creditpayPage.waitIfWrongTermMessage();
    }

    @Test
    void shouldNotPayWhenShortYear() {
        val info = getShortYearCard();
        creditpayPage.fillForm(info);
        creditpayPage.waitIfWrongFormatMessage();
    }

    @Test
    void shouldNotPayWhenSymbolYear() {
        val info = getSymbolYearCard();
        creditpayPage.fillForm(info);
        assertTrue(creditpayPage.getYearField().isEmpty());
    }

    @Test
    void shouldNotPayWhenLetterYear() {
        val info = getLetterYearCard();
        creditpayPage.fillForm(info);
        assertTrue(creditpayPage.getYearField().isEmpty());
    }

    @Test
    void shouldNotPayWhenPastMonthPresentYear() {
        val info = getPastMonthPresentYearCard();
        creditpayPage.fillForm(info);
        LocalDate today = LocalDate.now();
        if (today.getMonth().getValue() == 1) creditpayPage.waitIfCardExpiredMessage();
        else creditpayPage.waitIfWrongTermMessage();
    }

    /* Проверки поля "Владелец" */

    @Test
    void shouldNotPayWhenEmptyOwner() {
        val info = getEmptyOwnerCard();
        creditpayPage.fillForm(info);
        creditpayPage.waitIfShouldFillFieldMessage();
    }

    @Test
    void shouldNotPayWhenOneWordOwner() {
        val info = getOneWordOwnerCard();
        creditpayPage.fillForm(info);
        creditpayPage.waitIfWrongFormatMessage();
    }

    @Test
    void shouldNotPayWhenThreeWordOwner() {
        val info = getThreeWordOwnerCard();
        creditpayPage.fillForm(info);
        creditpayPage.waitIfWrongFormatMessage();
    }

    @Test
    void shouldNotPayWhenSymbolOwner() {
        val info = getSymbolNameOwnerCard();
        creditpayPage.fillForm(info);
        creditpayPage.waitIfWrongFormatMessage();
    }

    @Test
    void shouldNotPayWhenRussianOwner() {
        val info = getRussianNameOwnerCard();
        creditpayPage.fillForm(info);
        creditpayPage.waitIfWrongFormatMessage();
    }

    @Test
    void shouldNotPayWhenNumbersOwner() {
        val info = getNumberNameOwnerCard();
        creditpayPage.fillForm(info);
        creditpayPage.waitIfWrongFormatMessage();
    }

    /* Проверки поля "CVV" */

    @Test
    void shouldNotPayWhenEmptyCVV() {
        val info = getEmptyCVVCard();
        creditpayPage.fillForm(info);
        assertEquals("Поле обязательно для заполнения", creditpayPage.getCVVSubLine().trim());
        creditpayPage.waitIfWrongFormatMessage();

    }

    @Test
    void shouldNotPayWhenShortCVV() {
        val info = getShortCVVCard();
        creditpayPage.fillForm(info);
        creditpayPage.waitIfWrongFormatMessage();
    }

    @Test
    void shouldCutLongCVV() {
        val info = getLongCVVCard();
        creditpayPage.fillForm(info);
        String expected = getLongCVVCard().getCvv().substring(4);
        assertEquals(expected, creditpayPage.getCVVField());
    }

    @Test
    void shouldNotPayWhenSymbolCVV() {
        val info = getSymbolCVVCard();
        creditpayPage.fillForm(info);
        assertTrue(creditpayPage.getCVVField().isEmpty());
    }

    @Test
    void shouldNotPayWhenLetterCVV() {
        val info = getLetterCVVCard();
        creditpayPage.fillForm(info);
        assertTrue(creditpayPage.getCVVField().isEmpty());
    }

}
