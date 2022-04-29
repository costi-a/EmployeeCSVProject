package com.sparta.employeecsv.model.test;

import com.sparta.employeecsv.model.Employee;
import com.sparta.employeecsv.model.HandleUniqueValues;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

class HandleUniqueValuesTest {
    private HandleUniqueValues handleUniqueValues;

    @Test
    @DisplayName("Calculate the number of unique Ids")
    void calculateUniqueIds() {
        handleUniqueValues = new HandleUniqueValues();
        Map<String, Integer> mapIds = new HashMap<>();
        mapIds.put("test1", 1);
        mapIds.put("test2", 1);
        mapIds.put("test3", 1);

        Assertions.assertEquals(3, handleUniqueValues.calculateUniqueIds(mapIds));
    }
}