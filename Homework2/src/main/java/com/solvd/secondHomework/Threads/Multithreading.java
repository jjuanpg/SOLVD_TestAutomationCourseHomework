package com.solvd.secondHomework.Threads;

public class Multithreading extends Thread{

    private final int threadNumber;
    public Multithreading (int threadNumber){
        this.threadNumber = threadNumber;
    }
    @Override
    public void run(){
        for (int i=1; i <= 5; i++){
            System.out.println(i+" From thread: "+threadNumber);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
