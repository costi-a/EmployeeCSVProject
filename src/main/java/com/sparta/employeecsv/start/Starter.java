package com.sparta.employeecsv.start;
import controller.Manager;

import java.util.concurrent.TimeUnit;

public class Starter {
    public static void main(String... args)  {
        try {
            Manager m = new Manager();
            // m.manageProgram("EmployeeRecords.csv");
            m.manageProgram("EmployeeRecordsLarge.csv");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}