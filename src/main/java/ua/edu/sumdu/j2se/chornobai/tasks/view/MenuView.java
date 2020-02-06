package ua.edu.sumdu.j2se.chornobai.tasks.view;

import java.util.InputMismatchException;
import java.util.Scanner;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class MenuView {

    final static Logger logger = Logger.getLogger(MenuView.class);

    public int printMenu() {
        logger.info("Open main menu");
        Scanner scanner = new Scanner(System.in);
        int number = 0;
        System.out.println("\n" + "-------------What do you want to do?--------------");
        System.out.println("1 - Add task");
        System.out.println("2 - Edit task");
        System.out.println("3 - Delete task");
        System.out.println("4 - View info about task");
        System.out.println("5 - View calendar");
        System.out.println("6 - Exit");
        try {
            number = scanner.nextInt();
        }
        catch (InputMismatchException e){
            System.out.println("Error: not an integer");
            logger.log(Level.FATAL, "Exception: ", e);
        }
        return number;
    }
}
