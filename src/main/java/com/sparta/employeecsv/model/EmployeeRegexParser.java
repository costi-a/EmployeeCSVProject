package com.sparta.employeecsv.model;

import java.sql.Date;

public class EmployeeRegexParser {

    public Employee parseEmployee(String employeeID, String namePrefix, String firstName,
                                  String middleInitial, String lastName, String gender,
                                  String emailAddress, String dateOfBirth, String dateOfJoining,
                                  String salary) {

        Employee employee;

        employee = new Employee(parseEmployeeID(employeeID), parseNamePrefix(namePrefix), parseName(firstName),
                parseMidInitial(middleInitial), parseName(lastName), parseGender(gender), parseEmail(emailAddress),
                parseDate(dateOfBirth), parseDate(dateOfJoining), parseSalary(salary)
                );

        return employee;
    }
    private Integer parseEmployeeID(String employeeID) {
        if ("\\d{1,9}".matches(employeeID)) {
            return Integer.valueOf(employeeID);
        }
        return null;
    }
    private String parseNamePrefix(String namePrefix) {
        if ("A-Za-z{2,6}".matches(namePrefix)) {
            return namePrefix;
        }
        return null;
    }
    private String parseName(String name) {
        if (name.length() < 1 || name == null )   {
            return null;
        }
        return name;

    }
    private Character parseMidInitial(String middleInitial) {
        if (middleInitial.length() != 1 || middleInitial == null)   {
            return null;
        }
        return middleInitial.charAt(0);
    }
    private Character parseGender(String gender) {
        if ("M|F".matches(gender))  {
            return gender.charAt(0);
        }
        return null;
    }
    private String parseEmail(String emailAddress) {
        if (("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\\\.[A-Z]{2,6}$").matches(emailAddress)) {
            return emailAddress;
        }
        return null;
    }
    private Date parseDate(String date) {
        if (date == null)   {
            return null;
        }

        String[] dateSplit = date.split("/");

        if (dateSplit.length != 3)  {
            return null;
        }

        StringBuilder newDate = new StringBuilder();

        newDate.append(dateSplit[1]).append("-")
                .append(dateSplit[0]).append("-")
                .append(dateSplit[2]);

        return Date.valueOf(newDate.toString());

    }

    private Float parseSalary(String salary) {
        if (("\\d{1,9}").matches(salary))   {
            return Float.valueOf(salary);
        }
        return null;
    }


}
