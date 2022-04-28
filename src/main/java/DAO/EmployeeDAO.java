package DAO;

import com.sparta.employeecsv.database.ConnectionFactory;
import com.sparta.employeecsv.model.Employee;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class EmployeeDAO{
    public boolean createEmployee(Employee newEmployee){
        Employee employee = new Employee();
        return false;
    }
    public Employee getEmployeeById(int employee_id){
        Connection conn = ConnectionFactory.getConnection();
        Employee result;
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(
                    "SELECT * FROM EMPLOYEE_RECORDS WHERE employee_id=" + employee_id);
            if (rs.next() == false) return null;
            result = new Employee(
                    rs.getString("EmployeeID"),
                    rs.getString("NamePrefix"),
                    rs.getString("FirstName"),
                    rs.getString("MiddleInitial"),
                    rs.getString("LastName"),
                    rs.getString("Gender"),
                    rs.getString("Email"),
                    rs.getDate("DateOfBirth"),
                    rs.getDate("DateOfJoining"),
                    rs.getInt("Salary"),
                    rs.getTimestamp("last_update"));
        } catch(SQLException e){
            e.printStackTrace();
            result = null;
        }
        return result;
    }
    public ArrayList<Employee> getAllEmployees(){
        return null;
    }
    public boolean deleteEmployeeById(int employee_id){
        return false;
    }
    public boolean updateEmployee(Employee newEmployeeState){
        return false;
    }
}