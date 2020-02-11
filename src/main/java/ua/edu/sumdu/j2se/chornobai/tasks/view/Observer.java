package ua.edu.sumdu.j2se.chornobai.tasks.view;

import ua.edu.sumdu.j2se.chornobai.tasks.model.ArrayTaskList;

public interface Observer {
    void update(ArrayTaskList taskList);
}
