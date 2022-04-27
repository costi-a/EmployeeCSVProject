package com.sparta.employeecsv.model;

import java.util.Map;

public class HandleUniqueValues {
    public int calculateUniqueIds(Map<String, Integer> mapIds) {
        // here we will count the ids with value of 1
        int counter = 0;

        try {
            // if key (id) as a value of 1 we increase the counter
            for(String id: mapIds.keySet()) {
                if(mapIds.get(id) == 1) {
                    counter++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return counter;
    }
}
