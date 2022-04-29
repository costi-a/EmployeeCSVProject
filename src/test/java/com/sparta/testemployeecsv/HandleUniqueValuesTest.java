package com.sparta.testemployeecsv;

import com.sparta.employeecsv.model.HandleUniqueValues;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class HandleUniqueValuesTest {
    private HandleUniqueValues handleUnique;

    @BeforeEach
    public void setUp() {
        handleUnique = new HandleUniqueValues();
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
}
