package ua.edu.sumdu.j2se.chornobai.tasks.model;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.google.gson.Gson;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;


public class TaskIO {
    final static Logger logger = Logger.getLogger(TaskIO.class);

    public static void write(AbstractTaskList tasks, OutputStream out) throws IOException {
       try (DataOutputStream dataOutputStream = new DataOutputStream(out)){
           dataOutputStream.writeInt(tasks.size());
           tasks.getStream().forEach(task -> {
               try {
                   dataOutputStream.writeInt(task.getTitle().length());
                   for (int i = 0; i < task.getTitle().length(); i++){
                       dataOutputStream.writeChar(task.getTitle().charAt(i));
                   }
                   dataOutputStream.writeBoolean(task.isActive());
                   dataOutputStream.writeInt(task.getRepeatInterval());
                   if (task.isRepeated()) {
                       String ldtStart = task.getStartTime().format(DateTimeFormatter.ISO_DATE_TIME);
                       dataOutputStream.writeInt(ldtStart.getBytes().length);
                       dataOutputStream.writeBytes(ldtStart);
                       String ldtEnd = task.getEndTime().format(DateTimeFormatter.ISO_DATE_TIME);
                       dataOutputStream.writeInt(ldtEnd.getBytes().length);
                       dataOutputStream.writeBytes(ldtEnd);
                   }
                   else {
                       String ldtTime = task.getTime().format(DateTimeFormatter.ISO_DATE_TIME);
                       dataOutputStream.writeInt(ldtTime.getBytes().length);
                       dataOutputStream.writeBytes(ldtTime);
                   }
               } catch (IOException e) {
                   logger.log(Level.FATAL, "Exception: ", e);
                   e.printStackTrace();
               }
                   }
           );
       }
    }

    public static void read(AbstractTaskList tasks, InputStream in) throws IOException{
        try (DataInputStream dataInputStream = new DataInputStream(in)) {
            int countOfTasks = dataInputStream.readInt();
            for (int i = 0; i < countOfTasks; i++) {
                int titleLength = dataInputStream.readInt();
                StringBuilder title = new StringBuilder();
                for (int j = 0; j < titleLength; j++) {
                    title.append(dataInputStream.readChar());
                }
                boolean isActive = dataInputStream.readBoolean();
                int repeatInterval = dataInputStream.readInt();
                if (repeatInterval != 0) {
                    LocalDateTime start = LocalDateTime.parse(new String(dataInputStream.readNBytes(dataInputStream.readInt())), DateTimeFormatter.ISO_DATE_TIME);
                    LocalDateTime end = LocalDateTime.parse(new String(dataInputStream.readNBytes(dataInputStream.readInt())), DateTimeFormatter.ISO_DATE_TIME);
                    Task task = new Task(title.toString(), start, end, repeatInterval, isActive);
                    task.setActive(isActive);
                    tasks.add(task);
                }
                else {
                    LocalDateTime time = LocalDateTime.parse(new String(dataInputStream.readNBytes(dataInputStream.readInt())), DateTimeFormatter.ISO_DATE_TIME);
                    Task task = new Task(title.toString(), time, isActive);
                    task.setActive(isActive);
                    tasks.add(task);
                }
            }
        }
    }

    public static void writeBinary(AbstractTaskList tasks, File file) throws IOException{
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        write(tasks,fileOutputStream);
        fileOutputStream.close();
    }

    public static void readBinary(AbstractTaskList tasks, File file) throws IOException{
        FileInputStream fileInputStream = new FileInputStream(file);
        read(tasks,fileInputStream);
        fileInputStream.close();
    }


    public static void write(AbstractTaskList tasks, Writer out) throws IOException {
        Gson gson = new Gson();
        String line = gson.toJson(tasks);
        out.write(line);
        out.flush();
    }

    public static void read(AbstractTaskList tasks, Reader in) throws IOException {
        BufferedReader reader = new BufferedReader(in);
        String line = reader.readLine();
        AbstractTaskList list = new Gson().fromJson(line, tasks.getClass());
        for(Task task: list) {
            tasks.add(task);
        }
    }

    public static void writeText(AbstractTaskList tasks, File file) {
        Gson gson = new Gson();
        String line = gson.toJson(tasks);
        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write(line);
            fileWriter.flush();
        } catch (IOException e) {
            logger.log(Level.FATAL, "Exception: ", e);
            e.printStackTrace();
        }
    }

    public static void readText(AbstractTaskList tasks, File file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();
            AbstractTaskList list = new Gson().fromJson(line, tasks.getClass());
            for (Task task : list) {
                tasks.add(task);
            }
        } catch (IOException e) {
            logger.log(Level.FATAL, "Exception: ", e);
            e.printStackTrace();
        }
    }
}
