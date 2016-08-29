package com.connexta.functional

import com.connexta.functional.beans.Department
import com.connexta.functional.beans.Employee
import com.connexta.functional.beans.Review

class TestUtils {
    static List<Department> createDepartments(IntRange range) {
        range.collect {
            def dept = new Department()
            dept.name = "Dep-$it"
            createEmployees(dept, it)
            createReviews(dept)
            dept.manager = dept.employees.first()
            dept
        }
    }

    static void createEmployees(Department department, int idx) {
        def employees = (120 + idx..2400 + idx).step(120).collect {
            def emp = new Employee()
            emp.salary = it
            emp.name = "John $it From $department.name"
            emp.department = department
            emp
        }
        department.setEmployees(employees)
    }

    static void createReviews(Department department) {
        department.employees.eachWithIndex { emp, idx ->
            def years
            if (emp.salary % 8 == 0) {
                years = (2010..2016)
            } else if (emp.salary % 9 == 0) {
                years = (2012..2016)
            } else {
                years = (2015..2016)
            }

            years.each { year ->
                def rev = new Review()
                rev.reviewYear = year
                rev.answerQuestion(1, year + idx, 'good boy')
                rev.answerQuestion(2, idx + 200)
                rev.answerQuestion(3, 3)
                department.addEmployeeReview(emp, rev)
            }
        }
    }

}
