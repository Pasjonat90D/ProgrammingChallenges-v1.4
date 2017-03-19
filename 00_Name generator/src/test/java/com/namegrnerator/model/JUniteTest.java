package com.namegrnerator.model;
import com.namegrnerator.controller.Generator;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
/**
 * Created by Andrzej on 2017-03-19.
 */
public class JUniteTest {


    @Test
    public void test() {
        Person person1 = Generator.generatorPeron();
        Person prerson2 = Generator.generatorPeron();
        assertEquals(person1.getName(),prerson2.getName());

    }
}
