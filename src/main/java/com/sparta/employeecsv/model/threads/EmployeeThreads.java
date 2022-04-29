package com.sparta.employeecsv.model.threads;

import com.sparta.employeecsv.database.ConnectionFactory;
import com.sparta.employeecsv.model.Employee;

import java.sql.Connection;
import java.util.LinkedList;

public class EmployeeThreads implements Runnable    {

    private LinkedList<Employee> employees;
    private  Connection connection;

    public EmployeeThreads(LinkedList<Employee> employees) {
        this.employees = employees;
        this.connection = ConnectionFactory.getConnection();
    }

    // split the list of employees into smaller lists of a given amount
    private LinkedList<LinkedList<Employee>> splitList(int splitSize) {

        LinkedList<LinkedList<Employee>> splitEmployeeList = new LinkedList<>();

        int listSize = (employees.size() / splitSize);
        //split into smaller lists
        for (int i = 0; i < listSize; i++)  {

            LinkedList<Employee> employeeList = new LinkedList<>();

            for (int j = 0; j < splitSize; j++) {
                employeeList.add(employees.get((i * splitSize) + j));
            }
            //add the split lists to the array list of lists
            splitEmployeeList.add(employeeList);
        }

        int listRemainders = (employees.size() % splitSize);
        //add the remaining employees to a list
        if (listRemainders > 0) {

            LinkedList<Employee> employeeList = new LinkedList<>();

            for (int i = (employees.size() - listRemainders); i < employees.size(); i++)    {

                employeeList.add(employees.get(i));
            }
        }
        return splitEmployeeList;
    }


    @Override
    public void run() {
        LinkedList<LinkedList<Employee>> splitEmployeeList = splitList(200);
        //split the list into 200 threads and run them in the manager

        ThreadManager tm = new ThreadManager(splitEmployeeList);
        try {
            tm.runThreads();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
