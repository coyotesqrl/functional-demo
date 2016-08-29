package com.connexta.functional;

import java.util.List;

import com.connexta.functional.beans.Department;
import com.connexta.functional.beans.Employee;
import com.connexta.functional.beans.Review;

public class SimpleReduction {
    public int getDepartmentSalaries(Department department) {
        int salary = 0;
        if (department != null && department.getEmployees() != null) {
            for (Employee employee : department.getEmployees()) {
                salary += employee.getSalary();
            }
        }

        return salary;
    }

    public int getMaxSalary(Department department) {
        int maxSalary = 0;
        if (department != null && department.getEmployees() != null) {
            for (Employee employee : department.getEmployees()) {
                maxSalary = Math.max(maxSalary, employee.getSalary());
            }
        }

        return maxSalary;
    }

    public int getNumEmployees(List<Department> departments) {
        int num = 0;
        for (Department department : departments) {
            num += department.getEmployees()
                    .size();
        }

        return num;
    }

    public int getEmployeeTotalRanking(Employee employee, int year) {
        List<Review> reviews = employee.getDepartment()
                .getEmployeeReviews(employee);
        Review yearReview = null;
        for (Review review : reviews) {
            if (review.getReviewYear() == year) {
                yearReview = review;
                break;
            }
        }

        int ranking = 0;
        if (yearReview != null && yearReview.getQuestionRankings() != null) {
            for (Integer qRank : yearReview.getQuestionRankings()
                    .values()) {
                ranking += qRank;
            }
        }

        return ranking;
    }
}
