package ua.edu.sumdu.j2se.chornobai.tasks.controller;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.chornobai.tasks.model.ArrayTaskList;
import ua.edu.sumdu.j2se.chornobai.tasks.model.Task;
import ua.edu.sumdu.j2se.chornobai.tasks.model.TimeTypes;
import ua.edu.sumdu.j2se.chornobai.tasks.view.AddEditTaskView;
import ua.edu.sumdu.j2se.chornobai.tasks.view.MenuView;
import ua.edu.sumdu.j2se.chornobai.tasks.view.Observer;
import ua.edu.sumdu.j2se.chornobai.tasks.view.PrintTasksView;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class EditTaskController implements Observable {
    private List<Observer> observers;
    boolean isEdit = false;
    private AddTaskController addTaskController;
    private EnterLocalDateTimeController enterLocalDateTimeController;
    private AddEditTaskView addEditTaskView;
    private MenuView menuView;
    private PrintTasksView printTasksView;

    public EditTaskController(AddTaskController addTaskController, AddEditTaskView addEditTaskView,
                              PrintTasksView printTasksView, EnterLocalDateTimeController enterLocalDateTimeController,
                              MenuView menuView) {
        observers = new LinkedList<>();
        this.addTaskController = addTaskController;
        this.addEditTaskView = addEditTaskView;
        this.printTasksView = printTasksView;
        this.enterLocalDateTimeController = enterLocalDateTimeController;
        this.menuView = menuView;
    }

    final static Logger logger = Logger.getLogger(EditTaskController.class);
    public void chooseEditTask(ArrayTaskList taskList) {
        int index = addEditTaskView.enterIndex(taskList, "edit");
        isEdit = false;
        if (index != 0) {
            Task task = taskList.getTask(index - 1);
            printTasksView.printInfoAboutTask(task);
            editTask(task);
            if(isEdit) notifyObservers(taskList);
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
                isEdit = true;
                break;
            case 2:
                task.setActive(!task.isActive());
                logger.info("Change active status for task " + task.getTitle());
                isEdit = true;
                break;
            case 3:
                addEditTaskView.printMessageAboutTime(TimeTypes.types.EXECUTION);
                task.setTime(enterLocalDateTimeController.getLocalDateTime());
                logger.info("Change time for task " + task.getTitle());
                isEdit = true;
                break;
            case 4:
                addEditTaskView.printMessageAboutTime(TimeTypes.types.START);
                LocalDateTime startTime = enterLocalDateTimeController.getLocalDateTime();
                LocalDateTime endTime = addTaskController.checkEndTime(startTime);
                int interval = addEditTaskView.enterInterval();
                task.setTime(startTime, endTime, interval);
                logger.info("Change start, end time and interval for task " + task.getTitle());
                isEdit = true;
                break;
            case 0:
                isEdit = false;
                break;
            default:
                isEdit = false;
                editTask(task);
                break;
        }
    }

    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void notifyObservers(ArrayTaskList taskList) {
        for (Observer observer: observers
        ) {
            observer.update(taskList);
        }
    }
}
