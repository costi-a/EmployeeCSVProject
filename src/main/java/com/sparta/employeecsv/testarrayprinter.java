package com.sparta.employeecsv;

import com.sparta.employeecsv.model.EmployeeFileReader;

import java.io.FileNotFoundException;

public class testarrayprinter {

    public static void main(String... args)  {
        try {
            EmployeeFileReader.readFile("EmployeeRecords.csv");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
