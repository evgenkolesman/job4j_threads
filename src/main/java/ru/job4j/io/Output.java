package ru.job4j.io;

import java.io.*;

/**
 * Класс необходим для записи контента в файл
 *
 * @author Kolesnikov Evgeniy
 * @version 1.0
 */
public class Output {
    private File file;

    public Output(File file) {
        this.file = file;
    }


    public void saveContent(String content) {
        try (BufferedWriter out = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(file)))) {
            for (int i = 0; i < content.length(); i += 1) {
                out.write(content.charAt(i));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

