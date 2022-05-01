package com.sparta.employeecsv.display;

import com.sparta.employeecsv.model.Employee;

import java.util.LinkedList;

public class DisplayInfo {
    // Provide a simple user interface to com.sparta.employeecsv.display the results of reading the file –
    // how many unique, clean records there are, how many duplicates,
    // how many records with missing fields, possibly com.sparta.employeecsv.display the questionable records
    public void printResults(int duplicateIds, int uniqueIds,
                             LinkedList<Employee> employeesList, int nullValues) {
        try {
            System.out.println("List of employees: " + employeesList);
            System.out.println("There " + (uniqueIds > 1 || uniqueIds == 0 ?
                            "are: ": "is: ") + uniqueIds + " unique employees");
            System.out.println("There " + (duplicateIds > 1 || duplicateIds == 0 ?
                    "are: ": "is: ") + duplicateIds + " duplicates employees");
            System.out.println("There " + (nullValues > 1 || nullValues == 0 ?
                    "are: ": "is: ") + nullValues + " null value employees");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void printTimeTaken(long duration) {
        System.out.println("it took " + duration + " second" + (duration > 1 || duration == 0 ?
                "s" : "") + " to clear the tables, create them and write data into db");
    }
}
