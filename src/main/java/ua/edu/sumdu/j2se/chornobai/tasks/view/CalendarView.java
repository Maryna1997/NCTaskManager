package ua.edu.sumdu.j2se.chornobai.tasks.view;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.chornobai.tasks.model.Task;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

public class CalendarView {

    final static Logger logger = Logger.getLogger(CalendarView.class);

    public void viewCalendar(SortedMap<LocalDateTime, Set<Task>> result) {
        for (Map.Entry<LocalDateTime, Set<Task>> element: result.entrySet()
        ) {
            LocalDateTime key = element.getKey();
            Set<Task> value = element.getValue();
            System.out.println("\n" + "-------------Date: " + key.toString() + "-------------");
            int i = 1;
            for (Task task: value
            ) {
                System.out.println("\n" + "************ Task #" + i++ + "************");
                System.out.println(task.getTitle());
            }
            logger.info("Print calendar");
        }
    }
}
