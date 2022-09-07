package com.solvd.secondHomework.Threads;
import java.sql.Connection;

public interface ConnectionPool {

        String getConnection();
        String releaseConnection();
        String getUrl();
        String getUser();
        String getPassword();
}
