package com.namegrnerator.controller;

import com.namegrnerator.dataToGenarate.Data;
import com.namegrnerator.model.Person;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Andrzej on 2017-03-12.
 */
public class Generator {


    public static Person generatorPeron (){

        Random random = new Random();

        int rand = random.nextInt(Data.names.length);

        int years = ThreadLocalRandom.current().nextInt(1950, 2016 + 1);
        int month = ThreadLocalRandom.current().nextInt(1, 12 + 1);
        int day = ThreadLocalRandom.current().nextInt(1, 28 + 1);
        LocalDateTime birthday = LocalDateTime.of(years,month,day,0,0);
        Person person = new Person(Data.names[rand],Data.surnames[rand],Data.numberPhones[rand],birthday.toLocalDate(),Data.address[rand]);

        return person;



    }



}
