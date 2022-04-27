package com.sparta.employeecsv.display;

import com.sparta.employeecsv.model.Employee;

import java.util.LinkedList;

public class DisplayInfo {
    // Provide a simple user interface to com.sparta.employeecsv.display the results of reading the file –
    // how many unique, clean records there are, how many duplicates,
    // how many records with missing fields, possibly com.sparta.employeecsv.display the questionable records
    public void printResults(int duplicateIds, int uniqueIds, LinkedList<Employee> employeesList) {
        System.out.println("List of employees: " + employeesList);
        System.out.println("There are: " + uniqueIds + " unique ids");
        System.out.println("There are: " + duplicateIds + " duplicates");
    }
}
