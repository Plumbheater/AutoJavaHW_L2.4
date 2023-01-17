package pages;

import com.codeborne.selenide.SelenideElement;
import data.DataHelper;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class BalanceTransferPage {

    private SelenideElement deposit1 = $x("//div[@data-test-id='92df3f1c-a033-48e6-8390-206f6b1f56c0']/button");
    private SelenideElement deposit2 = $x("//div[@data-test-id='0f3f5c2a-249e-4c3d-8287-09f7a039391d']/button");
    private SelenideElement listText1 = $("[data-test-id = dashboard]");
    private SelenideElement listText2 = $("h1");
    private SelenideElement balanceCard1 = $("[data-test-id = '92df3f1c-a033-48e6-8390-206f6b1f56c0']");

    private SelenideElement balanceCard2 = $("[data-test-id = '0f3f5c2a-249e-4c3d-8287-09f7a039391d']");


    private SelenideElement amount = $("[data-test-id = amount] input");
    private SelenideElement cards = $("[data-test-id = from] input");
    private SelenideElement transButton = $("[data-test-id = action-transfer]");
    private SelenideElement listText3 = $("[data-test-id = dashboard]");
    private SelenideElement listText4 = $("h1");


    public BalanceTransferPage transferBalance1(DataHelper.NumberCard card, DataHelper.Amount sum) {
        deposit1.click();
        listText1.shouldBe(exactText("  Личный кабинет"), visible);
        listText2.shouldBe(exactText("Пополнение карты"), visible);
        amount.setValue(Integer.toString(sum.getAmount()));
        cards.setValue(card.getCard());
        transButton.click();
        listText3.shouldBe(exactText("  Личный кабинет"), visible);
        listText4.shouldBe(exactText("Ваши карты"), visible);

        return new BalanceTransferPage();
    }

    public BalanceTransferPage transferBalance2(DataHelper.NumberCard card, DataHelper.Amount sum) {
        deposit2.click();
        listText1.shouldBe(exactText("  Личный кабинет"), visible);
        listText2.shouldBe(exactText("Пополнение карты"), visible);
        amount.setValue(Integer.toString(sum.getAmount()));
        cards.setValue(card.getCard());
        transButton.click();
        listText3.shouldBe(exactText("  Личный кабинет"), visible);
        listText4.shouldBe(exactText("Ваши карты"), visible);

        return new BalanceTransferPage();
    }

    public BalanceTransferPage transferBalanceWithError(DataHelper.NumberCard card, DataHelper.Amount sum) {
        deposit2.click();
        listText1.shouldBe(exactText("  Личный кабинет"), visible);
        listText2.shouldBe(exactText("Пополнение карты"), visible);
        amount.setValue(Integer.toString(sum.getAmount()));
        cards.setValue(card.getCard());
        transButton.click();

        return new BalanceTransferPage();

    }

    public int getBalanceOfCard1() {
        return Integer.parseInt(balanceCard1.getText().split(",")[1].replaceAll("\\D+", ""));
    }

    public int getBalanceOfCard2() {
        return Integer.parseInt(balanceCard2.getText().split(",")[1].replaceAll("\\D+", ""));

    }

}

