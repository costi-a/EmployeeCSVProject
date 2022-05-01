package com.sparta.employeecsv.controller;

import com.sparta.employeecsv.model.Employee;

import java.util.LinkedList;

public class StoringIds {
    public LinkedList<String> storeIds(LinkedList<Employee> employeesList) {
        // here we will add all the ids
        LinkedList<String> ids = new LinkedList<>();
        /* takes around 2 seconds
        try {
            for(int i = 0; i < employeesList.size(); i++) {
                Employee employee = employeesList.get(i);
                ids.add(employee.getEmployeeID());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ids; */

        try { // takes around 0 seconds
            // long start = System.nanoTime() / 1000000000;
            employeesList.stream()
                    .filter(employee -> ids.add(employee.getEmployeeID()))
                    .toList();

            // long finish = System.nanoTime() / 1000000000;
            // long duration = finish - start;
            // System.out.println("storeIds " + duration);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ids;
    }
}
