package DAO;

import com.sparta.employeecsv.model.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeDAOTest {
    private EmployeeDAO employeeDAO;

    @Test
    void getEmployeesTest() {
        employeeDAO = new EmployeeDAO();
        ArrayList<Employee> list = new ArrayList<>();


    }

    @Test
    public void testReadEmployee() throws SQLException {
        employeeDAO = new EmployeeDAO();
        Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sys", "root", "Zidane.92");
        Statement statement = conn.createStatement();
        String query = "SELECT * FROM EMPLOYEE_RECORDS WHERE EmployeeID = 111282";
        ResultSet rs = statement.executeQuery(query);
        if(!rs.isBeforeFirst()){
            System.out.println("result set is empty");
        }
        else {
            System.out.println("Result set is not empty");
        }

    }


    @Test
    void testInsertEmployee() throws SQLException {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sys", "root", "Zidane.92");
            Statement statement = conn.createStatement();
            String query = " insert into employee_records (employeeID, namePrefix, firstName, middleInitial, " +
                    "lastName, gender, emailAddress, dateOfBirth, dateOfJoining, salary)"
                    + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement preparedStmt = conn.prepareStatement(query);

            preparedStmt.setString (1, "222222");
            preparedStmt.setString (2, "Mr.");
            preparedStmt.setString (3, "TestName");
            preparedStmt.setString (4, "X");
            preparedStmt.setString (5, "TestLastName");
            preparedStmt.setString (6, "M");
            preparedStmt.setString (7, "Test@email.com");
            preparedStmt.setDate (8, Date.valueOf("1982/12/05"));
            preparedStmt.setDate (9, Date.valueOf("2022/04/05"));
            preparedStmt.setFloat (10, (float) 5000.56);

            preparedStmt.execute();
            conn.close();
        } catch (Exception e)
        {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }
    }
}