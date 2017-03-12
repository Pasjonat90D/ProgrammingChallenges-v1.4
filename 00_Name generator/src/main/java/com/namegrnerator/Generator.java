package com.namegrnerator;

import com.namegrnerator.dataToGenarate.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Andrzej on 2017-03-12.
 */
public class Generator {


    public static Person generatorPeron (){
        Person person = new Person();
        Random random = new Random();

        int rand = random.nextInt(Data.names.length);
        person.setName(Data.names[rand]);
        person.setName(Data.surname[rand]);
        int years = ThreadLocalRandom.current().nextInt(1950, 2016 + 1);
        int month = ThreadLocalRandom.current().nextInt(1, 12 + 1);
        int day = ThreadLocalRandom.current().nextInt(1, 28 + 1);
        LocalDateTime birthday = LocalDateTime.of(years,month,day,0,0);
        person.setBirthday(birthday.toLocalDate());
        return person;



    }



}
