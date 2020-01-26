package ua.edu.sumdu.j2se.chornobai.tasks.model;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Objects;
import java.util.NoSuchElementException;
import java.util.stream.Stream;


public class LinkedTaskList extends AbstractTaskList {
    private Node first;
    private Node last;
    private int countOfTasks;

    @Override
    public AbstractTaskList getInstance() {
        return new LinkedTaskList();
    }

    @Override
    public Stream<Task> getStream() {
        LinkedList<Task> linkedList = new LinkedList<>();
        for (Task task : this) {
            linkedList.add(task);
        }
        return linkedList.stream();
    }

    public void add(Task task) {
        if(task == null) {
            throw new IllegalArgumentException("Task is empty!");
        }
        Node newNode = new Node(task);

        if(first == null) {
            first = newNode;
            last = newNode;
            countOfTasks = 1;
        }
        else {
            last.setNext(newNode);
            newNode.setPrevious(last);
            last = newNode;
            countOfTasks++;
        }
    }

    public boolean remove(Task task) {
        if(task == null) {
            throw new IllegalArgumentException("Task is empty!");
        }
        Node tempNode = first;
        while(tempNode != null && !tempNode.getTask().equals(task)){
            tempNode = tempNode.getNext();
        }
        if(tempNode != null && tempNode.getTask().equals(task)){
            if(tempNode.getPrevious() != null){
                tempNode.getPrevious().setNext(tempNode.getNext());
                if(last== tempNode){
                    last = tempNode.getPrevious();
                }
            }
            if(tempNode.getNext() != null){
                tempNode.getNext().setPrevious(tempNode.getPrevious());
                if(first == tempNode){
                    first = tempNode.getNext();
                }
            }
            if(tempNode.getNext() == null && tempNode.getPrevious() == null){
                first = null;
                last = null;
            }
            countOfTasks--;
            return true;
        }
        return false;
    }

    public int size() {
        return countOfTasks;
    }

    public Task getTask(int index){
        if (index < 0) {
            throw new IndexOutOfBoundsException("The negative index");
        }
        if(index <= countOfTasks - 1){
            Node tempNode = first;
            for(int i = 0; i < index; i++){
                tempNode= tempNode.getNext();
            }
            return tempNode.getTask();
        }
        else{
            return null;
        }
    }

    /*
    public LinkedTaskList incoming(int from, int to){
        if(from < 0) {
            throw new IllegalArgumentException("The time from should be above 0!");
        }
        if(to <= 0) {
            throw new IllegalArgumentException("The time to should be above 0!");
        }
        if(to <= from) {
            throw new IllegalArgumentException("The time to should be above the time from!");
        }
       LinkedTaskList arrayOfScheduledTasks = new LinkedTaskList();
        for (int i = 0; i < countOfTasks; i++) {
            if (getTask(i).nextTimeAfter(from) > from && getTask(i).nextTimeAfter(from) <= to && getTask(i).isActive()) {
                arrayOfScheduledTasks.add(getTask(i));
            }
        }
        return arrayOfScheduledTasks;
    }*/

    @Override
    public Iterator<Task> iterator(){
        return new LinkedTaskListIterator();
    }

    private class LinkedTaskListIterator implements Iterator<Task> {
        private Node temp = new Node();
        private boolean wasMoved;
        private int indexCurrentElement = -1;

        {
            temp.setNext(first);
        }

        @Override
        public Task next(){
            temp = temp.getNext();
            wasMoved = true;
            indexCurrentElement++;
            if(temp == null) {
                throw new NoSuchElementException();
            }else {
                return temp.getTask();
            }
        }

        @Override
        public boolean hasNext(){
            return (temp.getNext() != null);
        }

        @Override
        public void remove(){
            if(!wasMoved) {
                throw new IllegalStateException();
            }else {
                if(temp.getPrevious() == null){
                    first = temp.getNext();
                    temp.getNext().setPrevious(null);
                }else if(temp.getNext() == null){
                    last = temp.getPrevious();
                    temp.getPrevious().setNext(null);
                }else{
                    temp.getPrevious().setNext(temp.getNext());
                    temp.getNext().setPrevious(temp.getPrevious());
                }
                wasMoved = false;
                countOfTasks--;
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LinkedTaskList that = (LinkedTaskList) o;
        if (countOfTasks != that.countOfTasks) return false;
        Iterator<Task> thisIterator = iterator();
        Iterator<Task> thatIterator = that.iterator();
        while(thisIterator.hasNext()){
            if(!thisIterator.next().equals(thatIterator.next())){
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, last, countOfTasks);
    }

    @Override
    public LinkedTaskList clone() throws CloneNotSupportedException {
        LinkedTaskList clone = (LinkedTaskList) super.clone();
        Iterator<Task> iterator = iterator();
        clone.first = clone.last = null;
        clone.countOfTasks = 0;
        while(iterator.hasNext()){
            clone.add(iterator.next());
        }
        return clone;
    }

}
