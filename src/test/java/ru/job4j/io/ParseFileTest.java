package ru.job4j.io;

import org.junit.Ignore;
import org.junit.Test;
import ru.job4j.concurrent.ConcurrentOutput;

import java.io.File;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


public class ParseFileTest {

    @Test
    @Ignore
    public void parsingWithUnicode() {
        ParseFile parseFile = new ParseFile(
                new File("src/main/java/ru/job4j/io/Test.txt"));
        assertThat(parseFile.getContent(), is("Привет, менторы!"));
    }

    @Test
    @Ignore
    public void parsingWithOutUnicode() {
        ParseFile parseFile = new ParseFile(
                new File("src/main/java/ru/job4j/io/Test.txt"));
        assertThat(parseFile.getContentWithoutUnicode(), is("Привет менторы"));
    }

}