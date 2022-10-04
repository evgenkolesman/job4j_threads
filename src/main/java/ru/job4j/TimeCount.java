package ru.job4j;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class TimeCount {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Введите данные");
//        Thread.sleep(4000);
        LocalTime res = null;
        ArrayList<String> timeContainer = new ArrayList<>();
        Scanner in = new Scanner(System.in);
        in = new Scanner(System.in);
//
//        String s = in.nextLine();
//
//        String[] s1 = s.split(" ", 10);
//        int resHours = 0;
//        int resMinutes = 0;
//        if (s1.length != 0 && s1.length % 2 == 0) {
//            for (int i = 0; i < s1.length - 2; i++) {
//
//                if (i == 0 || i % 2 != 0) {
//                    LocalTime res1 = countDayTime(s1[i], s1[i + 1]);
//                    resHours += res1.getHour();
//                    resMinutes += res1.getMinute();
//                }
//            }
        System.out.println(countDayTime("10:24", "18:57"));


//            resHours += resMinutes / 60;
//            resMinutes = resMinutes % 60;

//            System.out.println(" Время отработано " + (resHours) + " часов " + (resMinutes) + " минут");

        System.out.println(LocalTime.parse("08:47").plusHours(4L));
        }




    private static LocalTime countDayTime(String dayStart, String dayEnd) {
        LocalTime parse = LocalTime.parse(dayStart);
        return LocalTime.parse(dayEnd).minusHours(parse.getHour()).minusMinutes(parse.getMinute()).minusHours(8).minusMinutes(30);
    }


}
