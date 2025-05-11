package model;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Represents a reminder with a title, date, and time.
 */
public class Reminder implements Serializable {
    private String title;
    private LocalDate date;
    private LocalTime time;

    /**
     * Constructs a new Reminder.
     *
     * @param title the reminder title
     * @param date  the reminder date
     * @param time  the reminder time
     */
    public Reminder(String title, LocalDate date, LocalTime time) {
        this.title = title;
        this.date = date;
        this.time = time;
    }

    /**
     * Gets the reminder title.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Gets the reminder date.
     *
     * @return the date
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Gets the reminder time.
     *
     * @return the time
     */
    public LocalTime getTime() {
        return time;
    }

    /**
     * Returns a string representation of the reminder.
     *
     * @return a string describing the reminder
     */
    @Override
    public String toString() {
        return title + " at " + date + " " + time;
    }
}
