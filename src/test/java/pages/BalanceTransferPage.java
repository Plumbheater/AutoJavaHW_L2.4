package pages;

import com.codeborne.selenide.SelenideElement;
import data.DataHelper;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class BalanceTransferPage {

    private SelenideElement amount = $("[data-test-id = amount] input");
    private SelenideElement cards = $("[data-test-id = from] input");
    private SelenideElement transButton = $("[data-test-id = action-transfer]");

    private SelenideElement error = $("[data-test-id = error-notification]");

    public void incomingData(DataHelper.NumberCard card, int sum) {
        amount.setValue(Integer.toString(sum));
        cards.setValue(card.getCard());
        transButton.click();
    }

    public void error() {
        error.shouldBe(exactText("Ошибка\n" +
                "Ошибка! Произошла ошибка"), visible);
    }

    public DashboardPage transBalance(DataHelper.NumberCard card, int sum) {
        incomingData(card, sum);
        return new DashboardPage();
    }
}

