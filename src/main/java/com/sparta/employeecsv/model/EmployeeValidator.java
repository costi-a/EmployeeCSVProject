package com.sparta.employeecsv.model;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class EmployeeValidator {
    public Employee parseEmployee(String employeeID, String namePrefix, String firstName,
                                  String middleInitial, String lastName, String gender,
                                  String emailAddress, String dateOfBirth, String dateOfJoining,
                                  String salary) throws ParseException {
        Employee employee;

        employee = new Employee(parseEmployeeID(employeeID), parseNamePrefix(namePrefix), parseName(firstName),
                parseMidInitial(middleInitial), parseName(lastName), parseGender(gender), parseEmail(emailAddress),
                parseDate(dateOfBirth), parseDate(dateOfJoining), parseSalary(salary)
                );
        return employee;
    }
    private Integer parseEmployeeID(String employeeID) {
        if (employeeID.matches("\\d{1,9}")) {
            return Integer.valueOf(employeeID);
        }
        return null;
    }
    private String parseNamePrefix(String namePrefix) {
        boolean rightPrefix = namePrefix.equals("Mrs.") || namePrefix.equals("Mr.") ||
                namePrefix.equals("Ms.") || namePrefix.equals("Dr.") || namePrefix.equals("Hon.") ||
                namePrefix.equals("Drs.");
        if (rightPrefix) {
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
        if (gender.matches("M|F"))  {
            return gender.charAt(0);
        }
        return null;
    }
    private String parseEmail(String emailAddress) {
        String regex = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\." +
                "[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        if (emailAddress.matches(regex)) {
            return emailAddress;
        }
        return null;
    }
    private Date parseDate(String date) throws ParseException {
        if (date == null) {
            return null;
        }

        String[] dateSplit = date.split("/");

        if (dateSplit.length != 3) {
            return null;
        }

        StringBuilder sb = new StringBuilder();

        sb.append(dateSplit[0]).append("/").append(dateSplit[1]).append("/").append(dateSplit[2]);

        String dateString = sb.toString();


        SimpleDateFormat df = new SimpleDateFormat("dd/mm/yyyy");
        Date newDate = null;
        try {
            newDate = df.parse(dateString);
        } catch (ParseException e)  {
            e.printStackTrace();
        }


        java.sql.Date sqlDate = new java.sql.Date(newDate.getTime());

        return sqlDate;
    }

    private Float parseSalary(String salary) {
        if ((salary).matches("(?!0+(?:\\\\.0+)?$)[0-9]+(?:\\\\.[0-9]+)?"))   {
            return Float.valueOf(salary);
        }
        return null;
    }
}
