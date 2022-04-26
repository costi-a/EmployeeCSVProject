package com.sparta.employeecsv.model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class HandleDuplicates {
    public Map<String, Integer> returnHashMapDuplicates(String[] ids) {
        // setting an hasmap so that we can count the the ids
        Map<String, Integer> charMap = new HashMap<>();
        // if id doesn't exists we put into the hashmap the id as a key and the value 1
        // if id exists we just increase the value by 1
        for(String id: ids) {
            if(charMap.containsKey(id)) {
                charMap.put(id, charMap.get(id) + 1);
            } else {
                charMap.put(id, 1);
            }
        }
        System.out.println(charMap);
        return charMap;
    }

    public int calculateSumDuplicates(Map<String, Integer> charMap) {
        // here there are all the values of the hashmap greater than 1 (how many duplicates id)
        LinkedList<Integer> duplicatesNumbers = new LinkedList<>();

        for(String id: charMap.keySet()) {
            if(charMap.get(id) > 1) {
                duplicatesNumbers.add(charMap.get(id));
            }
        }

        // here we are calculating how many duplicates ids we have by just adding the values
        int sum = 0;
        for(int i = 0; i < duplicatesNumbers.size(); i++) {
            sum += duplicatesNumbers.get(i);
        }
        return sum;
    }

    public LinkedList<String> returnIds(Map<String, Integer> charMap) {
        LinkedList<String> duplicatesIds = new LinkedList<>();

        // if value of key (id) is greater than one we add it to the LinkedList
        for(String id: charMap.keySet()) {
            if(charMap.get(id) > 1) {
                duplicatesIds.add(id);
            }
        }
        return duplicatesIds;
    }
}