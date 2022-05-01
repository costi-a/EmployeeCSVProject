package controller;

import com.sparta.employeecsv.model.Employee;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SaveEmployees {
    public LinkedList saveEmployees(BufferedReader br) throws IOException {
        try {
            // list where all the employees will be added
            LinkedList<Employee> employeesList = new LinkedList<>();
            // data validator
            EmployeeValidator employeeParser = new EmployeeValidator();

            System.out.println("updating LinkedList<Employee> employeesList...");

            String line;
            br.readLine();

            while ((line = br.readLine()) != null)   {
                String[] values = line.split(",");
                // validating data
                Employee employee = employeeParser.parseEmployee(values[0], values[1],
                            values[2], values[3], values[4], values[5],
                            values[6], values[7], values[8], values[9]);
                employeesList.add(employee);
            }
            return employeesList;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public List storeEmployeesNullValues(LinkedList<Employee> employeesList) {
        // list where all the employees with null values will be added
        List<Employee> employeesNullList = new ArrayList<>();
        System.out.println("updating List<Employee> employeesNullList...");

        try {
            for(int i = 0; i < employeesList.size(); i++) {
                Employee employee = employeesList.get(i);

                boolean invalidData = employee.getEmployeeID() == null ||
                        employee.getNamePrefix() == null ||
                        employee.getFirstName() == null ||
                        employee.getMiddleInitial() == null ||
                        employee.getLastName() == null ||
                        employee.getGender() == null ||
                        employee.getEmailAddress() == null ||
                        employee.getDateOfBirth() == null ||
                        employee.getDateOfJoining() == null ||
                        employee.getSalary() == null;

                if(invalidData) {
                    employeesNullList.add(employee);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return employeesNullList;
    }

    public LinkedList storeEmployeesValidValues(LinkedList<Employee> employeesList) {
        // list where all the employees with null values will be added
        LinkedList<Employee> employeesValidList = new LinkedList<>();
        System.out.println("updating LinkedList<Employee> employeesValidList...");

        try {
            for(int i = 0; i < employeesList.size(); i++) {
                Employee employee = employeesList.get(i);

                boolean invalidData = employee.getEmployeeID() == null ||
                        employee.getNamePrefix() == null ||
                        employee.getFirstName() == null ||
                        employee.getMiddleInitial() == null ||
                        employee.getLastName() == null ||
                        employee.getGender() == null ||
                        employee.getEmailAddress() == null ||
                        employee.getDateOfBirth() == null ||
                        employee.getDateOfJoining() == null ||
                        employee.getSalary() == null;

                if(!invalidData) {
                    employeesValidList.add(employee);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return employeesValidList;
    }
}