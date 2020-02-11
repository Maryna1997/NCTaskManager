package ua.edu.sumdu.j2se.chornobai.tasks.controller;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.chornobai.tasks.model.ArrayTaskList;
import ua.edu.sumdu.j2se.chornobai.tasks.model.Task;
import ua.edu.sumdu.j2se.chornobai.tasks.model.TimeTypes;
import ua.edu.sumdu.j2se.chornobai.tasks.view.AddEditTaskView;
import ua.edu.sumdu.j2se.chornobai.tasks.view.Observer;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class AddTaskController implements Observable{
    private List<Observer> observers;
    private EnterLocalDateTimeController enterLocalDateTimeController;
    private AddEditTaskView addEditTaskView;

    public AddTaskController(EnterLocalDateTimeController enterLocalDateTimeController, AddEditTaskView addEditTaskView) {
        observers = new LinkedList<>();
        this.enterLocalDateTimeController = enterLocalDateTimeController;
        this.addEditTaskView = addEditTaskView;
    }

    final static Logger logger = Logger.getLogger(AddTaskController.class);

    public void addTask(ArrayTaskList taskList) {

        String title = addEditTaskView.enterTitle();
        boolean isActive = addEditTaskView.enterIsActive();
        boolean isRepeated = addEditTaskView.enterIsRepeated();
        if (isRepeated) {
            addEditTaskView.printMessageAboutTime(TimeTypes.types.START);
            LocalDateTime start = enterLocalDateTimeController.getLocalDateTime();
            LocalDateTime end = checkEndTime(start);

            int interval = addEditTaskView.enterInterval();
            Task newTask = new Task(title, start, end, interval, isActive);
            taskList.add(newTask);
            notifyObservers(taskList);
            logger.info("Add new task - " + title);
        }
        else {
            addEditTaskView.printMessageAboutTime(TimeTypes.types.EXECUTION);
            LocalDateTime time = enterLocalDateTimeController.getLocalDateTime();
            Task newTask = new Task(title, time, isActive);
            taskList.add(newTask);
            notifyObservers(taskList);
            logger.info("Add new task - " + title);
        }
    }

    public LocalDateTime checkEndTime(LocalDateTime startTime) {
        addEditTaskView.printMessageAboutTime(TimeTypes.types.END);
        LocalDateTime endTime = enterLocalDateTimeController.getLocalDateTime();
        if (endTime.isBefore(startTime) || endTime.equals(startTime)){
            addEditTaskView.printEqualsTimesError();
            return checkEndTime(startTime);
        }
        return endTime;
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
