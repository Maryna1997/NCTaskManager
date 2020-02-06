package ua.edu.sumdu.j2se.chornobai.tasks.view;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.chornobai.tasks.model.Task;

public class UserNotificationsView {

    final static Logger logger = Logger.getLogger(UserNotificationsView.class);

    public void printNotification(Task task) {
        System.out.println("\n" + "NOTIFICATION: " + task.getTitle() );
        logger.info("New notification: " + task.getTitle());
    }
}
