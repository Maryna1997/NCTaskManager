package ua.edu.sumdu.j2se.chornobai.tasks.controller;


import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.chornobai.tasks.model.ArrayTaskList;
import ua.edu.sumdu.j2se.chornobai.tasks.view.MenuView;
import ua.edu.sumdu.j2se.chornobai.tasks.view.PrintTasksView;

import java.util.InputMismatchException;
import java.util.Scanner;

public class DeleteTaskController {
    final static Logger logger = Logger.getLogger(DeleteTaskController.class);
    public static void deleteTask (ArrayTaskList taskList){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of task you want to delete:");
        PrintTasksView.printListOfTasks(taskList);
        System.out.println("0. Return");
        int index = enterIndex(taskList.size());
        if(index != 0) {
            boolean isRemove = taskList.remove(taskList.getTask(index - 1));
            if (isRemove) {
                System.out.println("\n" + "The task number " + index + " deleted!");
                logger.info("Deleted task #" + index);
            }
        }
    }

    public static int enterIndex(int size) {
        Scanner scanner = new Scanner(System.in);
        int index = -1;
        try {
            index  = scanner.nextInt();
            if (index < 0 || index > size){
                System.out.println("Task # " + index + " no exists!" + "\n" + "\n" + "Enter the number of task you want to delete:");
                return enterIndex(size);
            }
        }
        catch (InputMismatchException e) {
            System.out.println("Error: not an integer");
            return enterIndex(size);
        }
        return index;
    }
}
