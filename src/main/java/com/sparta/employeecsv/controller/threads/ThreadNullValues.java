package com.sparta.employeecsv.controller.threads;

import com.sparta.employeecsv.database.DatabaseDriver;
import com.sparta.employeecsv.model.Employee;

import java.util.List;

public class ThreadNullValues implements Runnable {
    private List<Employee> listSplitted;

    public ThreadNullValues(List<Employee> listSplitted) {
        this.listSplitted = listSplitted;
    }

    public void populateNullTableWithThreads(List<Employee> listSplitted) {
        System.out.println("populating null table...");
        DatabaseDriver dbDriver = new DatabaseDriver();
        dbDriver.populateThreadTableNullEmployee(listSplitted);
    }

    @Override
    public void run() {
        populateNullTableWithThreads(this.listSplitted);
    }
}