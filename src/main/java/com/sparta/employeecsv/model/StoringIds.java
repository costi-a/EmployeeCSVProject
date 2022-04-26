package com.sparta.employeecsv.model;

import java.util.LinkedList;

public class StoringIds {
    public LinkedList<String> storeIds(LinkedList<Employee> employeesList) {
        try {
            // here we will add all the ids
            LinkedList<String> ids = new LinkedList<>();

            for(int i = 0; i < employeesList.size(); i++) {
                Employee employee = employeesList.get(i);
                ids.add(employee.getEmployeeID());
            }
            return ids;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
