package com.sparta.employeecsv.model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;

public class EmployeeFileReader {

    private static ArrayList<Employee> employeesList;
    //private ArrayList<Employee> duplicatesList;
    
    public static void readFile(String file) throws FileNotFoundException {
        
        employeesList = new ArrayList<>();
        //duplicatesList = new ArrayList<>();

        EmployeeRegexParser employeeParser = new EmployeeRegexParser();

        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            String line;
            br.readLine();

            while ((line = br.readLine()) != null)   {
                String[] values = line.split(",");
                Employee employee = employeeParser.parseEmployee(values[0],values[1],
                        values[2], values[3], values[4], values[5],
                        values[6], values[7], values[8], values[9]);

                employeesList.add(employee);

            }
        } catch (ParseException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
