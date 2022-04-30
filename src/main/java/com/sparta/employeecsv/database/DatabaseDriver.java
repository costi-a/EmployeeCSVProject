package com.sparta.employeecsv.database;
import com.sparta.employeecsv.model.Employee;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
public class DatabaseDriver {
    private static Connection connection;

    public DatabaseDriver() {
        connection = ConnectionFactory.getConnection();
    }

    public void createTableUniqueEmployee() {
        try {
            //create the employee list table in the database
            String createTable = "CREATE TABLE EMPLOYEE_RECORDS (" +
                    "EmployeeID VARCHAR(6)," +
                    "NamePrefix VARCHAR(6)," +
                    "FirstName VARCHAR(25)," +
                    "MiddleInitial CHAR(1)," +
                    "LastName VARCHAR(25)," +
                    "Gender CHAR(1)," +
                    "Email VARCHAR(50)," +
                    "DateOfBirth DATE," +
                    "DateOfJoining DATE," +
                    "Salary DECIMAL(10,2)," +
                    "PRIMARY KEY (EmployeeID)" +
                    ");";
            Statement st = connection.createStatement();
            st.executeUpdate(createTable);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // table for large file
    public void createThreadTableUniqueEmployee() {
        try {
            //create the employee list table in the database
            String createThreadTable = "CREATE TABLE THREAD_EMPLOYEE_RECORDS (" +
                    "EmployeeID VARCHAR(6)," +
                    "NamePrefix VARCHAR(6)," +
                    "FirstName VARCHAR(25)," +
                    "MiddleInitial CHAR(1)," +
                    "LastName VARCHAR(25)," +
                    "Gender CHAR(1)," +
                    "Email VARCHAR(50)," +
                    "DateOfBirth DATE," +
                    "DateOfJoining DATE," +
                    "Salary DECIMAL(10,2)," +
                    "PRIMARY KEY (EmployeeID)" +
                    ");";
            Statement st = connection.createStatement();
            st.executeUpdate(createThreadTable);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createTableDuplicatesEmployee() {
        try {
            //create the employee list table in the database
            String createDupTable = "CREATE TABLE EMPLOYEE_DUP_RECORDS (" +
                    "EmployeeID VARCHAR(6)," +
                    "NamePrefix VARCHAR(6)," +
                    "FirstName VARCHAR(25)," +
                    "MiddleInitial CHAR(1)," +
                    "LastName VARCHAR(25)," +
                    "Gender CHAR(1)," +
                    "Email VARCHAR(50)," +
                    "DateOfBirth DATE," +
                    "DateOfJoining DATE," +
                    "Salary DECIMAL(10,2)" +
                    ");";
            Statement st = connection.createStatement();
            st.executeUpdate(createDupTable);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createThreadTableDuplicatesEmployee() {
        try {
            //create the employee list table in the database
            String createThreadDupTable = "CREATE TABLE THREAD_EMPLOYEE_DUP_RECORDS (" +
                    "EmployeeID VARCHAR(6)," +
                    "NamePrefix VARCHAR(6)," +
                    "FirstName VARCHAR(25)," +
                    "MiddleInitial CHAR(1)," +
                    "LastName VARCHAR(25)," +
                    "Gender CHAR(1)," +
                    "Email VARCHAR(50)," +
                    "DateOfBirth DATE," +
                    "DateOfJoining DATE," +
                    "Salary DECIMAL(10,2)" +
                    ");";
            Statement st = connection.createStatement();
            st.executeUpdate(createThreadDupTable);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void populateTableDuplicateEmployee(List<Employee> employeeList) {
        //for each employee in the list get their details and add it to the database
        try (PreparedStatement ps = connection.prepareStatement(getInsertDuplicatesSQL())) {
            for (Employee employee : employeeList) {
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
            System.out.println("EMPLOYEE_DUP_RECORDS updated correctly");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // populate large dup table
    public void populateThreadTableDuplicateEmployee(List<Employee> employeeList) {
        //for each employee in the list get their details and add it to the database
        try (PreparedStatement ps = connection.prepareStatement(getInsertThreadDuplicatesSQL())) {
            for (Employee employee : employeeList) {
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
            System.out.println("THREAD_EMPLOYEE_DUP_RECORDS updated correctly");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void populateTableUniqueEmployee(List<Employee> employeeList) {
        //for each employee in the list get their details and add it to the database
        try (PreparedStatement ps = connection.prepareStatement(getInsertSQL())) {
            for (Employee employee : employeeList) {
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
            System.out.println("EMPLOYEE_RECORDS updated correctly");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // populate large unique table
    public void populateTableMultiList(List<Employee> employeeList) {
        //for each employee in the list get their details and add it to the database
        try (PreparedStatement ps = connection.prepareStatement(getInsertMultiListSQL())) {
            for (Employee employee : employeeList) {
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
            System.out.println("THREAD_EMPLOYEE_RECORDS updated correctly");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void clearDuplicateTable()    {
        //drop the table from the database
        String drop = "DROP TABLE IF EXISTS EMPLOYEE_DUP_RECORDS";
        try {
            Statement st = connection.createStatement();
            st.executeUpdate(drop);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // large file
    public void clearThreadDuplicateTable()    {
        //drop the table from the database
        String drop = "DROP TABLE IF EXISTS THREAD_EMPLOYEE_DUP_RECORDS";
        try {
            Statement st = connection.createStatement();
            st.executeUpdate(drop);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void clearUniqueTable()    {
        //drop the table from the database
        String drop = "DROP TABLE IF EXISTS EMPLOYEE_RECORDS";
        try {
            Statement st = connection.createStatement();
            st.executeUpdate(drop);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // large file
    public void clearThreadUniqueTable()    {
        //drop the table from the database
        String drop = "DROP TABLE IF EXISTS THREAD_EMPLOYEE_RECORDS";
        try {
            Statement st = connection.createStatement();
            st.executeUpdate(drop);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private String getInsertDuplicatesSQL() {
        Properties sqlProps = new Properties();
        try {
            sqlProps.load(new FileReader("sql.properties"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sqlProps.getProperty("db.sql-insert-duplicates");
    }

    private String getInsertThreadDuplicatesSQL() {
        Properties sqlProps = new Properties();
        try {
            sqlProps.load(new FileReader("sql.properties"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sqlProps.getProperty("db.sql-insert-duplicates-thread");
    }

    private String getInsertSQL() {
        //get the sql insert property from the properties file
        Properties sqlProps = new Properties();
        try {
            sqlProps.load(new FileReader("sql.properties"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sqlProps.getProperty("db.sql-insert");
    }

    private String getInsertMultiListSQL() {
        //get the sql insert property from the properties file
        Properties sqlProps = new Properties();
        try {
            sqlProps.load(new FileReader("sql.properties"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sqlProps.getProperty("db.sql-insert-thread");
    }
}