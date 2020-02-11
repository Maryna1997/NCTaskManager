package ua.edu.sumdu.j2se.chornobai.tasks.controller;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.chornobai.tasks.model.ArrayTaskList;
import ua.edu.sumdu.j2se.chornobai.tasks.view.AddEditTaskView;
import ua.edu.sumdu.j2se.chornobai.tasks.view.Observer;

import java.util.LinkedList;
import java.util.List;


public class DeleteTaskController implements Observable {
    private List<Observer> observers;
    private AddEditTaskView addEditTaskView;

    public DeleteTaskController(AddEditTaskView addEditTaskView) {
        observers = new LinkedList<>();
        this.addEditTaskView = addEditTaskView;
    }

    public void deleteTask (ArrayTaskList taskList){
        int index = addEditTaskView.enterIndex(taskList, "delete");
        if(index != 0) {
            boolean isRemove = taskList.remove(taskList.getTask(index - 1));
            if (isRemove) {
                addEditTaskView.printMessageAboutDeletedTask(index);
                notifyObservers(taskList);
            }
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
