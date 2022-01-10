package ru.netology.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class PaymentPage {
    private final ElementsCollection fields = $$(".input__control");
    private final SelenideElement numberField = $("[placeholder='0000 0000 0000 0000']");
    private final SelenideElement monthField = $("[placeholder='08']");
    private final SelenideElement yearField = $("[placeholder='22']");
    private final SelenideElement ownerField = fields.get(3);
    private final SelenideElement cvvField = $("[placeholder='999']");

    private final ElementsCollection subLines = $$(".input__sub");
    private final SelenideElement cvvSubLine = subLines.get(1);


    private final SelenideElement continueButton = $(byText("Продолжить"));

    private final SelenideElement successNotification = $(byText("Операция одобрена Банком."));
    private final SelenideElement failNotification = $(byText("Ошибка! Банк отказал в проведении операции."));
    private final SelenideElement wrongFormatMessage = $(byText("Неверный формат"));
    private final SelenideElement wrongTermMessage = $(byText("Неверно указан срок действия карты"));
    private final SelenideElement cardExpiredMessage = $(byText("Истёк срок действия карты"));
    private final SelenideElement fieldFillRequiredMessage = $(byText("Поле обязательно для заполнения"));

    public void fillForm(DataHelper.CardInfo info) {
        numberField.setValue(info.getNumber());
        monthField.setValue(info.getMonth());
        yearField.setValue(info.getYear());
        ownerField.setValue(info.getOwner());
        cvvField.setValue(info.getCvv());
        continueButton.click();
    }

    public void waitIfSuccessMessage() {
        successNotification.waitUntil(Condition.visible, 15000);
    }

    public void waitIfFailMessage() {
        failNotification.waitUntil(Condition.visible, 15000);
    }

    public void waitIfWrongFormatMessage() {
        wrongFormatMessage.waitUntil(Condition.visible, 10000);
    }

    public void waitIfWrongTermMessage() {
        wrongTermMessage.waitUntil(Condition.visible, 10000);
    }

    public void waitIfCardExpiredMessage() {
        cardExpiredMessage.waitUntil(Condition.visible, 10000);
    }

    public void waitIfShouldFillFieldMessage() {
        fieldFillRequiredMessage.waitUntil(Condition.visible, 10000);
    }

    public String getNumberField() {
        return numberField.getText();
    }

    public String getMonthField() {
        return monthField.getText();
    }
    public String getYearField() {
        return yearField.getText();
    }
    public String getCVVField() {
        return cvvField.getText();
    }
    public String getCVVSubLine(){
        return cvvSubLine.getText();
    }


}
