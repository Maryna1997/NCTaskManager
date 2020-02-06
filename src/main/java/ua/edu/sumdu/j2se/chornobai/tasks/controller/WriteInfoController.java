package ua.edu.sumdu.j2se.chornobai.tasks.controller;

import ua.edu.sumdu.j2se.chornobai.tasks.model.ArrayTaskList;
import ua.edu.sumdu.j2se.chornobai.tasks.model.TaskIO;
import java.io.*;
import org.apache.log4j.Logger;

public class WriteInfoController {
    final static Logger logger = Logger.getLogger(WriteInfoController.class);
    public void writeInfo(ArrayTaskList taskList) throws IOException {
        File file = new File(".\\src\\main\\files\\myTasks.bin");
        if (file.exists()){
            file.delete();
        }
        File newFile = new File(".\\src\\main\\files\\myTasks.bin");
        newFile.createNewFile();
        try(ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(newFile));) {
            TaskIO.write(taskList, objectOutputStream);
            logger.info("Write info on file");
        }
    }
}
