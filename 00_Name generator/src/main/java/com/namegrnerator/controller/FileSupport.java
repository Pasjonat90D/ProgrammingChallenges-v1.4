package com.namegrnerator.controller;

import java.io.*;

/**
 * Created by Andrzej on 2017-03-16.
 */
public class FileSupport {


    File file ;

    public FileSupport(String s){
        String basePath = new File("").getAbsolutePath();
        String pathFile = basePath+s;
        this.file = new File(pathFile);
    }

    public void lineNumber(){
        try{
        LineNumberReader line = new LineNumberReader(new FileReader(file));
        line.close();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
