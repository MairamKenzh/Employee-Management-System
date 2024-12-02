package com.example.employeemanagementsystem;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class HelloController {

    @FXML
    private Button addButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Button updateSalariesButton;

    @FXML
    private Button calculateButton;

    @FXML
    private TextField nameField;

    @FXML
    private ComboBox<String> employeeTypeSelector;

    @FXML
    private TextField salaryField;

    @FXML
    private TextField hoursField;

    @FXML
    private TableView<Employee> employeeTableView;

    @FXML
    private TableColumn<Employee, String> nameColumn;

    @FXML
    private TableColumn<Employee, String> typeColumn;

    @FXML
    private TableColumn<Employee, Double> salaryColumn;

    private ObservableList<Employee> employeeList = FXCollections.observableArrayList();

    public void initialize() {
        // Инициализация таблицы
        nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFullName()));
        typeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPositionType()));
        salaryColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getBaseSalary()).asObject());
        employeeTableView.setItems(employeeList);

        // Заполнение выпадающего списка
        employeeTypeSelector.getItems().addAll("Full-Time", "Part-Time", "Contractor");
    }

    @FXML
    private void addEmployee(ActionEvent event) {
        String name = nameField.getText();
        String type = employeeTypeSelector.getValue();
        String salaryInput = salaryField.getText();
        String hoursInput = hoursField.getText();

        if (name.isEmpty() || type == null) {
            showAlert("Error", "Please fill in all required fields.");
            return;
        }

        try {
            Employee employee = null;

            switch (type) {
                case "Full-Time":
                    double fixedSalary = Double.parseDouble(salaryInput);
                    employee = new FullTimeEmployee(name, fixedSalary);
                    break;

                case "Part-Time":
                    double hourlyRate = Double.parseDouble(salaryInput);
                    int hoursWorked = Integer.parseInt(hoursInput);
                    employee = new PartTimeEmployee(name, hourlyRate, hoursWorked);
                    employee.setBaseSalary(employee.computeFinalSalary());
                    break;

                case "Contractor":
                    double contractorRate = Double.parseDouble(salaryInput);
                    int contractorHours = Integer.parseInt(hoursInput);
                    int maxHours = 160;
                    employee = new Contractor(name, contractorRate, maxHours, contractorHours);
                    employee.setBaseSalary(employee.computeFinalSalary());
                    break;
            }

            if (employee != null) {
                employeeList.add(employee);
                clearFields();
            }

        } catch (NumberFormatException e) {
            showAlert("Error", "Please enter valid numerical values for Salary/Rate and Hours Worked.");
        }
    }

    @FXML
    private void deleteSelectedEmployee(ActionEvent event) {
        Employee selectedEmployee = employeeTableView.getSelectionModel().getSelectedItem();
        if (selectedEmployee != null) {
            employeeList.remove(selectedEmployee);
        } else {
            showAlert("Error", "Please select an employee to delete.");
        }
    }

    @FXML
    private void updateSalaries(ActionEvent event) {
        for (Employee employee : employeeList) {
            double updatedSalary = employee.computeFinalSalary();
            employee.setBaseSalary(updatedSalary);
        }
        employeeTableView.refresh();
    }

    @FXML
    private void calculateTotalSalaries(ActionEvent event) {
        if (employeeList.isEmpty()) {
            showAlert("Info", "No employees to calculate salaries.");
            return;
        }

        double totalSalaries = 0;
        for (Employee employee : employeeList) {
            totalSalaries += employee.getBaseSalary();
        }

        showAlert("Total Salaries", "The total salaries of all employees: " + String.format("%.2f", totalSalaries));
    }

    private void clearFields() {
        nameField.clear();
        employeeTypeSelector.getSelectionModel().clearSelection();
        salaryField.clear();
        hoursField.clear();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}