package tests;

import data.DataHelper;
import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.BalanceTransferPage;
import pages.ErrorPage;
import pages.LoginPage;
import pages.VerificationPage;

import static com.codeborne.selenide.Selenide.open;

public class TestCard {

    @BeforeEach
    void setUp() {
        open("http://localhost:9999/");
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val loginUp = loginPage.loginUp(authInfo);
        val verificationPage = new VerificationPage();
        val code = DataHelper.getVerificationCode();
        val verificationCode = verificationPage.verification(code);
    }


    @Test
    @DisplayName("Перевод 0 рублей со 2ой карты на 1ю")
    void minTransferFrom2to1Card() {
        val sum = new DataHelper.Amount(0);
        val balanceTransferPage = new BalanceTransferPage();
        val card = DataHelper.getNumberCard2();
        val balanceOfCardBefore = balanceTransferPage.getBalanceOfCard1();
        val balanceTransfer = balanceTransferPage.transferBalance1(card, sum);
        Assertions.assertEquals(balanceOfCardBefore + sum.getAmount(), balanceTransferPage.getBalanceOfCard1());
    }

    @Test
    @DisplayName("Перевод всей суммы со 2ой карты на 1ю")
    void maxTransferFrom2to1Card() {
        val sum = new DataHelper.Amount(10000);
        val balanceTransferPage = new BalanceTransferPage();
        val card = DataHelper.getNumberCard2();
        val balanceOfCardBefore = balanceTransferPage.getBalanceOfCard1();
        val balanceTransfer = balanceTransferPage.transferBalance1(card, sum);
        Assertions.assertEquals(balanceOfCardBefore + sum.getAmount(), balanceTransferPage.getBalanceOfCard1());
    }


    @Test
    @DisplayName("Перевод 0 рублей со 1ой карты на 2ю")
    void minTransferFrom1to2Card() {
        val sum = new DataHelper.Amount(0);
        val balanceTransferPage = new BalanceTransferPage();
        val card = DataHelper.getNumberCard1();
        val balanceOfCardBefore = balanceTransferPage.getBalanceOfCard2();
        val balanceTransfer = balanceTransferPage.transferBalance2(card, sum);
        Assertions.assertEquals(balanceOfCardBefore + sum.getAmount(), balanceTransferPage.getBalanceOfCard2());

    }

    @Test
    @DisplayName("Перевод всей суммы со 1ой карты на 2ю")
    void maxTransferFrom1to2Card() {
        val sum = new DataHelper.Amount(10000);
        val balanceTransferPage = new BalanceTransferPage();
        val card = DataHelper.getNumberCard1();
        val balanceOfCardBefore = balanceTransferPage.getBalanceOfCard2();
        val balanceTransfer = balanceTransferPage.transferBalance2(card, sum);
        Assertions.assertEquals(balanceOfCardBefore + sum.getAmount(), balanceTransferPage.getBalanceOfCard2());

    }

    @Test
    @DisplayName("Перевод 0 рублей с несуществующего номера карты на 1ю")
    void noMinTransferFrom2to1Card() {
        val sum = new DataHelper.Amount(0);
        val balanceTransferPage = new BalanceTransferPage();
        val card = DataHelper.getNumberCard3();
        val balanceOfCardBefore = balanceTransferPage.getBalanceOfCard1();
        val balanceTransfer = balanceTransferPage.transferBalanceWithError(card, sum);
        val error = new ErrorPage().error();
    }

    @Test
    @DisplayName("Перевод всей суммы с несуществующего номера карты на 1ю")
    void noMaxTransferFrom2to1Card() {
        val sum = new DataHelper.Amount(10000);
        val balanceTransferPage = new BalanceTransferPage();
        val card = DataHelper.getNumberCard3();
        val balanceOfCardBefore = balanceTransferPage.getBalanceOfCard1();
        val balanceTransfer = balanceTransferPage.transferBalanceWithError(card, sum);
        val error = new ErrorPage().error();
    }

    @Test
    @DisplayName("Перевод 0 рублей с несуществующего номера карты на 2ю")
    void noMinTransferFrom1to2Card() {
        val sum = new DataHelper.Amount(0);
        val balanceTransferPage = new BalanceTransferPage();
        val card = DataHelper.getNumberCard3();
        val balanceOfCardBefore = balanceTransferPage.getBalanceOfCard1();
        val balanceTransfer = balanceTransferPage.transferBalanceWithError(card, sum);
        val error = new ErrorPage().error();
    }

    @Test
    @DisplayName("Перевод всей суммы с несуществующего номера карты на 2ю")
    void noMaxTransferFrom1to2Card() {
        val sum = new DataHelper.Amount(10000);
        val balanceTransferPage = new BalanceTransferPage();
        val card = DataHelper.getNumberCard3();
        val balanceOfCardBefore = balanceTransferPage.getBalanceOfCard1();
        val balanceTransfer = balanceTransferPage.transferBalanceWithError(card, sum);
        val error = new ErrorPage().error();
    }

    @Test
    @DisplayName("Перевод суммы превышающий баланс карты со 2ой карты на 1ю")
    void highTransferFrom2to1Card() {
        val sum = new DataHelper.Amount(10001);
        val balanceTransferPage = new BalanceTransferPage();
        val card = DataHelper.getNumberCard2();
        val balanceOfCardBefore = balanceTransferPage.getBalanceOfCard1();
        val balanceTransfer = balanceTransferPage.transferBalance1(card, sum);
        val error = new ErrorPage().error();
    }


    @Test
    @DisplayName("Перевод суммы превышающий баланс карты со 1ой карты на 2ю")
    void highTransferFrom1to2Card() {
        val sum = new DataHelper.Amount(10001);
        val balanceTransferPage = new BalanceTransferPage();
        val card = DataHelper.getNumberCard1();
        val balanceOfCardBefore = balanceTransferPage.getBalanceOfCard2();
        val balanceTransfer = balanceTransferPage.transferBalance2(card, sum);
        val error = new ErrorPage().error();

    }
}
