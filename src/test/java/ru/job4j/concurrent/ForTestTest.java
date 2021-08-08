package ru.job4j.concurrent;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

class ForTestTest {

    @Test
    public void forTestTest() {
        ForTest f = new ForTest("AAA");
        assertEquals(f.getA(), "AAA");
    }

}