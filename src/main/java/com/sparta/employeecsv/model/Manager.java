package com.sparta.employeecsv.model;

import com.sparta.employeecsv.display.DisplayInfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Map;

public class Manager {
    public void manageProgram() throws IOException {
        try {
            // reading the file
            EmployeeFileReader rf = new EmployeeFileReader();
            BufferedReader br = rf.readFile("EmployeeRecords.csv");

            // saving employees into list
            SaveEmployees saveEmployees = new SaveEmployees();
            LinkedList<Employee> employeesList = saveEmployees.saveEmployees(br);

            // storing ids that we will use with the HandleDuplicates
            StoringIds sIds = new StoringIds();
            LinkedList<String> ids = sIds.storeIds(employeesList);

            HandleDuplicates hd = new HandleDuplicates();
            // we first return an hashmap so that we have something like this:
            // {a: 1, b: 3, c: 5} (a: 1 means there is only 1 a, b: 3 means there are 3 b)
            // so that we can check duplicates and unique values
            Map<String, Integer> mapIds = hd.returnHashMapIds(ids);

            // counting duplicates
            int duplicateIds = hd.calculateSumDuplicates(mapIds);

            // calculating unique values
            HandleUniqueValues uniqueValues = new HandleUniqueValues();
            int uniqueIds = uniqueValues.calculateUniqueIds(mapIds);

            // displaying the info
            DisplayInfo dInfo = new DisplayInfo();
            dInfo.printResults(duplicateIds, uniqueIds, employeesList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}