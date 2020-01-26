package ua.edu.sumdu.j2se.chornobai.tasks.model;

import java.util.*;
import java.util.stream.Stream;

public class ArrayTaskList extends AbstractTaskList {

    private Task [] tasks = new Task [7];
    private int countOfTasks;

    @Override
    public Stream<Task> getStream() {
        return new ArrayList<>(Arrays.asList(tasks).subList(0, countOfTasks)).stream();
    }

    @Override
    public AbstractTaskList getInstance() {
        return new ArrayTaskList();
    }

    public void add(Task task) {
        if(task == null) {
            throw new IllegalArgumentException("Task is empty!");
        }
        if(countOfTasks == tasks.length){
            Task [] tmp = new Task[tasks.length + 10];
            for(int i = 0; i < countOfTasks; i++){
                tmp[i] = tasks[i];
            }
            tasks = tmp;
        }
        tasks[countOfTasks] = task;
        countOfTasks++;
    }

    public boolean remove(Task task) {
        if(task == null) {
            throw new IllegalArgumentException("Task is empty!");
        }
        if(countOfTasks > 0) {
            for (int i = 0; i < countOfTasks; i++) {
                if (task.equals(tasks[i])) {
                    tasks[i] = tasks[countOfTasks-1];
                    tasks[countOfTasks-1] = null;
                    countOfTasks--;
                    return true;
                }
            }
        }
        return false;
    }

    public int size() {
        return countOfTasks;
    }

    public Task getTask(int index) {
        if (index >= 0 && index < countOfTasks) {
            return tasks[index];
        }
        else return null;
    }

    /*
    public ArrayTaskList incoming(int from, int to){
        if(from < 0) {
            throw new IllegalArgumentException("The time from should be above 0!");
        }
        if(to <= 0) {
            throw new IllegalArgumentException("The time to should be above 0!");
        }
        if(to <= from) {
            throw new IllegalArgumentException("The time to should be above the time from!");
        }
        ArrayTaskList arrayOfScheduledTasks = new ArrayTaskList();
        for (int i = 0; i < countOfTasks; i++) {
            if (tasks[i].nextTimeAfter(from) > from && tasks[i].nextTimeAfter(from) <= to && tasks[i].isActive()) {
                arrayOfScheduledTasks.add(tasks[i]);
            }
        }
        return arrayOfScheduledTasks;
    }
     */

    @Override
    public Iterator<Task> iterator(){
        return new ArrayTaskListIterator();
    }

    private class ArrayTaskListIterator implements Iterator<Task> {

        private boolean wasMoved;
        private int indexCurrentElement = -1;

        @Override
        public boolean hasNext() {
            return indexCurrentElement + 1 <= countOfTasks - 1;
        }

        @Override
        public Task next() {
            if(indexCurrentElement + 1 > countOfTasks - 1){
                throw new NoSuchElementException();
            }else{
                wasMoved = true;
                return tasks[++indexCurrentElement];
            }
        }

        @Override
        public void remove() {
            if(!wasMoved) {
                throw new IllegalStateException();
            }else{
                for(int i = indexCurrentElement; i <= countOfTasks - 2; i++){
                    tasks[i] = tasks[i + 1];
                }
                tasks[countOfTasks - 1] = null;
                wasMoved = false;
               countOfTasks--;
                indexCurrentElement--;
            }
        }
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ArrayTaskList)) return false;
        ArrayTaskList that = (ArrayTaskList) o;
        return countOfTasks == that.countOfTasks &&
                Arrays.equals(tasks, that.tasks);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(countOfTasks);
        result = 31 * result + Arrays.hashCode(tasks);
        return result;
    }

    @Override
    public ArrayTaskList clone() throws CloneNotSupportedException {
        ArrayTaskList clone = (ArrayTaskList) super.clone();
        clone.tasks = new Task[countOfTasks];
        for(int i = 0; i < countOfTasks; i++){
            clone.tasks[i] = tasks[i]/*.clone()*/;
        }
        return clone;
    }
}

