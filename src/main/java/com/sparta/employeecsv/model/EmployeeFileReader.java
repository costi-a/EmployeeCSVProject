package com.sparta.employeecsv.model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;

public class EmployeeFileReader {
    public static HashMap<String, Employee> employeesList;
    private static ArrayList<Employee> duplicatesList;
    
    public static void readFile(String file) throws FileNotFoundException {
        employeesList = new HashMap<>();
        duplicatesList = new ArrayList<>();
        EmployeeValidator employeeParser = new EmployeeValidator();

        try {
            employeesList = new HashMap<String, Employee>();
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            String line;
            br.readLine();

            while ((line = br.readLine()) != null)   {
                String[] values = line.split(",");
                Employee employee = employeeParser.parseEmployee(values[0],values[1],
                        values[2], values[3], values[4], values[5],
                        values[6], values[7], values[8], values[9]);
                
                if(employeesList.containsKey(values[0]))   {
                    duplicatesList.add(employee);
                    employeesList.remove(values[0]);
                }
                employeesList.put(values[0], employee);
            }
        } catch (ParseException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
