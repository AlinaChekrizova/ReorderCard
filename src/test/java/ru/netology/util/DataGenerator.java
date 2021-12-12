package ru.netology.util;

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
                    faker.date().future(4 , TimeUnit.DAYS).toString(),
                    faker.name().fullName(),
                    faker.phoneNumber().cellPhone()
            );
        }
    }
}
