package controller;


import com.sparta.employeecsv.model.Employee;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class EmployeeValidator {
    private Employee employee;

    public Employee parseEmployee(String employeeID, String namePrefix, String firstName,
                                  String middleInitial, String lastName, String gender,
                                  String emailAddress, String dateOfBirth, String dateOfJoining,
                                  String salary) throws ParseException {
        try {


            employee = new Employee(parseEmployeeID(employeeID), parseNamePrefix(namePrefix), parseName(firstName),
                    parseMidInitial(middleInitial), parseName(lastName), parseGender(gender), parseEmail(emailAddress),
                    parseDate(dateOfBirth), parseDate(dateOfJoining), parseSalary(salary)
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return employee;
    }

    // id.length needs to be between 4 and 9
    private String parseEmployeeID(String employeeID) {
        try {
            if (employeeID.matches("\\d{4,9}")) {
                return employeeID;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    private String parseNamePrefix(String namePrefix) {
        try {
            boolean rightPrefix = namePrefix.equals("Mrs.") || namePrefix.equals("Mr.") ||
                    namePrefix.equals("Ms.") || namePrefix.equals("Dr.") || namePrefix.equals("Hon.") ||
                    namePrefix.equals("Drs.") || namePrefix.equals("Prof.");
            if (rightPrefix) {
                return namePrefix;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    private String parseName(String name) {
        try {
            if (name.length() < 1 || name == null ) {
                return null;
            } else {
                return name;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Character parseMidInitial(String middleInitial) {
        try {
            if (middleInitial.length() != 1 || middleInitial == null) {
                return null;
            } else {
                return middleInitial.charAt(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Character parseGender(String gender) {
        try {
            if (gender.matches("M|F"))  {
                return gender.charAt(0);
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    private String parseEmail(String emailAddress) {
        try {
            String regex = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\." +
                    "[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
            if (emailAddress.matches(regex)) {
                return emailAddress;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    private Date parseDate(String date) {
        java.util.Date newDate = null;
        Date sqlDate = null;

        try {
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
            newDate = df.parse(dateString);
            sqlDate = new Date(newDate.getTime());
        } catch (ParseException e)  {
            e.printStackTrace();
        }
        return sqlDate;
    }

    // doesn't accept salary that starts with 0
    private Float parseSalary(String salary) {
        try {
            if ((salary).matches("(?!0+(?:\\\\.0+)?$)[0-9]+(?:\\\\.[0-9]+)?"))   {
                return Float.valueOf(salary);
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
