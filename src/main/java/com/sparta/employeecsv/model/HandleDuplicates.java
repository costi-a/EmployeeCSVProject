package com.sparta.employeecsv.model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class HandleDuplicates {
    public Map<String, Integer> returnHashMapIds(LinkedList<String> ids) {
        // setting an hasmap so that we can count the the ids
        Map<String, Integer> mapIds = new HashMap<>();

        try {
            // if id doesn't exists we put into the hashmap the id as a key and the value 1
            // if id exists we just increase the value by 1
            for(String id: ids) {
                if(mapIds.containsKey(id)) {
                    mapIds.put(id, mapIds.get(id) + 1);
                } else {
                    mapIds.put(id, 1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapIds;
    }

    public int calculateSumDuplicates(Map<String, Integer> mapIds) {
        int sum = 0;

        try {
            // here there are all the values of the hashmap greater than 1 (how many duplicates id)
            LinkedList<Integer> duplicatesNumbers = new LinkedList<>();

            for(String id: mapIds.keySet()) {
                if(mapIds.get(id) > 1) {
                    duplicatesNumbers.add(mapIds.get(id));
                }
            }

            // here we are calculating how many duplicates ids we have by just adding the values
            for(int i = 0; i < duplicatesNumbers.size(); i++) {
                sum += duplicatesNumbers.get(i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sum;
    }

    public LinkedList<String> returnIds(Map<String, Integer> mapIds) {
        LinkedList<String> duplicatesIds = new LinkedList<>();

        try {
            // if value of key (id) is greater than one we add it to the LinkedList
            for(String id: mapIds.keySet()) {
                if(mapIds.get(id) > 1) {
                    duplicatesIds.add(id);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return duplicatesIds;
    }
}