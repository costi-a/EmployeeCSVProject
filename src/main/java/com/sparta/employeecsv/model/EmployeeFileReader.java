package com.sparta.employeecsv.model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.LinkedList;
import java.util.List;

public class EmployeeFileReader {
    public void readFile(String file) throws FileNotFoundException {
        try {
            List<Employee> employeesList = new LinkedList<>();
            EmployeeValidator employeeParser = new EmployeeValidator();

            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            String line;
            br.readLine();

            while ((line = br.readLine()) != null)   {
                String[] values = line.split(",");
                System.out.println("id " + values[0]);
                Employee employee = employeeParser.parseEmployee(values[0], values[1],
                        values[2], values[3], values[4], values[5],
                        values[6], values[7], values[8], values[9]);
                
                employeesList.add(employee);
            }
            System.out.println("employee " + employeesList);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
