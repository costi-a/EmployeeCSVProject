package com.sparta.employeecsv.model;

import java.util.Date;

public class Employee {
    private String employeeID;
    private String namePrefix;
    private String firstName;
    private Character middleInitial;
    private String lastName;
    private Character gender;
    private String emailAddress;
    private Date dateOfBirth;
    private Date dateOfJoining;
    private Float salary;

    public Employee (String employeeID, String namePrefix, String firstName,
                     Character middleInitial, String lastName, Character gender,
                     String emailAddress, Date dateOfBirth, Date dateOfJoining,
                     Float salary)  {

        this.employeeID     = employeeID;
        this.namePrefix     = namePrefix;
        this.firstName      = firstName;
        this.middleInitial  = middleInitial;
        this.lastName       = lastName;
        this.gender         = gender;
        this.emailAddress   = emailAddress;
        this.dateOfBirth    = dateOfBirth;
        this.dateOfJoining  = dateOfJoining;
        this.salary         = salary;
    }

    public Employee()   {
        super();
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public String getNamePrefix() {
        return namePrefix;
    }

    public void setNamePrefix(String namePrefix) {
        this.namePrefix = namePrefix;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Character getMiddleInitial() {
        return middleInitial;
    }

    public void setMiddleInitial(Character middleInitial) {
        this.middleInitial = middleInitial;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Character getGender() {
        return gender;
    }

    public void setGender(Character gender) {
        this.gender = gender;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public java.sql.Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public java.sql.Date getDateOfJoining() {
        return dateOfJoining;
    }

    public void setDateOfJoining(Date dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }

    public Float getSalary() {
        return salary;
    }

    public void setSalary(Float salary) {
        this.salary = salary;
    }

    public String toString()    {
        return " Employee ID [ " +
                ", ID: " + getEmployeeID() +
                ", Name Prefix: " + getNamePrefix() +
                ", First Name: " + getFirstName() +
                ", Last Name: " + getLastName() +
                ", Gender: " + getGender() +
                ", Email: " + getEmailAddress() +
                ", D.O.B: " + getDateOfBirth() +
                ", D.O.J: " + getDateOfJoining() +
                ", Salary: £" + getSalary() + " ]\n";

    }
}
