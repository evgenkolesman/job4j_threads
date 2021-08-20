package ru.job4j.io;

/**Д
 * Класс необходим для считывания контента из файла
 *
 * @author Kolesnikov Evgeniy
 * @version 1.0
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Input {
    public synchronized int readContent(File file) {
        int a = 0;
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            a = in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return a;
    }

}
