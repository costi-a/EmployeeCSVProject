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

    public DatabaseDriver() throws SQLException {
        connection = ConnectionFactory.getConnection();
    }

    public void createTable() {
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

    public void populateTable(LinkedList<Employee> employeeList) {
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

    public void clearTable()    {
        //drop the table from the database
        String drop = "DROP TABLE IF EXISTS EMPLOYEE_RECORDS";
        try {
            Statement st = connection.createStatement();
            st.executeUpdate(drop);
        } catch (SQLException e) {
            e.printStackTrace();
        }
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

    public ArrayList<Employee> getEmployees()   {

        String query = "SELECT * FROM EMPLOYEE_RECORDS";
        ArrayList<Employee> list = new ArrayList<>();
        Employee employee = null;
        ResultSet rs = null;

        try {
            Statement statement = connection.createStatement();
            rs = statement.executeQuery(query);

            while (rs.next())   {
                employee = new Employee();
                //for each employee set and store the details
                employee.setEmployeeID(rs.getString("EmployeeID"));
                employee.setNamePrefix(rs.getString("NamePrefix"));
                employee.setFirstName(rs.getString("FirstName"));
                employee.setMiddleInitial((rs.getString("MiddleInitial")).charAt(0));
                employee.setLastName(rs.getString("LastName"));
                employee.setGender((rs.getString("Gender")).charAt(0));
                employee.setEmailAddress(rs.getString("Email"));
                employee.setDateOfBirth(rs.getDate("DateOfBirth"));
                employee.setDateOfJoining(rs.getDate("DateOfJoining"));
                employee.setSalary(rs.getFloat("Salary"));

                list.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;

    }

    public Employee getEmployeeByID(int employeeID) {
        String query = "SELECT * FROM EMPLOYEE_RECORDS where EmployeeID = ?";
        List<Employee> list = new ArrayList<Employee>();

        Employee employee = null;
        ResultSet rs = null;

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, employeeID);
            rs = ps.executeQuery();

            while (rs.next())   {
                employee = new Employee();
                //retrieve the employee info corresponding to the employee id
                employee.setEmployeeID(rs.getString("EmployeeID"));
                employee.setNamePrefix(rs.getString("NamePrefix"));
                employee.setFirstName(rs.getString("FirstName"));
                employee.setMiddleInitial((rs.getString("MiddleInitial")).charAt(0));
                employee.setLastName(rs.getString("LastName"));
                employee.setGender((rs.getString("Gender")).charAt(0));
                employee.setEmailAddress(rs.getString("Email"));
                employee.setDateOfBirth(rs.getDate("DateOfBirth"));
                employee.setDateOfJoining(rs.getDate("DateOfJoining"));
                employee.setSalary(rs.getFloat("Salary"));
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }
}