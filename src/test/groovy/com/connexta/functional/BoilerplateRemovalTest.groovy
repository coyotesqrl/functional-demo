package com.connexta.functional

import com.connexta.functional.beans.Department
import com.connexta.functional.beans.Employee
import spock.lang.Specification

class BoilerplateRemovalTest extends Specification {
    private BoilerplateRemoval boilerplateRemoval

    def setup() {
        boilerplateRemoval = new BoilerplateRemoval()
    }

    def 'add a collection of dates to an employee'() {
        setup:
        def emp = new Employee()
        def datesMap = [:]
        if (hire) {
            datesMap.HIRE = hire
        }
        if (start) {
            datesMap.START = start
        }
        if (review) {
            datesMap.REVIEW = review
        }

        when:
        boilerplateRemoval.setEmployeeDates(emp, datesMap)

        then:
        emp.hireDate == hire
        emp.startDate == start
        emp.lastReviewDate == review

        where:
        hire             | start            | review
        new Date() - 10  | new Date() - 5   | new Date()
        new Date() - 20  | new Date() - 1   | null
        new Date() - 100 | new Date() - 100 | null
    }

    def 'adding manager name to attributes'() {
        setup:
        def mgr = new Employee()
        mgr.name = 'test manager'
        def emp = new Employee()
        emp.manager = mgr

        when:
        boilerplateRemoval.addManagerNameToAttributes(emp)

        then:
        emp.getAttribute('MGR_NAME') == mgr.name
    }

    def 'adding subordinate names to manager attributes'() {
        setup:
        def dept = TestUtils.createDepartments((1..1)).first()
        dept.employees
        def empNames = dept.employees.tail().collect { it -> it.name }

        when:
        boilerplateRemoval.addSubordNamesToAttributes(dept.employees.first())

        then:
        dept.employees.first().getAttribute('SUBORD_NAME') == empNames
    }

    def 'adding department name to attributes'() {
        setup:
        def dept = new Department()
        dept.name = 'test department'
        def emp = new Employee()
        emp.department = dept

        when:
        boilerplateRemoval.addDepartmentNameToAttributes(emp)

        then:
        emp.getAttribute('DEPT_NAME') == dept.name
    }
}
