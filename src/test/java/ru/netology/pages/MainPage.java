package ru.netology.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class MainPage {
    private static final SelenideElement buyBtn = $(byText("Купить"));
    private static final SelenideElement creditBtn =  $(byText("Купить в кредит"));
    private static final SelenideElement wayHeader = $("#root > div > h3");


    public void payWithDebitCard() {
        buyBtn.click();
        wayHeader.shouldHave(Condition.exactText("Оплата по карте"));
    }

    public void payWithCreditCard() {
        creditBtn.click();
        wayHeader.shouldHave(Condition.exactText("Кредит по данным карты"));
    }
}
