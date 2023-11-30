package models;
/*************Example***************
 CLASS: Time.java
 CSC212 Data structures - Project phase II
 Fall 2023
 EDIT DATE:
 11/26/2023
 TEAM:
 farmers
 AUTHORS:
 Mohammed, (ID443101692)
 ***********************************/
public class Time {
    private final int hours;
    private final int minutes;

    public Time(int hours, int minutes) {
        this.hours = hours;
        this.minutes = minutes;
    }

    public String toString() {
        return hours + ":" + minutes;
    }

    private int getTotalMinutes() {
        return hours * 60 + minutes;
    }

    public boolean conflictsWith(Time t) {
        int diff = getTotalMinutes() - t.getTotalMinutes();
        if (diff < 0) {
            diff = -diff;
        }
        return diff <= 10;
    }
}
