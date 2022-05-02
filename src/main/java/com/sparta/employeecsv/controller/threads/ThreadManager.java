package com.sparta.employeecsv.controller.threads;

import com.sparta.employeecsv.model.Employee;
import java.util.List;

public class ThreadManager {
    public Thread createUniqueThreads(int lists,
                                    List<List<Employee>> splittedList) {
        Thread tr = null;

        try {
            // takes around 0 seconds
            for(int i = 0; i < lists; i++) {
                ThreadUniqueValues tm = new ThreadUniqueValues(splittedList.get(i));
                tr = new Thread(tm);
                tr.start();
            } /*
            splittedList.stream()
                    .forEach(list -> {
                        ThreadDuplicateValues tm = new ThreadDuplicateValues(list);
                        Thread tr = new Thread(tm);
                        tr.start();
                        try {
                            tr.join();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }); */
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tr;
    }

    public void createDuplicateThreads(// int lists,
                              List<List<Employee>> splittedList) {

        try {
            /* takes around 0 seconds
            for(int i = 0; i < lists; i++) {
                ThreadDuplicateValues tm = new ThreadDuplicateValues(splittedList.get(i));
                Thread tr = new Thread(tm);
                tr.start();
            } */

            splittedList.stream()
                    .forEach(list -> {
                        ThreadDuplicateValues tm = new ThreadDuplicateValues(list);
                        Thread tr = new Thread(tm);
                        tr.start();
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createNullThreads(// int lists,
                              List<List<Employee>> splittedList) {
        try {
            /* takes around 0 seconds
            for(int i = 0; i < lists; i++) {
                ThreadNullValues tm = new ThreadNullValues(splittedList.get(i));
                Thread tr = new Thread(tm);
                tr.start();
                tr.join();
            } */
            splittedList.stream()
                    .forEach(list -> {
                        ThreadNullValues tm = new ThreadNullValues(list);
                        Thread tr = new Thread(tm);
                        tr.start();
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}