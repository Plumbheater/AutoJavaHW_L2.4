package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class DashboardPage {

    private SelenideElement deposit1 = $x("//div[@data-test-id='92df3f1c-a033-48e6-8390-206f6b1f56c0']/button");
    private SelenideElement deposit2 = $x("//div[@data-test-id='0f3f5c2a-249e-4c3d-8287-09f7a039391d']/button");
    private SelenideElement listText1 = $("[data-test-id = dashboard]");
    private SelenideElement listText2 = $("h1");

    private SelenideElement cancel = $("[data-test-id = action-cancel]");

    public DashboardPage pageDashboard() {
        listText1.shouldBe(exactText("  Личный кабинет"), visible);
        listText2.shouldBe(exactText("Ваши карты"), visible);
        deposit1.click();
        listText1.shouldBe(exactText("  Личный кабинет"), visible);
        listText2.shouldBe(exactText("Пополнение карты"), visible);
        cancel.click();
        listText1.shouldBe(exactText("  Личный кабинет"), visible);
        listText2.shouldBe(exactText("Ваши карты"), visible);
        deposit2.click();
        listText1.shouldBe(exactText("  Личный кабинет"), visible);
        listText2.shouldBe(exactText("Пополнение карты"), visible);
        cancel.click();
        listText1.shouldBe(exactText("  Личный кабинет"), visible);
        listText2.shouldBe(exactText("Ваши карты"), visible);
        return new DashboardPage();
    }

    public DashboardPage returnDashboardPage() {
        listText1.shouldBe(exactText("  Личный кабинет"), visible);
        listText2.shouldBe(exactText("Ваши карты"), visible);
        return  new DashboardPage();
    }
}
