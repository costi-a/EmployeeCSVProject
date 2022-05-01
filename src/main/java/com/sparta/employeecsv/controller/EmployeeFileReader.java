package com.sparta.employeecsv.controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class EmployeeFileReader {
    public BufferedReader readFile(String file) throws FileNotFoundException {
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            return br;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
