package ua.edu.sumdu.j2se.chornobai.tasks.view;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.chornobai.tasks.model.ArrayTaskList;
import ua.edu.sumdu.j2se.chornobai.tasks.model.TimeTypes;

import java.util.InputMismatchException;
import java.util.Scanner;

public class AddEditTaskView {
     private PrintTasksView printTasksView;

    public AddEditTaskView(PrintTasksView printTasksView) {
        this.printTasksView = printTasksView;
    }

    final static Logger logger = Logger.getLogger(AddEditTaskView.class);

    public String enterTitle() {
        System.out.println("Enter title:");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public boolean enterIsRepeated() {
        Scanner scanner = new Scanner(System.in);
        boolean isRepeated = false;
        System.out.println("Is task repeated (true/false)?");
        try {
            isRepeated = scanner.nextBoolean();
        }
        catch (InputMismatchException e) {
            System.out.println("Error: enter 'true' or 'false'");
            logger.log(Level.FATAL, "Exception: ", e);
            return enterIsRepeated();
        }
        return isRepeated;
    }

    public int enterInterval() {
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
            logger.log(Level.FATAL, "Exception: ", e);
            return enterInterval();
        }
        return interval;
    }

    public int enterIndex(ArrayTaskList taskList, String action) {
        Scanner scanner = new Scanner(System.in);
        int index = -1;
        System.out.println("\n" + "Enter the number of task you want to " + action + ":");

        printTasksView.printListOfTasks(taskList);
        System.out.println("0. Return");
        try {
            index =  scanner.nextInt();
            if (index < 0 || index > taskList.size()){
                System.out.println("Task # " + index + " no exists!" + "\n" + "\n" + "Enter again:");
                return enterIndex(taskList, action);
            }
        }
        catch (InputMismatchException e) {
            System.out.println("Error: not an integer");
            logger.log(Level.FATAL, "Exception: ", e);
            return enterIndex(taskList, action);
        }
       return index;
    }

    public int printEditTaskMenu () {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n" + "-------------What do you want to edit?-------------");
        System.out.println("1 - Title");
        System.out.println("2 - Active");
        System.out.println("3 - Time");
        System.out.println("4 - Start time, end time, interval");
        System.out.println("0 - Return");
        try {
            return scanner.nextInt();
        }
        catch (InputMismatchException e) {
            System.out.println("Error: not an integer");
            logger.log(Level.FATAL, "Exception: ", e);
            return printEditTaskMenu();
        }
    }

    public void printMessageAboutTime (TimeTypes.types timeType){
        switch (timeType) {
            case START:
                System.out.println("Enter start time:");
                break;
            case END:
                System.out.println("Enter end time:");
                break;
            case EXECUTION:
                System.out.println("Enter task execution time:");
                break;
        }
        System.out.println("(format: YYYY-MM-DD hh:mm)");
    }

    public void printEqualsTimesError() {
        System.out.println("End time must be greater than start time");
    }

    public void printMessageAboutDeletedTask(int index) {
        System.out.println("\n" + "The task number " + index + " deleted!");
        logger.info("Deleted task #" + index);
    }
}
