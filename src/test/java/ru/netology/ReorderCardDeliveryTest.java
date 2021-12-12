package ru.netology;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.Selenide;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.info.OrderCardInfo;
import ru.netology.util.DataGenerator;

import java.time.Duration;
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

    @Test
    void shouldSendForm() {
        OrderCardInfo info = DataGenerator.ReorderCard.generateInfo("ru");

        open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue(info.getCity());
        $("[data-test-id='date'] input").setValue(info.getDate());
        $("[data-test-id='name'] input").setValue(info.getFullName());
        $("[data-test-id='phone'] input").setValue(info.getPhone());
        $(".checkbox__box").click();
        $$("button").get(1).click();
        $("[data-test-id='success-notification']").should(appear);
        $("[class='notification__title']").shouldHave(exactText("Успешно!"));
//        assertEquals("Успешно!\nВстреча успешно запланирована на " + $("[data-test-id=date] input").getValue(),$("[data-test-id='success-notification']").getText() );
        $("[class='notification__content']").shouldHave(Condition.text("Встреча успешно запланирована на " + $("[data-test-id=date] input").getValue()));
    }

    @Test
    void shouldReorderCard() {
        OrderCardInfo info = DataGenerator.ReorderCard.generateInfo("ru");

        open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue(info.getCity());
        $("[data-test-id='date'] input").setValue(info.getDate());
        $("[data-test-id='name'] input").setValue(info.getFullName());
        $("[data-test-id='phone'] input").setValue(info.getPhone());
        $(".checkbox__box").click();
        $$("button").get(1).click();
        $("[data-test-id='success-notification']").should(appear);
        $("[class='notification__title']").shouldHave(exactText("Успешно!"));
        $("[class='notification__content']").shouldHave(Condition.text("Встреча успешно запланирована на " + $("[data-test-id=date] input").getValue()));

        $$("button").get(1).click();
        $("[data-test-id='replan-notification']").should(appear);
        $$("button").get(3).click();
        $("[class='notification__title']").shouldHave(exactText("Успешно!"));
        $("[class='notification__content']").shouldHave(Condition.text("Встреча успешно запланирована на " + $("[data-test-id=date] input").getValue()));

    }

}
