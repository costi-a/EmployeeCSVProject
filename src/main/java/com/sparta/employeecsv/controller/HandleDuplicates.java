package com.sparta.employeecsv.controller;

import com.sparta.employeecsv.model.Employee;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class HandleDuplicates {
    public Map<String, Integer> getIdsCounter(LinkedList<String> ids) {
        // setting an hasmap so that we can count the the ids
        Map<String, Integer> mapIds = new HashMap<>();

        try { /* takes around 0 seconds
            // if id doesn't exists we put into the hashmap the id as a key and the value 1
            // if id exists we just increase the value by 1
            for(String id: ids) {
                if(mapIds.containsKey(id)) {
                    mapIds.put(id, mapIds.get(id) + 1);
                } else {
                    mapIds.put(id, 1);
                }
            } */

            // long start = System.nanoTime() / 1000000000;
            // takes around 0 seconds
            // if id doesn't exists we put into the hashmap the id as a key and the value 1
            // if id exists we just increase the value by 1
            ids.stream()
                    .forEach(id -> {
                        if(mapIds.containsKey(id)) {
                            mapIds.put(id, mapIds.get(id) + 1);
                        } else {
                            mapIds.put(id, 1);
                        }
                    });
            // long finish = System.nanoTime() / 1000000000;
            // long duration = finish - start;
            // System.out.println("getIdsCounter " + duration);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapIds;
    }

    public int calculateSumDuplicates(Map<String, Integer> mapIds) {
        int sum = 0;
        // here there are all the values of the hashmap greater than 1 (how many duplicates id)
        LinkedList<Integer> duplicatesNumbers = new LinkedList<>();

        try { /* takes around 0 seconds

            for(String id: mapIds.keySet()) {
                if(mapIds.get(id) > 1) {
                    duplicatesNumbers.add(mapIds.get(id));
                }
            }

            // here we are calculating how many duplicates ids we have by just adding the values
            for(int i = 0; i < duplicatesNumbers.size(); i++) {
                sum += duplicatesNumbers.get(i);
            }

            */
            // long start = System.nanoTime() / 1000000000;
            // takes around 0 seconds
            Set<Map.Entry<String, Integer>> entries = mapIds.entrySet();

            // if id has a value > 1 we add the number into duplicateNumbers
            entries.stream()
                    .filter(id -> id.getValue() > 1)
                    .forEach(id -> {
                        duplicatesNumbers.add(id.getValue());
                    });

            if(duplicatesNumbers.size() > 0) {
                // adding each value of duplicateNumbers to each other
                sum = duplicatesNumbers.stream()
                        .reduce((a, b) -> a + b)
                        .get();
            }
            // long finish = System.nanoTime() / 1000000000;
            // long duration = finish - start;
            // System.out.println("calculateSumDuplicates " + duration);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sum;
    }

    public LinkedList<String> getDuplicatesIds(Map<String, Integer> mapIds) {
        LinkedList<String> duplicatesIds = new LinkedList<>();

        try { /* takes around 0 seconds
            // if value of key (id) is greater than one we add it to the LinkedList
            for(String id: mapIds.keySet()) {
                if(mapIds.get(id) > 1) {
                    duplicatesIds.add(id);
                }
            } */
            // long start = System.nanoTime() / 1000000000;
            // takes around 0 seconds
            Set<Map.Entry<String, Integer>> entries = mapIds.entrySet();
            entries.stream()
                    // if id has a value > 1 we add it to duplicateIds
                    .filter(id -> id.getValue() > 1)
                    .forEach(id -> {
                        duplicatesIds.add(id.getKey());
                    });
            // long finish = System.nanoTime() / 1000000000;
            // long duration = finish - start;
            // System.out.println("getDuplicatesIds " + duration);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return duplicatesIds;
    }

    public LinkedList<Employee> returnDuplicatesEmployees(LinkedList<String> duplicatesIds,
                                                          LinkedList<Employee> employeesList) {
        LinkedList<Employee> duplicatesEmployees = new LinkedList<>();


        try { /*
            // loop that goes inside ALL the employees
            for(int i = 0; i < employeesList.size(); i++) {
                // 1 employee
                Employee employee = employeesList.get(i);

                // if the ID of the employee matches the one inside duplicate list we
                // add employee inside another list
                if(duplicatesIds.contains(employee.getEmployeeID())) {
                    duplicatesEmployees.add(employee);
                }
            } */

            // long start = System.nanoTime() / 1000000000;
            // takes around 0 seconds
            employeesList.stream()
                    .forEach(employee -> {
                        duplicatesIds.stream()
                                .forEach(id -> {
                                    // if employee id is equal to the id in duplicatesIds
                                    // we add the employee into duplicatesEmployees
                                    if(id.contains(employee.getEmployeeID())) {
                                        duplicatesEmployees.add(employee);
                                    }
                                });
                    });
            // long finish = System.nanoTime() / 1000000000;
            // long duration = finish - start;
            // System.out.println("returnDuplicatesEmployees " + duration);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return duplicatesEmployees;
    }
}