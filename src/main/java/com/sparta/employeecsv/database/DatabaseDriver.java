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

    // this function will be used to create tables
    public String createTables() {
        StringBuilder str = new StringBuilder();
        str.append("EmployeeID VARCHAR(6),");
        str.append("NamePrefix VARCHAR(6),");
        str.append("FirstName VARCHAR(25),");
        str.append("MiddleInitial CHAR(1),");
        str.append("LastName VARCHAR(25),");
        str.append("Gender CHAR(1),");
        str.append("Email VARCHAR(50),");
        str.append("DateOfBirth DATE,");
        str.append("DateOfJoining DATE,");
        str.append("Salary DECIMAL(10,2)");
        return str.toString();
    }

    // this function will be used to populate data
    public void populateData(PreparedStatement ps, List<Employee> employeeList) {
        try {
            for(Employee employee: employeeList) {
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void loadPropertiesFile(Properties sqlProps) throws IOException {
        sqlProps.load(new FileReader("sql.properties"));
    }

    public void createTableUniqueEmployee() {
        try {
            //create the employee list table in the database
            String createTable = "CREATE TABLE EMPLOYEE_RECORDS (" +
                    createTables() + "," +
                    "PRIMARY KEY (EmployeeID)" +
                    ");";
            Statement st = connection.createStatement();
            st.executeUpdate(createTable);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createTableNullEmployee() {
        try {
            //create the employee list table in the database
            String createTable = "CREATE TABLE NULL_EMPLOYEE_RECORDS (" +
                    createTables() + "," +
                    "`idNULL_EMPLOYEE_RECORDS` INT NOT NULL AUTO_INCREMENT,\n" +
                    "PRIMARY KEY (`idNULL_EMPLOYEE_RECORDS`)" +
                    ");";
            Statement st = connection.createStatement();
            st.executeUpdate(createTable);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createThreadTableNullEmployee() {
        try {
            //create the employee list table in the database
            String createTable = "CREATE TABLE THREAD_NULL_EMPLOYEE_RECORDS (" +
                    createTables() + "," +
                    "`idTHREAD_NULL_EMPLOYEE_RECORDS` INT NOT NULL AUTO_INCREMENT,\n" +
                    "PRIMARY KEY (`idTHREAD_NULL_EMPLOYEE_RECORDS`)" +
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
                    createTables() + "," +
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
                    createTables() + ");";
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
                    createTables() + ");";
            Statement st = connection.createStatement();
            st.executeUpdate(createThreadDupTable);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void populateTableDuplicateEmployee(List<Employee> employeeList) {
        //for each employee in the list get their details and add it to the database
        try (PreparedStatement ps = connection.prepareStatement(getInsertDuplicatesSQL())) {
            populateData(ps, employeeList);
            System.out.println("EMPLOYEE_DUP_RECORDS updated correctly");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void populateTableNullEmployee(List<Employee> employeeList) {
        //for each employee in the list get their details and add it to the database
        try (PreparedStatement ps = connection.prepareStatement(getInsertNullSQL())) {
            populateData(ps, employeeList);
            System.out.println("NULL_EMPLOYEE_RECORDS updated correctly");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void populateThreadTableNullEmployee(List<Employee> employeeList) {
        //for each employee in the list get their details and add it to the database
        try (PreparedStatement ps = connection.prepareStatement(getInsertThreadNullSQL())) {
            populateData(ps, employeeList);
            System.out.println("THREAD_NULL_EMPLOYEE_RECORDS updated correctly");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // populate large dup table
    public void populateThreadTableDuplicateEmployee(List<Employee> employeeList) {
        //for each employee in the list get their details and add it to the database
        try (PreparedStatement ps = connection.prepareStatement(getInsertThreadDuplicatesSQL())) {
            populateData(ps, employeeList);
            System.out.println("THREAD_EMPLOYEE_DUP_RECORDS updated correctly");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void populateTableUniqueEmployee(List<Employee> employeeList) {
        //for each employee in the list get their details and add it to the database
        try (PreparedStatement ps = connection.prepareStatement(getInsertSQL())) {
            populateData(ps, employeeList);
            System.out.println("EMPLOYEE_RECORDS updated correctly");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // populate large unique table
    public void populateTableMultiList(List<Employee> employeeList) {
        //for each employee in the list get their details and add it to the database
        try (PreparedStatement ps = connection.prepareStatement(getInsertMultiListSQL())) {
            populateData(ps, employeeList);
            System.out.println("THREAD_EMPLOYEE_RECORDS updated correctly");
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    public void clearDuplicateTable() {
        //drop the table from the database
        String drop = "DROP TABLE IF EXISTS EMPLOYEE_DUP_RECORDS";
        try {
            Statement st = connection.createStatement();
            st.executeUpdate(drop);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void clearNullTable() {
        //drop the table from the database
        String drop = "DROP TABLE IF EXISTS NULL_EMPLOYEE_RECORDS";
        try {
            Statement st = connection.createStatement();
            st.executeUpdate(drop);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void clearThreadNullTable() {
        //drop the table from the database
        String drop = "DROP TABLE IF EXISTS THREAD_NULL_EMPLOYEE_RECORDS";
        try {
            Statement st = connection.createStatement();
            st.executeUpdate(drop);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void clearThreadDuplicateTable() {
        //drop the table from the database
        String drop = "DROP TABLE IF EXISTS THREAD_EMPLOYEE_DUP_RECORDS";
        try {
            Statement st = connection.createStatement();
            st.executeUpdate(drop);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void clearUniqueTable() {
        //drop the table from the database
        String drop = "DROP TABLE IF EXISTS EMPLOYEE_RECORDS";
        try {
            Statement st = connection.createStatement();
            st.executeUpdate(drop);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void clearThreadUniqueTable() {
        //drop the table from the database
        String drop = "DROP TABLE IF EXISTS THREAD_EMPLOYEE_RECORDS";
        try {
            Statement st = connection.createStatement();
            st.executeUpdate(drop);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private String getInsertNullSQL() {
        Properties sqlProps = new Properties();
        try {
            loadPropertiesFile(sqlProps);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sqlProps.getProperty("db.sql-insert-null");
    }

    private String getInsertThreadNullSQL() {
        Properties sqlProps = new Properties();
        try {
            loadPropertiesFile(sqlProps);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sqlProps.getProperty("db.sql-insert-null-thread");
    }

    private String getInsertDuplicatesSQL() {
        Properties sqlProps = new Properties();
        try {
            loadPropertiesFile(sqlProps);
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
            loadPropertiesFile(sqlProps);
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
            loadPropertiesFile(sqlProps);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sqlProps.getProperty("db.sql-insert");
    }

    private String getInsertMultiListSQL() throws IOException {
        //get the sql insert property from the properties file
        Properties sqlProps = new Properties();
        try {
            loadPropertiesFile(sqlProps);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return sqlProps.getProperty("db.sql-insert-thread");
    }
}