package ru.job4j.io;

import java.io.*;
import java.util.function.Predicate;

/**
 * Class may be used for getting content from File
 * way of getting is parsing
 * in constructor we need to insert FILE source to read
 * we have two classes Input(constructor is default) and
 * Output (it`s necessary to input File source to write)
 *
 * @author Kolesnikov Evgeniy
 * @version 1.0
 */

public class ParseFile {
    private final File file;

    public ParseFile(File file) {
        this.file = file;
    }

    public synchronized String getContent() {
        Predicate<Character> filter = (s -> s > 0);
        return content(filter);
    }

    public synchronized String getContentWithoutUnicode() {
        Predicate<Character> filter = (s -> s > 0x80 && s != '\u0000');
        return content(filter);
    }

    private synchronized String content(Predicate<Character> filter) {
        StringBuilder output = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            int data;
            while ((data = in.read()) > 0) {
                if (filter.test((char) data)) {
                    output.append((char) data);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return output.toString();
    }
}

