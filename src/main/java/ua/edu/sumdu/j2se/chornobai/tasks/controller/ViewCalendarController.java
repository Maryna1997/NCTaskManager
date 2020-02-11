package ua.edu.sumdu.j2se.chornobai.tasks.controller;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.chornobai.tasks.model.ArrayTaskList;
import ua.edu.sumdu.j2se.chornobai.tasks.model.Task;
import ua.edu.sumdu.j2se.chornobai.tasks.model.Tasks;
import ua.edu.sumdu.j2se.chornobai.tasks.model.TimeTypes;
import ua.edu.sumdu.j2se.chornobai.tasks.view.AddEditTaskView;
import ua.edu.sumdu.j2se.chornobai.tasks.view.CalendarView;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.SortedMap;

public class ViewCalendarController {
    private AddTaskController addTaskController;
    private EnterLocalDateTimeController enterLocalDateTimeController;
    private AddEditTaskView addEditTaskView;
    private CalendarView calendarView;

    public ViewCalendarController(EnterLocalDateTimeController enterLocalDateTimeController, AddTaskController addTaskController,
                                  AddEditTaskView addEditTaskView, CalendarView calendarView) {
        this.enterLocalDateTimeController = enterLocalDateTimeController;
        this.addTaskController = addTaskController;
        this.addEditTaskView = addEditTaskView;
        this.calendarView = calendarView;
    }

    public void viewCalendar(ArrayTaskList taskList) {
        addEditTaskView.printMessageAboutTime(TimeTypes.types.START);
        LocalDateTime startDate = enterLocalDateTimeController.getLocalDateTime();
        LocalDateTime endDate = addTaskController.checkEndTime(startDate);
        SortedMap<LocalDateTime, Set<Task>> result = Tasks.calendar(taskList, startDate, endDate);
        calendarView.viewCalendar(result);
    }
}
