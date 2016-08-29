package com.connexta.functional

import com.connexta.functional.beans.Department
import spock.lang.Specification

class SimpleReductionTest extends Specification {
    private List<Department> departments
    private SimpleReduction simpleReduction
    def employeeTotalRanking

    def setup() {
        simpleReduction = new SimpleReduction()
        departments = TestUtils.createDepartments((1..5))
        employeeTotalRanking = { dept, emp, year ->
            dept.getEmployeeReviews(emp).find { r ->
                r.reviewYear == year
            }*.questionRankings*.values()?.flatten()?.sum() ?: 0
        }
    }

    def 'test department salary summing'() {
        expect:
        departments.each {
            assert simpleReduction.getDepartmentSalaries(it) == it.employees*.salary.sum() as int
        }
    }

    def 'test max salary'() {
        expect:
        departments.each {
            assert simpleReduction.getMaxSalary(it) == it.employees*.salary.max() as int
        }
    }

    def 'count all employees'() {
        expect:
        simpleReduction.getNumEmployees(departments) == departments*.employees.flatten().size()
    }

    def 'get employee total ranking for a year'() {
        expect:
        departments.each { dept ->
            dept.employees.each { emp ->
                (2010..2016).each { year ->
                    assert employeeTotalRanking(dept, emp, year) ==
                            simpleReduction.getEmployeeTotalRanking(emp, year)
                }
            }
        }
    }
}
