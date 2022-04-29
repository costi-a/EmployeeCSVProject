package com.sparta.employeecsv.model.test;

import com.sparta.employeecsv.model.HandleDuplicates;
import org.junit.jupiter.api.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class HandleDuplicatesTest {
    private HandleDuplicates handleDuplicates;

    @Test
    void returnHashMapIds() {
        handleDuplicates = new HandleDuplicates();

        LinkedList<String> mapIdsTest = new LinkedList<>();
        mapIdsTest.add("4514");
        mapIdsTest.add("4515");
        mapIdsTest.add("4515");
        mapIdsTest.add("4517");
        Map<String, Integer> mapIdsTestExpected = new HashMap<>();
        mapIdsTestExpected.put("4514", 1);
        mapIdsTestExpected.put("4515", 2);
        mapIdsTestExpected.put("4517", 1);
        Assertions.assertEquals(4, mapIdsTest.size());
        Assertions.assertEquals(mapIdsTestExpected, handleDuplicates.returnHashMapIds(mapIdsTest));
    }

    @Test
    @DisplayName("Return the sum of duplicated Ids")
    void calculateSumDuplicates() {
        handleDuplicates = new HandleDuplicates();

        Map<String, Integer> mapIdsTest = new HashMap<>();
        mapIdsTest.put("1234", 2);
        mapIdsTest.put("1235", 2);
        mapIdsTest.put("1236", 2);
        mapIdsTest.put("1237", 2);
        Assertions.assertEquals(8, handleDuplicates.calculateSumDuplicates(mapIdsTest));
    }

    @Test
    void returnIds() {
        handleDuplicates = new HandleDuplicates();

        Map<String, Integer> mapIdsTest = new HashMap<>();
        mapIdsTest.put("1237", 2);
        mapIdsTest.put("1236", 2);
        mapIdsTest.put("1235", 2);
        mapIdsTest.put("1234", 2);

        LinkedList<String> duplicatesIds = new LinkedList<>();
        duplicatesIds.add("1237");
        duplicatesIds.add("1236");
        duplicatesIds.add("1235");
        duplicatesIds.add("1234");
        Assertions.assertEquals(duplicatesIds, handleDuplicates.returnIds(mapIdsTest));

    }
}