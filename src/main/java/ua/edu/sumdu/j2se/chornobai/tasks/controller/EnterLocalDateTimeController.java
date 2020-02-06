package ua.edu.sumdu.j2se.chornobai.tasks.controller;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.chornobai.tasks.view.EnterLocalDateTimeView;

import java.time.LocalDateTime;
import java.util.Calendar;

public class EnterLocalDateTimeController {
    private EnterLocalDateTimeView enterLocalDateTimeView;

    public EnterLocalDateTimeController(EnterLocalDateTimeView enterLocalDateTimeView) {
        this.enterLocalDateTimeView = enterLocalDateTimeView;
    }

    final static Logger logger = Logger.getLogger(EditTaskController.class);
    public LocalDateTime getLocalDateTime(){
        int year = enterLocalDateTimeView.enterYear();
        int month = enterLocalDateTimeView.enterMonth();
        Calendar myCalendar = (Calendar) Calendar.getInstance().clone();
        myCalendar.set(year, month - 1, 1);
        int max_date = myCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        int day = enterLocalDateTimeView.enterDay(max_date);
        int hours = enterLocalDateTimeView.enterHours();
        int minutes = enterLocalDateTimeView.enterMinutes();
        LocalDateTime date = LocalDateTime.of(year, month, day, hours, minutes);
        return date;
    }
}
