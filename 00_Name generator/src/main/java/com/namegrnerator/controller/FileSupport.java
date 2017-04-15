package com.namegrnerator.controller;

import java.io.*;
import java.util.Random;

/**
 * Created by Andrzej on 2017-03-16.
 */
public class FileSupport {


    File file;

    String pathFile ;

    public FileSupport(String s) {

        pathFile = new File(s).getAbsolutePath();
        this.file = new File(pathFile);
    }

    public String randomLine() {
        int lineNumberName = 0;
        String name = "";
        try {
            String base = file.getAbsolutePath();
            LineNumberReader lineNumberReaderName = new LineNumberReader(new FileReader(pathFile));
            lineNumberReaderName.skip(Long.MAX_VALUE);
            lineNumberName = lineNumberReaderName.getLineNumber() + 1;
            Random random = new Random();
            BufferedReader reader = new BufferedReader(new FileReader(file));
            int rand = random.nextInt(lineNumberName);
            String line ;
            for (int i = 0; (line = reader.readLine()) != null; i++) {
                if (rand == i) {
                    name = line;
                }
            }
            lineNumberReaderName.close();
        }  catch (IOException e) {
            e.printStackTrace();
        }
        return name;
    }

}
