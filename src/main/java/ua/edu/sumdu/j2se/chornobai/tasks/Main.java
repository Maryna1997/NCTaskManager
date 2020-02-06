package ua.edu.sumdu.j2se.chornobai.tasks;

import org.apache.log4j.Level;
import ua.edu.sumdu.j2se.chornobai.tasks.controller.*;
import ua.edu.sumdu.j2se.chornobai.tasks.model.ArrayTaskList;
import ua.edu.sumdu.j2se.chornobai.tasks.model.TaskIO;
import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.chornobai.tasks.view.*;

import java.io.*;

public class Main {

	final static Logger logger = Logger.getLogger(Main.class);
	public static void main(String[] args) throws IOException {
		WriteInfoController writeInfoController = new WriteInfoController();
		CalendarView calendarView = new CalendarView();
		EnterLocalDateTimeView enterLocalDateTimeView = new EnterLocalDateTimeView();
		MenuView menuView = new MenuView();
		PrintTasksView printTasksView = new PrintTasksView();
		UserNotificationsView userNotificationsView  = new UserNotificationsView();

		AddEditTaskView addEditTaskView = new AddEditTaskView(printTasksView);
		DeleteTaskController deleteTaskController = new DeleteTaskController(addEditTaskView);
		EnterLocalDateTimeController enterLocalDateTimeController = new EnterLocalDateTimeController(enterLocalDateTimeView);
		AddTaskController addTaskController = new AddTaskController(enterLocalDateTimeController, addEditTaskView);
		EditTaskController editTaskController = new EditTaskController(addTaskController, addEditTaskView,
				                                                       printTasksView, enterLocalDateTimeController, menuView);
		ViewCalendarController viewCalendarController = new ViewCalendarController(enterLocalDateTimeController, addTaskController,
				                                                                   addEditTaskView, calendarView);

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
				logger.log(Level.FATAL, "Exception: ", e);
			}
		}

		UserNotificationsController thread = new UserNotificationsController(taskList, userNotificationsView);
		thread.setDaemon(true);
		thread.start();

		AppController appController = new AppController(addTaskController, editTaskController, viewCalendarController,
				                                        deleteTaskController, writeInfoController, menuView, printTasksView);
		appController.showMenu(taskList);
	}
}
