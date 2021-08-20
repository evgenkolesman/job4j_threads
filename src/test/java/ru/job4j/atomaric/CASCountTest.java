package ru.job4j.atomaric;


import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class CASCountTest {

    @Test
    public void incrementTest() {
        CASCount casCount = new CASCount();
        Thread first = new Thread( () -> {
            casCount.increment();
        });
        first.start();
        try {
            first.wait(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertThat(casCount.getVal(), is(1));
    }
}