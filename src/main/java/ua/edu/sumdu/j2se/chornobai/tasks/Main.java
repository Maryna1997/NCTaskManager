package ua.edu.sumdu.j2se.chornobai.tasks;

import ua.edu.sumdu.j2se.chornobai.tasks.controller.AppController;
import ua.edu.sumdu.j2se.chornobai.tasks.controller.UserNotificationsController;
import ua.edu.sumdu.j2se.chornobai.tasks.model.ArrayTaskList;
import ua.edu.sumdu.j2se.chornobai.tasks.model.Task;
import ua.edu.sumdu.j2se.chornobai.tasks.model.TaskIO;
import org.apache.log4j.Logger;

import java.io.*;

public class Main {
	final static Logger logger = Logger.getLogger(Main.class);
	public static void main(String[] args) throws IOException {
		logger.info("Start");
		ArrayTaskList taskList = new ArrayTaskList();

		File dir = new File(".\\src\\main\\files");
		File file = new File(".\\src\\main\\files\\myTasks.bin");

		if (!dir.exists()){
			dir.mkdir();
			logger.info("Create directory" + dir.getAbsolutePath());
		}

		if (file.exists()) {
			try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file))){
				TaskIO.read(taskList, objectInputStream);
			} catch (IOException e) {
				System.out.println("Reading error!");;
			}
		}

		UserNotificationsController thread = new UserNotificationsController(taskList);
		thread.setDaemon(true);
		thread.start();

		AppController appController = new AppController();
		AppController.showMenu(taskList);
	}
}
