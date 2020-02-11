package ua.edu.sumdu.j2se.chornobai.tasks.controller;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.chornobai.tasks.model.ArrayTaskList;
import ua.edu.sumdu.j2se.chornobai.tasks.model.Task;
import ua.edu.sumdu.j2se.chornobai.tasks.model.Tasks;
import ua.edu.sumdu.j2se.chornobai.tasks.view.UserNotificationsView;

import java.time.LocalDateTime;


public class UserNotificationsController extends Thread {
    private ArrayTaskList taskList;
    private UserNotificationsView userNotificationsView;

    public UserNotificationsController(ArrayTaskList taskList, UserNotificationsView userNotificationsView) {
        this.taskList = taskList;
        this.userNotificationsView = userNotificationsView;
    }

    final static Logger logger = Logger.getLogger(UserNotificationsController.class);

    @Override
    public void run() {
        logger.info("Run thread");
        Iterable<Task> result;
        while (true) {
            result = Tasks.incoming(taskList, LocalDateTime.now(), LocalDateTime.now().plusSeconds(1));
            for (Task task: result
                 ) {
                userNotificationsView.printNotification(task);
            }
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                logger.error("Interrupted Exception");
                logger.log(Level.FATAL, "Exception: ", e);
                currentThread().interrupt();
            }
        }
    }
}
