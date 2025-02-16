package com.example.demo;
import jakarta.persistence.*;
import java.util.*;
@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double budget;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @ManyToMany
    @JoinTable(
            name = "employee_project",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id")
    )
    private Set<Employee> employees = new HashSet<>();
}