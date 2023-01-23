package tests;

import data.DataHelper;
import lombok.val;
import org.junit.jupiter.api.*;
import pages.BalanceTransferPage;
import pages.DashboardPage;
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
        val dashboard = new DashboardPage();
        val pageDashboard = dashboard.pageDashboard();
    }

    @Test
    @DisplayName("Перевод 0 рублей со 2ой карты на 1ю")
    void minTransferFrom2to1Card() {
        int sum = 0;
        val balanceTransferPage = new BalanceTransferPage();
        val card = DataHelper.getNumberCard2();
        val balanceOfCardBefore1 = balanceTransferPage.getBalanceOfCard1();
        val balanceOfCardBefore2 = balanceTransferPage.getBalanceOfCard2();
        val balanceTransfer = balanceTransferPage.transferBalance1(card, sum);
        val dashboard = new DashboardPage().returnDashboardPage();
        Assertions.assertEquals(balanceOfCardBefore1 + sum, balanceTransferPage.getBalanceOfCard1());
        Assertions.assertEquals(balanceOfCardBefore2 - sum, balanceTransferPage.getBalanceOfCard2());
    }

    @Test
    @DisplayName("Перевод всей суммы со 2ой карты на 1ю")
    void maxTransferFrom2to1Card() {
        int sum = 10000;
        val balanceTransferPage = new BalanceTransferPage();
        val card = DataHelper.getNumberCard2();
        val balanceOfCardBefore1 = balanceTransferPage.getBalanceOfCard1();
        val balanceOfCardBefore2 = balanceTransferPage.getBalanceOfCard2();
        val balanceTransfer = balanceTransferPage.transferBalance1(card, sum);
        val dashboard = new DashboardPage().returnDashboardPage();
        Assertions.assertEquals(balanceOfCardBefore1 + sum, balanceTransferPage.getBalanceOfCard1());
        Assertions.assertEquals(balanceOfCardBefore2 - sum, balanceTransferPage.getBalanceOfCard2());
    }

    @Test
    @DisplayName("Перевод 0 рублей с 1ой карты на 2ю")
    void minTransferFrom1to2Card() {
        int sum = 0;
        val balanceTransferPage = new BalanceTransferPage();
        val card = DataHelper.getNumberCard1();
        val balanceOfCardBefore1 = balanceTransferPage.getBalanceOfCard1();
        val balanceOfCardBefore2 = balanceTransferPage.getBalanceOfCard2();
        val balanceTransfer = balanceTransferPage.transferBalance2(card, sum);
        val dashboard = new DashboardPage().returnDashboardPage();
        Assertions.assertEquals(balanceOfCardBefore2 + sum, balanceTransferPage.getBalanceOfCard2());
        Assertions.assertEquals(balanceOfCardBefore1 - sum, balanceTransferPage.getBalanceOfCard1());
    }

    @Test
    @DisplayName("Перевод всей суммы с 1ой карты на 2ю")
    void maxTransferFrom1to2Card() {
        int sum = 10000;
        val balanceTransferPage = new BalanceTransferPage();
        val card = DataHelper.getNumberCard1();
        val balanceOfCardBefore1 = balanceTransferPage.getBalanceOfCard1();
        val balanceOfCardBefore2 = balanceTransferPage.getBalanceOfCard2();
        val balanceTransfer = balanceTransferPage.transferBalance2(card, sum);
        val dashboard = new DashboardPage().returnDashboardPage();
        Assertions.assertEquals(balanceOfCardBefore2 + sum, balanceTransferPage.getBalanceOfCard2());
        Assertions.assertEquals(balanceOfCardBefore1 - sum, balanceTransferPage.getBalanceOfCard1());
    }

    @Test
    @DisplayName("Перевод 0 рублей с несуществующего номера карты на 1ю")
    void noMinTransferFrom2to1Card() {
        int sum = 0;
        val balanceTransferPage = new BalanceTransferPage();
        val card = DataHelper.getNumberCard3();
        val balanceTransfer = balanceTransferPage.transferBalanceWithError(card, sum);
        val error = balanceTransferPage.error();
    }

    @Test
    @DisplayName("Перевод всей суммы с несуществующего номера карты на 1ю")
    void noMaxTransferFrom2to1Card() {
        int sum = 10000;
        val balanceTransferPage = new BalanceTransferPage();
        val card = DataHelper.getNumberCard3();
        val balanceTransfer = balanceTransferPage.transferBalanceWithError(card, sum);
        val error = balanceTransferPage.error();
    }

    @Test
    @DisplayName("Перевод 0 рублей с несуществующего номера карты на 2ю")
    void noMinTransferFrom1to2Card() {
        int sum = 0;
        val balanceTransferPage = new BalanceTransferPage();
        val card = DataHelper.getNumberCard3();
        val balanceTransfer = balanceTransferPage.transferBalanceWithError(card, sum);
        val error = balanceTransferPage.error();
    }

    @Test
    @DisplayName("Перевод всей суммы с несуществующего номера карты на 2ю")
    void noMaxTransferFrom1to2Card() {
        int sum = 10000;
        val balanceTransferPage = new BalanceTransferPage();
        val card = DataHelper.getNumberCard3();
        val balanceTransfer = balanceTransferPage.transferBalanceWithError(card, sum);
        val error = balanceTransferPage.error();
    }

    @Test
    @DisplayName("Перевод суммы превышающий баланс карты со 2ой карты на 1ю")
    void highTransferFrom2to1Card() {
        int sum = 10001;
        val balanceTransferPage = new BalanceTransferPage();
        val card = DataHelper.getNumberCard2();
        val balanceTransfer = balanceTransferPage.transferBalance1(card, sum);
        val error = balanceTransferPage.error();
    }

    @Test
    @DisplayName("Перевод суммы превышающий баланс карты со 1ой карты на 2ю")
    void highTransferFrom1to2Card() {
        int sum = 10001;
        val balanceTransferPage = new BalanceTransferPage();
        val card = DataHelper.getNumberCard1();
        val balanceTransfer = balanceTransferPage.transferBalance2(card, sum);
        val error = balanceTransferPage.error();

    }
}
