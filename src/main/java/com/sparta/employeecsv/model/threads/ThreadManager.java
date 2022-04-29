package com.sparta.employeecsv.model.threads;

import com.sparta.employeecsv.model.Employee;

import java.util.Iterator;
import java.util.LinkedList;

public class ThreadManager {

    public Thread[] employeeThreads;

    private final LinkedList<LinkedList<Employee>> splitEmployeeList;

    public ThreadManager(LinkedList<LinkedList<Employee>> splitEmployeeList) {
        this.splitEmployeeList = splitEmployeeList;
    }

    public void createThreads() throws InterruptedException {
        employeeThreads = new Thread[splitEmployeeList.size()];

        //create the threads
        for (int i = 0; i < employeeThreads.length; i++) {
            employeeThreads[i] = new Thread(new EmployeeThreads(splitEmployeeList.get(i)));
        }
    }

    public void runThreads()    {

        for (int i = 0; i < employeeThreads.length; i++)    {
            employeeThreads[i].start();
            try {
                employeeThreads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
