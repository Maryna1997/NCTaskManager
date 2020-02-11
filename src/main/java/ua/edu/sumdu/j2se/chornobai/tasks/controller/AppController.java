package ua.edu.sumdu.j2se.chornobai.tasks.controller;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.chornobai.tasks.model.ArrayTaskList;
import ua.edu.sumdu.j2se.chornobai.tasks.view.MenuView;
import ua.edu.sumdu.j2se.chornobai.tasks.view.PrintTasksView;

import java.io.IOException;

public class AppController{
    private AddTaskController addTaskController;
    private EditTaskController editTaskController;
    private ViewCalendarController viewCalendarController;
    private DeleteTaskController deleteTaskController;
    private WriteInfoController writeInfoController;
    private MenuView menuView;
    private PrintTasksView printTasksView;

    final static Logger logger = Logger.getLogger(AppController.class);

    public AppController(AddTaskController addTaskController, EditTaskController editTaskController,
                         ViewCalendarController viewCalendarController, DeleteTaskController deleteTaskController,
                         WriteInfoController writeInfoController, MenuView menuView, PrintTasksView printTasksView) {
        this.addTaskController = addTaskController;
        this.editTaskController = editTaskController;
        this.viewCalendarController = viewCalendarController;
        this.deleteTaskController = deleteTaskController;
        this.writeInfoController = writeInfoController;
        this.menuView = menuView;
        this.printTasksView = printTasksView;
    }

    public void showMenu(ArrayTaskList taskList) throws IOException {

        int number = menuView.printMenu();

        switch (number) {
            case 1:
                logger.info("Choose add task");
                addTaskController.addTask(taskList);
                showMenu(taskList);
                break;
            case 2:
                logger.info("Choose edit task");
                editTaskController.chooseEditTask(taskList);
                showMenu(taskList);
                break;
            case 3:
                logger.info("Choose delete task");
                deleteTaskController.deleteTask(taskList);
                showMenu(taskList);
                break;
            case 4:
                logger.info("Choose print info about task");
                printTasksView.viewInfoAboutTasks(taskList);
                showMenu(taskList);
                break;
            case 5:
                logger.info("Choose print calendar");
                viewCalendarController.viewCalendar(taskList);
                showMenu(taskList);
                break;
            case 6:
                writeInfoController.writeInfo(taskList);
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
