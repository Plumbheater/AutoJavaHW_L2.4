package pages;

import com.codeborne.selenide.SelenideElement;
import data.DataHelper;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {
    private SelenideElement code = $("[data-test-id = code] input");
    private SelenideElement verificationButton = $("[data-test-id = action-verify]");
    private SelenideElement listText1 = $("[data-test-id = dashboard]");
    private SelenideElement listText2 = $("h1");


    public VerificationPage verification(DataHelper.VerificationCode codeVerification) {

        code.setValue(Integer.toString(codeVerification.getCode()));
        verificationButton.click();
        listText1.shouldBe(exactText("  Личный кабинет"), visible);
        listText2.shouldBe(exactText("Ваши карты"), visible);
        return new VerificationPage();
    }
}