package com.sparta.testemployeecsv;

import com.sparta.employeecsv.model.EmployeeFileReader;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReadFileTest {
    private EmployeeFileReader employeeFileReader;

    @Test
    public void fileReaderTest() {
        try {
            employeeFileReader.readFile("EmployeeeRecords.csv");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
