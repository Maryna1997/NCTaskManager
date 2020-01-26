package ua.edu.sumdu.j2se.chornobai.tasks.controller;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.chornobai.tasks.model.ArrayTaskList;
import ua.edu.sumdu.j2se.chornobai.tasks.model.Task;
import ua.edu.sumdu.j2se.chornobai.tasks.view.MenuView;
import ua.edu.sumdu.j2se.chornobai.tasks.view.PrintTasksView;

import java.time.LocalDateTime;
import java.util.Scanner;

public class EditTaskController {
    final static Logger logger = Logger.getLogger(EditTaskController.class);
    public static void chooseEditTask(ArrayTaskList taskList) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n" + "Enter the number of task you want to edit:");
        PrintTasksView.printListOfTasks(taskList);
        int index = scanner.nextInt();
        Task task = taskList.getTask(index - 1);
        PrintTasksView.printInfoAboutTask(task);
        editTask(task);
    }

    public static void editTask(Task task) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n" + "-------------What do you want to edit?-------------");
        System.out.println("1 - Title");
        System.out.println("2 - Active");
        System.out.println("3 - Time");
        System.out.println("4 - Start time, end time, interval");
        System.out.println("0 - Return");
        int number = scanner.nextInt();
        switch (number) {
            case 1:
                String oldTitle = task.getTitle();
                System.out.println("Enter new title:");
                task.setTitle(scanner.nextLine());
                logger.info("Change title for task " + oldTitle);
                break;
            case 2:
                task.setActive(!task.isActive());
                logger.info("Change active status for task " + task.getTitle());
                break;
            case 3:
                System.out.println("Enter new time:");
                task.setTime(EnterLocalDateTimeController.getLocalDateTime());
                logger.info("Change time for task " + task.getTitle());
                break;
            case 4:
                System.out.println("Enter new start time:");
                LocalDateTime startTime = EnterLocalDateTimeController.getLocalDateTime();
                LocalDateTime endTime = AddTaskController.checkEndTime(startTime);
                //System.out.println("Enter new end time:");
                //LocalDateTime endTime = EnterLocalDateTimeController.getLocalDateTime();
                //System.out.println("Enter new repeated interval:");
                int interval = AddTaskController.enterInterval();
                task.setTime(startTime, endTime, interval);
                logger.info("Change start, end time and interval for task " + task.getTitle());
                break;
            case 0:
                MenuView.printMenu();
                break;
            default:
                editTask(task);
                break;
        }

    }
}
