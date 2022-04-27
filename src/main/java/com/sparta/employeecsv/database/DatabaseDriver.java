package com.sparta.employeecsv.database;

import com.sparta.employeecsv.model.Employee;

import java.sql.*;
import java.util.List;

public class DatabaseDriver {
    private Connection connection;

    public DatabaseDriver() throws SQLException {
        connection = ConnectionFactory.getConnection();
    }

    public void createTable()   {
        try {
            //create the employee list table in the database
            String createTable = "Create Table EMPLOYEE-RECORDS " +
                    "EmployeeID VARCHAR(6)," +
                    "NamePrefix VARCHAR(6)," +
                    "FirstName VARCHAR(25)," +
                    "MiddleInitial CHAR(1)," +
                    "LastName VARCHAR(25)," +
                    "Gender CHAR(1)," +
                    "Email VARCHAR(50),"  +
                    "DateOfBirth DATE,"   +
                    "DateOfJoining DATE,"  +
                    "Salary DECIMAL(10,2)," +
                    "PRIMARY KEY (EmployeeID)" +
                    ");";

            Statement st = connection.createStatement();
            st.executeUpdate(createTable);
            st.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void populateTable(List<Employee> employeeList) {

        String dbInsert = "INSERT INTO EMPLOYEE-RECORDS " +
                "(EmployeeID, NamePrefix, FirstName, MiddleInitial, LastName, " +
                "Gender, Email, DateOfBirth, DateOfJoining, Salary)" +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement ps = connection.prepareStatement(dbInsert);

            for (Employee employee : employeeList)  {
                ps.setString(1, employee.getEmployeeID());
                ps.setString(2, employee.getNamePrefix());
                ps.setString(3, employee.getFirstName());
                ps.setString(4, employee.getMiddleInitial().toString());
                ps.setString(5, employee.getLastName());
                ps.setString(6, employee.getGender().toString());
                ps.setString(7, employee.getEmailAddress());
                ps.setDate(8, employee.getDateOfBirth());
                ps.setDate(9, employee.getDateOfJoining());
                ps.setFloat(10, employee.getSalary());

                ps.executeUpdate();
            }

            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
