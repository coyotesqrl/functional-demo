package com.connexta.functional.beans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Department {
    private String name;

    private Employee manager;

    private List<Employee> employees = new ArrayList<>();

    private Map<Employee, List<Review>> employeeReviews = new HashMap<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Review> getEmployeeReviews(Employee employee) {
        return employeeReviews.get(employee);
    }

    public void addEmployeeReview(Employee employee, Review review) {
        List<Review> reviews = employeeReviews.get(employee);
        if (reviews == null) {
            reviews = new ArrayList<>();
            employeeReviews.put(employee, reviews);
        }
        reviews.add(review);
    }
}
