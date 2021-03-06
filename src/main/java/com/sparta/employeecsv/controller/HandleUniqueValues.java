package com.sparta.employeecsv.controller;

import com.sparta.employeecsv.model.Employee;

import java.util.*;

public class HandleUniqueValues {
    public int calculateUniqueIds(Map<String, Integer> mapIds) {
        // here we will count the ids with value of 1
        int counter = 0;

        try { /* takes around 0 seconds
            // if key (id) as a value of 1 we increase the counter
            for(String id: mapIds.keySet()) {
                if(mapIds.get(id) == 1) {
                    counter++;
                }
            } */

            // long start = System.nanoTime() / 1000000000;
            // takes around 0 seconds
            Set<Map.Entry<String, Integer>> entries = mapIds.entrySet();
            List uniqueIds = entries.stream()
                    // if key (id) as a value of 1 save into uniqueIds
                    .filter(id -> id.getValue() == 1)
                    .toList();
            counter = uniqueIds.size();
            // long finish = System.nanoTime() / 1000000000;
            // long duration = finish - start;
            // System.out.println("calculateUniqueIds " + duration);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return counter;
    }

    public LinkedList<Employee> returnUniqueEmployees(LinkedList<Employee> employeesList,
                                                      Map<String, Integer> mapIds) {
        LinkedList<Employee> uniqueEmployees = new LinkedList<>();

        try { // takes around 75 seconds
            System.out.println("updating List<Employee> uniqueEmployees...");
            // looping into employees list with duplicates
            for(int i = 0; i < employeesList.size(); i++) {
                // saving employee so that we can compare the dulicate ids
                // and save all the other data about the employee
                Employee employee = employeesList.get(i);

                // looping into hashmap ids with duplicates and declaring id
                for(String id: mapIds.keySet()) {
                    // if id it's unique we save the employee into another list
                    if(employee.getEmployeeID().equals(id) && mapIds.get(id) == 1) {
                        uniqueEmployees.add(employee);
                    }
                }
            }

            /* takes around 264 seconds
            long start = System.nanoTime() / 1000000000;
            Set<Map.Entry<String, Integer>> entries = mapIds.entrySet();
            entries.stream()
                    .filter(id -> id.getValue() == 1)
                    .forEach(id -> {
                        employeesList.stream()
                                .filter(employee -> employee.getEmployeeID().equals(id.getKey()))
                                .forEach(employee -> {
                                    uniqueEmployees.add(employee);
                                });
                    });
            long finish = System.nanoTime() / 1000000000;
            long duration = finish - start;
            System.out.println("storeIds " + duration);
            */
        } catch (Exception e) {
            e.printStackTrace();
        }
        return uniqueEmployees;
    }
}
