package com.namegrnerator.controller;

import com.namegrnerator.Main;
import com.namegrnerator.dataToGenarate.Data;
import com.namegrnerator.model.Person;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Andrzej on 2017-03-12.
 */
public class Generator {


    public static Person generatorPeron(String gender) {
        FileSupport names = new FileSupport(nameFileForNamesPerson(gender));

        Random random = new Random();

        int numberPhone = ThreadLocalRandom.current().nextInt(100000000, 999999998 + 1);
        int rand = random.nextInt(Data.names.length);

        Person person = new Person(names.randomLine(), Data.surnames[rand], numberPhone, randomBirthday().toLocalDate(), Data.address[rand]);
        return person;

    }

    private static String nameFileForNamesPerson(String gender) {
        if (gender.equals("Female")) {
            return "src/main/java/com/namegrnerator/dataToGenarate/NameF.txt";
        } else if (gender.equals("Male")) {
            return "src/main/java/com/namegrnerator/dataToGenarate/NameM";
        } else {
            Random rand = new Random();
            if (rand.nextBoolean() == true) {
                return "src/main/java/com/namegrnerator/dataToGenarate/NameF.txt";
            } else {
                return "src/main/java/com/namegrnerator/dataToGenarate/NameM";
            }
        }
    }

    private static LocalDateTime randomBirthday() {
        int years = ThreadLocalRandom.current().nextInt(1950, 2016 + 1);
        int month = ThreadLocalRandom.current().nextInt(1, 12 + 1);
        int day = ThreadLocalRandom.current().nextInt(1, 28 + 1);
        LocalDateTime birthday = LocalDateTime.of(years, month, day, 0, 0);
        return birthday;
    }

}
