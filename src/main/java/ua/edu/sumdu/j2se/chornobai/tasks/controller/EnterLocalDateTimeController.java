package ua.edu.sumdu.j2se.chornobai.tasks.controller;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.InputMismatchException;
import java.util.Scanner;

public class EnterLocalDateTimeController {
    public static LocalDateTime getLocalDateTime(){
        System.out.println("(format: YYYY-MM-DD hh:mm)");
        Scanner scanner = new Scanner(System.in);
        int year = enterYear();
        int month = enterMonth();
        Calendar myCalendar = (Calendar) Calendar.getInstance().clone();
        myCalendar.set(year, month - 1, 1);
        int max_date = myCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        int day = enterDay(max_date);
        int hours = enterHours();
        int minutes = enterMinutes();
        LocalDateTime date = LocalDateTime.of(year, month, day, hours, minutes);
        return date;
    }

    public static int enterYear() {
        Scanner scanner = new Scanner(System.in);
        int year = 0;
        System.out.println("enter year(YYYY):");
        try {
            year = scanner.nextInt();
            if (year < 1000) {
                System.out.println("Please, use correct format (YYYY)");
                return enterYear();
            }
        }
        catch (InputMismatchException e) {
            System.out.println("Error: not an integer");
            return enterYear();
        }
       return year;
    }

    public static int enterMonth() {
        Scanner scanner = new Scanner(System.in);
        int month = 0;
        System.out.println("enter month(MM):");
        try {
            month = scanner.nextInt();
            if (month < 1 || month > 12) {
                System.out.println("Please, use correct format (MM)");
                return enterMonth();
            }
        }
        catch (InputMismatchException e) {
            System.out.println("Error: not an integer");
            return enterMonth();
        }
        return month;
    }

    public static int enterDay(int max_day) {
        Scanner scanner = new Scanner(System.in);
        int day = 0;
        System.out.println("enter day(DD):");
        try {
            day = scanner.nextInt();
            if (day < 1 || day > max_day) {
                System.out.println("Please, use correct format (DD)");
                return enterDay(max_day);
            }
        }
        catch (InputMismatchException e) {
            System.out.println("Error: not an integer");
            return enterDay(max_day);
        }
        return day;
    }

    public static int enterHours() {
        Scanner scanner = new Scanner(System.in);
        int hours = 0;
        System.out.println("enter hours(hh):");
        try {
            hours = scanner.nextInt();
            if (hours < 0 || hours > 23) {
                System.out.println("Please, use correct format (hh)");
                return enterHours();
            }
        }
        catch (InputMismatchException e) {
            System.out.println("Error: not an integer");
            return enterHours();
        }
       return hours;
    }

    public static int enterMinutes() {
        Scanner scanner = new Scanner(System.in);
        int minutes = 0;
        System.out.println("enter minutes(mm):");
        try {
            minutes = scanner.nextInt();
            if (minutes < 0 || minutes > 59) {
                System.out.println("Please, use correct format (mm)");
                return enterMinutes();
            }
        }
        catch (InputMismatchException e) {
            System.out.println("Error: not an integer");
            return enterMinutes();
        }
        return minutes;
    }
}
