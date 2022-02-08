package ru.netology;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import ru.netology.info.OrderCardInfo;
import ru.netology.util.DataGenerator;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;


public class ReorderCardDeliveryTest {


    @Test
    void shouldSendForm() {
        OrderCardInfo info = DataGenerator.ReorderCard.generateInfo("ru");
        String planningDate = DataGenerator.ReorderCard.generateDate(4);

        open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue(info.getCity());
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(planningDate);
        $("[data-test-id='name'] input").setValue(info.getFullName());
        $("[data-test-id='phone'] input").setValue(info.getPhone());
        $(".checkbox__box").click();
        $$("button").get(1).click();
        $("[data-test-id='success-notification']").should(appear);
        $("[class='notification__title']").shouldHave(exactText("Успешно!"));
        $("[class='notification__content']").shouldHave(text("Встреча успешно запланирована на " + planningDate));
    }

    @Test
    void shouldReorderCard() {
        OrderCardInfo info = DataGenerator.ReorderCard.generateInfo("ru");
        String planningDate = DataGenerator.ReorderCard.generateDate(6);

        open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue(info.getCity());
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(planningDate);
        $("[data-test-id='name'] input").setValue(info.getFullName());
        $("[data-test-id='phone'] input").setValue(info.getPhone());
        $(".checkbox__box").click();
        $("button.button").click();
        $("[data-test-id='success-notification']").should(appear);
        $("[class='notification__title']").shouldHave(exactText("Успешно!"));
        $("[class='notification__content']").shouldHave(text("Встреча успешно запланирована на " + planningDate));
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(planningDate);
        $("button.button").click();
        $("[data-test-id='replan-notification']").should(appear);
        $$("button").get(3).click();
        $("[class='notification__title']").shouldHave(exactText("Успешно!"));
        $("[class='notification__content']").shouldHave(text("Встреча успешно запланирована на " + planningDate));

    }

}
