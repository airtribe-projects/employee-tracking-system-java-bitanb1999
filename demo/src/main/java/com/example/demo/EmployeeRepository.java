package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByNameContaining(String name);
    List<Employee> findByDepartmentId(Long departmentId);
    List<Employee> findByProjectsIsEmpty();
    List<Employee> findByProjectsId(Long projectId);
    Employee findByEmail(String email);
}


