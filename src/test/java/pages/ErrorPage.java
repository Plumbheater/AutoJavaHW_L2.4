package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class ErrorPage {

    private SelenideElement error = $("[data-test-id = error-notification]");


    public ErrorPage error() {
        error.shouldBe(exactText("Ошибка\n" +
                "Ошибка! Произошла ошибка"), visible);
        return new ErrorPage();
    }
}
