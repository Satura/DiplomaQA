package ru.netology.data;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.text.DecimalFormat;
import java.util.Date;

@AllArgsConstructor
public class DataHelper {

    @Value
    public static class CardInfo {
        String number;
        String month;
        String year;
        String owner;
        String cvv;
    }

    public static Date today = new Date();
    public static Integer tMonth = today.getMonth() + 1;
    public static Integer tYear = today.getYear() - 100;
    public static Integer pYear = tYear - 1;
    public static Integer pMonth;
    public static DecimalFormat dF = new DecimalFormat( "00" );

    static int getPastMonth(){
        if (tMonth == 00 || tMonth == 01) {
            pMonth = 12;
            tYear--;
        } else pMonth = tMonth - 1;

        return pMonth;
    }

    public static CardInfo getValidApprovedCard(){
        return new CardInfo("4444 4444 4444 4441", dF.format(tMonth), dF.format(tYear+1), "Popova Irina", "123");
    }

    public static CardInfo getValidDeclinedCard(){
        return new CardInfo("4444 4444 4444 4442", dF.format(tMonth), dF.format(tYear+1), "Popova Irina", "123");
    }

// Для проверок поля "Номер карты"

    public static CardInfo getEmptyNumberCard(){
        return new CardInfo("", dF.format(tMonth), dF.format(tYear+1), "Popova Irina", "123");
    }

    public static CardInfo getShortNumberCard(){
        return new CardInfo("4444 4444 44 4442", dF.format(tMonth), dF.format(tYear+1), "Popova Irina", "123");
    }

    public static CardInfo getStrangerBankNumberCard(){
        return new CardInfo("2200 4444 4444 4442", dF.format(tMonth), dF.format(tYear+1), "Popova Irina", "123");
    }

    public static CardInfo getSymbolNumberCard(){
        return new CardInfo("%$%$&", dF.format(tMonth), dF.format(tYear+1), "Popova Irina", "123");
    }

    public static CardInfo getLetterNumberCard(){
        return new CardInfo("ghdj fhyr jghy mvgs", dF.format(tMonth), dF.format(tYear+1), "Popova Irina", "123");
    }

// Для проверок поля "Месяц"

    public static CardInfo getEmptyMonthCard(){
        return new CardInfo("4444 4444 4444 4441", "", dF.format(tYear+1), "Popova Irina", "123");
    }

    public static CardInfo getZeroMonthCard(){
        return new CardInfo("4444 4444 4444 4441", "00", dF.format(tYear+1), "Popova Irina", "123");
    }

    public static CardInfo getWrongMonthCard(){
        return new CardInfo("4444 4444 4444 4441", "25", dF.format(tYear+1), "Popova Irina", "123");
    }

    public static CardInfo getSymbolMonthCard(){
                return new CardInfo("4444 4444 4444 4441", "#$", dF.format(tYear+1), "Popova Irina", "123");

    }

    public static CardInfo getLetterMonthCard(){
                return new CardInfo("4444 4444 4444 4441", "#$", dF.format(tYear+1), "Popova Irina", "123");

    }

// Для проверок поля "Год"

    public static CardInfo getEmptyYearCard(){
        return new CardInfo("4444 4444 4444 4441", dF.format(tMonth), "", "Popova Irina", "123");
    }

    public static CardInfo getPastYearCard(){
        return new CardInfo("4444 4444 4444 4441", dF.format(tMonth), dF.format(pYear), "Popova Irina", "123");
    }

    public static CardInfo getMuchFutureYearCard(){
        return new CardInfo("4444 4444 4444 4441", dF.format(tMonth), dF.format(tYear+15), "Popova Irina", "123");
    }
    public static CardInfo getShortYearCard(){
        return new CardInfo("4444 4444 4444 4441", dF.format(tMonth), "4", "Popova Irina", "123");
    }

    public static CardInfo getSymbolYearCard(){
        return new CardInfo("4444 4444 4444 4441", dF.format(tMonth), "#^", "Popova Irina", "123");
    }

    public static CardInfo getLetterYearCard(){
        return new CardInfo("4444 4444 4444 4441", dF.format(tMonth), "rt", "Popova Irina", "123");
    }

    public static CardInfo getPastMonthPresentYearCard(){
        pMonth = getPastMonth();
        return new CardInfo("4444 4444 4444 4441", dF.format(pMonth), dF.format(tYear), "Popova Irina", "123");

    }

// Для проверок поля "Владелец"
    public static CardInfo getEmptyOwnerCard(){
        return new CardInfo("4444 4444 4444 4441", dF.format(tMonth), dF.format(tYear+1), "", "123");
    }

    public static CardInfo getOneWordOwnerCard(){
        return new CardInfo("4444 4444 4444 4441", dF.format(tMonth), dF.format(tYear+1), "Popova", "123");
    }

    public static CardInfo getThreeWordOwnerCard(){
        return new CardInfo("4444 4444 4444 4441", dF.format(tMonth), dF.format(tYear+1), "Popova Irina Petrovna", "123");
    }

    public static CardInfo getSymbolNameOwnerCard(){
        return new CardInfo("4444 4444 4444 4441", dF.format(tMonth), dF.format(tYear+1), "#$%#% #$%#^&*", "123");
    }

    public static CardInfo getRussianNameOwnerCard(){
        return new CardInfo("4444 4444 4444 4441", dF.format(tMonth), dF.format(tYear+1), "Попова Ирина", "123");
    }

    public static CardInfo getNumberNameOwnerCard(){
        return new CardInfo("4444 4444 4444 4441", dF.format(tMonth), dF.format(tYear+1), "98651 216549", "123");
    }

// Для проверок поля "CVV"
    public static CardInfo getEmptyCVVCard(){
        return new CardInfo("4444 4444 4444 4441", dF.format(tMonth), dF.format(tYear+1), "Popova Irina", "");
    }

    public static CardInfo getShortCVVCard(){
        return new CardInfo("4444 4444 4444 4441", dF.format(tMonth), dF.format(tYear+1), "Popova Irina", "4");
    }

    public static CardInfo getLongCVVCard(){
        return new CardInfo("4444 4444 4444 4441", dF.format(tMonth), dF.format(tYear+1), "Popova Irina", "7894");
    }

    public static CardInfo getSymbolCVVCard(){
        return new CardInfo("4444 4444 4444 4441", dF.format(tMonth), dF.format(tYear+1), "Popova Irina", "#$%");
    }

    public static CardInfo getLetterCVVCard(){
        return new CardInfo("4444 4444 4444 4441", dF.format(tMonth), dF.format(tYear+1), "Popova Irina", "dfg");
    }

}
