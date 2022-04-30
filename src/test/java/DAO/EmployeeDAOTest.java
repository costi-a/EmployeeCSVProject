package DAO;

import com.sparta.employeecsv.model.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeDAOTest {

    @Test
    @DisplayName("1st - Test if insert works")
    void insertEmployeeTest() throws SQLException {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/sys", "root", "Zidane.92");
            Statement statement = conn.createStatement();
            String query = "INSERT INTO EMPLOYEE_RECORDS (employeeID, namePrefix, firstName, middleInitial, " +
                    "lastName, gender, email, dateOfBirth, dateOfJoining, salary)"
                    + " VALUES ('222222','Mr.','TestName','X','TestLastName','M','Test@email.com','1982/12/12','1999/12/12','5555.56')";

            PreparedStatement preparedStmt = conn.prepareStatement(query);
            boolean rs = preparedStmt.execute();
            if(!rs){
                System.out.println("new record created.");
            }
            else {
                System.out.println("new record not created.");
            }
        } catch (Exception e)
        {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }
    }

    @Test
    @DisplayName("2nd - Test to check if new record exists in the SQL database.")
    public void getEmployeeByIdTest() throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/sys", "root", "Zidane.92");
        Statement statement = conn.createStatement();
        String query = "SELECT * FROM EMPLOYEE_RECORDS WHERE EmployeeID = 222222";
        PreparedStatement preparedStmt = conn.prepareStatement(query);
        boolean rs = preparedStmt.execute();
        if(!rs){
            System.out.println("new record does not exist in the database.");
        }
        else {
            System.out.println("new record exist in the database.");
        }
    }

    @Test
    @DisplayName("3rd - Test to check if new record has been deleted from the SQL database.")
    public void EmployeeByIdTest() throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/sys", "root", "Zidane.92");
        Statement statement = conn.createStatement();
        String query = "DELETE FROM EMPLOYEE_RECORDS WHERE EmployeeID = 222222";
        PreparedStatement preparedStmt = conn.prepareStatement(query);
        boolean rs = preparedStmt.execute();
        if(!rs){
            System.out.println("new record deleted.");
        }
        else {
            System.out.println("new record not deleted.");
        }
        conn.close();
    }
}