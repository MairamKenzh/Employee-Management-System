package com.example.employeemanagementsystem;

public class Contractor extends Employee {
    private double hourlyRate;
    private int maxAllowedHours;
    private int hoursLogged;

    public Contractor(String fullName, double hourlyRate, int maxAllowedHours, int hoursLogged) {
        super(fullName, "Contractor", 0.0);
        this.hourlyRate = hourlyRate;
        this.maxAllowedHours = maxAllowedHours;
        this.hoursLogged = hoursLogged;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public int getMaxAllowedHours() {
        return maxAllowedHours;
    }

    public void setMaxAllowedHours(int maxAllowedHours) {
        this.maxAllowedHours = maxAllowedHours;
    }

    public int getHoursLogged() {
        return hoursLogged;
    }

    public void setHoursLogged(int hoursLogged) {
        this.hoursLogged = hoursLogged;
    }

    @Override
    public double computeFinalSalary() {
        int effectiveHours = Math.min(hoursLogged, maxAllowedHours);
        return hourlyRate * effectiveHours;
    }
}