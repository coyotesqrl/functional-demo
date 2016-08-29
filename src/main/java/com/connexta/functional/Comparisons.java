package com.connexta.functional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.connexta.functional.beans.Department;
import com.connexta.functional.beans.Employee;

public class Comparisons {

    Comparator<Employee> employeeComp = new Comparator<Employee>() {
        @Override
        public int compare(Employee o1, Employee o2) {
            // switch for highest to lowest
            return Integer.compare(o2.getSalary(), o1.getSalary());
        }
    };

    public List<Employee> sortEmployeesHighesttoLowestSalary(List<Department> departments) {
        List<Employee> employees = new ArrayList<>();

        for (Department department : departments) {
            employees.addAll(department.getEmployees());
        }

        employees.sort(employeeComp);
        return employees;
    }

    /**
     * Sorts a comma-delimited set of releasability trigraphs/tetragraphs in the following manner:
     * <ol>
     * <li>If present, the trigraph USA will always be first</li>
     * <li>Next will come any three-letter country ISO codes in alphabetical order</li>
     * <li>Finally will come any four-letter alliances in alphabetical order</li>
     * </ol>
     * Any tokenized values in the input string that are shorter than three characters or longer
     * than four will be excluded.
     * <p>
     * This method does not attempt to verify the trigraphs/tetragraphs in any other way.
     *
     * @param input a comma-delimited set of tokens to sort
     * @return a re-sorted comma-delimted string
     */
    public String sortedSecurityReleasability(String input) {
        if (input == null) {
            return "";
        }

        List<String> trigraphs = new ArrayList<>();
        List<String> tetragraphs = new ArrayList<>();

        String[] split = input.split(",");
        for (String token : split) {
            token = token.trim();
            if (token.length() == 3) {
                trigraphs.add(token);
            }
            if (token.length() == 4) {
                tetragraphs.add(token);
            }
        }

        Collections.sort(trigraphs);
        Collections.sort(tetragraphs);

        if (trigraphs.contains("USA")) {
            trigraphs.remove("USA");
            trigraphs.add(0, "USA");
        }

        trigraphs.addAll(tetragraphs);
        return String.join(",", trigraphs);
    }
}
