package ua.edu.sumdu.j2se.chornobai.tasks.controller;

import ua.edu.sumdu.j2se.chornobai.tasks.model.ArrayTaskList;
import ua.edu.sumdu.j2se.chornobai.tasks.view.Observer;

public interface Observable {
    void registerObserver(Observer o);
    void notifyObservers(ArrayTaskList taskList);
}
