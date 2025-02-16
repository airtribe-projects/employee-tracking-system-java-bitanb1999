package com.example.demo;
import jakarta.persistence.*;
import java.util.*;
@Entity
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double budget;

    @OneToMany(mappedBy = "department")
    private Set<Employee> employees = new HashSet<>();

    @OneToMany(mappedBy = "department")
    private Set<Project> projects = new HashSet<>();

}
