package model.appointment;

import java.util.Date;

public class TimeSlot {
    private Date date;
    private Date time;

    public TimeSlot(Date date, Date time) {
        this.date = date;
        this.time = time;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Date: " + date + ", Time: " + time;
    }
}
