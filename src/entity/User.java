package entity;

import java.util.ArrayList;

public class User {
    private final Integer id;

    private String name;

    private ArrayList<Task> tasks;

    private Pomodoro pomodoro;

    private Statistics staData;

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

    public void setName(String name) {
        this.name = name;
    }

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void setPomodoro(Pomodoro pomodoro) {
        this.pomodoro = pomodoro;
    }

    public void setStaData(Statistics staData) {
        this.staData = staData;
    }
}
