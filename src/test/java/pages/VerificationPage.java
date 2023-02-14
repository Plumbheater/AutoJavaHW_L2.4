package pages;

import com.codeborne.selenide.SelenideElement;
import data.DataHelper;
import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {
    private SelenideElement code = $("[data-test-id = code] input");
    private SelenideElement verificationButton = $("[data-test-id = action-verify]");



    public DashboardPage verification(DataHelper.VerificationCode codeVerification) {

        code.setValue(Integer.toString(codeVerification.getCode()));
        verificationButton.click();

        return new DashboardPage();
    }
}