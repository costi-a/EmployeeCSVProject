package controller;
import com.sparta.employeecsv.database.DatabaseDriver;
import com.sparta.employeecsv.display.DisplayInfo;
import com.sparta.employeecsv.model.Employee;
import controller.threads.ThreadManagerDuplicateValues;
import controller.threads.ThreadManagerNullValues;
import controller.threads.ThreadManagerUniqueValues;

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
            Map<String, Integer> mapIds = hd.returnHashMapIds(ids);
            // counting duplicates
            int duplicateIdsInt = hd.calculateSumDuplicates(mapIds);

            // returning all the employees ids
            LinkedList<String> duplicatesIds = hd.returnIds(mapIds);

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
            dbDriver.clearUniqueTable();
            dbDriver.createTableUniqueEmployee();

            // populating the table with unique ids employees
            dbDriver.populateTableUniqueEmployee(uniqueEmployees);

            if(duplicatesEmployees.size() > 0) {
                // dropping, creating table duplicate employee
                dbDriver.clearDuplicateTable();
                dbDriver.createTableDuplicatesEmployee();

                // populating the table with duplicate ids employees
                dbDriver.populateTableDuplicateEmployee(duplicatesEmployees);
            }

            if(employeesNullValues.size() > 0) {
                // dropping, creating table null employee
                dbDriver.clearNullTable();
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

            // splitting unique list into 4 lists
            if(uniqueEmployees.size() < 10000) {
                splittedListUniqueValidValues = sp.splitList(3000, uniqueEmployees);
            } else {
                splittedListUniqueValidValues = sp.splitList(17000, uniqueEmployees);
            }

            // creating thread with value null so that we can create, start and join them
            // only if there are duplicates
            Thread thread5 = null;
            Thread thread6 = null;
            Thread thread7 = null;
            Thread thread8 = null;
            Thread thread9 = null;
            Thread thread10 = null;
            Thread thread11 = null;
            Thread thread12 = null;

            // handling duplicate values
            if(duplicateIdsInt > 0) {
                if(duplicatesEmployees.size() < 120) {
                    // splitting in 4 lists
                    splittedListDuplicateValidValues =
                            sp.splitList(30, duplicatesEmployees);
                } else {
                    splittedListDuplicateValidValues =
                            sp.splitList(17000, duplicatesEmployees);
                }

                // dropping, creating table duplicate employee tables
                dbDriver.clearThreadDuplicateTable();
                dbDriver.createThreadTableDuplicatesEmployee();

                ThreadManagerDuplicateValues tm5 = new ThreadManagerDuplicateValues
                        (splittedListDuplicateValidValues.get(0));
                ThreadManagerDuplicateValues tm6 = new ThreadManagerDuplicateValues
                        (splittedListDuplicateValidValues.get(1));
                ThreadManagerDuplicateValues tm7 = new ThreadManagerDuplicateValues
                        (splittedListDuplicateValidValues.get(2));
                ThreadManagerDuplicateValues tm8 = new ThreadManagerDuplicateValues
                        (splittedListDuplicateValidValues.get(3));

                // creating thread dup values
                thread5 = new Thread(tm5);
                thread6 = new Thread(tm6);
                thread7 = new Thread(tm7);
                thread8 = new Thread(tm8);

                thread5.start();
                thread6.start();
                thread7.start();
                thread8.start();
            }

            if(employeesNullValues.size() > 0) {
                // splitting in 4 lists
                splittedListNullValidValues =
                        sp.splitList(300, employeesNullValues);

                // dropping, creating table null employee tables
                dbDriver.clearThreadNullTable();
                dbDriver.createThreadTableNullEmployee();

                ThreadManagerNullValues tm9 = new ThreadManagerNullValues
                        (splittedListNullValidValues.get(0));
                ThreadManagerNullValues tm10 = new ThreadManagerNullValues
                        (splittedListNullValidValues.get(1));
                ThreadManagerNullValues tm11 = new ThreadManagerNullValues
                        (splittedListNullValidValues.get(2));
                ThreadManagerNullValues tm12 = new ThreadManagerNullValues
                        (splittedListNullValidValues.get(3));

                // creating thread null values
                thread9 = new Thread(tm9);
                thread10 = new Thread(tm10);
                thread11 = new Thread(tm11);
                thread12 = new Thread(tm12);

                thread9.start();
                thread10.start();
                thread11.start();
                thread12.start();
            }

            // creating thread manager unique values
            ThreadManagerUniqueValues tm1 = new ThreadManagerUniqueValues
                    (splittedListUniqueValidValues.get(0));
            ThreadManagerUniqueValues tm2 = new ThreadManagerUniqueValues
                    (splittedListUniqueValidValues.get(1));
            ThreadManagerUniqueValues tm3 = new ThreadManagerUniqueValues
                    (splittedListUniqueValidValues.get(2));
            ThreadManagerUniqueValues tm4 = new ThreadManagerUniqueValues
                    (splittedListUniqueValidValues.get(3));

            // creating thread
            Thread thread1 = new Thread(tm1);
            Thread thread2 = new Thread(tm2);
            Thread thread3 = new Thread(tm3);
            Thread thread4 = new Thread(tm4);

            // calculating start time
            long startTimeThreads = ct.calculateStartTime();

            // dropping, creating table null employee
            dbDriver.clearThreadNullTable();
            dbDriver.createThreadTableNullEmployee();

            // dropping, creating table unique employee tables
            dbDriver.clearThreadUniqueTable();
            dbDriver.createThreadTableUniqueEmployee();

            // starting threads
            thread1.start();
            thread2.start();
            thread3.start();
            thread4.start();

            // waiting for threads to finish so that we can calculate the time taken
            thread1.join();
            thread2.join();
            thread3.join();
            thread4.join();

            // if thread 5 is null also 6-7-8 are
            if(thread5 != null) {
                // waiting for threads to finish so that we can calculate the time taken
                thread5.join();
                thread6.join();
                thread7.join();
                thread8.join();
            }

            // if thread 9 is null also 6-7-8 are
            if(thread9 != null) {
                // waiting for threads to finish so that we can calculate the time taken
                thread9.join();
                thread10.join();
                thread11.join();
                thread12.join();
            }

            long durationThreads = ct.calculateEndTime(startTimeThreads);

            // printing time taken
            dInfo.printTimeTaken(durationThreads);

            // stopping the program in order to let the user read info
            TimeUnit.SECONDS.sleep(10);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}