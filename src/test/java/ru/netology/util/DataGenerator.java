package ru.netology.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import com.github.javafaker.Faker;
import lombok.experimental.UtilityClass;
import ru.netology.info.OrderCardInfo;

@UtilityClass
public class DataGenerator {

    @UtilityClass
    public static class ReorderCard {
        public static OrderCardInfo generateInfo(String locale){
            Faker faker = new Faker(new Locale(locale));
            return new OrderCardInfo(
                    faker.address().city(),
                    faker.name().fullName(),
                    faker.bothify("+79#########")
            );
        }
        public String generateDate(int days) {
            return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        }
    }
}
