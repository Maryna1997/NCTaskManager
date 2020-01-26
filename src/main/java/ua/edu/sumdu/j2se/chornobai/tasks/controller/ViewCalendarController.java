package ua.edu.sumdu.j2se.chornobai.tasks.controller;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.chornobai.tasks.model.ArrayTaskList;
import ua.edu.sumdu.j2se.chornobai.tasks.model.Task;
import ua.edu.sumdu.j2se.chornobai.tasks.model.Tasks;
import ua.edu.sumdu.j2se.chornobai.tasks.view.PrintTasksView;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

public class ViewCalendarController {
    final static Logger logger = Logger.getLogger(ViewCalendarController.class);
    public static void viewCalendar(ArrayTaskList taskList) {
        System.out.println("Enter start date:");
        LocalDateTime startDate = EnterLocalDateTimeController.getLocalDateTime();
        LocalDateTime endDate = AddTaskController.checkEndTime(startDate);
        //System.out.println("Enter end date:");
        //LocalDateTime endDate = EnterLocalDateTimeController.getLocalDateTime();

        SortedMap<LocalDateTime, Set<Task>> result = Tasks.calendar(taskList, startDate, endDate);
        for (Map.Entry<LocalDateTime, Set<Task>> element: result.entrySet()
             ) {
            LocalDateTime key = element.getKey();
            Set<Task> value = element.getValue();
            System.out.println("\n" + "-------------Date: " + key.toString() + "-------------");
            int i = 1;
            for (Task task: value
            ) {
                System.out.println("\n" + "************ Task #" + i++ + "************");
                //PrintTasksView.printInfoAboutTask(task);
                System.out.println(task.getTitle());
                logger.info("Print calendar");
            }
        }
    }
}
