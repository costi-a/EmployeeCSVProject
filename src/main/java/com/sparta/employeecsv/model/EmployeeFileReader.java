package com.sparta.employeecsv.model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class EmployeeFileReader {

    private ArrayList<Employee> employeesList;
    //private ArrayList<Employee> duplicatesList;
    
    public void readFile(String file) throws FileNotFoundException {
        
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
                employee

            }
        }catch (IOException e)  {
            e.printStackTrace();
        }
        
    }
}
