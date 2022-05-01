package com.sparta.employeecsv.model.threads;

import com.sparta.employeecsv.database.ConnectionFactory;
import com.sparta.employeecsv.database.DatabaseDriver;
import com.sparta.employeecsv.model.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Map;

import static com.sparta.employeecsv.model.threads.EmployeeThreads.splitList;

public class LargeThreadTest {

    public static void main(String[] args) throws IOException, SQLException, InterruptedException {

        EmployeeFileReader rf = new EmployeeFileReader();
        BufferedReader br = rf.readFile("EmployeeRecordsLarge.csv");

        // saving employees into list
        SaveEmployees saveEmployees = new SaveEmployees();
        LinkedList employeesList = saveEmployees.saveEmployees(br);

        // storing ids that we will use with the HandleDuplicates
        StoringIds sIds = new StoringIds();
        LinkedList<String> ids = sIds.storeIds(employeesList);
        HandleDuplicates hd = new HandleDuplicates();

        // we first return an hashmap so that we have something like this:
        // {a: 1, b: 3, c: 5} (a: 1 means there is only 1 a, b: 3 means there are 3 b)
        // so that we can check duplicates and unique values
        Map<String, Integer> mapIds = hd.returnHashMapIds(ids);



        // calculating unique values
        HandleUniqueValues uniqueValues = new HandleUniqueValues();

        // calculating an employee list with unique ids
        LinkedList<Employee> uniqueEmployees = uniqueValues.returnUniqueEmployees(employeesList,
                mapIds);


        DatabaseDriver databaseDriver = new DatabaseDriver();
        databaseDriver.clearUniqueTable();
        databaseDriver.createTableUniqueEmployee();


        new EmployeeThreads(uniqueEmployees);
        ThreadManager tm = new ThreadManager();
        tm.createThreads(splitList(30000));

        tm.runThreads();

        ConnectionFactory cf = new ConnectionFactory();
        ConnectionFactory.closeConnection();

    }
}