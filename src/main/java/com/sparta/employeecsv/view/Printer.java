package com.sparta.employeecsv.view;

import com.sparta.employeecsv.model.Employee;

import java.util.Map;

public class Printer {


    public static void printEmployeeValues(Map<Integer, Employee> employeeMap) {
        for (Map.Entry<Integer, Employee> entry : employeeMap.entrySet()) {
            System.out.println(entry.getValue());
        }
    }

}
