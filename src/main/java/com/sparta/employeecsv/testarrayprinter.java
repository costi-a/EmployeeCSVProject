package com.sparta.employeecsv;

import com.sparta.employeecsv.model.Employee;
import com.sparta.employeecsv.model.EmployeeFileReader;

import java.io.FileNotFoundException;

import static com.sparta.employeecsv.model.EmployeeFileReader.employeesList;

public class testarrayprinter {

    public static void main(String... args)  {
        try {
            EmployeeFileReader.readFile("EmployeeRecords.csv");

            System.out.println(employeesList);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
