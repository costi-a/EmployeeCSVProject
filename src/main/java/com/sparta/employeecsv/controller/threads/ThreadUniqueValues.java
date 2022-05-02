package com.sparta.employeecsv.controller.threads;

import com.sparta.employeecsv.database.DatabaseDriver;
import com.sparta.employeecsv.model.Employee;

import java.util.List;

public class ThreadUniqueValues implements Runnable {
    private List<Employee> listSplitted;

    public ThreadUniqueValues(List<Employee> listSplitted) {
        this.listSplitted = listSplitted;
    }

    public void populateTableWithThreads(List<Employee> listSplitted) {
        System.out.println("populating unique table...");
        DatabaseDriver dbDriver = new DatabaseDriver();
        dbDriver.populateTableMultiList(listSplitted);
    }

    @Override
    public void run() {
        populateTableWithThreads(this.listSplitted);
    }
}
