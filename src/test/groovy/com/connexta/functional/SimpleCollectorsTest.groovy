package com.connexta.functional

import com.connexta.functional.beans.Attribute
import com.connexta.functional.beans.Department
import spock.lang.Specification

import static com.connexta.functional.beans.AttributeType.INT_TYPE
import static com.connexta.functional.beans.AttributeType.STR_TYPE

class SimpleCollectorsTest extends Specification {
    private Department dept
    private BoilerplateRemoval boilerplateRemoval
    private SimpleCollectors simpleCollectors

    def setup() {
        dept = TestUtils.createDepartments((1..1)).first()
        boilerplateRemoval = new BoilerplateRemoval()
        simpleCollectors = new SimpleCollectors()
    }

    def 'get single attribute for all employees'() {
        setup:
        dept.employees.each { emp ->
            def attr = new Attribute(STR_TYPE, false, 'test_attr')
            attr.value = emp.name
            emp.addAttribute(attr)
        }

        when:
        def attributes = simpleCollectors.getEmployeeAttribute(dept, 'test_attr')

        then:
        attributes.size() == dept.employees.size()
        attributes.each { k, v ->
            assert k.name == v
            assert k.getAttribute('test_attr') == v
        }
    }

    def 'get multi attribute for all employees'() {
        setup:
        dept.employees.each { emp ->
            def attr = new Attribute(INT_TYPE, true, 'test_attr')
            attr.value = [emp.salary, 1, 2, 3]
            emp.addAttribute(attr)
        }

        when:
        def attributes = simpleCollectors.getEmployeeAttribute(dept, 'test_attr')

        then:
        attributes.size() == dept.employees.size()
        attributes.each { k, v ->
            assert v.size() == 4
            assert v.first() == k.salary
            assert v.tail() == [1, 2, 3]
        }
    }

    def 'get list of attributes for all employees'() {
        setup:
        dept.employees.each { emp ->
            def attr = new Attribute(INT_TYPE, true, 'test_attr')
            attr.value = [emp.salary, 1, 2, 3]
            emp.addAttribute(attr)
            attr = new Attribute(STR_TYPE, false, 'test_attr2')
            attr.value = emp.name
            emp.addAttribute(attr)
        }

        when:
        def attributes = simpleCollectors.getEmployeeAttributes(dept, ['test_attr', 'test_attr2'])

        then:
        attributes.size() == dept.employees.size()
        attributes.each { k, v ->
            assert v.size() == 2
            if (v.first() instanceof String) {
                assert v.last() instanceof ArrayList
                assert v.first() == k.name
                assert v.last().first() == k.salary
            } else {
                assert v.first() instanceof ArrayList
                assert v.last() instanceof String
                assert v.first().first() == k.salary
                assert v.last() == k.name
            }
        }
    }
}
