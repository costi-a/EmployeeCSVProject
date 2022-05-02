package com.sparta.employeecsv.controller.threads;

import com.sparta.employeecsv.model.Employee;
import java.util.List;

public class ThreadManager {
    public void createUniqueThreads(int lists,
                                    List<List<Employee>> splittedList) {
        try {
            for(int i = 0; i < lists; i++) {
                ThreadUniqueValues tm = new ThreadUniqueValues(splittedList.get(i));
                Thread tr = new Thread(tm);
                tr.start();
                tr.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void createDuplicateThreads(int lists,
                              List<List<Employee>> splittedList) {
        try {
            for(int i = 0; i < lists; i++) {
                ThreadDuplicateValues tm = new ThreadDuplicateValues(splittedList.get(i));
                Thread tr = new Thread(tm);
                tr.start();
                tr.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void createNullThreads(int lists,
                              List<List<Employee>> splittedList) {
        try {
            for(int i = 0; i < lists; i++) {
                ThreadNullValues tm = new ThreadNullValues(splittedList.get(i));
                Thread tr = new Thread(tm);
                tr.start();
                tr.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}