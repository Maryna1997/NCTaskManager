package ua.edu.sumdu.j2se.chornobai.tasks.controller;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.chornobai.tasks.model.ArrayTaskList;
import ua.edu.sumdu.j2se.chornobai.tasks.view.AddEditTaskView;


public class DeleteTaskController {

    private AddEditTaskView addEditTaskView;

    public DeleteTaskController(AddEditTaskView addEditTaskView) {
        this.addEditTaskView = addEditTaskView;
    }

    final static Logger logger = Logger.getLogger(DeleteTaskController.class);
    public void deleteTask (ArrayTaskList taskList){
        int index = addEditTaskView.enterIndex(taskList, "delete");
        if(index != 0) {
            boolean isRemove = taskList.remove(taskList.getTask(index - 1));
            if (isRemove) {
                addEditTaskView.printMessageAboutDeletedTask(index);
            }
        }
    }
}
