package ua.edu.sumdu.j2se.chornobai.tasks.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class Task implements Cloneable, Serializable {

    private String title;
    private LocalDateTime time;
    private LocalDateTime start;
    private LocalDateTime end;
    private int interval;
    private boolean active;
    private boolean repeated;

    public Task(String title, LocalDateTime time) {
        if(time == null) {
            throw new IllegalArgumentException("The time should be above 0!");
        }
        this.title = title;
        this.time = time;
        this.active = false;
    }

    public Task(String title, LocalDateTime start, LocalDateTime end, int interval) {
        if(interval < 0) {
            throw new IllegalArgumentException("The interval should be above 0!");
        }
        this.title = title;
        this.start = start;
        this.end = end;
        this.interval = interval;
        this.active = false;
        this.repeated = true;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active1) {
        active = active1;
    }

    public boolean isRepeated() {
        return repeated;
    }

    public LocalDateTime getTime() {
        if (repeated) {
            return start;
        }
        else {
            return time;
        }
    }

    public void setTime(LocalDateTime time) {
        if (repeated) {
            repeated = false;
            this.time = time;
        }
        else this.time = time;
    }

    public LocalDateTime getStartTime() {
        if (repeated) {
            return start;
        }
        else {
            return time;
        }
    }

    public LocalDateTime getEndTime() {
        if (repeated) {
            return end;
        }
        else {
            return time;
        }
    }

    public int getRepeatInterval() {
        if (repeated) {
            return interval;
        }
        else {
            return 0;
        }
    }

    public void setTime (LocalDateTime start, LocalDateTime end, int interval) {
        if(interval < 0) {
            throw new IllegalArgumentException("The interval should be above 0!");
        }

        if (!repeated) {
            repeated = true;
            this.start = start;
            this.end = end;
            this.interval = interval;
        }
        else {
            this.start = start;
            this.end = end;
            this.interval = interval;
        }
    }

    public LocalDateTime nextTimeAfter(LocalDateTime current) {

        if (active) {
            if (repeated) {
                if (current.isAfter(end)) return null;
                else if (start.isAfter(current)) return start;
                else {
                    LocalDateTime nextTime = start;
                    while (nextTime.isBefore(end) || nextTime.equals(end)) {
                       if (current.isBefore(nextTime)) return nextTime;
                       else nextTime = nextTime.plusSeconds(interval);
                    }

                }
            }
            else {
                if (time.isAfter(current)) return time;
                else return null;
            }
        }
       return null;
    }

    @Override
    public boolean equals(Object obj) {
        Task o = (Task) obj;
        if(o.title == this.title && o.time == this.time && o.start == this.start && o.end == this.end && o.interval == this.interval && o.active == this.active) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, time, start, end, interval, active);
    }

    @Override
    public Task clone() throws CloneNotSupportedException {
        Task temp = (Task) super.clone();
        temp.title = this.title;
        return temp;
    }

    @Override
    public String toString() {
        return "Task{" +
                "title='" + title + '\'' +
                ", time=" + time +
                ", end=" + end +
                ", interval=" + interval +
                ", start=" + start +
                ", active=" + active +
                ", repeated=" + repeated +
                '}';
    }
}
