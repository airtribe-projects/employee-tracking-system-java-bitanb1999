package com.example.demo;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    @Query("SELECT SUM(p.budget) FROM Project p WHERE p.department.id = :departmentId")
    Double calculateTotalBudgetByDepartmentId(@Param("departmentId") Long departmentId);
}


