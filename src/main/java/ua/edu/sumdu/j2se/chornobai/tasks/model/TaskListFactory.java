package ua.edu.sumdu.j2se.chornobai.tasks.model;

public class TaskListFactory {
    public static AbstractTaskList createTaskList(ListTypes.types type){
        if (type == ListTypes.types.LINKED) {
            return new LinkedTaskList();
        }
        if(type == ListTypes.types.ARRAY){
            return new ArrayTaskList();
        }
        return null;
    }
}
