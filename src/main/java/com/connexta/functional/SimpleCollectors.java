package com.connexta.functional;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.connexta.functional.beans.Department;
import com.connexta.functional.beans.Employee;

public class SimpleCollectors {
    public Map<Employee, Serializable> getEmployeeAttribute(Department department,
            String attrName) {
        HashMap<Employee, Serializable> map = new HashMap<>();
        if (department != null && department.getEmployees() != null) {
            for (Employee employee : department.getEmployees()) {
                if (employee.getAttribute(attrName) != null) {
                    map.put(employee, employee.getAttribute(attrName));
                }
            }
        }

        return map;
    }

    public Map<Employee, List<Serializable>> getEmployeeAttributes(Department department,
            List<String> attrNames) {
        HashMap<Employee, List<Serializable>> map = new HashMap<>();
        if (department != null && department.getEmployees() != null) {
            for (Employee employee : department.getEmployees()) {
                for (String attrName : attrNames) {
                    if (employee.getAttribute(attrName) != null) {
                        List<Serializable> serializables = map.get(employee);
                        if (serializables == null) {
                            serializables = new ArrayList<>();
                            map.put(employee, serializables);
                        }
                        serializables.add(employee.getAttribute(attrName));
                    }
                }
            }
        }

        return map;
    }
}
