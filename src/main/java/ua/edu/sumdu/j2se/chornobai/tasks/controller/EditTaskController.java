package ua.edu.sumdu.j2se.chornobai.tasks.controller;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.chornobai.tasks.model.ArrayTaskList;
import ua.edu.sumdu.j2se.chornobai.tasks.model.Task;
import ua.edu.sumdu.j2se.chornobai.tasks.model.TimeTypes;
import ua.edu.sumdu.j2se.chornobai.tasks.view.AddEditTaskView;
import ua.edu.sumdu.j2se.chornobai.tasks.view.MenuView;
import ua.edu.sumdu.j2se.chornobai.tasks.view.PrintTasksView;

import java.time.LocalDateTime;

public class EditTaskController {
    private AddTaskController addTaskController;
    private EnterLocalDateTimeController enterLocalDateTimeController;
    private AddEditTaskView addEditTaskView;
    private MenuView menuView;
    private PrintTasksView printTasksView;

    public EditTaskController(AddTaskController addTaskController, AddEditTaskView addEditTaskView,
                              PrintTasksView printTasksView, EnterLocalDateTimeController enterLocalDateTimeController,
                              MenuView menuView) {
        this.addTaskController = addTaskController;
        this.addEditTaskView = addEditTaskView;
        this.printTasksView = printTasksView;
        this.enterLocalDateTimeController = enterLocalDateTimeController;
        this.menuView = menuView;
    }

    final static Logger logger = Logger.getLogger(EditTaskController.class);
    public void chooseEditTask(ArrayTaskList taskList) {
        int index = addEditTaskView.enterIndex(taskList, "edit");
        if (index != 0) {
            Task task = taskList.getTask(index - 1);
            printTasksView.printInfoAboutTask(task);
            editTask(task);
        }
    }

    public void editTask(Task task) {
        int number = addEditTaskView.printEditTaskMenu();
        switch (number) {
            case 1:
                String oldTitle = task.getTitle();
                String newTitle = addEditTaskView.enterTitle();
                task.setTitle(newTitle);
                logger.info("Change title for task " + oldTitle);
                break;
            case 2:
                task.setActive(!task.isActive());
                logger.info("Change active status for task " + task.getTitle());
                break;
            case 3:
                addEditTaskView.printMessageAboutTime(TimeTypes.types.EXECUTION);
                task.setTime(enterLocalDateTimeController.getLocalDateTime());
                logger.info("Change time for task " + task.getTitle());
                break;
            case 4:
                addEditTaskView.printMessageAboutTime(TimeTypes.types.START);
                LocalDateTime startTime = enterLocalDateTimeController.getLocalDateTime();
                LocalDateTime endTime = addTaskController.checkEndTime(startTime);
                int interval = addEditTaskView.enterInterval();
                task.setTime(startTime, endTime, interval);
                logger.info("Change start, end time and interval for task " + task.getTitle());
                break;
            case 0:
                break;
            default:
                editTask(task);
                break;
        }

    }
}
