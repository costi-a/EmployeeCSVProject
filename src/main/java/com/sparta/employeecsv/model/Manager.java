package com.sparta.employeecsv.model;
import DAO.EmployeeDAO;
import com.sparta.employeecsv.database.ConnectionFactory;
import com.sparta.employeecsv.database.DatabaseDriver;
import com.sparta.employeecsv.display.DisplayInfo;
import java.io.BufferedReader;
import java.util.LinkedList;
import java.util.Map;
public class Manager {
    public void manageProgram() {
        try {
            // reading the file
            EmployeeFileReader rf = new EmployeeFileReader();
            BufferedReader br = rf.readFile("EmployeeRecords.csv");

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
            LinkedList<Employee> uniqueEmployees = uniqueValues.returnUniqueEmployees(employeesList,
                    mapIds);

            // dropping and creating table
            DatabaseDriver dbDriver = new DatabaseDriver();

            // calculating first parameter for method calculate time taken
            CalculateTimeTaken ct = new CalculateTimeTaken();
            long startTimeSeconds = ct.calculateStartTime();

            // dropping, creating table unique employee tables
            dbDriver.clearUniqueTable();
            dbDriver.createTableUniqueEmployee();

            // dropping, creating table duplicate employee tables
            dbDriver.clearDuplicateTable();
            dbDriver.createTableDuplicatesEmployee();

            // populating the table with duplicate ids employees
            dbDriver.populateTableDuplicateEmployee(duplicatesEmployees);

            // populating the table with unique ids employees
            dbDriver.populateTableUniqueEmployee(uniqueEmployees);

            // calculating time taken
            long duration = ct.calculateEndTime(startTimeSeconds);

            // printing time taken
            dInfo.printTimeTaken(duration);

            // finding and removing employee by ID
            EmployeeDAO eDAO = new EmployeeDAO();
            Employee employee = eDAO.getEmployeeByID("198429");
            System.out.println("emp " + employee);
            eDAO.deleteEmployeeById("111282");

            // closing connection
            ConnectionFactory cf = new ConnectionFactory();
            cf.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}