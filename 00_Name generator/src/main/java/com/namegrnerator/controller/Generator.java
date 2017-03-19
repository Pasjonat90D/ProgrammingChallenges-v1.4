package com.namegrnerator.controller;

import com.namegrnerator.dataToGenarate.Data;
import com.namegrnerator.model.Person;

import java.io.*;
import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Andrzej on 2017-03-12.
 */
public class Generator {


    public static Person generatorPeron() {
        int lineNumberNameF = 0;
        int lineNumberNameM = 0;
        String name = "";
        try {
            String basePath = new File("").getAbsolutePath();
            String pathFileNameF = new File("src/main/java/com/namegrnerator/dataToGenarate/NameF.txt").getAbsolutePath();
            LineNumberReader lineNumberReaderNameF = new LineNumberReader(new FileReader(pathFileNameF));
            lineNumberReaderNameF.skip(Long.MAX_VALUE);
            lineNumberNameF = lineNumberReaderNameF.getLineNumber() + 1;
            String pathFileNameM = new File("src/main/java/com/namegrnerator/dataToGenarate/NameM").getAbsolutePath();
            LineNumberReader lineNumberReaderNameM = new LineNumberReader(new FileReader(pathFileNameM));
            lineNumberReaderNameM.skip(Long.MAX_VALUE);
            lineNumberNameM = lineNumberReaderNameM.getLineNumber() + 1;
            Random random = new Random();
            BufferedReader reader = new BufferedReader(new FileReader(pathFileNameF));
            int rand = random.nextInt(lineNumberNameF);
            String line;
            for (int i = 0; (line = reader.readLine()) != null; i++) {
                if (rand == i) {
                    name = line;
                }
            }

            FileSupport nowy = new FileSupport(pathFileNameF);

            lineNumberReaderNameF.close();
        } catch (IOException e) {
            //fack YEA
            String basePath = new File("").getAbsolutePath();
            System.out.println("base: " + basePath);
            String path = new File("src/main/java/com/namegrnerator/dataToGenarate/NameF.txt").getAbsolutePath();
            System.out.println("File path: " + path);
            e.printStackTrace();
        }
        Random random = new Random();

        int rand = random.nextInt(Data.names.length);
        int years = ThreadLocalRandom.current().nextInt(1950, 2016 + 1);
        int month = ThreadLocalRandom.current().nextInt(1, 12 + 1);
        int day = ThreadLocalRandom.current().nextInt(1, 28 + 1);
        LocalDateTime birthday = LocalDateTime.of(years, month, day, 0, 0);
        Person person = new Person(name, Data.surnames[rand], Data.numberPhones[rand], birthday.toLocalDate(), Data.address[rand]);

        return person;


    }


}
