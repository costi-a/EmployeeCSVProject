package com.sparta.employeecsv.controller;

import com.sparta.employeecsv.model.Employee;

import java.util.ArrayList;
import java.util.List;

public class SplitListIntoMultipleLists {
    // split the list of employees into smaller lists of a given amount
    public List<List<Employee>> splitList(int sublistSize,
                                                      List<Employee> uniqueEmployees) {

        List<List<Employee>> splitEmployeeList = new ArrayList<>();

        try {
            System.out.println("splitting list into lists of maximum " + sublistSize + " size");
            final int uniqueSize = uniqueEmployees.size();

            for (int i = 0; i < uniqueSize; i += sublistSize) {
                splitEmployeeList.add(new ArrayList<Employee>(
                        uniqueEmployees.subList(i, Math.min(uniqueSize, i + sublistSize)))
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return splitEmployeeList;
    }
}
