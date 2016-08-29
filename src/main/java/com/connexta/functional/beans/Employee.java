package com.connexta.functional.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class Employee {
    private String name;

    private String email;

    private Employee manager;

    private Department department;

    private int salary;

    private Date hireDate;

    private Date startDate;

    private Date lastReviewDate;

    private List<Attribute> attributes = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getLastReviewDate() {
        return lastReviewDate;
    }

    public void setLastReviewDate(Date lastReviewDate) {
        this.lastReviewDate = lastReviewDate;
    }

    public List<String> getAttributeNames() {
        return attributes.stream()
                .map(Attribute::getName)
                .collect(Collectors.toList());
    }

    public void addAttribute(Attribute attribute) {
        this.attributes.add(attribute);
    }

    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }

    public Serializable getAttribute(String attrName) {
        return attributes.stream()
                .filter(a -> a.getName()
                        .equals(attrName))
                .findFirst()
                .map(Attribute::getValue)
                .orElse(null);
    }
}
