package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import data.DataHelper;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class DashboardPage {

    private SelenideElement buttonOfDeposit1 = $x("//div[@data-test-id='92df3f1c-a033-48e6-8390-206f6b1f56c0']/button");
    private SelenideElement buttonOfDeposit2 = $x("//div[@data-test-id='0f3f5c2a-249e-4c3d-8287-09f7a039391d']/button");

    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";
    private ElementsCollection cards = $$(".list__item div");

    private SelenideElement listText1 = $("[data-test-id = dashboard]");
    private SelenideElement listText2 = $("h1");
    private SelenideElement updateButton = $("[data-test-id=action-reload]");


    public DashboardPage() {
        listText1.shouldBe(exactText("  Личный кабинет"), visible);
        listText2.shouldBe(exactText("Ваши карты"), visible);
        updateButton.shouldBe(visible);
    }


    public BalanceTransferPage buttonFirstDeposit() {
        buttonOfDeposit1.click();
        return new BalanceTransferPage();
    }

    public BalanceTransferPage buttonSecondDeposit() {
        buttonOfDeposit2.click();
        return new BalanceTransferPage();
    }
    public int getBalanceOfCard(DataHelper.NumberCard numberCard) {
        var text = cards.findBy(text(numberCard.getCard().substring(16))).getText();
        return extractBalance(text);
    }

    private int extractBalance(String text) {
        var start = text.indexOf(balanceStart);
        var finish = text.indexOf(balanceFinish);
        var value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }

}


