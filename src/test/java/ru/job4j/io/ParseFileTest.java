package ru.job4j.io;

import org.junit.Test;

import java.io.File;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ParseFileTest {

    @Test

    public void parsingWithUnicode() {
        ParseFile parseFile = new ParseFile(
                new File("src/main/java/ru/job4j/io/Test.txt"));
        assertThat(parseFile.getContent(), is("Привет, менторы!"));
    }

    @Test
    public void parsingWithOutUnicode() {
        ParseFile parseFile = new ParseFile(
                new File("src/main/java/ru/job4j/io/Test.txt"));
        assertThat(parseFile.getContentWithoutUnicode(), is("Привет менторы"));
    }

}