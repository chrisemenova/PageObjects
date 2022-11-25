package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.visible;

public class DashboardPage {
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";
    @FindBy(css = "[data-test-id=dashboard]")
    private SelenideElement heading;
//  private SelenideElement heading = $("[data-test-id=dashboard]");
    @FindBy(css= ".list__item div")
    private ElementsCollection cards;
//  private ElementsCollection cards = $$(".list__item div");

//    public DashboardPage() {
//        heading.shouldBe(visible);
//    }

    public int getCardBalance(DataHelper.CardInfo cardInfo) {
    var text = cards.get(1).getText();
        return extractBalance(text);
    }

    private int extractBalance(String text) {
        var start = text.indexOf(balanceStart);
        var finish = text.indexOf(balanceFinish);
        var value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }

    public TransferPage selectCardToTransfer(DataHelper.CardInfo cardInfo) {
        cards.findBy(attribute("data-test-id", cardInfo.getTestId())).$("button").click();
        return new TransferPage();
    }

}
