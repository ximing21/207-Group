package entity;

import java.util.ArrayList;

public class User {
    private final Integer id;

    private final String name;

    private final ArrayList<Task> tasks;

    private final Pomodoro pomodoro;

    private final Statistics staData;

    public User(Integer id, String name, ArrayList<Task> tasks, Pomodoro pomodoro, Statistics staData) {
        this.id = id;
        this.name = name;
        this.tasks = tasks;
        this.pomodoro = pomodoro;
        this.staData = staData;
    }

    public User(Integer id, String name) {
        this.id = id;
        this.name = name;
        this.tasks = null;
        this.pomodoro = null;
        this.staData = null;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public Pomodoro getPomodoro() {
        return pomodoro;
    }

    public Statistics getStaData() {
        return staData;
    }
}
