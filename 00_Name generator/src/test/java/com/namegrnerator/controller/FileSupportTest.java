package com.namegrnerator.controller;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.Assert.assertThat;

/**
 * Created by Andrzej on 2017-04-05.
 */
public class FileSupportTest {

    @Rule
    public ExpectedException thrown= ExpectedException.none();

    @Test
    public void fileNotExistShouldReturnException() {
        thrown.expect(FileNotFoundException.class);

            FileSupport files = new FileSupport("src/main/java/com/namegrnerator/dataToGenarate/Suname");
            files.randomLine();


    }



    @Test
    public void throwsNothing() {
        // no exception expected, none thrown: passes.
    }

    @Test
    public void throwsExceptionWithSpecificType() {
        thrown.expect(NullPointerException.class);
        throw new NullPointerException();
    }
}