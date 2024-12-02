package com.example.employeemanagementsystem;

public abstract class Employee {
    private String fullName;
    private String positionType;
    private double baseSalary;

    public Employee(String fullName, String positionType, double baseSalary) {
        this.fullName = fullName;
        this.positionType = positionType;
        this.baseSalary = baseSalary;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPositionType() {
        return positionType;
    }

    public void setPositionType(String positionType) {
        this.positionType = positionType;
    }

    public double getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(double baseSalary) {
        this.baseSalary = baseSalary;
    }

    public abstract double computeFinalSalary();
}