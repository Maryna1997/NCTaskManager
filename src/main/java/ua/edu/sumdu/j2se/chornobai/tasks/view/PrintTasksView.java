package ua.edu.sumdu.j2se.chornobai.tasks.view;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.chornobai.tasks.model.ArrayTaskList;
import ua.edu.sumdu.j2se.chornobai.tasks.model.Task;

public class PrintTasksView {
    final static Logger logger = Logger.getLogger(PrintTasksView.class);
    public static void printListOfTasks(ArrayTaskList taskList){
        if (taskList.size() > 0){
            System.out.println( "-------------Your tasks-------------");
            for (int i = 1; i <= taskList.size(); i++){
                System.out.println(i + ". " + taskList.getTask(i-1).getTitle());
            }
            logger.info("Print list of tasks");
        }
    }

    public static void printInfoAboutTask (Task task){
        System.out.println("Title: " + task.getTitle());
        System.out.println("Repeated: " + task.isRepeated());
        System.out.println("Active: " + task.isActive());
        if (task.isRepeated()) {
            System.out.println("Start time: " + task.getStartTime());
            System.out.println("End time: " + task.getEndTime());
            System.out.println("Interval:" + task.getRepeatInterval());
        }
        else {
            System.out.println("Time: " + task.getTime());
        }
        logger.info("Print info about task " + task.getTitle());
    }
}
