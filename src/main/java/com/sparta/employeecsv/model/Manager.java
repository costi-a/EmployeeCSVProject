package com.sparta.employeecsv.model;
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
            int duplicateIds = hd.calculateSumDuplicates(mapIds);

            // calculating unique values
            HandleUniqueValues uniqueValues = new HandleUniqueValues();
            int uniqueIds = uniqueValues.calculateUniqueIds(mapIds);

            // displaying the info
            DisplayInfo dInfo = new DisplayInfo();
            dInfo.printResults(duplicateIds, uniqueIds, employeesList);

            // dropping and creating table
            DatabaseDriver dbDriver = new DatabaseDriver();
            dbDriver.clearTable();
            dbDriver.createTable();

            // calculating an employee list with unique ids
            LinkedList<Employee> uniqueEmployees = uniqueValues.returnUniqueEmployees(employeesList, mapIds);
            // populating the table with unique ids employees
            dbDriver.populateTable(uniqueEmployees);

            // closing connection
            ConnectionFactory cf = new ConnectionFactory();
            cf.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}