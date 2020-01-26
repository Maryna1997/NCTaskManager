package ua.edu.sumdu.j2se.chornobai.tasks.model;

import java.time.LocalDateTime;
import java.util.*;

public class Tasks {
    public static Iterable<Task> incoming(Iterable<Task> tasks, LocalDateTime start, LocalDateTime end) {
        if (start == null) {
            throw new IllegalArgumentException("From time must not be null");
        }
        if (end == null) {
            throw new IllegalArgumentException("To time must not be null");
        }
        if (start.isAfter(end)) {
            throw new IllegalArgumentException("Start time can not be less than end time");
        }
        if (tasks == null) {
            throw new IllegalArgumentException("Tasks must not be null");
        }
       LinkedTaskList arrayOfScheduledTasks = new LinkedTaskList();
        Iterator<Task> iter = tasks.iterator();
        while (iter.hasNext()){
            Task tmp = iter.next();
            LocalDateTime nextTime = tmp.nextTimeAfter(start);
            if(nextTime == null){
                continue;
            }
            if(nextTime.isAfter(start) && nextTime.isBefore(end) || nextTime.equals(end)){
                arrayOfScheduledTasks.add(tmp);
            }
        }
        //this.getStream().filter(task -> task.nextTimeAfter(from).isAfter(from) && task.nextTimeAfter(from).isBefore(to)|| task.nextTimeAfter(from).equals(to) || task.isActive()).forEach(arrayOfScheduledTasks::add);
        return arrayOfScheduledTasks;
    }

    public static SortedMap<LocalDateTime, Set<Task>> calendar(Iterable<Task> tasks, LocalDateTime start, LocalDateTime end) {
        if (start == null) {
            throw new IllegalArgumentException("From time must not be null");
        }
        if (end == null) {
            throw new IllegalArgumentException("To time must not be null");
        }
        if (start.isAfter(end)) {
            throw new IllegalArgumentException("Start time can not be less than end time");
        }
        if (tasks == null) {
            throw new IllegalArgumentException("Tasks must not be null");
        }
        SortedMap<LocalDateTime, Set<Task>> result = new TreeMap<LocalDateTime, Set<Task>>();
        Iterable<Task> arrayOfScheduledTasks = incoming(tasks, start, end);
        for (Task tmp: arrayOfScheduledTasks) {
            LocalDateTime happening = tmp.nextTimeAfter(start);
            while (happening != null && !happening.isAfter(end)){
                if (happening.isAfter(start) && !happening.isAfter(end)) {
                    if (result.containsKey(happening)) {
                        result.get(happening).add(tmp);
                    } else {
                        result.put(happening, new HashSet<Task>());
                        result.get(happening).add(tmp);
                    }
                }
                happening = tmp.nextTimeAfter(happening);
            }
        }
        return result;
    }
}
