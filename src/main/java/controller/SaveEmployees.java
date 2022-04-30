package controller;

import com.sparta.employeecsv.model.Employee;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.LinkedList;

public class SaveEmployees {
    public LinkedList saveEmployees(BufferedReader br) throws IOException {
        try {
            // list where all the employees will be added
            LinkedList<Employee> employeesList = new LinkedList<>();
            // data validator
            EmployeeValidator employeeParser = new EmployeeValidator();

            String line;
            br.readLine();

            while ((line = br.readLine()) != null)   {
                String[] values = line.split(",");
                // validating data
                Employee employee = employeeParser.parseEmployee(values[0], values[1],
                            values[2], values[3], values[4], values[5],
                            values[6], values[7], values[8], values[9]);

                System.out.println("employee " + employee);
                System.out.println("id " + employee.getEmployeeID());

                // if one of data is null we don't add it to the list
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
                    System.out.println(employee.getEmployeeID());
                    employeesList.add(employee);
                }
            }

            return employeesList;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}