package com.sparta.employeecsv.start;
import com.sparta.employeecsv.model.Manager;

public class Starter {
    public static void main(String... args)  {
        try {
            Manager m = new Manager();
            m.manageProgram();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
