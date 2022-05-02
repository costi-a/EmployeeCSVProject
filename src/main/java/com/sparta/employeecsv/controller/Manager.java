package com.sparta.employeecsv.controller;
import com.sparta.employeecsv.controller.threads.ThreadManager;
import com.sparta.employeecsv.database.DatabaseDriver;
import com.sparta.employeecsv.display.DisplayInfo;
import com.sparta.employeecsv.model.Employee;
import com.sparta.employeecsv.controller.threads.ThreadDuplicateValues;
import com.sparta.employeecsv.controller.threads.ThreadNullValues;
import com.sparta.employeecsv.controller.threads.ThreadUniqueValues;

import java.io.BufferedReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Manager {

    public void manageProgram(String fileName) {
        try {
            // reading the file
            EmployeeFileReader rf = new EmployeeFileReader();
            BufferedReader br = rf.readFile(fileName);

            // saving employees into list
            SaveEmployees saveEmployees = new SaveEmployees();
            LinkedList<Employee> employeesList = saveEmployees.saveEmployees(br);

            // storing employees into 2 lists, one with valid values and the other with null values
            List<Employee> employeesNullValues =
                    saveEmployees.storeEmployeesNullValues(employeesList);
            LinkedList<Employee> employeesValidValues =
                    saveEmployees.storeEmployeesValidValues(employeesList);

            // storing ids that we will use with the HandleDuplicates
            StoringIds sIds = new StoringIds();
            LinkedList<String> ids = sIds.storeIds(employeesValidValues);
            HandleDuplicates hd = new HandleDuplicates();
            // we first return an hashmap so that we have something like this:
            // {a: 1, b: 3, c: 5} (a: 1 means there is only 1 a, b: 3 means there are 3 b)
            // so that we can check duplicates and unique values
            Map<String, Integer> mapIds = hd.getIdsCounter(ids);
            // counting duplicates
            int duplicateIdsInt = hd.calculateSumDuplicates(mapIds);

            LinkedList<String> duplicatesIds = hd.getDuplicatesIds(mapIds);

            // returning a list of duplicate ids
            LinkedList<Employee> duplicatesEmployees = hd.returnDuplicatesEmployees(duplicatesIds,
                    employeesValidValues);

            // calculating unique values
            HandleUniqueValues uniqueValues = new HandleUniqueValues();
            int uniqueIds = uniqueValues.calculateUniqueIds(mapIds);

            // displaying the info
            DisplayInfo dInfo = new DisplayInfo();
            int nullValues = employeesNullValues.size();

            // calculating an employee list with unique ids
            LinkedList<Employee> uniqueEmployees = uniqueValues.returnUniqueEmployees(
                    employeesValidValues, mapIds);

            dInfo.printResults(duplicateIdsInt, uniqueIds, uniqueEmployees, nullValues);

            // dropping and creating table
            DatabaseDriver dbDriver = new DatabaseDriver();

            // calculating first parameter for method calculate time taken
            CalculateTimeTaken ct = new CalculateTimeTaken();
            long startTimeSeconds = ct.calculateStartTime();

            // dropping, creating table unique employee
            dbDriver.clearTable("EMPLOYEE_RECORDS");
            dbDriver.createTableUniqueEmployee();

            // populating the table with unique ids employees
            dbDriver.populateTableUniqueEmployee(uniqueEmployees);

            if(duplicatesEmployees.size() > 0) {
                // dropping, creating table duplicate employee
                dbDriver.clearTable("EMPLOYEE_DUP_RECORDS");
                dbDriver.createTableDuplicatesEmployee();

                // populating the table with duplicate ids employees
                dbDriver.populateTableDuplicateEmployee(duplicatesEmployees);
            }

            if(employeesNullValues.size() > 0) {
                // dropping, creating table null employee
                dbDriver.clearTable("NULL_EMPLOYEE_RECORDS");
                dbDriver.createTableNullEmployee();

                // populating the table with null values employees
                dbDriver.populateTableNullEmployee(employeesNullValues);
            }

            // calculating time taken
            long duration = ct.calculateEndTime(startTimeSeconds);

            // printing time taken
            dInfo.printTimeTaken(duration);

            // finding and removing employee by ID
            EmployeeDAO eDAO = new EmployeeDAO();
            Employee employee = eDAO.getEmployeeByID("198429");
            System.out.println("getEmployeeById " + employee);
            System.out.println("employee deleted: " + eDAO.deleteEmployeeById("111282"));;

            // stopping the program for 5 seconds in order to let the user read the info
            TimeUnit.SECONDS.sleep(5);

            // splitting threads into multiple lists
            SplitListIntoMultipleLists sp = new SplitListIntoMultipleLists();
            List<List<Employee>> splittedListUniqueValidValues;
            List<List<Employee>> splittedListDuplicateValidValues;
            List<List<Employee>> splittedListNullValues;

            System.out.println("using threads...");
            TimeUnit.SECONDS.sleep(3);

            // calculating start time
            long startTimeThreads = ct.calculateStartTime();

            // splitting unique list into 4 lists
            if(uniqueEmployees.size() < 10000) {
                splittedListUniqueValidValues = sp.splitList(5000, uniqueEmployees);
            } else {
                splittedListUniqueValidValues = sp.splitList(35000, uniqueEmployees);
            }

            // handling duplicate values
            if(duplicateIdsInt > 0) {
                // splitting in 2 lists
                splittedListDuplicateValidValues = sp.splitList(60, duplicatesEmployees);

                // dropping, creating table duplicate employee tables
                dbDriver.clearTable("THREAD_EMPLOYEE_DUP_RECORDS");
                dbDriver.createThreadTableDuplicatesEmployee();

                ThreadManager tm = new ThreadManager();
                int dupSubListSize = splittedListDuplicateValidValues.size();
                tm.createDuplicateThreads(dupSubListSize, splittedListDuplicateValidValues);
            }

            if(employeesNullValues.size() > 0) {
                // splitting in 2 lists
                splittedListNullValues =
                        sp.splitList(500, employeesNullValues);

                // dropping, creating table null employee tables
                dbDriver.clearTable("THREAD_NULL_EMPLOYEE_RECORDS");
                dbDriver.createThreadTableNullEmployee();

                ThreadManager tm = new ThreadManager();
                int nullSubListSize = splittedListNullValues.size();
                tm.createNullThreads(nullSubListSize, splittedListNullValues);
            }

            // dropping, creating table unique employee tables
            dbDriver.clearTable("THREAD_EMPLOYEE_RECORDS");
            dbDriver.createThreadTableUniqueEmployee();

            ThreadManager tm = new ThreadManager();
            int validSubListSize = splittedListUniqueValidValues.size();
            tm.createUniqueThreads(validSubListSize, splittedListUniqueValidValues);

            long durationThreads = ct.calculateEndTime(startTimeThreads);

            // printing time taken
            dInfo.printTimeTaken(durationThreads);

            // stopping for 10 seconds before reading the large file
            TimeUnit.SECONDS.sleep(10);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}