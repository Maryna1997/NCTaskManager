package ua.edu.sumdu.j2se.chornobai.tasks.view;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.util.InputMismatchException;
import java.util.Scanner;

public class EnterLocalDateTimeView {

    final static Logger logger = Logger.getLogger(AddEditTaskView.class);

    public int enterYear() {
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
            logger.log(Level.FATAL, "Exception: ", e);
            return enterYear();
        }
        return year;
    }

    public int enterMonth() {
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
            logger.log(Level.FATAL, "Exception: ", e);
            return enterMonth();
        }
        return month;
    }

    public int enterDay(int max_day) {
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
            logger.log(Level.FATAL, "Exception: ", e);
            return enterDay(max_day);
        }
        return day;
    }

    public int enterHours() {
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
            logger.log(Level.FATAL, "Exception: ", e);
            return enterHours();
        }
        return hours;
    }

    public int enterMinutes() {
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
            logger.log(Level.FATAL, "Exception: ", e);
            return enterMinutes();
        }
        return minutes;
    }
}
