package PC_object;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class CheckUpDay {
    private LocalDate date;
    private String temp_start, temp_middle, temp_end;
    private static ArrayList<Schedule> list_schedule = new ArrayList<>();
    public CheckUpDay(LocalDate date) {
        this.date = date;
        for (int a = 7; a < 22; a++) {
            if (a < 10) {
                temp_start = "0" + a + ":00";
                temp_middle = "0" + a + ":30";
                if (a == 9) {
                    temp_end = (a + 1) + ":00";
                } else {
                    temp_end = "0" + (a + 1) + ":00";
                }
            } else {
                temp_start = a + ":00";
                temp_middle = a + ":30";
                temp_end = (a + 1) + ":00";
            }

            list_schedule.add(new Schedule(temp_start, temp_middle));
            list_schedule.add(new Schedule(temp_middle, temp_end));
        }
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getTemp_start() {
        return temp_start;
    }

    public void setTemp_start(String temp_start) {
        this.temp_start = temp_start;
    }

    public String getTemp_middle() {
        return temp_middle;
    }

    public void setTemp_middle(String temp_middle) {
        this.temp_middle = temp_middle;
    }

    public String getTemp_end() {
        return temp_end;
    }

    public void setTemp_end(String temp_end) {
        this.temp_end = temp_end;
    }

    public ArrayList<Schedule> getList_schedule() {
        return list_schedule;
    }

    public void setList_schedule(ArrayList<Schedule> list_schedule) {
        this.list_schedule = list_schedule;
    }
}
