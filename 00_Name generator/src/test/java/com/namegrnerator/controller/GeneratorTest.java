package com.namegrnerator.controller;

import com.namegrnerator.model.Person;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Andrzej on 2017-04-05.
 */
public class GeneratorTest {
    @Test
    public void testGeneratorPersonIsNotNull()  {
        assertNotNull(Generator.generatorPeron("Random"));

    }

}