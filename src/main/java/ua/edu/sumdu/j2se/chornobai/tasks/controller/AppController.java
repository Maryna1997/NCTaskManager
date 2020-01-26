package ua.edu.sumdu.j2se.chornobai.tasks.controller;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.chornobai.tasks.model.ArrayTaskList;
import ua.edu.sumdu.j2se.chornobai.tasks.model.Task;
import ua.edu.sumdu.j2se.chornobai.tasks.view.MenuView;
import ua.edu.sumdu.j2se.chornobai.tasks.view.PrintTasksView;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AppController {
    final static Logger logger = Logger.getLogger(AppController.class);
    public static void showMenu(ArrayTaskList taskList) throws IOException {
        logger.info("Open main menu");
        Scanner scanner = new Scanner(System.in);
        int number = 0;

        MenuView.printMenu();
        try {
            number = scanner.nextInt();
        }
        catch (InputMismatchException e){
            System.out.println("Error: not an integer");
        }

        switch (number) {
            case 1:
                logger.info("Choose add task");
                AddTaskController.addTask(taskList);
                showMenu(taskList);
                break;
            case 2:
                logger.info("Choose edit task");
                EditTaskController.chooseEditTask(taskList);
                showMenu(taskList);
                break;
            case 3:
                logger.info("Choose delete task");
                DeleteTaskController.deleteTask(taskList);
                showMenu(taskList);
                break;
            case 4:
                logger.info("Choose print info about task");
                if (taskList.size() > 0) {
                    int i = 1;
                    System.out.println("\n" + "-------------List of tasks:-------------");
                    for (Task task : taskList
                    ) {
                        System.out.println("\n" + "Task #" + i++);
                        PrintTasksView.printInfoAboutTask(task);
                    }
                }
                else System.out.println("Your task list is empty!");
                showMenu(taskList);
                break;
            case 5:
                logger.info("Choose print calendar");
                ViewCalendarController.viewCalendar(taskList);
                showMenu(taskList);
                break;
            case 6:
                WriteInfoController.writeInfo(taskList);
                logger.info("Exit");
                System.exit(0);
                break;
            default:
                logger.info("Wrong choice");
                showMenu(taskList);
                break;
        }

    }
}
