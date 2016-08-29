package com.connexta.functional;

import static com.connexta.functional.beans.AttributeType.STR_TYPE;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.connexta.functional.beans.Attribute;
import com.connexta.functional.beans.Employee;

public class BoilerplateRemoval {
    public void setEmployeeDates(Employee employee, Map<String, Date> dates) {
        for (Map.Entry<String, Date> row : dates.entrySet()) {
            if (row.getKey()
                    .equals("HIRE")) {
                if (employee.getHireDate() != null) {
                    System.out.printf("Existing value present for %s date: %s%n",
                            "HIRE",
                            employee.getHireDate()
                                    .toString());
                }
                employee.setHireDate(row.getValue());
            }
            if (row.getKey()
                    .equals("START")) {
                if (employee.getStartDate() != null) {
                    System.out.printf("Existing value present for %s date: %s%n",
                            "START",
                            employee.getStartDate()
                                    .toString());
                }
                employee.setStartDate(row.getValue());
            }
            if (row.getKey()
                    .equals("REVIEW")) {
                if (employee.getLastReviewDate() != null) {
                    System.out.printf("Existing value present for %s date: %s%n",
                            "REVIEW",
                            employee.getLastReviewDate()
                                    .toString());
                }
                employee.setLastReviewDate(row.getValue());
            }
        }
    }

    public void addManagerNameToAttributes(Employee employee) {
        String name = employee.getManager()
                .getName();
        Attribute mgrNameAttr = new Attribute(STR_TYPE, false, "MGR_NAME");
        mgrNameAttr.setValue(name);
        employee.addAttribute(mgrNameAttr);
    }

    public void addSubordNamesToAttributes(Employee employee) {
        // Make sure this employee is a manager
        if (employee.getDepartment()
                .getManager()
                .equals(employee)) {
            List<Employee> employees = employee.getDepartment()
                    .getEmployees();
            ArrayList<String> subOrdNames = new ArrayList<>();
            for (Employee subOrd : employees) {
                if (subOrd.equals(employee)) {
                    continue;
                }
                subOrdNames.add(subOrd.getName());
            }
            Attribute subOrdNameAttr = new Attribute(STR_TYPE, true, "SUBORD_NAME");
            subOrdNameAttr.setValue(subOrdNames);
            employee.addAttribute(subOrdNameAttr);
        }
    }

    public void addDepartmentNameToAttributes(Employee employee) {
        String name = employee.getDepartment()
                .getName();
        Attribute deptName = new Attribute(STR_TYPE, false, "DEPT_NAME");
        deptName.setValue(name);
        employee.addAttribute(deptName);
    }
}
