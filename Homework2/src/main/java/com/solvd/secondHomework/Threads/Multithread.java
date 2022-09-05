package com.solvd.secondHomework.Threads;

public class Multithread implements Runnable{

    private final int threadNumber;
    public Multithread (int threadNumber){
        this.threadNumber = threadNumber;
    }
    @Override
    public void run(){
        for (int i=1; i < 5; i++){
            System.out.println(i+" From thread: "+threadNumber);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
