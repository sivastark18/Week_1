package com.example;

import com.example.dao.EmployeeDAO;
import com.example.model.Employee;

public class Main {
    public static void main(String[] args) {
        EmployeeDAO dao = new EmployeeDAO();

        Employee emp = new Employee();
        emp.setName("John Doe");
        emp.setDepartment("Human Resources");

        Integer id = dao.addEmployee(emp);

        System.out.println("Employee inserted with ID: " + id);
    }
}
