package ru.job4j.io;

import java.io.*;
import java.util.function.Predicate;

public class ParseFile {
    private final File file;

    public ParseFile(File file) {
        this.file = file;
    }

    public String getContent() {
        Predicate<Character> filter = (s -> s > 0);
        return content(filter);
    }

    public String getContentWithoutUnicode() {
        Predicate<Character> filter = (s -> s > 0 && s < 0x80);
        return content(filter);
    }

    private String content(Predicate<Character> filter) {
        StringBuilder output = new StringBuilder();
        InputOutput io = new InputOutput();
        int data = io.readContent(file);
        while (filter.test((char) data)) {
            output.append((char) data);

        }
        return output.toString();
    }

    private class InputOutput {
        public void saveContent(String content) {
            try (BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)))) {
                for (int i = 0; i < content.length(); i += 1) {
                    out.write(content.charAt(i));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public int readContent(File file) {
            int a = 0;
            try (BufferedReader in = new BufferedReader(new FileReader(file))) {
                a = in.read();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return a;
        }

    }
}
