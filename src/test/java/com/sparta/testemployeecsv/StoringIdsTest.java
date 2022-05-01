package com.sparta.testemployeecsv;

import com.sparta.employeecsv.model.Employee;
import com.sparta.employeecsv.controller.EmployeeValidator;
import com.sparta.employeecsv.controller.StoringIds;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.LinkedList;

public class StoringIdsTest {
    private StoringIds store;
    private Employee employee;
    private Employee employee2;

    @Test
    public void testStoreOneId() throws ParseException {
        EmployeeValidator employeeParser = new EmployeeValidator();
        employee = employeeParser.parseEmployee("111111", "Mrs.",
                "fakeName", "I", "fakeLastName",
                "F", "fake@email.com", "1982/01/05",
                "2008/01/02", "69294.04");

        LinkedList<Employee> employeesList = new LinkedList<>();
        employeesList.add(employee);
        store = new StoringIds();
        LinkedList<String> result = store.storeIds(employeesList);
        LinkedList<String> expected = new LinkedList<>();
        expected.add("111111");

        Assertions.assertEquals(expected, result);
    }

    @Test
    public void testStoreOneDuplicateId() throws ParseException {
        EmployeeValidator employeeParser = new EmployeeValidator();
        employee = employeeParser.parseEmployee("111111", "Mrs.",
                "fakeName", "I", "fakeLastName",
                "F", "fake@email.com", "1982/01/05",
                "2008/01/02", "69294.04");

        employee2 = employeeParser.parseEmployee("111111", "Mrs.",
                "fakeName", "I", "fakeLastName",
                "F", "fake@email.com", "1982/01/05",
                "2008/01/02", "69294.04");

        LinkedList<Employee> employeesList = new LinkedList<>();
        employeesList.add(employee);
        employeesList.add(employee2);
        store = new StoringIds();
        LinkedList<String> result = store.storeIds(employeesList);
        LinkedList<String> expected = new LinkedList<>();
        expected.add("111111");
        expected.add("111111");

        Assertions.assertEquals(expected, result);
    }

    @Test
    public void testStoreTwoDifferentId() throws ParseException {
        EmployeeValidator employeeParser = new EmployeeValidator();
        employee = employeeParser.parseEmployee("111112", "Mrs.",
                "fakeName", "I", "fakeLastName",
                "F", "fake@email.com", "1982/01/05",
                "2008/01/02", "69294.04");

        employee2 = employeeParser.parseEmployee("111111", "Mrs.",
                "fakeName", "I", "fakeLastName",
                "F", "fake@email.com", "1982/01/05",
                "2008/01/02", "69294.04");

        LinkedList<Employee> employeesList = new LinkedList<>();
        employeesList.add(employee);
        employeesList.add(employee2);
        store = new StoringIds();
        LinkedList<String> result = store.storeIds(employeesList);
        LinkedList<String> expected = new LinkedList<>();
        expected.add("111112");
        expected.add("111111");

        Assertions.assertEquals(expected, result);
    }
}