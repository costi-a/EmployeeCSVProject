package com.sparta.employeecsv.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.LinkedList;
import java.util.List;

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
                // validating data and adding to the list
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
}