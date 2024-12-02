package com.example.employeemanagementsystem;

public class FullTimeEmployee extends Employee {

    public FullTimeEmployee(String fullName, double fixedSalary) {
        super(fullName, "Full-Time", fixedSalary);
    }

    @Override
    public double computeFinalSalary() {
        return getBaseSalary();
    }
}