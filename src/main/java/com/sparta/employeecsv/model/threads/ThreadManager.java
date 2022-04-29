package com.sparta.employeecsv.model.threads;

import com.sparta.employeecsv.model.Employee;

import java.util.LinkedList;

public class ThreadManager {

    public static Thread[] employeeThreads;

    public void createThreads(LinkedList<LinkedList<Employee>> splitEmployeeList) throws IllegalThreadStateException {

        employeeThreads = new Thread[splitEmployeeList.size()];
        System.out.println(splitEmployeeList.size());

        //create the threads
        for (int i = 0; i < employeeThreads.length; i++) {
            employeeThreads[i] = new Thread(new EmployeeThreads(splitEmployeeList.get(i)));
            System.out.println("Thread Created");
        }
    }

    public void runThreads() throws IllegalThreadStateException    {
        for (int i = 0; i < employeeThreads.length; i++)    {
            employeeThreads[i].start();
            System.out.println("Thread Running");
            try {
                employeeThreads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}