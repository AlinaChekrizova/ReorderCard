package ru.netology;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.Selenide;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import ru.netology.info.OrderCardInfo;
import ru.netology.util.DataGenerator;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;
import static org.junit.jupiter.api.Assertions.*;
import static com.codeborne.selenide.Selectors.*;


public class ReorderCardDeliveryTest {

    private Faker faker;

    @BeforeEach
    void setUpAll() {
        faker = new Faker(new Locale("ru"));
    }

    @Test
    void shouldGenerateTestInfo() {

    }

    public String generateDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    @Test
    void shouldSendForm() {
        OrderCardInfo info = DataGenerator.ReorderCard.generateInfo("ru");
        String planningDate = generateDate(4);

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
        String planningDate = generateDate(6);

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
