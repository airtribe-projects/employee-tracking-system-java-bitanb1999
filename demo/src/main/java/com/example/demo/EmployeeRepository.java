package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByNameContaining(String name);
    List<Employee> findByDepartmentId(Long departmentId);
    List<Employee> findByProjectsIsEmpty();
    List<Employee> findByProjectsId(Long projectId);
    Employee findByName(String name);
    Optional<Employee> findByUsername(String username);
}


