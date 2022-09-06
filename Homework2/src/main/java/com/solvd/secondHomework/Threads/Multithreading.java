package com.solvd.secondHomework.Threads;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Multithreading extends Thread{

    private static final Logger logger = LogManager.getLogger(Multithreading.class);
    private final int threadNumber;
    public Multithreading (int threadNumber){
        this.threadNumber = threadNumber;
    }
    @Override
    public void run(){
        for (int i=1; i<6; i++){
            logger.info(i+" From thread: "+threadNumber);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
