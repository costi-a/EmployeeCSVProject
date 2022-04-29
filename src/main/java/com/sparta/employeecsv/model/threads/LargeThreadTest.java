package com.sparta.employeecsv.model.threads;

import com.sparta.employeecsv.database.DatabaseDriver;
import com.sparta.employeecsv.model.Employee;
import com.sparta.employeecsv.model.EmployeeFileReader;
import com.sparta.employeecsv.model.SaveEmployees;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;

public class LargeThreadTest {

    public static void main(String[] args) throws IOException, SQLException {

        EmployeeFileReader rf = new EmployeeFileReader();
        BufferedReader br = rf.readFile("EmployeeRecordsLarge.csv");

        // saving employees into list
        SaveEmployees saveEmployees = new SaveEmployees();
        LinkedList<Employee> employeesList = saveEmployees.saveEmployees(br);

        DatabaseDriver databaseDriver = new DatabaseDriver();
        databaseDriver.clearUniqueTable();
        databaseDriver.createTableUniqueEmployee();

        EmployeeThreads employeeThread = new EmployeeThreads(employeesList);

        employeeThread.run();


    }
}
