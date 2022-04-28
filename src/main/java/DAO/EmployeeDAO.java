package DAO;

import com.sparta.employeecsv.database.ConnectionFactory;
import com.sparta.employeecsv.model.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO{

    private Connection connection;

    public EmployeeDAO(Connection connection) {
        this.connection = ConnectionFactory.getConnection();
    }

    public boolean createEmployee(Employee newEmployee){
        Employee employee = new Employee();
        return false;
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
    public boolean deleteEmployeeById(int employee_id){
        return false;
    }
    public boolean updateEmployee(Employee newEmployeeState){
        return false;
    }
}