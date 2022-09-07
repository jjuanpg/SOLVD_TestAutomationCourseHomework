package com.solvd.secondHomework.Threads;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Multithread implements Runnable, ConnectionPool{

    private static final Logger logger = LogManager.getLogger(Multithread.class);
    private String url;
    private String user;
    private String password;
    private final int threadNumber;

    public Multithread(String url, String user, String password, int threadNumber) {
        this.url = url;
        this.user = user;
        this.password = password;
        this.threadNumber = threadNumber;
    }
    public Multithread (int threadNumber){
        this.threadNumber = threadNumber;
    }
    @Override
    public void run(){
        logger.info(getConnection());
        for (int i=1; i<6; i++){
            logger.info(i+" From thread: "+threadNumber);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        logger.info(releaseConnection());
    }

    @Override
    public String getConnection() {
        return "The thread "+threadNumber+" is connected...";
    }

    @Override
    public String releaseConnection() {
        return "Disconnecting thread... "+threadNumber;
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public String getUser() {
        return user;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
