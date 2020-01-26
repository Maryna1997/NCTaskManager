package ua.edu.sumdu.j2se.chornobai.tasks.controller;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.chornobai.tasks.model.ArrayTaskList;
import ua.edu.sumdu.j2se.chornobai.tasks.model.Task;
import ua.edu.sumdu.j2se.chornobai.tasks.model.Tasks;

import java.time.LocalDateTime;


public class UserNotificationsController extends Thread {
    private ArrayTaskList taskList;
    final static Logger logger = Logger.getLogger(UserNotificationsController.class);
    public UserNotificationsController(ArrayTaskList taskList) {
        this.taskList = taskList;
    }

    @Override
    public void run() {
        logger.info("Run thread");
        Iterable<Task> result;
        while (true) {
            result = Tasks.incoming(taskList, LocalDateTime.now(), LocalDateTime.now().plusMinutes(1));
            for (Task task: result
                 ) {
                System.out.println("\n" + "NOTIFICATION: " + task.getTitle() );
                logger.info("New notification: " + task.getTitle());
            }
            try {
                sleep(60000);
            } catch (InterruptedException e) {
                logger.error("Interrupted Exception");
                currentThread().interrupt();
            }
        }
    }
}
