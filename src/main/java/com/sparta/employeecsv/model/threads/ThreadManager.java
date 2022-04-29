package com.sparta.employeecsv.model.threads;

import com.sparta.employeecsv.model.Employee;

import java.util.ArrayList;
import java.util.LinkedList;

public class ThreadManager {

    private Thread[] employeeThreads;

    private LinkedList<LinkedList<Employee>> splitEmployeeList;

    public ThreadManager(LinkedList<LinkedList<Employee>> splitEmployeeList) {
        this.splitEmployeeList = splitEmployeeList;
    }

    public void runThreads() throws InterruptedException {
        employeeThreads = new Thread[splitEmployeeList.size()];

        //run the threads
        for (int i = 0; i < employeeThreads.length; i++) {
            employeeThreads[i] = new Thread(new EmployeeThreads(splitEmployeeList.get(i)));
        }

        for (Thread thread : employeeThreads) {
            thread.start();
            try {
                thread.join();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
