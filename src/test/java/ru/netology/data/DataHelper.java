package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import lombok.Value;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.Locale;

@AllArgsConstructor
public class DataHelper {

    public static LocalDate today = LocalDate.now();
    public static Integer tMonth = today.getMonthValue();
    public static Integer tYear = today.getYear() - 2000;
    public static Integer pYear = tYear - 1;
    public static Integer pMonth;
    public static DecimalFormat dF = new DecimalFormat("00");
    public static Integer cvv = (int) (Math.random() * 1_000);
    public static Integer cvvLong = (int) (Math.random() * 10_000);
    static Integer cvvShort = (int) (Math.random() * 10);

    static Faker faker = new Faker(new Locale("en"));
    static String owner = faker.name().lastName() + " " + faker.name().firstName();

    static int getPastMonth() {
        if (tMonth == 01) {
            pMonth = 12;
            tYear--;
        } else pMonth = tMonth - 1;

        return pMonth;
    }

    public static CardInfo getValidApprovedCard() {
        return new CardInfo("4444 4444 4444 4441", dF.format(tMonth), dF.format(tYear + 1), owner, cvv.toString());
    }

    public static CardInfo getValidDeclinedCard() {
        return new CardInfo("4444 4444 4444 4442", dF.format(tMonth), dF.format(tYear + 1), owner, cvv.toString());
    }

    public static CardInfo getEmptyNumberCard() {
        return new CardInfo("", dF.format(tMonth), dF.format(tYear + 1), owner, cvv.toString());
    }

// Для проверок поля "Номер карты"

    public static CardInfo getShortNumberCard() {
        return new CardInfo("4444 4444 44 4442", dF.format(tMonth), dF.format(tYear + 1), owner, cvv.toString());
    }

    public static CardInfo getStrangerBankNumberCard() {
        return new CardInfo("2200 4444 4444 4442", dF.format(tMonth), dF.format(tYear + 2), owner, cvv.toString());
    }

    public static CardInfo getSymbolNumberCard() {
        return new CardInfo("%$%$&", dF.format(tMonth), dF.format(tYear + 1), owner, cvv.toString());
    }

    public static CardInfo getLetterNumberCard() {
        return new CardInfo("ghdj fhyr jghy mvgs", dF.format(tMonth), dF.format(tYear + 1), owner, cvv.toString());
    }

    public static CardInfo getEmptyMonthCard() {
        return new CardInfo("4444 4444 4444 4441", "", dF.format(tYear + 1), owner, cvv.toString());
    }

// Для проверок поля "Месяц"

    public static CardInfo getZeroMonthCard() {
        return new CardInfo("4444 4444 4444 4441", "00", dF.format(tYear + 1), owner, cvv.toString());
    }

    public static CardInfo getWrongMonthCard() {
        return new CardInfo("4444 4444 4444 4441", "25", dF.format(tYear + 1), owner, cvv.toString());
    }

    public static CardInfo getSymbolMonthCard() {
        return new CardInfo("4444 4444 4444 4441", "#$", dF.format(tYear + 1), owner, cvv.toString());

    }

    public static CardInfo getLetterMonthCard() {
        return new CardInfo("4444 4444 4444 4441", "#$", dF.format(tYear + 1), owner, cvv.toString());

    }

    public static CardInfo getEmptyYearCard() {
        return new CardInfo("4444 4444 4444 4441", dF.format(tMonth), "", owner, cvv.toString());
    }

// Для проверок поля "Год"

    public static CardInfo getPastYearCard() {
        return new CardInfo("4444 4444 4444 4441", dF.format(tMonth), dF.format(pYear), owner, cvv.toString());
    }

    public static CardInfo getMuchFutureYearCard() {
        return new CardInfo("4444 4444 4444 4441", dF.format(tMonth), dF.format(tYear + 15), owner, cvv.toString());
    }

    public static CardInfo getShortYearCard() {
        return new CardInfo("4444 4444 4444 4441", dF.format(tMonth), "4", owner, cvv.toString());
    }

    public static CardInfo getSymbolYearCard() {
        return new CardInfo("4444 4444 4444 4441", dF.format(tMonth), "#^", owner, cvv.toString());
    }

    public static CardInfo getLetterYearCard() {
        return new CardInfo("4444 4444 4444 4441", dF.format(tMonth), "rt", owner, cvv.toString());
    }

    public static CardInfo getPastMonthPresentYearCard() {
        pMonth = getPastMonth();
        return new CardInfo("4444 4444 4444 4441", dF.format(pMonth), dF.format(tYear), owner, cvv.toString());

    }

    public static CardInfo getEmptyOwnerCard() {
        return new CardInfo("4444 4444 4444 4441", dF.format(tMonth), dF.format(tYear + 1), "", cvv.toString());
    }

// Для проверок поля "Владелец"

    public static CardInfo getOneWordOwnerCard() {
        return new CardInfo("4444 4444 4444 4441", dF.format(tMonth), dF.format(tYear + 1), "Popova", cvv.toString());
    }

    public static CardInfo getThreeWordOwnerCard() {
        return new CardInfo("4444 4444 4444 4441", dF.format(tMonth), dF.format(tYear + 1), "Popova Irina Petrovna", cvv.toString());
    }

    public static CardInfo getSymbolNameOwnerCard() {
        return new CardInfo("4444 4444 4444 4441", dF.format(tMonth), dF.format(tYear + 1), "#$%#% #$%#^&*", cvv.toString());
    }

    public static CardInfo getRussianNameOwnerCard() {
        return new CardInfo("4444 4444 4444 4441", dF.format(tMonth), dF.format(tYear + 1), "Попова Ирина", cvv.toString());
    }

    public static CardInfo getNumberNameOwnerCard() {
        return new CardInfo("4444 4444 4444 4441", dF.format(tMonth), dF.format(tYear + 1), "98651 216549", cvv.toString());
    }

    public static CardInfo getEmptyCVVCard() {
        return new CardInfo("4444 4444 4444 4441", dF.format(tMonth), dF.format(tYear + 1), owner, "");
    }

// Для проверок поля "CVV"

    public static CardInfo getShortCVVCard() {
        return new CardInfo("4444 4444 4444 4441", dF.format(tMonth), dF.format(tYear + 1), owner, cvvShort.toString());
    }

    public static CardInfo getLongCVVCard() {
        return new CardInfo("4444 4444 4444 4441", dF.format(tMonth), dF.format(tYear + 1), owner, cvvLong.toString());
    }

    public static CardInfo getSymbolCVVCard() {
        return new CardInfo("4444 4444 4444 4441", dF.format(tMonth), dF.format(tYear + 1), owner, "#$%");
    }

    public static CardInfo getLetterCVVCard() {
        return new CardInfo("4444 4444 4444 4441", dF.format(tMonth), dF.format(tYear + 1), owner, "dfg");
    }

    @Value
    public static class CardInfo {
        String number;
        String month;
        String year;
        String owner;
        String cvv;
    }

}
