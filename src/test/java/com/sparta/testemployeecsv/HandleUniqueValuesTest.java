package com.sparta.testemployeecsv;

import com.sparta.employeecsv.model.Employee;
import com.sparta.employeecsv.model.EmployeeValidator;
import com.sparta.employeecsv.model.HandleUniqueValues;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class HandleUniqueValuesTest {
    private HandleUniqueValues handleUnique;
    private Employee employee;
    private Employee employee2;
    private EmployeeValidator employeeParser;

    @BeforeEach
    public void setUp() {
        handleUnique = new HandleUniqueValues();
        employeeParser = new EmployeeValidator();
    }

    @Test
    public void testCounterUniqueValuesInsertOneValue() {
        Map<String, Integer> mapIds = new HashMap<>();
        mapIds.put("111111", 1);

        int result = handleUnique.calculateUniqueIds(mapIds);
        Assertions.assertEquals(1, result);
    }

    @Test
    public void testCounterUniqueValuesInsertTwoValues() {
        Map<String, Integer> mapIds = new HashMap<>();
        mapIds.put("111111", 1);
        mapIds.put("111112", 1);

        int result = handleUnique.calculateUniqueIds(mapIds);
        Assertions.assertEquals(2, result);
    }

    @Test
    public void testCounterUniqueValuesInsertThreeValuesWithOneDuplicate() {
        Map<String, Integer> mapIds = new HashMap<>();
        mapIds.put("111111", 1);
        mapIds.put("111112", 1);
        mapIds.put("111113", 2);

        int result = handleUnique.calculateUniqueIds(mapIds);
        Assertions.assertEquals(2, result);
    }

    @Test
    public void testCounterUniqueValuesInsertZeroValues() {
        Map<String, Integer> mapIds = new HashMap<>();

        int result = handleUnique.calculateUniqueIds(mapIds);
        Assertions.assertEquals(0, result);
    }

    @Test
    public void testReturnUniqueEmployeesWithOneEmployee() throws ParseException {
        employee = employeeParser.parseEmployee("111111", "Mrs.",
                "fakeName", "I", "fakeLastName",
                "F", "fake@email.com", "1982/01/05",
                "2008/01/02", "69294.04");

        LinkedList<Employee> employeesList = new LinkedList<>();
        employeesList.add(employee);

        Map<String, Integer> mapIds = new HashMap<>();
        mapIds.put("111111", 1);

        LinkedList<Employee> result = handleUnique.returnUniqueEmployees(employeesList, mapIds);

        Assertions.assertEquals(employeesList, result);
    }

    @Test
    public void testReturnUniqueEmployeesWithTwoEmployees() throws ParseException {
        employee = employeeParser.parseEmployee("111111", "Mrs.",
                "fakeName", "I", "fakeLastName",
                "F", "fake@email.com", "1982/01/05",
                "2008/01/02", "69294.04");

        employee2 = employeeParser.parseEmployee("111112", "Mrs.",
                "fakeName", "I", "fakeLastName",
                "F", "fake@email.com", "1982/01/05",
                "2008/01/02", "69294.04");

        LinkedList<Employee> employeesList = new LinkedList<>();
        employeesList.add(employee);
        employeesList.add(employee2);

        Map<String, Integer> mapIds = new HashMap<>();
        mapIds.put("111111", 1);
        mapIds.put("111112", 1);

        LinkedList<Employee> result = handleUnique.returnUniqueEmployees(employeesList, mapIds);

        Assertions.assertEquals(employeesList, result);
    }

    @Test
    public void testReturnUniqueEmployeesWithTwoEmployeesOneDuplicate() throws ParseException {
        employee = employeeParser.parseEmployee("111111", "Mrs.",
                "fakeName", "I", "fakeLastName",
                "F", "fake@email.com", "1982/01/05",
                "2008/01/02", "69294.04");

        LinkedList<Employee> employeesList = new LinkedList<>();
        employeesList.add(employee);

        Map<String, Integer> mapIds = new HashMap<>();
        mapIds.put("111111", 1);
        mapIds.put("111112", 2);

        LinkedList<Employee> result = handleUnique.returnUniqueEmployees(employeesList, mapIds);

        Assertions.assertEquals(employeesList, result);
    }
}
