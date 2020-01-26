package ua.edu.sumdu.j2se.chornobai.tasks.controller;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.chornobai.tasks.model.ArrayTaskList;
import ua.edu.sumdu.j2se.chornobai.tasks.model.Task;

import java.time.LocalDateTime;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AddTaskController {
    final static Logger logger = Logger.getLogger(AddTaskController.class);
    public static void addTask(ArrayTaskList taskList) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter title:");
        String title = scanner.nextLine();
        boolean isRepeated = enterIsRepeated();
        if (isRepeated) {
            System.out.println("Enter start time:");
            LocalDateTime start = EnterLocalDateTimeController.getLocalDateTime();
            //System.out.println("Enter end time:");
            //LocalDateTime end = EnterLocalDateTimeController.getLocalDateTime();
            LocalDateTime end = checkEndTime(start);

            int interval = enterInterval();
            Task newTask = new Task(title, start, end, interval);
            taskList.add(newTask);
            logger.info("Add new task - " + title);
        }
        else {
            System.out.println("Enter task execution time:");
            LocalDateTime time = EnterLocalDateTimeController.getLocalDateTime();
            Task newTask = new Task(title, time);
            taskList.add(newTask);
            logger.info("Add new task - " + title);
        }

    }

    public static boolean enterIsRepeated() {
        Scanner scanner = new Scanner(System.in);
        boolean isRepeated = false;
        System.out.println("Is task repeated (true/false)?");
        try {
            isRepeated = scanner.nextBoolean();
        }
        catch (InputMismatchException e) {
            System.out.println("Error: enter 'true' or 'false'");
            enterIsRepeated();
        }
        return isRepeated;
    }

    public static LocalDateTime checkEndTime(LocalDateTime startTime) {
        System.out.println("Enter end time:");
        LocalDateTime endTime = EnterLocalDateTimeController.getLocalDateTime();
        if (endTime.isBefore(startTime) || endTime.equals(startTime)){
            System.out.println("End time must be greater than start time");
            return checkEndTime(startTime);
        }
        return endTime;
    }

    public static int enterInterval() {
        Scanner scanner = new Scanner(System.in);
        int interval = 0;
        System.out.println("Enter repeated interval:");
        try {
            interval  = scanner.nextInt();
            if (interval < 1){
                System.out.println("Interval must be greater than 0");
                return enterInterval();
            }
        }
        catch (InputMismatchException e) {
            System.out.println("Error: not an integer");
            return enterInterval();
        }
        return interval;
    }
}
