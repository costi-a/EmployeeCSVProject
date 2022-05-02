package com.sparta.employeecsv.controller.threads;

import com.sparta.employeecsv.database.DatabaseDriver;
import com.sparta.employeecsv.model.Employee;

import java.util.List;

public class ThreadDuplicateValues implements Runnable {
    private List<Employee> listSplitted;

    public ThreadDuplicateValues(List<Employee> listSplitted) {
        this.listSplitted = listSplitted;
    }

    public void populateDupTableWithThreads(List<Employee> listSplitted) {
        System.out.println("populating dup table...");
        DatabaseDriver dbDriver = new DatabaseDriver();
        dbDriver.populateThreadTableDuplicateEmployee(listSplitted);
    }

    @Override
    public void run() {
        populateDupTableWithThreads(this.listSplitted);
    }
}