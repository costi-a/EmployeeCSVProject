package controller;

<<<<<<< HEAD:src/main/java/com/sparta/employeecsv/model/HandleUniqueValues.java
import java.util.LinkedList;
import java.util.Map;
import java.util.Objects;
=======
import com.sparta.employeecsv.model.Employee;

import java.util.*;
import java.util.stream.Collectors;
>>>>>>> c372a782041c7d64283a2a06a724a24960928cff:src/main/java/controller/HandleUniqueValues.java

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
                    // if key (id) as a value of 1 save into uniqueIds
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
                    if(Objects.equals(employee.getEmployeeID(), id) && mapIds.get(id) == 1) {
                        uniqueEmployees.add(employee);
                    }
                }
            }

            /* takes around 264 seconds
            Set<Map.Entry<String, Integer>> entries = mapIds.entrySet();
            entries.stream()
                    .filter(id -> id.getValue() == 1)
                    .forEach(id -> {
                        employeesList.stream()
                                .filter(employee -> employee.getEmployeeID().equals(id.getKey()))
                                .forEach(employee -> {
                                    uniqueEmployees.add(employee);
                                });
                    }); */
        } catch (Exception e) {
            e.printStackTrace();
        }
        return uniqueEmployees;
    }
}
