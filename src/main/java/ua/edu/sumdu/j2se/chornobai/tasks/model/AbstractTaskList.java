package ua.edu.sumdu.j2se.chornobai.tasks.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.stream.Stream;

public abstract class AbstractTaskList implements Iterable<Task>, Cloneable, Serializable {
    abstract public void add(Task task);
    abstract public boolean remove(Task task);
    abstract public int size();
    abstract public Task getTask(int index);
    abstract public Stream<Task> getStream();

    abstract public AbstractTaskList getInstance();

    public final AbstractTaskList incoming(LocalDateTime from, LocalDateTime to){
//        if(from < 0) {
//            throw new IllegalArgumentException("The time from should be above 0!");
//        }
//        if(to <= 0) {
//            throw new IllegalArgumentException("The time to should be above 0!");
//        }
//        if(to <= from) {
//            throw new IllegalArgumentException("The time to should be above the time from!");
//        }
        AbstractTaskList arrayOfScheduledTasks = getInstance();
//        for (int i = 0; i < size(); i++) {
////            if (getTask(i).nextTimeAfter(from) > from && getTask(i).nextTimeAfter(from) <= to && getTask(i).isActive()) {
////                arrayOfScheduledTasks.add(getTask(i));
////            }
////        }
        this.getStream().filter(task -> task.nextTimeAfter(from).isAfter(from) && task.nextTimeAfter(from).isBefore(to)|| task.nextTimeAfter(from).equals(to) || task.isActive()).forEach(arrayOfScheduledTasks::add);
        return arrayOfScheduledTasks;
    }
}
