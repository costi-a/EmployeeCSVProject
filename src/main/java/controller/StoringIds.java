package controller;

import com.sparta.employeecsv.model.Employee;

import java.util.LinkedList;

public class StoringIds {
    public LinkedList<String> storeIds(LinkedList<Employee> employeesList) {
        // here we will add all the ids
        LinkedList<String> ids = new LinkedList<>();
        /*
        try {
            for(int i = 0; i < employeesList.size(); i++) {
                Employee employee = employeesList.get(i);
                ids.add(employee.getEmployeeID());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ids; */
        try {
            employeesList.stream()
                    .filter(employee -> ids.add(employee.getEmployeeID()))
                    .toList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ids;
    }
}
