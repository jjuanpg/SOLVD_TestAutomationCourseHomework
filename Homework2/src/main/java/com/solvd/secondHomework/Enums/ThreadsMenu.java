package com.solvd.secondHomework.Enums;

public enum ThreadsMenu {

    EXTENDED_THREAD("Thread using 'extends Thread'."),
    RUNNABLE_THREAD("Thread using 'implements Runnable'."),
    THREAD_POOL("Thread pool.");

    private final String description;
    ThreadsMenu(String s) {
        this.description = s;
    }

    @Override
    public String toString(){
        return description;
    }
}
