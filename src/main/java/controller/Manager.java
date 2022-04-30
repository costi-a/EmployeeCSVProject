package controller;
import com.sparta.employeecsv.database.ConnectionFactory;
import com.sparta.employeecsv.database.DatabaseDriver;
import com.sparta.employeecsv.display.DisplayInfo;
import com.sparta.employeecsv.model.Employee;
import controller.threads.ThreadManager;

import java.io.BufferedReader;
import java.util.ArrayList;
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

            // storing ids that we will use with the HandleDuplicates
            StoringIds sIds = new StoringIds();
            LinkedList<String> ids = sIds.storeIds(employeesList);
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
                    employeesList);

            // calculating unique values
            HandleUniqueValues uniqueValues = new HandleUniqueValues();
            int uniqueIds = uniqueValues.calculateUniqueIds(mapIds);

            // displaying the info
            DisplayInfo dInfo = new DisplayInfo();
            dInfo.printResults(duplicateIdsInt, uniqueIds, employeesList);

            // calculating an employee list with unique ids
            List<Employee> uniqueEmployees = uniqueValues.returnUniqueEmployees(employeesList,
                    mapIds);

            // dropping and creating table
            DatabaseDriver dbDriver = new DatabaseDriver();

            // calculating first parameter for method calculate time taken
            CalculateTimeTaken ct = new CalculateTimeTaken();
            long startTimeSeconds = ct.calculateStartTime();

            // dropping, creating table unique employee tables
            dbDriver.clearUniqueTable();
            dbDriver.createTableUniqueEmployee();

            // populating the table with unique ids employees
            dbDriver.populateTableUniqueEmployee(uniqueEmployees);

            // dropping, creating table duplicate employee tables
            dbDriver.clearDuplicateTable();
            dbDriver.createTableDuplicatesEmployee();

            // populating the table with duplicate ids employees
            dbDriver.populateTableDuplicateEmployee(duplicatesEmployees);

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
            List<List<Employee>> splittedList = new ArrayList<>();

            if(uniqueEmployees.size() < 10000) {
                splittedList = sp.splitList(3000, uniqueEmployees);
            } else {
                splittedList = sp.splitList(17000, uniqueEmployees);
            }

            System.out.println("using threads...");
            TimeUnit.SECONDS.sleep(3);

            // creating thread manager
            ThreadManager tm1 = new ThreadManager(splittedList.get(0));
            ThreadManager tm2 = new ThreadManager(splittedList.get(1));
            ThreadManager tm3 = new ThreadManager(splittedList.get(2));
            ThreadManager tm4 = new ThreadManager(splittedList.get(3));

            // creating thread
            Thread thread1 = new Thread(tm1);
            Thread thread2 = new Thread(tm2);
            Thread thread3 = new Thread(tm3);
            Thread thread4 = new Thread(tm4);

            // calculating start time
            long startTimeThreads = ct.calculateStartTime();

            // dropping, creating table unique employee tables
            dbDriver.clearThreadUniqueTable();
            dbDriver.createThreadTableUniqueEmployee();

            if(duplicateIdsInt > 0) {
                // dropping, creating table duplicate employee tables
                dbDriver.clearThreadDuplicateTable();
                dbDriver.createThreadTableDuplicatesEmployee();

                // populating the table with duplicate ids employees
                dbDriver.populateThreadTableDuplicateEmployee(duplicatesEmployees);
            }

            // starting threads
            thread1.start();
            thread2.start();
            thread3.start();
            thread4.start();

            long durationThreads = ct.calculateEndTime(startTimeThreads);

            // printing time taken
            dInfo.printTimeTaken(durationThreads);

            // stopping the program in order to let the user read info
            TimeUnit.SECONDS.sleep(15);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}