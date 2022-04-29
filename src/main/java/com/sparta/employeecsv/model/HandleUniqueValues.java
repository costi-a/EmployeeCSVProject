package com.sparta.employeecsv.model;

import java.util.*;
import java.util.stream.Collectors;

public class HandleUniqueValues {
    public int calculateUniqueIds(Map<String, Integer> mapIds) {
        // here we will count the ids with value of 1
        int counter = 0;

        try { /*
            // if key (id) as a value of 1 we increase the counter
            for(String id: mapIds.keySet()) {
                if(mapIds.get(id) == 1) {
                    counter++;
                }
            } */

            Set<Map.Entry<String, Integer>> entries = mapIds.entrySet();
            List uniqueIds = entries.stream()
                    .filter(id -> id.getValue() == 1)
                    .toList();
            counter = uniqueIds.size();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return counter;
    }

    public LinkedList<Employee> returnUniqueEmployees(LinkedList<Employee> employeesList,
                                                      Map<String, Integer> mapIds) {
        LinkedList<Employee> uniqueEmployees = new LinkedList<>();

        try { /*
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
            } */

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

        } catch (Exception e) {
            e.printStackTrace();
        }
        return uniqueEmployees;
    }
}
