package AccountManager;

public class TimeSlot {
    private String date;
    private String time;

    public TimeSlot(String date, String time) {
        this.date = date;
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    @Override
    public String toString() {
        return date + " at " + time;
    }
}

