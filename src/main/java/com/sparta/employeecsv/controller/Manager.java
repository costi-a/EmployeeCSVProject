package com.sparta.employeecsv.controller;
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
            eDAO.deleteEmployeeById("111282");

            // stopping the program for 5 seconds in order to let the user read the info
            TimeUnit.SECONDS.sleep(5);

            // splitting threads into multiple lists
            SplitListIntoMultipleLists sp = new SplitListIntoMultipleLists();
            List<List<Employee>> splittedListUniqueValidValues;
            List<List<Employee>> splittedListDuplicateValidValues;
            List<List<Employee>> splittedListNullValidValues;

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

            // creating thread with value null/duplicates so that we can create, start and join them
            // only if there are null/duplicates
            Thread thread7 = null;
            Thread thread8 = null;
            // Thread thread9 = null;
            // Thread thread10 = null;
            // Thread thread11 = null;
            // Thread thread12 = null;
            Thread thread13 = null;
            Thread thread14 = null;
            // Thread thread15 = null;
            // Thread thread16 = null;
            // Thread thread17 = null;
            // Thread thread18 = null;

            // handling duplicate values
            if(duplicateIdsInt > 0) {
                // splitting in 2 lists
                splittedListDuplicateValidValues = sp.splitList(60, duplicatesEmployees);

                // dropping, creating table duplicate employee tables
                dbDriver.clearTable("THREAD_EMPLOYEE_DUP_RECORDS");
                dbDriver.createThreadTableDuplicatesEmployee();

                ThreadDuplicateValues tm7 = new ThreadDuplicateValues
                        (splittedListDuplicateValidValues.get(0));
                ThreadDuplicateValues tm8 = new ThreadDuplicateValues
                        (splittedListDuplicateValidValues.get(1));
                // ThreadManagerDuplicateValues tm9 = new ThreadManagerDuplicateValues
                //      (splittedListDuplicateValidValues.get(2));
                // ThreadManagerDuplicateValues tm10 = new ThreadManagerDuplicateValues
                //      (splittedListDuplicateValidValues.get(3));
                // ThreadManagerDuplicateValues tm11 = new ThreadManagerDuplicateValues
                //      (splittedListDuplicateValidValues.get(4));
                // ThreadManagerDuplicateValues tm12 = new ThreadManagerDuplicateValues
                //      (splittedListDuplicateValidValues.get(5));

                // creating thread dup values
                thread7 = new Thread(tm7);
                thread8 = new Thread(tm8);
                // thread9 = new Thread(tm9);
                // thread10 = new Thread(tm10);
                // thread11 = new Thread(tm11);
                // thread12 = new Thread(tm12);

                thread7.start();
                thread8.start();
                // thread9.start();
                // thread10.start();
                // thread11.start();
                // thread12.start();
            }

            if(employeesNullValues.size() > 0) {
                // splitting in 2 lists
                splittedListNullValidValues =
                        sp.splitList(500, employeesNullValues);

                // dropping, creating table null employee tables
                dbDriver.clearTable("THREAD_NULL_EMPLOYEE_RECORDS");
                dbDriver.createThreadTableNullEmployee();

                ThreadNullValues tm13 = new ThreadNullValues
                        (splittedListNullValidValues.get(0));
                ThreadNullValues tm14 = new ThreadNullValues
                        (splittedListNullValidValues.get(1));
                // ThreadManagerNullValues tm15 = new ThreadManagerNullValues
                        // (splittedListNullValidValues.get(2));
                // ThreadManagerNullValues tm16 = new ThreadManagerNullValues
                        // (splittedListNullValidValues.get(3));
                // ThreadManagerNullValues tm17 = new ThreadManagerNullValues
                        // (splittedListNullValidValues.get(4));
                // ThreadManagerNullValues tm18 = new ThreadManagerNullValues
                        // (splittedListNullValidValues.get(5));

                // creating thread null values
                thread13 = new Thread(tm13);
                thread14 = new Thread(tm14);
                // thread15 = new Thread(tm15);
                // thread16 = new Thread(tm16);
                // thread17 = new Thread(tm17);
                // thread18 = new Thread(tm18);

                thread13.start();
                thread14.start();
                // thread15.start();
                // thread16.start();
                // thread17.start();
                // thread18.start();
            }

            // creating thread manager unique values
            ThreadUniqueValues tm1 = new ThreadUniqueValues
                    (splittedListUniqueValidValues.get(0));
            ThreadUniqueValues tm2 = new ThreadUniqueValues
                    (splittedListUniqueValidValues.get(1));
            // ThreadManagerUniqueValues tm3 = new ThreadManagerUniqueValues
                    // (splittedListUniqueValidValues.get(2));
            // ThreadManagerUniqueValues tm4 = new ThreadManagerUniqueValues
                    // (splittedListUniqueValidValues.get(3));
            // ThreadManagerUniqueValues tm5 = new ThreadManagerUniqueValues
                    // (splittedListUniqueValidValues.get(4));
            // ThreadManagerUniqueValues tm6 = new ThreadManagerUniqueValues
                    // (splittedListUniqueValidValues.get(5));

            // creating thread
            Thread thread1 = new Thread(tm1);
            Thread thread2 = new Thread(tm2);
            // Thread thread3 = new Thread(tm3);
            // Thread thread4 = new Thread(tm4);
            // Thread thread5 = new Thread(tm5);
            // Thread thread6 = new Thread(tm6);

            // dropping, creating table unique employee tables
            dbDriver.clearTable("THREAD_EMPLOYEE_RECORDS");
            dbDriver.createThreadTableUniqueEmployee();

            // starting threads
            thread1.start();
            thread2.start();
            // thread3.start();
            // thread4.start();
            // thread5.start();
            // thread6.start();

            // waiting for threads to finish so that we can calculate the time taken
            thread1.join();
            thread2.join();
            // thread3.join();
            // thread4.join();
            // thread5.join();
            // thread6.join();

            // if thread 7 is null also 8 is
            if(thread7 != null) {
                // waiting for threads to finish so that we can calculate the time taken
                thread7.join();
                thread8.join();
                // thread9.join();
                // thread10.join();
                // thread11.join();
                // thread12.join();
            }

            // if thread 13 is null also 14 is
            if(thread13 != null) {
                // waiting for threads to finish so that we can calculate the time taken
                thread13.join();
                thread14.join();
                // thread15.join();
                // thread16.join();
                // thread17.join();
                // thread18.join();
            }

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