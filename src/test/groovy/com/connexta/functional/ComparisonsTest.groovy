package com.connexta.functional

import com.connexta.functional.beans.Department
import com.connexta.functional.beans.Employee
import com.connexta.functional.beans.Review
import spock.lang.Specification

class ComparisonsTest extends Specification {
    private List<Department> departments
    private Comparisons comparisons

    def setup() {
        comparisons = new Comparisons()
        departments = TestUtils.createDepartments((1..5))
    }

    def 'test employee sort by salary'() {
        setup:
        def currentSalary = 2405

        when:
        def emps = comparisons.sortEmployeesHighesttoLowestSalary(departments)

        then:
        emps.first().salary == 2405
        emps.last().salary == 121
        emps.each {
            assert it.salary <= currentSalary
            currentSalary = it.salary
        }
    }

    def 'test releasability sorting'() {
        expect:
        output == comparisons.sortedSecurityReleasability(input)

        where:
        input                             | output
        'NZL,GBR,HELLO,FVEY,ACGU,AUS,CAN' | 'AUS,CAN,GBR,NZL,ACGU,FVEY'
        'USA, GMIF,ECTF ,CAN,FRA,DEU'     | 'USA,CAN,DEU,FRA,ECTF,GMIF'
        'NATO,FVEY,CAN,USA,BEL,GBR,UNCK'  | 'USA,BEL,CAN,GBR,FVEY,NATO,UNCK'
        'AB,HELLO,WORLD,MCFI,CAN,USA'     | 'USA,CAN,MCFI'
        'AB,HELLO'                        | ''
        'FVEY,ACGU,GMIF'                  | 'ACGU,FVEY,GMIF'
        'USA,CAN,BEL'                     | 'USA,BEL,CAN'
        'CAN,GBR,USA'                     | 'USA,CAN,GBR'
        ''                                | ''
        null                              | ''
    }
}
