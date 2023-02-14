package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class DashboardPage {

    public SelenideElement deposit1 = $x("//div[@data-test-id='92df3f1c-a033-48e6-8390-206f6b1f56c0']/button");
    public SelenideElement deposit2 = $x("//div[@data-test-id='0f3f5c2a-249e-4c3d-8287-09f7a039391d']/button");
    public SelenideElement balanceCard1 = $("[data-test-id = '92df3f1c-a033-48e6-8390-206f6b1f56c0']");

    public SelenideElement balanceCard2 = $("[data-test-id = '0f3f5c2a-249e-4c3d-8287-09f7a039391d']");
    private SelenideElement listText1 = $("[data-test-id = dashboard]");
    private SelenideElement listText2 = $("h1");
    private SelenideElement updateButton = $("[data-test-id=action-reload]");


    public DashboardPage() {
        listText1.shouldBe(exactText("  Личный кабинет"), visible);
        listText2.shouldBe(exactText("Ваши карты"), visible);
        updateButton.shouldBe(visible);
    }

    public BalanceTransferPage pageDashboard(SelenideElement deposit) {
        deposit.click();
        return new BalanceTransferPage();
    }

    public int getBalanceOfCard(SelenideElement balanceCard) {
        return Integer.parseInt(balanceCard.getText().split(",")[1].replaceAll("\\D+", ""));
    }
}

