package com.sparta.testemployeecsv;

import com.sparta.employeecsv.model.EmployeeFileReader;

import java.io.FileNotFoundException;

public class testClass {

    public static void main(String... args)  {
        try {
            EmployeeFileReader.readFile("EmployeeRecords.csv");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
